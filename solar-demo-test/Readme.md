
# SOLR embedded server tutorial 
## Admin page 
```http
http://localhost:8983/solr/#/starter-book/query?q=a3&q.op=OR&indent=true&df=author
```

## Film index 

```
    ./solr start
```
### delete
```
    ./solr delete -c collection_name
```
## starter-book index
### create index 
```bash
 ./solr create_core -c starter-book
```
#### put the data 
```bash
  ./post -c starter-book starter-book.json
```


## Tutorials

https://factorpad.com/tech/solr/tutorial/solr-fields.html

Reference
https://factorpad.com/tech/solr/reference/solr-field-types.html
https://factorpad.com/tech/solr/reference/solr-field-type-properties.html

```
<schema>
  <types>
    <fieldType>
  <fields>
    <field>
  <copyField>
  <dynamicField>
  <similarity>
  <uniqueKey>
</schema>
```