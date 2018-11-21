
package org.dieschnittstelle.jee.esa.jws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.dieschnittstelle.jee.esa.entities.erp.ws.AbstractProduct;


/**
 * <p>Java class for createProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createProductResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://dieschnittstelle.org/jee/esa/entities/erp/ws}abstractProduct" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createProductResponse", propOrder = {
    "_return"
})
public class CreateProductResponse {

    @XmlElement(name = "return")
    protected AbstractProduct _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link AbstractProduct }
     *     
     */
    public AbstractProduct getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractProduct }
     *     
     */
    public void setReturn(AbstractProduct value) {
        this._return = value;
    }

}
