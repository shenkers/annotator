/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sol
 */
@Singleton
public class PersistenceService {

    Logger logger = LoggerFactory.getLogger(PersistenceService.class);

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
              EntityManager annotationEntityManager
    ) {
        logger.info("injecting entitymanager "+annotationEntityManager);
         this.annotationEntityManager = annotationEntityManager;
    }
    
    public void persist(Locus a) {
        logger.info("persist requested");
        EntityTransaction transaction = annotationEntityManager.getTransaction();
        logger.info("start transaction");
        Locus find = annotationEntityManager.find(Locus.class, a.getId());
        
        transaction.begin();
        // if the entity is not present, add it
        if(find==null){
            logger.info("didn't find entity, adding");
        annotationEntityManager.persist(a);
        }
        // otherwise, update it
        else{
            logger.info("found entity, merging");
            annotationEntityManager.merge(a);
        }
        transaction.commit();
    }

//    public List<Annotation> queryAnnotations() {
//        CriteriaQuery<Annotation> query = annotationEntityManager.getCriteriaBuilder().createQuery(Annotation.class);
//        query.from(Annotation.class);
//        List<Annotation> resultList = annotationEntityManager.createQuery(query).getResultList();
//        return resultList;
//    }
    
    public List<Locus> queryLoci() {
        CriteriaQuery<Locus> query = annotationEntityManager.getCriteriaBuilder().createQuery(Locus.class);
        query.from(Locus.class);
        List<Locus> resultList = annotationEntityManager.createQuery(query).getResultList();
        return resultList;
    }
    
    public List<Locus> querySourceLoci(String source) {
        CriteriaBuilder cb = annotationEntityManager.getCriteriaBuilder();
        CriteriaQuery<Locus> query = cb.createQuery(Locus.class);
        
        Root<Locus> locus = query.from(Locus.class);
        query.where(cb.equal(locus.get(Locus_.id).get(LocusId_.source), source));
        
        List<Locus> resultList = annotationEntityManager.createQuery(query).getResultList();
        return resultList;
    }

}
