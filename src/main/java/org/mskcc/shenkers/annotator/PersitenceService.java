/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author sol
 */
public class PersitenceService {


    EntityManager 
//            userEntityManager, sourceEntityManager, 
            annotationEntityManager;

//    @Inject
//    public void setEntityManagers(
//            @Named("users") EntityManager userEntityManager,
//            @Named("sources") EntityManager sourceEntityManager,
//            @Named("annotations") EntityManager annotationEntityManager
//    ) {
//        this.userEntityManager = userEntityManager;
//        this.sourceEntityManager = sourceEntityManager;
//        this.annotationEntityManager = annotationEntityManager;
//    }
    
     @Inject
    public void setEntityManager(
              @Named("annotations") EntityManager annotationEntityManager
    ) {
         this.annotationEntityManager = annotationEntityManager;
    }

    public void persist(Annotation a) {
        EntityTransaction transaction = annotationEntityManager.getTransaction();
        transaction.begin();
        annotationEntityManager.persist(a);
        transaction.commit();
    }

    public List<Annotation> queryAnnotations() {
        CriteriaQuery<Annotation> query = annotationEntityManager.getCriteriaBuilder().createQuery(Annotation.class);
        query.from(Annotation.class);
        List<Annotation> resultList = annotationEntityManager.createQuery(query).getResultList();
        return resultList;
    }

}
