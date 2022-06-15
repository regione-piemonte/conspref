/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.business.consprefboweb;

import it.csi.conspref.consprefboweb.business.consprefboweb.impl.CittadiniApiServiceImpl;
import it.csi.conspref.consprefboweb.business.consprefboweb.impl.InformativaApiServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {


    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(CittadiniApiServiceImpl.class);
        resources.add(InformativaApiServiceImpl.class);
        return resources;
    }




}