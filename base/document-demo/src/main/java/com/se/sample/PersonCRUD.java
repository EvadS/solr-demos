package com.se.sample;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class PersonCRUD {
    public static void main(String[] args) throws Exception {
        //Preparing the Solr client
        String urlString = "http://localhost:8983/solr/solr_sample";
        SolrClient solr = new HttpSolrClient.Builder(urlString).build();

        //Preparing the Solr document
        SolrInputDocument doc = new SolrInputDocument();

        //Adding fields to the document
        String value = "003";

        doc.addField("id", value);
        doc.addField("name", "Rajaman");
        doc.addField("age", "34");
        doc.addField("addr", "vishakapatnam");

        //Adding the document to Solr
        solr.add(doc);

        //Saving the changes
        solr.commit();
        System.out.println("Documents added");

        update();
        delete(value);

        addDocs();

        querySetParams();


        PersonSolrServer pserver = new PersonSolrServer();
        pserver.queryList(Integer.toString(1002), 1, 1000);
        int a=0;
    }

    public static void update() throws SolrServerException, IOException {
        //Preparing the Solr client
        String urlString = "http://localhost:8983/solr/solr_sample";
        SolrClient Solr = new HttpSolrClient.Builder(urlString).build();

        //Preparing the Solr document
        SolrInputDocument doc = new SolrInputDocument();

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setAction(UpdateRequest.ACTION.COMMIT, false, false);
        SolrInputDocument myDocumentInstantlycommited = new SolrInputDocument();

        myDocumentInstantlycommited.addField("id", "002");
        myDocumentInstantlycommited.addField("name", "Rahman");
        myDocumentInstantlycommited.addField("age", "27");
        myDocumentInstantlycommited.addField("addr", "hyderabad");

        updateRequest.add(myDocumentInstantlycommited);
        UpdateResponse rsp = updateRequest.process(Solr);

        System.out.println("Documents Updated");
    }

    public static void delete(String value) throws SolrServerException, IOException {
        //Preparing the Solr client
        String urlString = "http://localhost:8983/solr/solr_sample";
        SolrClient Solr = new HttpSolrClient.Builder(urlString).build();

        //Preparing the Solr document
        SolrInputDocument doc = new SolrInputDocument();

        //Deleting the documents from Solr
        Solr.deleteByQuery("id:" + value);

        //Saving the document
        Solr.commit();
        System.out.println("Documents deleted");
    }

    public static void addDocs() {
        String urlString = "http://localhost:8983/solr/solr_sample";
        SolrClient server = new HttpSolrClient.Builder(urlString).build();
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 1001);
        doc.addField("name", "name 1");
        doc.addField("remark", "This is SolrInputDocuments 1 remark");
        doc.addField("f_text_ru", "русське текст");

        docs.add(doc);

        doc = new SolrInputDocument();
        doc.addField("id", 1002);
        doc.addField("name", "name 2");
        doc.addField("remark", "This is SolrInputDocuments 2 remark");
        doc.addField("f_text_ru", "русське текст второй");

        docs.add(doc);

        try {
            UpdateResponse response = server.add(docs);
            server.commit();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("documents added");


    }

    public static void querySetParams() {

        String urlString = "http://localhost:8983/solr/solr_sample";
        SolrClient server = new HttpSolrClient.Builder(urlString).build();

        SolrQuery params = new SolrQuery("id:1002");
        params.setHighlight(true);
        params.addHighlightField("name");
        params.setHighlightSimplePre("<font color='red'>");
        params.setHighlightSimplePost("</font>");

        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
