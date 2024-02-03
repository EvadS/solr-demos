package com.se.sample;

import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeQuery {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeQuery.class);
    private final SolrQuery _query;

    public EmployeeQuery(){
        this._query = new SolrQuery();
        this._query.setQuery("{!join from=employee-id fromIndex=department to=id}{!v=$qq}");
    }
    public EmployeeQuery setEmployeeId(String empId) {
        if (empId != null) {
            this._query.add("qq", "id:"+ empId);
        }
        return this;
    }

    public SolrQuery toQuery() {
        LOGGER.debug("query {}", this._query.toQueryString());
        return  this._query;
    }
}
