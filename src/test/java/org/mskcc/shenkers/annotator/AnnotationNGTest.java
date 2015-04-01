/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.name.Names;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
public class AnnotationNGTest {

    public AnnotationNGTest() {
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
     * Test of getPrimaryKey method, of class Annotation.
     */
//    @Test
//    public void testGetPrimaryKey2() {
//        String annotations = "annotations2";
//        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//
//        GRange range = new GRange("X", 1, 2, false);
//        Annotation a = new Annotation(new Locus(range, "source1"), "sol", Status.false_pos, "does it work?");
//
//        EntityTransaction transaction = annotationEntityManager.getTransaction();
//        transaction.begin();
////        annotationEntityManager.persist(a);
//        annotationEntityManager.merge(a);
//        transaction.commit();
//
////        new StandardServiceRegistryBuilder().applySetting(configuration.getProperties()).
////       String annotations = "annotations";
////         String userHomeDir = System.getProperty("user.home", ".");
////        String systemDir = userHomeDir + "/annotator";
//// 
////        // Set the db system directory.
////        System.setProperty("derby.system.home", systemDir);
//////        EntityManager userEntityManager = Persistence.createEntityManagerFactory(users).createEntityManager();
//////        EntityManager sourceEntityManager = Persistence.createEntityManagerFactory(sources).createEntityManager();
////        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//    }
//
////     @Test
//    public void testGetPrimaryKey() {
//        String annotations = "annotations2";
//        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//        GRange range = new GRange("X", 1, 2, false);
//        Annotation a = new Annotation(new Locus(range, "source1"), "sol", Status.false_pos, "does it work?");
//
//        Annotation find = annotationEntityManager.find(Annotation.class, a.getUsername());
//        EntityTransaction transaction = annotationEntityManager.getTransaction();
//        transaction.begin();
//        find.setNotes("changed notes");
//        annotationEntityManager.persist(find);
//        transaction.commit();
////        new StandardServiceRegistryBuilder().applySetting(configuration.getProperties()).
////       String annotations = "annotations";
////         String userHomeDir = System.getProperty("user.home", ".");
////        String systemDir = userHomeDir + "/annotator";
//// 
////        // Set the db system directory.
////        System.setProperty("derby.system.home", systemDir);
//////        EntityManager userEntityManager = Persistence.createEntityManagerFactory(users).createEntityManager();
//////        EntityManager sourceEntityManager = Persistence.createEntityManagerFactory(sources).createEntityManager();
////        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//
//    }
    @Test
    public void testGetPrimaryKey3() {
        String annotations = "loci";
        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//3R:4895365-4897435
        GRange range = new GRange("X", 3, 6, false);
        Locus a = new Locus(range, "source1");
        a.getAnnotations().add(new Annotation("sol", Status.false_pos, "a second locus"));

        EntityTransaction transaction = annotationEntityManager.getTransaction();
        transaction.begin();
        Locus find = annotationEntityManager.find(Locus.class, a.getId());
        if (find == null) {
            annotationEntityManager.persist(a);
        }
        else{
            System.out.println("found entry");
        }
//        annotationEntityManager.merge(a);
        transaction.commit();
    }
    
    @Test
    public void testGetPrimaryKey4() {
        String annotations = "loci";
        EntityManager annotationEntityManager = Persistence.createEntityManagerFactory(annotations).createEntityManager();
//3R:4895365-4897435
        int start = 4895365;
        int end = 4897435;
        
        for(int i=0; i<=2000; i+= 200){
        GRange range = new GRange("3R", start+i, end+i, false);
        Locus a = new Locus(range, "source3");

        EntityTransaction transaction = annotationEntityManager.getTransaction();
        transaction.begin();
        Locus find = annotationEntityManager.find(Locus.class, a.getId());
        if (find == null) {
            annotationEntityManager.persist(a);
        }
        else{
            System.out.println("found entry");
        }
//        annotationEntityManager.merge(a);
        transaction.commit();
        }
    }
}
