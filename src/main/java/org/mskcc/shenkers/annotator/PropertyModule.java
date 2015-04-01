/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.AbstractModule;
import java.util.Optional;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author sol
 */
public class PropertyModule extends AbstractModule {

    @Override
    protected void configure() {
        Property<Optional<String>> userProperty = new SimpleObjectProperty<>(Optional.empty());
        Property<Optional<String>> sourceProperty = new SimpleObjectProperty<>(Optional.empty());
        StringProperty browserUriPropert = new SimpleStringProperty();
        StringProperty databaseUriPropert = new SimpleStringProperty();
        SharedProperties sharedProperties = new SharedProperties(userProperty, sourceProperty, browserUriPropert, databaseUriPropert);
        bind(SharedProperties.class).toInstance(sharedProperties);
    }

}
