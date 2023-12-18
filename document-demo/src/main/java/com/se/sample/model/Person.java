package com.se.sample.model;

public class Person {
    private long id;
    private  String name;
    private String remark;
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getRemark() {
        return remark;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Person() {
    }

    /**
     *
     * @param id
     * @param name
     * @param remark
     */
    public Person(long id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
}
