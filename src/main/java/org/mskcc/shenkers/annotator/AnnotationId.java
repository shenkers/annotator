/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author sol
 */
@Embeddable
public class AnnotationId implements Serializable {

    private String username;
    private String source;
    private String chr;
    private int s,e;
    private boolean neg;

    public AnnotationId() {
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnnotationId)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        AnnotationId other = (AnnotationId) obj;
        return other.getUsername().equals(getUsername()) && other.getSource().equals(getSource()) 
//                && other.getgRange().equals(getgRange())
                ;
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode() + getSource().hashCode() 
//                + getgRange().hashCode()
                ;
    }

    /**
     * @return the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param user the user to set
     */
    public void setUsername(String user) {
        this.username = user;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the chr
     */
    public String getChr() {
        return chr;
    }

    /**
     * @param chr the chr to set
     */
    public void setChr(String chr) {
        this.chr = chr;
    }

    /**
     * @return the s
     */
    public int getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(int s) {
        this.s = s;
    }

    /**
     * @return the e
     */
    public int getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(int e) {
        this.e = e;
    }

    /**
     * @return the neg
     */
    public boolean isNeg() {
        return neg;
    }

    /**
     * @param neg the neg to set
     */
    public void setNeg(boolean neg) {
        this.neg = neg;
    }
}
