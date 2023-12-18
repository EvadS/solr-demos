package com.se.sample;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.se.sample.models.ProductBean;
import com.se.sample.models.SolrJavaIntegration;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

public class SolrJavaLiveTest {

    private SolrJavaIntegration solrJavaIntegration;

  //  @Before
    public void setUp() throws Exception {

        solrJavaIntegration = new SolrJavaIntegration("http://localhost:8983/solr/bigboxstore");
        solrJavaIntegration.addSolrDocument("123456", "Kenmore Dishwasher", "599.99");
    }

    // TODO: HERE
    @Test
    public void add_calendar () throws SolrServerException, IOException {

     //   solrJavaIntegration = new SolrJavaIntegration("http://legislation-solr2.dev.liga360.net:8080/solr/calendar_core");

        String clientUrl = "http://solr-ips.dev.ligazakon.net:8080/solr/calendar_core";

        HttpSolrClient solrClient = new HttpSolrClient.Builder(clientUrl).build();

        SolrInputDocument solrInputDocument = new SolrInputDocument();

        solrInputDocument.addField( "f_type","1");
        solrInputDocument.addField("f_day_transfer",0);
        solrInputDocument.addField( "f_day_free",0);
        solrInputDocument.addField( "id","2018hd.xml_3");
        solrInputDocument.addField( "f_day",9);
        solrInputDocument.addField(  "f_month",5);
        solrInputDocument.addField( "f_year",2018);
        solrInputDocument.addField( "f_data","День перемоги над нацизмом у Другій світовій війні (День перемоги)");
        solrInputDocument.addField( "f_data_ext","ua");
        solrInputDocument.addField(  "f_type_cal","HOLIDAY");
        solrInputDocument.addField( "last_update","2023-07-03T12:58:22.954Z");

        solrClient.add(solrInputDocument);
        solrClient.commit();

        int a =0;
    }


    @Test
    public void whenAdd_thenVerifyAddedByQueryOnId() throws SolrServerException, IOException {

        SolrQuery query = new SolrQuery();
        query.set("q", "id:123456");
        QueryResponse response = null;

        response = solrJavaIntegration.getSolrClient().query(query);

        SolrDocumentList docList = response.getResults();
        assertEquals(1, docList.getNumFound());

        for (SolrDocument doc : docList) {
            assertEquals("Kenmore Dishwasher", (String) doc.getFieldValue("name"));
            assertEquals((Double) 599.99, (Double) doc.getFieldValue("price"));
        }
    }

    @Test
    public void whenAdd_thenVerifyAddedByQueryOnPrice() throws SolrServerException, IOException {

        SolrQuery query = new SolrQuery();
        query.set("q", "price:599.99");
        QueryResponse response = null;

        response = solrJavaIntegration.getSolrClient().query(query);

        SolrDocumentList docList = response.getResults();
        assertEquals(1, docList.getNumFound());

        for (SolrDocument doc : docList) {
            assertEquals("123456", (String) doc.getFieldValue("id"));
            assertEquals((Double) 599.99, (Double) doc.getFieldValue("price"));
        }
    }

    @Test
    public void whenAdd_thenVerifyAddedByQuery() throws SolrServerException, IOException {

        SolrDocument doc = solrJavaIntegration.getSolrClient().getById("123456");
        assertEquals("Kenmore Dishwasher", (String) doc.getFieldValue("name"));
        assertEquals((Double) 599.99, (Double) doc.getFieldValue("price"));
    }

    @Test
    public void whenAddBean_thenVerifyAddedByQuery() throws SolrServerException, IOException {

        ProductBean pBean = new ProductBean("888", "Apple iPhone 6s", "299.99");
        solrJavaIntegration.addProductBean(pBean);

        SolrDocument doc = solrJavaIntegration.getSolrClient().getById("888");
        assertEquals("Apple iPhone 6s", (String) doc.getFieldValue("name"));
        assertEquals((Double) 299.99, (Double) doc.getFieldValue("price"));
    }

    @Test
    public void whenDeleteById_thenVerifyDeleted() throws SolrServerException, IOException {

        solrJavaIntegration.deleteSolrDocumentById("123456");

        SolrQuery query = new SolrQuery();
        query.set("q", "id:123456");
        QueryResponse response = solrJavaIntegration.getSolrClient().query(query);

        SolrDocumentList docList = response.getResults();
        assertEquals(0, docList.getNumFound());
    }

    @Test
    public void whenDeleteByQuery_thenVerifyDeleted() throws SolrServerException, IOException {

        solrJavaIntegration.deleteSolrDocumentByQuery("name:Kenmore Dishwasher");

        SolrQuery query = new SolrQuery();
        query.set("q", "id:123456");
        QueryResponse response = null;

        response = solrJavaIntegration.getSolrClient().query(query);

        SolrDocumentList docList = response.getResults();
        assertEquals(0, docList.getNumFound());
    }
}
