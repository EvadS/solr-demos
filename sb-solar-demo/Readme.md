## Solr instance 
    -[x]SpringBoot
    -[x]HttpSolrClient 
    -[x] local solr instance is used for test

### windows 

I don't know how it should work on windows 
I used instruction from the getting started tutorial 
```
https://solr.apache.org/guide/8_11/solr-tutorial.html
```
In that case I create 2 core instance by 
```
    solr start -e cloud
```
Then create my collection with admin tool
```http
    http://localhost:8983/solr/#
```

I used bas solr.xml from  this project root directory to fix solr.xml error on startup 
