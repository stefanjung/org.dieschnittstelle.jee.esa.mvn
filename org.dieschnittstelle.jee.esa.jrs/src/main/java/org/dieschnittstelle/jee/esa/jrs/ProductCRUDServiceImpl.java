package org.dieschnittstelle.jee.esa.jrs;

import java.util.List;

import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;

/*
UE JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {


    /**
     * this accessor will be provided by the ServletContext, to which it is written by the ProductServletContextListener
     */
    private GenericCRUDExecutor<AbstractProduct> productCRUD;

    /**
     * here we will be passed the context parameters by the resteasy framework
     * note that the request context is only declared for illustration purposes, but will not be further used here
     *
     * @param servletContext
     */
    public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
        // read out the dataAccessor
        this.productCRUD = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
    }


    @Override
    public AbstractProduct createProduct(
            AbstractProduct prod) {
        return productCRUD.createObject(prod);
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        return productCRUD.readAllObjects();
    }

    @Override
    public AbstractProduct updateProduct(long id, AbstractProduct update) {
        return productCRUD.updateObject(update);
    }

    @Override
    public boolean deleteProduct(long id) {
        return productCRUD.deleteObject(id);
    }

    @Override
    public AbstractProduct readProduct(long id) {
        AbstractProduct ip = this.productCRUD.readObject(id);
        return ip;
//        if (ip != null) {
//            return ip;
//        } else {
//            throw new NotFoundException("the product with id " + id + " does not exist!");
//        }
    }

}
