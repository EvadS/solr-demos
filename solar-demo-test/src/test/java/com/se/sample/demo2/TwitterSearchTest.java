package com.se.sample.demo2;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.EmbeddedSolrServerFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class TwitterSearchTest {

    static final String CONFIGSET_DIR = "src/test/resources/configsets";
    private static SolrClient solrClient;

    // Define a static logger variable so that it references the
    private static  final Logger logger = LoggerFactory.getLogger(TwitterSearchTest.class);

    @BeforeClass
    public static void init() throws SolrServerException, IOException {
        String targetLocation = TwitterSearchTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();

        String solrHome = targetLocation + "my-demo-solr";
        logger.info("solrHome: {}",solrHome);

        solrClient = EmbeddedSolrServerFactory.create(solrHome, CONFIGSET_DIR, "twitter_search");


    }


    @Test
    public void demoAddDocument() throws SolrServerException, IOException {

        clearEmbeddedDataBase();

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", UUID.randomUUID().toString());
        document.addField("likes", 10);
        UpdateResponse updateResponse = solrClient.add(document);

        // Remember to commit your changes!
        solrClient.commit();

        SolrQuery solrQuery = new SolrQuery("*:*");
        QueryResponse response = solrClient.query(solrQuery);
        Assert.assertNotNull(response);

        SolrDocumentList solrDocuments = response.getResults();
        Assert.assertNotNull(solrDocuments);
        Assert.assertEquals(1, solrDocuments.getNumFound());

    }


    @Test
    public void testFindById() throws IOException, SolrServerException {

        TypeReference<List<Map<String, Object>>> reference = new TypeReference<List<Map<String, Object>>>() {};
        List<Map<String, Object>> documents = new ObjectMapper().readValue(Paths.get("exampledocs/tweets.json").toFile(), reference);

        List<SolrInputDocument> collect = documents.stream().map(d -> new SolrInputDocument(convertMap(d)))
                .collect(Collectors.toList());

        solrClient.add(collect);
        solrClient.commit();


        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("id:20428e5c-cefe-41e9-89f6-40a85b632b5a");
        final QueryResponse response = solrClient.query(solrQuery);
        logger.info("document found {}", response.getResults().getNumFound());
        boolean b = response.getResults().getNumFound() == 1;

        Assert.assertTrue("not all documents has been loade", response.getResults().getNumFound()== 1L);
    }

    //TODO: down grate to java 8
    public Map<String, SolrInputField> convertMap(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .map(e -> {
                    final SolrInputField solrInputField = new SolrInputField(e.getKey());
                    solrInputField.setValue(e.getValue());
                    return Map.entry(e.getKey(), solrInputField);
                }).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }




    private static void clearEmbeddedDataBase() throws SolrServerException, IOException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
    }


    @AfterClass
    public static void teardownClass() {
        try {
            clearEmbeddedDataBase();
            solrClient.close();
        } catch (Exception e) {
        }
    }

}

