package org.dieschnittstelle.jee.esa.ejb.client.shopping;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.CampaignTrackingRemote;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.CustomerTrackingRemote;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingCartRemote;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.ShoppingCartItem;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.crm.CustomerTransaction;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.CampaignTrackingClient;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.CustomerTrackingClient;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.ShoppingCartClient;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;
import org.dieschnittstelle.jee.esa.entities.erp.Campaign;

public class ShoppingSession implements ShoppingBusinessDelegate {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ShoppingSession.class);

	/*
	 * the three beans that are used
	 */
	private ShoppingCartRemote shoppingCart;

	private CustomerTrackingRemote customerTracking;

	private CampaignTrackingRemote campaignTracking;

	/**
	 * the customer
	 */
	private Customer customer;

	/**
	 * the touchpoint
	 */
	private AbstractTouchpoint touchpoint;

	public ShoppingSession() {
		logger.info("<constructor>");
		try {
			this.campaignTracking = new CampaignTrackingClient();
			this.customerTracking = new CustomerTrackingClient();
			this.shoppingCart = new ShoppingCartClient();
		} catch (Exception e) {
			throw new RuntimeException("initialise() failed: " + e, e);
		}
	}

	public void setTouchpoint(AbstractTouchpoint touchpoint) {
		this.touchpoint = touchpoint;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addProduct(AbstractProduct product, int units) {
		this.shoppingCart.addItem(new ShoppingCartItem(product.getId(), units, product instanceof Campaign));
	}

	/*
	 * verify whether campaigns are still valid
	 */
	public void verifyCampaigns() throws ShoppingException {
		if (this.customer == null || this.touchpoint == null) {
			throw new RuntimeException("cannot verify campaigns! No touchpoint has been set!");
		}

		for (ShoppingCartItem item : this.shoppingCart.getItems()) {
			if (item.isCampaign()) {
				int availableCampaigns = this.campaignTracking.existsValidCampaignExecutionAtTouchpoint(
						item.getErpProductId(), this.touchpoint);
				logger.info("got available campaigns for product " + item.getErpProductId() + ": "
						+ availableCampaigns);
				// we check whether we have sufficient campaign items available
				if (availableCampaigns < item.getUnits()) {
					throw new ShoppingException("verifyCampaigns() failed for productBundle " + item
							+ " at touchpoint " + this.touchpoint + "! Need " + item.getUnits()
							+ " instances of campaign, but only got: " + availableCampaigns);
				}
			}
		}
	}

	public void purchase()  throws ShoppingException {
		logger.info("purchase()");

		if (this.customer == null || this.touchpoint == null) {
			throw new RuntimeException(
					"cannot commit shopping session! Either customer or touchpoint has not been set: " + this.customer
							+ "/" + this.touchpoint);
		}

		// verify the campaigns
		verifyCampaigns();

		// remove the products from stock
		checkAndRemoveProductsFromStock();

		// then we add a new customer transaction for the current purchase
		List<ShoppingCartItem> products = this.shoppingCart.getItems();
		CustomerTransaction transaction = new CustomerTransaction(this.customer, this.touchpoint, products);
		transaction.setCompleted(true);
		customerTracking.createTransaction(transaction);

		logger.info("purchase(): done.\n");
	}

	/*
	 * to be implemented as server-side method for PAT2
	 */
	private void checkAndRemoveProductsFromStock() {
		logger.info("checkAndRemoveProductsFromStock");

		for (ShoppingCartItem item : this.shoppingCart.getItems()) {

			// TODO: ermitteln Sie das AbstractProduct für das gegebene ShoppingCartItem. Nutzen Sie dafür dessen erpProductId und die ProductCRUD EJB

			if (item.isCampaign()) {
				this.campaignTracking.purchaseCampaignAtTouchpoint(item.getErpProductId(), this.touchpoint,
						item.getUnits());

				// TODO: wenn Sie eine Kampagne haben, muessen Sie hier
				// 1) ueber die ProductBundle Objekte auf dem Campaign Objekt iterieren, und
				// 2) fuer jedes ProductBundle das betreffende Produkt in der auf dem Bundle angegebenen Anzahl, multipliziert mit dem Wert von
				// item.getUnits() aus dem Warenkorb,
				// - hinsichtlich Verfuegbarkeit ueberpruefen, und
				// - falls verfuegbar, aus dem Warenlager entfernen - nutzen Sie dafür die StockSystem EJB
				// (Anm.: item.getUnits() gibt Ihnen Auskunft darüber, wie oft ein Produkt, im vorliegenden Fall eine Kampagne, im
				// Warenkorb liegt)
			} else {
				// TODO: andernfalls (wenn keine Kampagne vorliegt) muessen Sie
				// 1) das Produkt in der in item.getUnits() angegebenen Anzahl hinsichtlich Verfuegbarkeit ueberpruefen und
				// 2) das Produkt, falls verfuegbar, in der entsprechenden Anzahl aus dem Warenlager entfernen
			}

		}
	}

}
