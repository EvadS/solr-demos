
## Create core
```cmd
%SOLR_HOME%/bin/start 
```
```cmd
 solr create -c films 
```

Get initial data from 
```cmd
%SOLR_HOME%\example\films
```

## fix name field

find <b>managed-schema</b> file in films/config 
change name field type like this 
```
<field name="name" type="text_general" multiValued="false" stored="true"/>
``` 