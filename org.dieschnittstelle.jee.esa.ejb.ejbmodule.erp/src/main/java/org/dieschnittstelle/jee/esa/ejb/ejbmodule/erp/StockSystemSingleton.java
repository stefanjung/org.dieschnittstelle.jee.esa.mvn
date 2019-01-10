package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp;

import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.PointOfSale;
import org.dieschnittstelle.jee.esa.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
@Remote(StockSystemRemote.class)
@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", serviceName = "StockSystemWebService", endpointInterface = "org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.StockSystemRemote")
public class StockSystemSingleton implements StockSystemRemote {

    @EJB
    PointOfSaleCRUDLocal posCRUD;

    @EJB
    StockItemCRUDLocal siCRUD;


    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem si = siCRUD.readStockItem(product, pos);

        if (si == null) {
            si = new StockItem(product, pos, units);
            siCRUD.createStockItem(si);
        } else {
            si.setUnits(si.getUnits() + units);
            siCRUD.updateStockItem(si);
        }
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        if (units > -1) {
            PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
            StockItem so = siCRUD.readStockItem(product, pos);
            so.setUnits(so.getUnits() - units);
            siCRUD.updateStockItem(so);
        }

    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        List<IndividualisedProductItem> products = new ArrayList<>();
        for (StockItem si : siCRUD.readStockItemsForPointOfSale(pos)) {
            products.add(si.getProduct());
        }
        return products;
    }


    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List<PointOfSale> pointOfSales = posCRUD.readAllPointsOfSale();
        List<IndividualisedProductItem> products = new ArrayList<>();
        List<StockItem> stockItems = new ArrayList<>();
        for (PointOfSale pos : pointOfSales) {
            stockItems.addAll(siCRUD.readStockItemsForPointOfSale(pos));
        }
        for (StockItem si : stockItems) {
            if (!products.contains(si.getProduct())) {
                products.add(si.getProduct());
            }
        }

        return products;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem so = siCRUD.readStockItem(product, pos);
        if (so == null) {
            return 0;
        }
        return so.getUnits();
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List<PointOfSale> posS = posCRUD.readAllPointsOfSale();
        List<StockItem> stockItems = new ArrayList<>();
        int units = 0;
        for (PointOfSale pos : posS) {
            stockItems.addAll(siCRUD.readStockItemsForPointOfSale(pos));
        }
        for (StockItem si : stockItems) {
            //check if units are equal
            if (si.getProduct().equals(product)) {
                units += si.getUnits();
            }
        }
        return units;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List<PointOfSale> pointOfSales = posCRUD.readAllPointsOfSale();
        List<StockItem> stockItems = new ArrayList<>();
        List<Long> items = new ArrayList<>();
        for (PointOfSale pos : pointOfSales) {
            stockItems.addAll(siCRUD.readStockItemsForPointOfSale(pos));
        }
        for (StockItem si : stockItems) {
            if (si.getProduct().equals(product)) {
                items.add(si.getPos().getId());
            }
        }
        return items;
    }
}
