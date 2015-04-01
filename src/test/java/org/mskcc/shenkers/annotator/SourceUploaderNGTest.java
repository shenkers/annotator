/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.name.Names;
import java.io.FileNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
public class SourceUploaderNGTest {

    public SourceUploaderNGTest() {
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

    @Test
    public void testGetPrimaryKey4() throws FileNotFoundException {

        String annotations = "loci";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(annotations);
        

        

        BEDIterator bi = new BEDIterator("/home/sol/annotator/dm3_PA_sites.bed");
        if (bi.hasNext()) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            while (bi.hasNext()) {

                Locus locus = bi.next();
                locus.getId().setS(locus.getId().getS()-300);
                locus.getId().setE(locus.getId().getE()+300);
                Locus find = em.find(Locus.class, locus.getId());
                if (find == null) {
                    em.persist(locus);
                } else {
                    System.out.println("found entry, skipping");
                }
            }
//        annotationEntityManager.merge(a);
            transaction.commit();
        }
        emf.close();
    }
}
