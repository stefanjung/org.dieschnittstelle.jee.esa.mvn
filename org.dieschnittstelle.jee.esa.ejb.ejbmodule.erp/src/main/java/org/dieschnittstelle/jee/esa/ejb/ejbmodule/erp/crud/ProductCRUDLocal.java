package org.dieschnittstelle.jee.esa.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

/*
 * TODO JPA3:
 * this interface shall be implemented using a stateless EJB with an EntityManager.
 * See TouchpointCRUDStateless for an example EJB with a similar scope of functionality
 */

@Stateless
@Local
public interface ProductCRUDLocal {

    public AbstractProduct createProduct(AbstractProduct prod);

    public List<AbstractProduct> readAllProducts();

    public AbstractProduct updateProduct(AbstractProduct update);

    public AbstractProduct readProduct(long productID);

    public boolean deleteProduct(long productID);

}
