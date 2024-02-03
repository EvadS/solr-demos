# SOLR base 

## link 
```http
https://solr.apache.org/downloads.html
```
*.tgz for linux 
*.zip for windows

## SOLR_HOME 
put to .bashrc
```
   SOLR_HOME="/home/evheniy/software/solr-8.11.2" 
```
update sys env
``` 
source ~/.profile
```

## Start 
move to solr dir 
```bash
 cd $SOLR_HOME
 cd bin
```
### start 
```
  ./solr start 
```
### check status 
```bash
 ./solr status  
```
### stop
```bash
 ./solr stop -all
```
link 
```bash
    http://localhost:8983/solr/#/
```
 
## Films index 
```bash
./solr create_core -c films
```

./post -c films example/films/films.json
find all
```
http://localhost:8983/solr/films/select?indent=true&q.op=OR&q=*%3A*
```

bin/solr create_core -c solr_help

bin/post -c solrhelp -filetypes html https://factorpad.com/tech/solr/index.html
