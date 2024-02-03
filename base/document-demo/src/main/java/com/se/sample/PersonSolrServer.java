package com.se.sample;

import com.se.sample.model.Person;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class PersonSolrServer {
    private final static String URL // = "http://localhost/solr/person";
     = "http://localhost:8983/solr/person";

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static Integer SOCKE_TTIMEOUT = 1000; // socket read timeout
    private final static Integer CONN_TIMEOUT = 100;
    private final static Integer MAXCONN_DEFAULT = 100;
    private final static Integer MAXCONN_TOTAL = 100;
    private final static Integer MAXRETRIES = 1;
    private HttpSolrClient server = null;


    private final static String ASC = "asc";


    public PersonSolrServer() throws MalformedURLException {

        server = new HttpSolrClient( URL );

        server.setRequestWriter(new BinaryRequestWriter());
        server.setSoTimeout(SOCKE_TTIMEOUT);// socket read timeout
        server.setConnectionTimeout(CONN_TIMEOUT);
        server.setDefaultMaxConnectionsPerHost(MAXCONN_DEFAULT);
        server.setMaxTotalConnections(MAXCONN_TOTAL);
        server.setFollowRedirects(false);
        server.setAllowCompression(true);
      //  server.setMaxRetries(MAXRETRIES); // defaults to 0.  > 1 not recommended.
    }

    public void createIndex(Person pd) throws Exception {

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", pd.getId());
        doc.addField("name", pd.getName());
        doc.addField("remark", pd.getRemark());
        server.add(doc);
       // server.optimize();
        server.commit();
        System.out.println("----!!!");
    }

    public void delIndex() throws Exception {
        server.deleteByQuery("*:*");
        server.commit();
        System.out.println("----!!!");
    }



    public  void query() throws SolrServerException, IOException {
//        CommonsHttpSolrServer solr = new CommonsHttpSolrServer(
//                "http://localhost:8983/solr/person");

        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.addFilterQuery("name : tester1");

        QueryResponse response = server.query(query);
        SolrDocumentList results = response.getResults();

        //iterate the results
        for (int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i));
        }
    }

    public List<Integer> queryList(String key, Integer start, Integer rows) throws Exception {
        SolrQuery query = new SolrQuery(getkey(key));
        query.setHighlight(true);
        query.addHighlightField("id");
        query.addHighlightField("chName");
        query.addHighlightField("enName");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        query.set("hl.usePhraseHighlighter", true);
        query.set("hl.highlightMultiTerm", true);
        query.set("hl.snippets", 3);
        query.set("hl.fragsize", 50);
        query.setStart(start);
        query.setRows(rows);

        QueryResponse rep = server.query(query);
        List<SolrDocument> docs = rep.getResults();
        List<Integer> idList = new ArrayList<Integer>();
        for(SolrDocument doc : docs) {
            idList.add(Integer.parseInt((String) doc.getFieldValue("id")));
            System.out.println(doc.getFieldValue("chName") + "|" + doc.getFieldValue("enName"));
        }
        return idList;
    }

    public String getkey(String strWord) {
        if(strWord.indexOf(" ") > 0 ){
            String wordAnd = strWord.replace(" ", "* AND *");
            String wordOr = strWord.replace(" ", "* *");
            String rt = "(*" + wordAnd + "*) *" + wordOr + "* " + strWord;
            return rt;
        } else {
            return "*" + strWord + "* " + strWord;
        }
    }
}
