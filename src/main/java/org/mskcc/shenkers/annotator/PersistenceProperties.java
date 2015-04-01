/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sol
 */
public class PersistenceProperties {
    private Property<EntityManagerFactory> emfProperty = new SimpleObjectProperty<>();
    private Property<EntityManager> emProperty = new SimpleObjectProperty<>();

    /**
     * @return the emfProperty
     */
    public Property<EntityManagerFactory> getEmfProperty() {
        return emfProperty;
    }

    /**
     * @return the emProperty
     */
    public Property<EntityManager> getEmProperty() {
        return emProperty;
    }
    
}
