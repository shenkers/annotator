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
public interface FeatureAnnotationAuthority {
    public FeatureAnnotation getNext();
    
    public FeatureAnnotation getPrev();
    
    void update(Annotation annotation);
}
