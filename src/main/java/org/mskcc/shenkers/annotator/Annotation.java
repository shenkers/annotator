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
public class Annotation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
    
    
    private Status status;
    private String notes;
    
    @EmbeddedId
            private AnnotationId id;

    public Annotation() {
    }

    public Annotation(GRange gRange, String source, String username, Status status, String notes) {
        this.status = status;
        this.notes = notes;
        this.id = new AnnotationId();
        setGRange(gRange);
        id.setSource(source);
        id.setUsername(username);
    }
    
    
    
//    @EmbeddedId
//    public AnnotationId getPrimaryKey(){
//        AnnotationId id = new AnnotationId();
//        id.setUser(username);
//        id.setUser(source);
//        id.setChr(chr);
//        id.setS(s);
//        id.setE(e);
//        id.setNeg(neg);
//        return id;
//    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the id
     */
    public AnnotationId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(AnnotationId id) {
        this.id = id;
    }
    
    public GRange getGRange(){
        return new GRange(id.getChr(),id.getS(),id.getE(),id.isNeg());
    }
    
    public void setGRange(GRange gRange){
        id.setChr(gRange.getChr());
        id.setS(gRange.getS());
        id.setE(gRange.getE());
        id.setNeg(gRange.isNeg());
    }

}
