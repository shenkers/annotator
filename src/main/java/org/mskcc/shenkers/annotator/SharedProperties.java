/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import java.util.Optional;
import javafx.beans.property.Property;

/**
 *
 * @author sol
 */
public class SharedProperties {

    private Property<Optional<String>> userProperty;
    private Property<Optional<String>> sourceProperty;
    private Property<String> browserUriProperty;
    private Property<String> databaseUriProperty;

    public SharedProperties(Property<Optional<String>> userProperty, Property<Optional<String>> sourceProperty, Property<String> browserUriProperty, Property<String> databaseUriProperty) {
        this.userProperty = userProperty;
        this.sourceProperty = sourceProperty;
        this.browserUriProperty = browserUriProperty;
        this.databaseUriProperty = databaseUriProperty;
    }

    /**
     * @return the userProperty
     */
    public Property<Optional<String>> getUserProperty() {
        return userProperty;
    }

    /**
     * @return the sourceProperty
     */
    public Property<Optional<String>> getSourceProperty() {
        return sourceProperty;
    }

    /**
     * @return the browserUriProperty
     */
    public Property<String> getBrowserUriProperty() {
        return browserUriProperty;
    }

    /**
     * @return the databaseUriProperty
     */
    public Property<String> getDatabaseUriProperty() {
        return databaseUriProperty;
    }
}
