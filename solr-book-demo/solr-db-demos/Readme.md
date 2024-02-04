

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