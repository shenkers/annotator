/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

public class AnnotationAuthorityImpl implements AnnotationAuthority {

    int i = -1;

    Annotation[] fas = new Annotation[]{
        new Annotation(new GRange("X", 1, 2, false), "source1", "user1", Status.undecided, "empty notes1"),
        new Annotation(new GRange("X", 1, 2, false), "source1", "user2", Status.true_pos, "empty notes2"),
        new Annotation(new GRange("X", 1, 2, false), "source2", "user1", Status.false_pos, "empty notes3")
    };

    @Override
    public Annotation getNext() {
        i = (i + 1) % 3;
        return fas[i];
    }

    @Override
    public Annotation getPrev() {
        i = (i + 2) % 3;
        return fas[i];
    }

    @Override
    public void update(Annotation annotation) {
        fas[i] = annotation;
    }

}
