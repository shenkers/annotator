/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author sol
 */
//@Embeddable
public class GRange {


    private String chr;
    private int s;
    private int e;
    private boolean neg;

    public GRange() {
    }

    public GRange(String ref, int s, int e, boolean neg) {
        this.chr = ref;
        this.s = s;
        this.e = e;
        this.neg = neg;
    }

    /**
     * @return the ref
     */
    public String getChr() {
        return chr;
    }

    /**
     * @param ref the ref to set
     */
    public void setChr(String ref) {
        this.chr = ref;
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

    @Override
    public int hashCode() {
        return getChr().hashCode() + getS() + getE() + (isNeg() ? 0 : 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GRange)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        GRange other = (GRange) obj;
        return other.getChr().equals(getChr()) && other.getS() == getS() && other.getE() == getE() && other.isNeg() == isNeg();
    }

    @Override
    public String toString() {
       return String.format("%s:%d-%d:%c",chr,s,e,neg?'-':'+');
    }
    
    
}
