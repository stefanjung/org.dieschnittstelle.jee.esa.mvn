
package org.dieschnittstelle.jee.esa.jws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="location"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="geoLat" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="geoLong" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", propOrder = {
    "geoLat",
    "geoLong",
    "id"
})
@XmlSeeAlso({
    Address.class
})
public class Location {

    protected long geoLat;
    protected long geoLong;
    protected long id;

    /**
     * Gets the value of the geoLat property.
     * 
     */
    public long getGeoLat() {
        return geoLat;
    }

    /**
     * Sets the value of the geoLat property.
     * 
     */
    public void setGeoLat(long value) {
        this.geoLat = value;
    }

    /**
     * Gets the value of the geoLong property.
     * 
     */
    public long getGeoLong() {
        return geoLong;
    }

    /**
     * Sets the value of the geoLong property.
     * 
     */
    public void setGeoLong(long value) {
        this.geoLong = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

}
