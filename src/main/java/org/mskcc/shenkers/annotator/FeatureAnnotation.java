/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

/**
 *
 * @author sol
 */
public class FeatureAnnotation {
    int id;
    
    String featureDesc;
    Annotation annotation;

    public FeatureAnnotation() {
    }

    public FeatureAnnotation(String feature_desc, Annotation annotation) {
        this.featureDesc = feature_desc;
        this.annotation = annotation;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public String getFeatureDesc() {
        return featureDesc;
    }
    
    public int getId() {
        return id;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public void setFeatureDesc(String featureDesc) {
        this.featureDesc = featureDesc;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
