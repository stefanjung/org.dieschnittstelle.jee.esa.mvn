package org.dieschnittstelle.jee.esa.entities.erp;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://dieschnittstelle.org/jee/esa/entities/erp/ws")
public enum ProductType {

    BREAD, ROLL, PASTRY;

    @JsonCreator
    public static ProductType deserialise(String pt) {
        return ProductType.valueOf(ProductType.class, pt);
    }
}
