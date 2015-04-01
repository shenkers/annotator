/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sol
 */
public class BrowserApi {
    
    Logger logger = LoggerFactory.getLogger(PersistenceService.class);

    Client client = ClientBuilder.newClient();
    
    public void setCoordinates(String uri, String chr, int start, int end){
        String path = "setCoordinates";
        logger.info(path);
        
        WebTarget target = client
                .target(uri)
                .path(path)
                .queryParam("chr", chr)
                .queryParam("start", start)
                .queryParam("end", end);
        
        logger.info("requesting URI: " + target.getUri().toString());
        try {
            Response get = target.request().get();
            logger.info("Response: " + get.getStatusInfo());
        } catch (Exception e) {
            logger.error("error requesting coordinates", e);
        }
    }
    
    public void addIntervalNamedTrack(String uri, String name, String chr, int start, int end){
        String path = "addIntervalNamedTrack";
        logger.info(path);
        
        WebTarget target = client
                .target(uri)
                .path(path)
                .queryParam("trackName", name)
                .queryParam("chr", chr)
                .queryParam("start", start)
                .queryParam("end", end);
        
        logger.info("requesting URI: " + target.getUri().toString());
        try {
            Response get = target.request().get();
            logger.info("Response: " + get.getStatusInfo());
        } catch (Exception e) {
            logger.error("error requesting coordinates", e);
        }
    }
    
}
