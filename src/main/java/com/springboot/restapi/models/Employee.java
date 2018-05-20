package com.springboot.restapi.models;

/**
 * Created by Vinit Badrike on 5/18/2018.
 */
public class Employee {

    private long id;

    private String name;

    public Employee(){
        id=0;
    }

    public Employee(long id, String name, int age, double salary){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name +"]";
    }


}
