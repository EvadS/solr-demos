package com.se.sample.service;

import com.se.sample.config.BookConfig;
import com.se.sample.model.Book;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.time.Instant;

public class SolrService {


    /**
     * create new book in sole core
     * @param book item to store
     * @return solr commit status
     * @throws SolrServerException
     * @throws IOException
     */
    public int indexingDocuments(Book book) throws SolrServerException, IOException {
        String urlString = BookConfig.BOOK_CORE_URL;// "http://localhost:8983/solr/bigboxstore";
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
      //  solr.setParser(new XMLResponseParser());

        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", book.getId());
        document.addField("isbn", book.getIsbn());
        document.addField("title", book.getTitle());
        // TODO: check multi lang
        document.addField("description_en", "");
        document.addField("title_de", "title_de");
        document.addField("pageCount", book.pageCount);

        // correct date format -> YYYY-MM-DDThh:mm:ssZ.
        Instant instant= book.getPublishedDate().getDate().toInstant();
        String instantDate = instant.toString();
        document.addField("publishedDate", instantDate);

        document.addField("thumbnailUrl", book.getThumbnailUrl());
        document.addField("longDescription", book.getLongDescription());
        document.addField("shortDescription", book.getShortDescription());
        document.addField("status", book.getStatus());
        document.addField("authors", book.getAuthors());
        document.addField("categories", book.categories);
        solr.add(document);
        UpdateResponse commit = solr.commit();

        return commit.getStatus();
    }

    public String seachIndexedDocumentsByField (String field, String param) throws SolrServerException, IOException {
        String urlString = BookConfig.BOOK_CORE_URL;
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();

        SolrQuery query = new SolrQuery();
        query.set("q", field +":"+param);// "price:599.99");
        QueryResponse response = solr.query(query);

        SolrDocumentList docList = response.getResults();

        return "";
    }


    public void deleteById(String id) throws SolrServerException, IOException {
        String urlString = BookConfig.BOOK_CORE_URL;
        HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();

        solr.deleteById(id);
        solr.commit();
        SolrQuery query = new SolrQuery();
        query.set("q", "id:"  + id);
        QueryResponse response = solr.query(query);
        SolrDocumentList docList = response.getResults();

        solr.close();
    }

    public void deleteSolrDocumentByQuery(String query) throws SolrServerException, IOException {
        String urlString = BookConfig.BOOK_CORE_URL;
        HttpSolrClient solrClient = new HttpSolrClient.Builder(urlString).build();


        solrClient.deleteByQuery(query);
        solrClient.commit();
    }
}
