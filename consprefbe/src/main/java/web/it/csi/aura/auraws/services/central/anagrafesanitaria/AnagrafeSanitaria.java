/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.aura.auraws.services.central.anagrafesanitaria;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AnagrafeSanitaria", targetNamespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", wsdlLocation = "file:/K:/workspace-csi-svil/delegheboweb/test/AURA.WS.AnagrafeSanitaria.CLS.wsdl")
public class AnagrafeSanitaria
    extends Service
{

    private final static URL ANAGRAFESANITARIA_WSDL_LOCATION;
    private final static WebServiceException ANAGRAFESANITARIA_EXCEPTION;
    private final static QName ANAGRAFESANITARIA_QNAME = new QName("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", "AnagrafeSanitaria");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/K:/workspace-csi-svil/delegheboweb/test/AURA.WS.AnagrafeSanitaria.CLS.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ANAGRAFESANITARIA_WSDL_LOCATION = url;
        ANAGRAFESANITARIA_EXCEPTION = e;
    }

    public AnagrafeSanitaria() {
        super(__getWsdlLocation(), ANAGRAFESANITARIA_QNAME);
    }

    public AnagrafeSanitaria(WebServiceFeature... features) {
        super(__getWsdlLocation(), ANAGRAFESANITARIA_QNAME, features);
    }

    public AnagrafeSanitaria(URL wsdlLocation) {
        super(wsdlLocation, ANAGRAFESANITARIA_QNAME);
    }

    public AnagrafeSanitaria(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ANAGRAFESANITARIA_QNAME, features);
    }

    public AnagrafeSanitaria(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AnagrafeSanitaria(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AnagrafeSanitariaSoap
     */
    @WebEndpoint(name = "AnagrafeSanitariaSoap")
    public AnagrafeSanitariaSoap getAnagrafeSanitariaSoap() {
        return super.getPort(new QName("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", "AnagrafeSanitariaSoap"), AnagrafeSanitariaSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AnagrafeSanitariaSoap
     */
    @WebEndpoint(name = "AnagrafeSanitariaSoap")
    public AnagrafeSanitariaSoap getAnagrafeSanitariaSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", "AnagrafeSanitariaSoap"), AnagrafeSanitariaSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ANAGRAFESANITARIA_EXCEPTION!= null) {
            throw ANAGRAFESANITARIA_EXCEPTION;
        }
        return ANAGRAFESANITARIA_WSDL_LOCATION;
    }

}
