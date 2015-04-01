/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author sol
 */
@Embeddable
public class Annotation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;

    private Status status;
    private String notes;
    private String username;

    public Annotation() {
    }

    public Annotation(String username, Status status, String notes) {
        this.status = status;
        this.notes = notes;
        this.username = username;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }   

}
