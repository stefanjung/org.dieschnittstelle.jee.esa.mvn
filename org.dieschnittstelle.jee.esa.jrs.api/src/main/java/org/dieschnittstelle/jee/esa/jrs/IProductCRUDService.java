package org.dieschnittstelle.jee.esa.jrs;

import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import java.util.List;

/*
 * UE JRS2:
 * deklarieren Sie hier Methoden fuer:
 * - die Erstellung eines Produkts
 * - das Auslesen aller Produkte
 * - das Auslesen eines Produkts
 * - die Aktualisierung eines Produkts
 * - das Loeschen eines Produkts
 * und machen Sie diese Methoden mittels JAX-RS Annotationen als WebService verfuegbar
 */

/*
 * UE JRS3: aendern Sie Argument- und Rueckgabetypen der Methoden von IndividualisedProductItem auf AbstractProduct
 */
public interface IProductCRUDService {

    public AbstractProduct createProduct(AbstractProduct prod);

    public List readAllProducts();

    public AbstractProduct updateProduct(long id, AbstractProduct update);

    boolean deleteProduct(long id);

    public AbstractProduct readProduct(long id);

}
