/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author sol
 */
public class BEDIterator implements Iterator<Locus> {

    Scanner scan;
    String splitRegex = "\t";
    boolean hasNext;

    public BEDIterator(File bedFile) throws FileNotFoundException {
        scan = new Scanner(bedFile);
        hasNext = scan.hasNext();
    }

    public BEDIterator(String bedFile) throws FileNotFoundException {
        this(new File(bedFile));
    }

    public boolean hasNext() {
        return hasNext;
    }

    public Locus next() {
        String[] item = scan.nextLine().split(splitRegex);

        String chr = item[0];
        String source = item.length > 3 ? item[3] : null;
        int start = Integer.parseInt(item[1]) + 1;
        int end = Integer.parseInt(item[2]);
        boolean isNeg = item.length > 5 ? item[5].charAt(0) == '-' : false;

        Locus region = new Locus(new GRange(chr, start, end, isNeg), source);

        if (!scan.hasNext()) {
            hasNext = false;
            scan.close();
        }

        return region;
    }

    public void remove() {

    }

}
