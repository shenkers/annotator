/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

public class AnnotationAuthorityImpl implements AnnotationAuthority {

    int i = -1;

    static Locus[] fas;
    
    static {
        Locus l1 = new Locus(new GRange("X", 1, 2, false), "source1");
        l1.getAnnotations().add(new Annotation("sol", Status.false_pos, "empty notes 1"));
        Locus l2 = new Locus(new GRange("X", 2, 3, false), "source2");
        l2.getAnnotations().add(new Annotation("sol", Status.false_pos, "empty notes 2"));
        Locus l3 = new Locus(new GRange("X", 2, 3, false), "source3");
        fas = new Locus[]{l1,l2,l3};
    }
    
//    = new Annotation[]{
//        new Annotation(new Locus(new GRange("X", 1, 2, false), "source1"), "user1", Status.undecided, "empty notes1"),
//        new Annotation(new Locus(new GRange("X", 1, 2, false), "source1"), "user2", Status.true_pos, "empty notes2"),
//        new Annotation(new Locus(new GRange("X", 1, 2, false), "source2"), "user1", Status.false_pos, "empty notes3")
//    };

//    @Override
//    public Locus getNext() {
//        i = (i + 1) % 3;
//        return fas[i];
//    }
//
//    @Override
//    public Locus getPrev() {
//        i = (i + 2) % 3;
//        return fas[i];
//    }

    @Override
    public void update(Locus annotation) {
        fas[i] = annotation;
    }

}
