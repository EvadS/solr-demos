package com.se.sample;

import com.se.sample.utils.DocumentUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static com.se.sample.config.Config.*;
import static org.junit.Assert.assertEquals;

public class DocumentsSolrTest {

    private final static  Logger logger = LoggerFactory.getLogger(DocumentsSolrTest.class);

 //   @Test
    public void import_document_to_solr() throws IOException, SolrServerException {

        String targetLocation = DocumentsSolrTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();
        String documentId = "T030541";
        String editionId = "T030541";
        String documentLocationFolder = targetLocation + TEST_SOLR_ITEMS_FOLDER + DOCUMENTS_FOLDER;
        String editionsLocationFolder = targetLocation + TEST_SOLR_ITEMS_FOLDER + EDITIONS_FOLDER;

        Map<String, Object> inputDocument =  DocumentUtils.readSorlDocumentFromFile(documentLocationFolder, documentId);
        Map<String, Object> editionsDocument =  DocumentUtils.readSorlDocumentFromFile(editionsLocationFolder, editionId);

        String documentsCoreUrl = SOLR_URL + DOCUMENTS_CORE ;
        logger.info("documents core url: {}",documentsCoreUrl);

        String editionsCoreUrl = SOLR_URL + EDITIONS_CORE ;
        logger.info("editions core url: {}",editionsCoreUrl);

        // put document to solr
        HttpSolrClient solrDocumentsClient = new HttpSolrClient.Builder(documentsCoreUrl).build();
        HttpSolrClient solrEditionsClient = new HttpSolrClient.Builder(editionsCoreUrl).build();

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        for (final Map.Entry<String, Object> oValue : inputDocument.entrySet()) {
            solrInputDocument.addField(oValue.getKey(),oValue.getValue() );
        }

       // UpdateResponse add = solrDocumentsClient.add(solrInputDocument);

        SolrInputDocument solrInputEdition = new SolrInputDocument();
        for (final Map.Entry<String, Object> oValue : editionsDocument.entrySet()) {
            solrInputEdition.addField(oValue.getKey(),oValue.getValue() );
        }

        //E012382.json
        UpdateResponse editionsUpdateResponse = solrEditionsClient.add(solrInputEdition);
        // read document from solr
    }


    @Test
    public void add_Z960254_to_documents_should_work_correct() throws IOException, SolrServerException {
        String targetLocation = DocumentsSolrTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();
        String documentId = "Z960254";
        String documentLocationFolder = targetLocation + TEST_SOLR_ITEMS_FOLDER + DOCUMENTS_FOLDER;

        Map<String, Object> inputDocument =  DocumentUtils.readSorlDocumentFromFile(documentLocationFolder, documentId);

        String documentsCoreUrl = SOLR_URL + DOCUMENTS_CORE ;
        logger.info("documents core url: {}",documentsCoreUrl);

        // put document to solr
        HttpSolrClient solrDocumentsClient = new HttpSolrClient.Builder(documentsCoreUrl).build();

        SolrInputDocument solrInputDocument = new SolrInputDocument();
        for (final Map.Entry<String, Object> oValue : inputDocument.entrySet()) {
            solrInputDocument.addField(oValue.getKey(),oValue.getValue() );
        }

        //E012382.json
        UpdateResponse editionsUpdateResponse = solrDocumentsClient.add(solrInputDocument);

        // get from solr

        SolrQuery query = new SolrQuery();
        query.set("q", "id:" + documentId);
        QueryResponse response = null;

        response = solrDocumentsClient.query(query);

        SolrDocumentList docList = response.getResults();

        for (SolrDocument doc : docList) {
            assertEquals("Kenmore Dishwasher", (String) doc.getFieldValue("name"));
            assertEquals((Double) 599.99, (Double) doc.getFieldValue("price"));
        }


    }

}
