/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

/**
 *
 * @author sol
 */
@Entity
public class Locus {

    @EmbeddedId
    private LocusId id;

    public Locus() {
    }

    public Locus(GRange gRange, String source) {
        this.id = new LocusId();
        setGRange(gRange);
        id.setSource(source);
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

}
