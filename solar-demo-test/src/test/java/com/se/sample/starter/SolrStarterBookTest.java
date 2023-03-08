package com.se.sample.starter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.EmbeddedSolrServerFactory;
import com.se.sample.demo2.TwitterSearchTest;
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

public class SolrStarterBookTest {
    static final String CONFIGSET_DIR = "src/test/resources/configsets";
    private static SolrClient solrClient;

    // Define a static logger variable so that it references the
    private static final Logger logger = LoggerFactory.getLogger(SolrStarterBookTest.class);

    @BeforeClass
    public static void init() throws SolrServerException, IOException {
        String targetLocation = SolrStarterBookTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();

        String solrHome = targetLocation + "starter";
        logger.info("solrHome: {}", solrHome);

        solrClient = EmbeddedSolrServerFactory.create(solrHome, CONFIGSET_DIR, "solr_starter_book");
        logger.info("SOLR client: " + solrClient.toString());
    }

    @Test
    public void testEmbeddedStartedCorrect() {
        Assert.assertTrue(true);
    }

    @AfterClass
    public static void teardownClass() {
        try {
            clearEmbeddedDataBase();
            solrClient.close();
        } catch (Exception e) {
            logger.error("Embedded solr shutdown error: {}", e);
        }
    }

    private static void clearEmbeddedDataBase() throws SolrServerException, IOException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
    }
}
