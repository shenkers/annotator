/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import org.apache.commons.validator.routines.UrlValidator;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author sol
 */
public class UriConfiguratorControllerNGTest {
    
    public UriConfiguratorControllerNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of connect method, of class UriConfiguratorController.
     */
    @Test
    public void testConnect() throws Exception {
        UrlValidator instance = //UrlValidator.getInstance(`);
        new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
        
        System.out.println(instance.isValid("http://localhost:12345"));
        System.out.println(instance.isValid("http://localhost:12345/"));
        System.out.println(instance.isValid("http://localhost:1234/"));
        System.out.println(instance.isValid("http://localhost:1234"));
    }
    
}
