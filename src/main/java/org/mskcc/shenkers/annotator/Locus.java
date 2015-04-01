/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author sol
 */
@Entity
public class Locus {

    @EmbeddedId
    private LocusId id;
    
    @ElementCollection
    private Set<Annotation> annotations;

    public Locus() {
    }

    public Locus(GRange gRange, String source) {
        this.id = new LocusId();
        setGRange(gRange);
        id.setSource(source);
        annotations = new HashSet<>();
    }

    @Override
    public String toString() {
        return String.format("'%s' : '%s'", id.getSource(), getGRange());
    }
    
    

    /**
     * @return the id
     */
    public LocusId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(LocusId id) {
        this.id = id;
    }

    public GRange getGRange() {
        return new GRange(id.getChr(), id.getS(), id.getE(), id.isNeg());
    }

    public void setGRange(GRange gRange) {
        id.setChr(gRange.getChr());
        id.setS(gRange.getS());
        id.setE(gRange.getE());
        id.setNeg(gRange.isNeg());
    }
    
    public String getSource(){
        return id.getSource();
    }

    /**
     * @return the annotations
     */
    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(Set<Annotation> annotations) {
        this.annotations = annotations;
    }

}
