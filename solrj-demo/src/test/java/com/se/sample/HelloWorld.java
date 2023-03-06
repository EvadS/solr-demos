package com.se.sample;

import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloWorld {

    static CoreContainer container;
    static EmbeddedSolrServer server;

    @BeforeClass
    public static void setUpBeforeClass() {
        try {
//
//            String targetLocation = EmbeddedSolrServerFactory.class
//                    .getProtectionDomain().getCodeSource().getLocation().getFile() + "/..";


        }catch (Exception ex){
            int a = 0;
        }
    }

    @Test
    public void init (){
        int a =0;
    }

}
