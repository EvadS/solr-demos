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
    [x] start Solr
    [x] create a collection 
    [x] index some basic document

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

### to stop 
```bash
 ./bin/solr stop -all
```
### to start existing node 
```bash
    ./bin/solr start -c -p 8983 -s example/cloud/node1/solr
     ./bin/solr start -c -p 7574 -s example/cloud/node1/solr
```

### TO delete 
```bash
  bin/solr delete -c techproducts
```

## Create a New Collection
Схема Solr — это отдельный файл (в формате XML), в котором хранятся сведения о полях и типах полей,
которые Solr должен понимать.

### create collection 
!!! Before that solr should be running
```bash
    ./solr start 
```
```bash
    bin/solr create -c films -s 2 -rf 2
```
WHERE :
    -s  shard 
    -rf replica


```bash
  bin/solr delete -c films
```

cгрупировать фильмы по годам , начиная с 20 лет назад
и заканчивая сегодняшним днем. О
curl 'http://localhost:8983/solr/films/select?q=*:*&rows=0&facet=true&facet.range=initial_release_date&facet.range.start=NOW-20YEAR&facet.range.end=NOW&facet.range.gap=%2B1YEAR'


curl 'http://localhost:8983/solr/films/select?q=*:*&rows=0'\
'&facet=true'\
'&facet.range=initial_release_date'\
'&facet.range.start=NOW-20YEAR'\
'&facet.range.end=NOW'\
'&facet.range.gap=%2B1YEAR'


сколько фильмов в категории «Драма» (поле жанр_str) снято режиссером.
```http
curl "http://localhost:8983/solr/films/select?q=*:*&rows=0&facet=on&facet.pivot=genre_str,directed_by_str"
```

### delete 
```bash
   bin/solr delete -c films
```

## create your own collection 
```bash
./bin/solr create -c localDocs -s 2 -rf 2
```

./bin/post -c localDocs /home/evheniy/Documents/

### Start Solr with a Specific Bundled Example
```
    bin/solr -e techproducts
```


https://solr.apache.org/guide/8_11/solr-configuration-files.html

```
    bin/solr create -c bookCollection
```
```bash
   bin/solr delete -c bookCollection
```
-------------

```
  ./solr start
```
http://localhost:8983/solr/admin/cores?action=create&name=search_twitter&instanceDir=configsets/search_twitter

http://localhost:8983/solr/search_twitter/schema

Post 


http://localhost:8983/solr/search_twitter/update


curl -X POST -H  'Content-Type: application/json' 'http://localhost:8983/solr/search_twitter/update' --data-binary '
{
    "add":{
        "doc":{
            "content":"Late night2",
            "likes":10
        }
    }
}'