package com.se.sample;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SolrDemoTest {
    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void testQuery() throws SolrServerException, IOException {
        try (SolrClient client =
                     new HttpSolrClient.Builder("http://localhost:8983/solr/films").build()
        ) {

            SolrQuery query = new SolrQuery("name:new");
            QueryResponse response = client.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            DocumentObjectBinder binder = new DocumentObjectBinder();
            List<Film> films = binder.getBeans(Film.class, solrDocumentList);

            films.forEach(f -> System.out.println(GSON.toJson(f)));
        }
    }

    @Test
    public void testFilterQuery() throws SolrServerException, IOException {
        try (SolrClient client =
                     new HttpSolrClient.Builder("http://localhost:8983/solr/films").build()
        ) {

            SolrQuery query = new SolrQuery("*:*");
            query.addFilterQuery("genre:\"Superhero movie\"");

            QueryResponse response = client.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            DocumentObjectBinder binder = new DocumentObjectBinder();
            List<Film> films = binder.getBeans(Film.class, solrDocumentList);

            films.forEach(f -> System.out.println(GSON.toJson(f)));
        }
    }

    @Test
    public void testFacetQuery() throws SolrServerException, IOException {
        try (SolrClient client =
                     new HttpSolrClient.Builder("http://localhost:8983/solr/films").build()
        ) {

            SolrQuery query = new SolrQuery("*:*");

            query.setRows(0);
            query.addFacetField("genre");
            query.setFacetLimit(15);

            QueryResponse response = client.query(query);
            FacetField genreFacet = response.getFacetField("genre");

            genreFacet.getValues().forEach(count -> {
                System.out.println(String.format("%s (%d)", count.getName(), count.getCount()));

            });

        }
    }


}
