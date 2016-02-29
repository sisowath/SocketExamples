package edu.lmu.cs.networking;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Player implements Serializable {
        // attribut(s)
    private String name;
    private int age;
    private Color color;    
        // methode(s)
    // constructeur(s)
    public Player(String n, int a, Color c) {
        this.name = n;
        this.age = a;
        this.color = c;
    }
    // accesseur(s)
    public String getName() { return name; }
    public int getAge() { return age; }
    public Color getColor() { return color; }
    // mutateur(s)
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setColor(Color color) { this.color = color; }
    // autre(s)
    public String toString() {
        return name + " :: " + age + " :: " + color;
    }
    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeUTF(name);
        oos.writeUTF(String.valueOf(age));
        oos.writeObject(color);
    }
    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.name = ois.readUTF();
        this.age = Integer.parseInt( ois.readUTF() );
        this.color = (Color)ois.readObject();
    }
    // readObject() et writeObject() :: http://blog.paumard.org/cours/java/chap10-entrees-sorties-serialization.html
}