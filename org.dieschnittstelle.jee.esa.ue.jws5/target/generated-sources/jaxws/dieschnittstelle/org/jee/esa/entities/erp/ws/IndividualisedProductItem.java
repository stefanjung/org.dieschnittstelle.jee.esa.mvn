
package dieschnittstelle.org.jee.esa.entities.erp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for individualisedProductItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="individualisedProductItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://org.dieschnittstelle/jee/esa/entities/erp/ws}abstractProduct"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="expirationAfterStocked" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="productType" type="{http://org.dieschnittstelle/jee/esa/entities/erp/ws}productType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "individualisedProductItem", propOrder = {
    "expirationAfterStocked",
    "productType"
})
public class IndividualisedProductItem
    extends AbstractProduct
{

    protected int expirationAfterStocked;
    @XmlSchemaType(name = "string")
    protected ProductType productType;

    /**
     * Gets the value of the expirationAfterStocked property.
     * 
     */
    public int getExpirationAfterStocked() {
        return expirationAfterStocked;
    }

    /**
     * Sets the value of the expirationAfterStocked property.
     * 
     */
    public void setExpirationAfterStocked(int value) {
        this.expirationAfterStocked = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link ProductType }
     *     
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductType }
     *     
     */
    public void setProductType(ProductType value) {
        this.productType = value;
    }

}
