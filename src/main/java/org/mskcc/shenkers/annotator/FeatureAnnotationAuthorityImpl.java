/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

public class FeatureAnnotationAuthorityImpl implements FeatureAnnotationAuthority {

    int i = -1;

    FeatureAnnotation[] fas = new FeatureAnnotation[]{
        new FeatureAnnotation("dummy_feature1", new Annotation("user1", Status.true_pos, "empty notes1")),
        new FeatureAnnotation("dummy_feature2", new Annotation("user1", Status.false_pos, "empty notes2")),
        new FeatureAnnotation("dummy_feature3", new Annotation("user1", Status.undecided, "empty notes3")),};

    @Override
    public FeatureAnnotation getNext() {
        i = (i + 1) % 3;
        return fas[i];
    }

    @Override
    public FeatureAnnotation getPrev() {
        i = (i + 2) % 3;
        return fas[i];
    }

    @Override
    public void update(Annotation annotation) {
        fas[i].setAnnotation(annotation);
    }

}
