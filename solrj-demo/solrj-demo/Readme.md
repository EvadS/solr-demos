# Guide To Solr in Java With Apache SolrJ

## Solar 


## Run solr
Move to solr bin folder 
specify 2 nodes 
```
    .\solr start -e cloud 
```

```
    bin/solr create -c 'bigboxstore'
```


## Tutorial
https://www.baeldung.com/apache-solrj

Solr Ref Guide 8.1
https://solr.apache.org/guide/8_1/solr-tutorial.html



## Solr notices 
admin page
```
    http://localhost:8983/solr/#/
```
### cloud tab
```http 
http://localhost:8983/solr/#/~cloud
```
### First tutorial
    [] start Solr
    [] create a collection 
    [] index some basic document

#### how to start Solr as a two-node cluster 
```bash
./solr start -e cloud
```
how many Solr nodes would you like to run in your local cluster? 
2
enter <b>techproducts<b>  as collection name

every times uses default value 
```bash
Please choose a configuration for the techproducts collection, available options are:
_default or sample_techproducts_configs [_default]
```
sample_techproducts_configs

to start node 
```
"C:\javasoftware\solr-8.11.2\bin\solr.cmd" start -cloud -p 7574 -s "C:\javasoftware\solr-8.11.2\example\cloud\node2\solr" -z l
ocalhost:9983


```

#### stop nodes 
```bash
 .\solr stop -all
```

### Index the Techproducts Data
data we will index is in the example/exampledocs directory.

```
bin/post -c techproducts example/exampledocs/*
```
#### Windows
i use bash 
```bash
  java -jar -Dc=techproducts -Dauto example\exampledocs\post.jar example\exampledocs\*
```
## search by signgle term
http://localhost:8983/solr/techproducts/select?q=foundation
----------
in powershell
```bash
   ./bin/solr start -c -p 7574 -s example/cloud/node2/solr -z localhost:9983
```

хз

bin/solr create -c films -s 2 -rf 2




https://solr.apache.org/guide/8_11/solr-tutorial.html