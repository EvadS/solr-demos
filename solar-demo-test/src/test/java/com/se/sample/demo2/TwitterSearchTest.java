package com.se.sample.demo2;



import com.se.sample.EmbeddedSolrServerFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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

        solrClient.deleteByQuery("*:*");
        solrClient.commit();

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "552199");
        document.addField("name", "Gouda cheese wheel");
        document.addField("price", "49.99");
        UpdateResponse updateResponse = solrClient.add(document);

        // Remember to commit your changes!
        solrClient.commit();


        SolrQuery solrQuery = new SolrQuery("*:*");
        QueryResponse response = solrClient.query(solrQuery);
        Assert.assertNotNull(response);

        SolrDocumentList solrDocuments = response.getResults();
        Assert.assertNotNull(solrDocuments);
        Assert.assertEquals(5, solrDocuments.getNumFound());

    }


    @AfterClass
    public static void teardownClass() {
        try {
            solrClient.close();
        } catch (Exception e) {
        }
    }

}

