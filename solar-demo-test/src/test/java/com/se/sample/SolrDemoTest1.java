package com.se.sample;

import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.junit.*;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.core.CoreContainer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class SolrDemoTest1 {

    static EmbeddedSolrServer server;
    static CoreContainer container;
    static EmbeddedSolrServer contentServer;

    @BeforeClass
    public static void setUpBeforeClass() {
        try {
        container = new CoreContainer("src/test/resources/testdata/solr");

           container.load();
           server = new EmbeddedSolrServer(container, "employee");
       }catch (Exception ex){
           int a = 0;
       }
    }

   // @Test
    public void  testEmployee(){

        EmployeeQuery query = new EmployeeQuery();
        query.setEmployeeId("4788717");
        SolrQuery solrQuery = query.toQuery();
        QueryResponse qResp;

        try {
            qResp = server.query(solrQuery);
            SolrDocumentList docList = qResp.getResults();
            Assert.assertEquals(1, docList.size());

            SolrDocument doc1 = docList.get(0);
            Assert.assertEquals("428666", doc1.getFieldValue("department-id"));
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }


}
