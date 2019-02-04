package org.dieschnittstelle.jee.esa.ejb.client.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote;
import org.dieschnittstelle.jee.esa.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.jee.esa.entities.crm.Customer;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

public class ShoppingSessionFacadeClient implements ShoppingBusinessDelegate {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ShoppingSessionFacadeClient.class);


	private ShoppingSessionFacadeRemote serviceProxy;

	public ShoppingSessionFacadeClient() {
		serviceProxy = EJBProxyFactory.getInstance().getProxy(ShoppingSessionFacadeRemote.class, "ejb:org.dieschnittstelle.jee.esa.ejb/org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm/ShoppingSessionFacadeStateful!org.dieschnittstelle.jee.esa.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote?stateful");
	}


	@Override
	public void setTouchpoint(AbstractTouchpoint touchpoint) {
		serviceProxy.setTouchpoint(touchpoint);
	}

	@Override
	public void setCustomer(Customer customer) {
		serviceProxy.setCustomer(customer);
	}

	@Override
	public void addProduct(AbstractProduct product, int units) {
		serviceProxy.addProduct(product, units);
	}

	@Override
	public void purchase() throws ShoppingException {
		serviceProxy.purchase();
	}

}