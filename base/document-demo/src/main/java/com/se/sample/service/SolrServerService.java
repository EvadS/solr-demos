package com.se.sample.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class SolrServerService {
    private final static String URL // = "http://localhost/solr/person";
            = "http://localhost:8983/solr/person";
    private static HttpSolrClient server = null;

    public static  void add() throws SolrServerException, IOException {

        //Preparing the Solr client
        String urlString = "http://localhost:8983/Solr/my_core";
        SolrClient Solr = new HttpSolrClient.Builder(urlString).build();

        //Preparing the Solr document
        SolrInputDocument doc = new SolrInputDocument();

        //Adding fields to the document
        doc.addField("id", "003");
        doc.addField("name", "Rajaman");
        doc.addField("age","34");
        doc.addField("addr","vishakapatnam");

        //Adding the document to Solr
        Solr.add(doc);

        //Saving the changes
        Solr.commit();
        System.out.println("Documents added");
    }
}
