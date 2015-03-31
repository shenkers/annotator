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
public class LocusId implements Serializable {

    private String source;
    private String chr;
    private Integer s,e;
    private Boolean neg;

    public LocusId() {
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocusId)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        LocusId other = (LocusId) obj;
        return other.getSource().equals(getSource()) 
                && other.getChr().equals(getChr())
                && other.getS().equals(getS())
                && other.getE().equals(getE())
                && other.isNeg().equals(isNeg())
                ;
//                && other.getgRange().equals(getgRange())
                
    }

    @Override
    public int hashCode() {
        return getSource().hashCode() 
                + getChr().hashCode()
                + getS().hashCode()
                + getE().hashCode()
                + isNeg().hashCode();
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
    public Integer getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(Integer s) {
        this.s = s;
    }

    /**
     * @return the e
     */
    public Integer getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(Integer e) {
        this.e = e;
    }

    /**
     * @return the neg
     */
    public Boolean isNeg() {
        return neg;
    }

    /**
     * @param neg the neg to set
     */
    public void setNeg(Boolean neg) {
        this.neg = neg;
    }
}
