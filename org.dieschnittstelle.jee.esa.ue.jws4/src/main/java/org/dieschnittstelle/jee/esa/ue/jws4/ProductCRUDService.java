package org.dieschnittstelle.jee.esa.ue.jws4;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.jee.esa.entities.GenericCRUDExecutor;
import org.dieschnittstelle.jee.esa.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.jee.esa.entities.erp.AbstractProduct;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

/*
 * UE JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer
 * die Umetzung der Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 * die Sie aus dem ServletContext auslesen koennen
 */
@WebService(targetNamespace = "http://dieschnittstelle.org/jee/esa/jws", name = "IProductCRUDService", serviceName = "ProductCRUDWebService", portName = "ProductCRUDPort")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ProductCRUDService {

    protected static Logger logger = org.apache.logging.log4j.LogManager
            .getLogger(ProductCRUDService.class);

    @Resource
    private WebServiceContext wscontext;

    @PostConstruct
    @WebMethod(exclude = true)
    public void initialiseContext() {
    }

    public List<AbstractProduct> readAllProducts() {
        HttpServletRequest httpRequest = (HttpServletRequest) wscontext
                .getMessageContext().get(MessageContext.SERVLET_REQUEST);

        logger.info("readAllProducts(): servlet request is: " + httpRequest);

        GenericCRUDExecutor<AbstractProduct> productCRUD = getGenericCRUDExecutor();
        logger.info("readAllProducts(): read productCRUD from servletContext: "
                + productCRUD);

        return productCRUD.readAllObjects();
    }

    public AbstractProduct createProduct(AbstractProduct product) {
        // obtain the CRUD executor from the servlet context
        GenericCRUDExecutor<AbstractProduct> productCRUD = getGenericCRUDExecutor();

        return productCRUD
                .createObject(product);
    }

    public AbstractProduct updateProduct(AbstractProduct update) {
        GenericCRUDExecutor<AbstractProduct> productCRUD = getGenericCRUDExecutor();

        return productCRUD.updateObject(update);
    }

    public boolean deleteProduct(long id) {
        // obtain the CRUD executor from the servlet context
        GenericCRUDExecutor<AbstractProduct> productCRUD = getGenericCRUDExecutor();

        return productCRUD.deleteObject(id);
    }

    public IndividualisedProductItem readProduct(long id) {
        GenericCRUDExecutor<AbstractProduct> productCRUD = getGenericCRUDExecutor();

        return (IndividualisedProductItem) productCRUD.readObject(id);
    }


    private GenericCRUDExecutor<AbstractProduct> getGenericCRUDExecutor(){
        return (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
                .getMessageContext().get(MessageContext.SERVLET_CONTEXT))
                .getAttribute("productCRUD");
    }

}
