package com.se.sample;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Hello world!
 *
 */
public class App 
{

    HttpSolrClient solrClient;

    public static void main( String[] args )
    {
        System.out.println(System.getProperty("java.vm.name"));
        System.out.println(System.getProperty("java.vm.info"));

        System.out.println(System.getProperty("os.arch"));


        System.out.println( "Hello World!" );
    }
}
