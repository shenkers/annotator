/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mskcc.shenkers.annotator;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;

/**
 *
 * @author sol
 */
public class PersistenceModule extends AbstractModule {

     @Override
    protected void configure() {
        bind(PersistenceProperties.class).toInstance(new PersistenceProperties());  
    }
    
    
}
