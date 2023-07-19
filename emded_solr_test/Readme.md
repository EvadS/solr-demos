# Embedded solr for testing 

open command line in solr home folder 
move to bin directory

Windows 
```bash
 solr start
```

After server has started run the next command to create new core 
```bash
 solr create_core -c book-core
```

then may be checked in admin tool 
```
 http://localhost:8983/solr/#
```


```bash
 solr stop 
```

remove conf folder in 
```
    $SOLR_HOME\book-core\conf
```
put config folder from test resource folder 
```
\src\test\resources\configsets\books
```
