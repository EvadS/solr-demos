package com.se.sample;

import com.se.sample.model.Book;
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
import com.se.sample.util.BookUtils;
import com.se.sample.config.EmbeddedSolrServerFactory;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class BookCollectionManipulationTest {
    static final String CONFIGSET_DIR = "src/test/resources/configsets";

    private static SolrClient solrClient;

    private static  final Logger logger = LoggerFactory.getLogger(BookCollectionManipulationTest.class);


    @BeforeClass
    public static void init() throws SolrServerException, IOException {
        String targetLocation = BookCollectionManipulationTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();

        String solrHome = targetLocation + "demo-solr-books";
        logger.info("solrHome: {}",solrHome);

        solrClient = EmbeddedSolrServerFactory.create(solrHome, CONFIGSET_DIR, "books");
    }

    @Test
    public void addDocumentShouldWorkCorrect() throws SolrServerException, IOException {
        clearEmbeddedDataBase();

        Book book = BookUtils.BuildRandomBook();

        SolrInputDocument inputDocument = new SolrInputDocument();
        inputDocument.addField("id", book.getId());
        inputDocument.addField("isbn", book.getIsbn());
        inputDocument.addField("author", book.getAuthor());
        inputDocument.addField("title", book.getTitle());
        inputDocument.addField("publisher", book.getPublisher());
        inputDocument.addField("genre", book.getGenre());
        inputDocument.addField("_text_", book.getPreview());

        UpdateResponse updateResponse = solrClient.add(inputDocument);
        // How to add many documents to solr
        //solrClient.add(Arrays.asList(doc1, doc2, doc3, doc4, doc5));

        // Remember to commit your changes!
        solrClient.commit();

        SolrQuery solrQuery = new SolrQuery("*:*");
        QueryResponse response = solrClient.query(solrQuery);
        Assert.assertNotNull(response);

        SolrDocumentList solrDocuments = response.getResults();
        Assert.assertNotNull(solrDocuments);
        Assert.assertEquals(1, solrDocuments.getNumFound());

        assertTrue(true);
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
