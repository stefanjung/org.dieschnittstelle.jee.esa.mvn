
package dieschnittstelle.org.jee.esa.entities.erp.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="productType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BREAD"/&gt;
 *     &lt;enumeration value="ROLL"/&gt;
 *     &lt;enumeration value="PASTRY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "productType")
@XmlEnum
public enum ProductType {

    BREAD,
    ROLL,
    PASTRY;

    public String value() {
        return name();
    }

    public static ProductType fromValue(String v) {
        return valueOf(v);
    }

}
