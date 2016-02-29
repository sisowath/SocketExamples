package edu.lmu.cs.networking;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    public Person(String n, int a) {
        this.name = n;
        this.age = a;
    }
    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public String toString() {
        return name + " " + age;
    }
}