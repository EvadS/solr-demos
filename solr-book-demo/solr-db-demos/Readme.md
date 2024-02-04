

%SOLR_DIST%.

```regexp
 bin/solr start
```

create core
```
bin/solr.cmd -e chp01
```

core location 
```
\solr-8.11.2\server\solr
```


import data with post 
```
 java -Dc=chp01 -Dtype=application/json  -jar -Dc=chp01  .\se-sample\post.jar \se-sample\chp01\*.json
```

parameters
-Dc = collection name
.\se-sample\post.jar  post jar location relative to running commands
file to import 


---------------
## delete all rows 
in update handler 
<delete><query>*:*</query></delete>

## create new core 
```cmd
bin/solr.cmd  create -c pdfs 
```

что это ?
bin/solr -e schemaless

## SOLR API
http://localhost:8983/solr/chp02/schema?wt=json

/collection/schema: retrieve the entire schema
/collection/schema/fields: retrieve information about all defined fields, or create new fields with optional copyField directives
/collection/schema/fields/name: retrieve information about a named field, or create a new named field with optional copyField directives
/collection/schema/dynamicfields: retrieve information about dynamic field rules
/collection/schema/dynamicfields/name: retrieve information about a named dynamic rule
/collection/schema/fieldtypes: retrieve information about field types
/collection/schema/fieldtypes/name: retrieve information about a named field type
/collection/schema/copyfields: retrieve information about copy fields, or create new copyField directives
/collection/schema/name: retrieve the schema name
/collection/schema/version: retrieve the schema version
/collection/schema/uniquekey: retrieve the defined uniqueKey
/collection/schema/similarity: retrieve the global similarity definition
/collection/schema/solrqueryparser/defaultoperator: retrieve the default operator