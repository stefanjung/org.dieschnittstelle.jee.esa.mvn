
package org.dieschnittstelle.jee.esa.jws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.dieschnittstelle.jee.esa.entities.crm.ws.AbstractTouchpoint;


/**
 * <p>Java class for readProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="readProductResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://dieschnittstelle.org/jee/esa/entities/crm/ws}abstractTouchpoint" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "readProductResponse", propOrder = {
    "_return"
})
public class ReadProductResponse {

    @XmlElement(name = "return")
    protected AbstractTouchpoint _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link AbstractTouchpoint }
     *     
     */
    public AbstractTouchpoint getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractTouchpoint }
     *     
     */
    public void setReturn(AbstractTouchpoint value) {
        this._return = value;
    }

}
