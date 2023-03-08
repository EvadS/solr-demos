


## Film index 

```
    ./solr start
```

```
    ./solr delete -c collection_name
```
## starter-book index

### create index 
```bash
 ./solr create_core -c starter-book
```
```bash
  ./post -c starter-book  starter-book.json
```


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