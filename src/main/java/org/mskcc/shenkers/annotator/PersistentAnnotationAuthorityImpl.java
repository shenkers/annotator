/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.Inject;
import java.util.List;
import javax.persistence.EntityManager;

public class PersistentAnnotationAuthorityImpl implements AnnotationAuthority {

    PersistenceService ps;
    List<Locus> loci;
    int i = 0;
    int size;

    @Inject
    public PersistentAnnotationAuthorityImpl(PersistenceService ps) {
        this.ps = ps;
        loci = ps.queryLoci();
        size = loci.size();
    }

    @Override
    public void update(Locus locus) {
        ps.persist(locus);
    }

}
