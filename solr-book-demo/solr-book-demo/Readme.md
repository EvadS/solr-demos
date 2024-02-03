## 

## Technologies 
     * [solr](https://solr.apache.org/downloads.html) Solr 8.11
     *  maven 
     * java 1.8
     * junit 4 
     * solrj
     * object mapper 
    * log4j


## How to start 
move to solr dir (SOLR_HOME)

```bash
bin/solr.cmd start
```

## book core create core 
```bash
  bin/solr.cmd create -c book_core  -p 8983

```

stop all solr 
```bash
 bin/solr.cmd stop -all 
```

put on
  * managed-schema 
  * schema.xml
to created core

## Aditional links 
https://livebook.manning.com/book/solr-in-action/chapter-14/33


все документі у которых не заполнено поле longDescription
// facetquery =!longDescription:[* TO *]