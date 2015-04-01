/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.AbstractModule;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author sol
 */
public class FXModule extends AbstractModule {

    @Override
    protected void configure() {
        FXMLLoader loader = new FXMLLoader();
        bind(FXMLLoader.class).toInstance(loader);
    }

}
