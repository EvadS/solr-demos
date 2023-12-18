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
import static com.se.sample.config.Config.DOCUMENTS_CORE;
import static org.junit.Assert.assertEquals;

public class AddEditionTest {

    private final static Logger logger = LoggerFactory.getLogger(DocumentsSolrTest.class);


    @Test
    public void add_MOZ30009_to_edition_should_work_correct() throws IOException, SolrServerException {
        String targetLocation = DocumentsSolrTest.class
                .getProtectionDomain().getCodeSource().getLocation().getFile();
        String id = "MOZ30009";
        String editionLocationFolder = targetLocation + TEST_SOLR_ITEMS_FOLDER + EDITIONS_FOLDER;

        Map<String, Object> inputDocument =  DocumentUtils.readSorlDocumentFromFile(editionLocationFolder, id);

        String editionCoreUrl = SOLR_URL + EDITIONS_CORE ;
        logger.info("documents core url: {}",editionCoreUrl);

        // put document to solr
        HttpSolrClient solrDocumentsClient = new HttpSolrClient.Builder(editionCoreUrl).build();

        SolrInputDocument solrInputEdition = new SolrInputDocument();
        for (final Map.Entry<String, Object> oValue : inputDocument.entrySet()) {
            solrInputEdition.addField(oValue.getKey(),oValue.getValue() );
        }

        UpdateResponse editionsUpdateResponse = solrDocumentsClient.add(solrInputEdition);

        // get from solr
        SolrQuery query = new SolrQuery();
        query.set("q", "id:" + id);
        QueryResponse response = null;

        response = solrDocumentsClient.query(query);

        SolrDocumentList docList = response.getResults();

        for (SolrDocument doc : docList) {
         //   assertEquals("Kenmore Dishwasher", (String) doc.getFieldValue("name"));
          //  assertEquals((Double) 599.99, (Double) doc.getFieldValue("price"));
        }

    }
}
