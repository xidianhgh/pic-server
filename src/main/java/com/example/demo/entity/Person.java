package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MI on 2019/2/19.
 */
//@ConfigurationProperties(prefix = "person")
@Component
@Entity
@Table(name = "person_d")
public class Person {
    private Integer id;
    @Value("${person.name2}")
    private String name;
    @Value("${person.age}")
    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
