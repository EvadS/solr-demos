package com.se.sample.starter;

import com.se.sample.EmbeddedSolrServerFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SolrStarterBookTest {
    static final String CONFIGSET_DIR = "src/test/resources/configsets";
    // Define a static logger variable so that it references the
    private static final Logger logger = LoggerFactory.getLogger(SolrStarterBookTest.class);
    private static SolrClient solrClient;

    @BeforeClass
    public static void init() throws SolrServerException, IOException {
        String targetLocation = SolrStarterBookTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();

        String solrHome = targetLocation + "starter";
        logger.info("solrHome: {}", solrHome);

        solrClient = EmbeddedSolrServerFactory.create(solrHome, CONFIGSET_DIR, "solr_starter_book");
        logger.info("SOLR client: " + solrClient);
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

    @Test
    public void testEmbeddedStartedCorrect() {
        Assert.assertTrue(true);
    }
}
