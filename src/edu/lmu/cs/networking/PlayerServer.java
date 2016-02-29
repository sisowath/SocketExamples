package edu.lmu.cs.networking;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayerServer {
        // attribut(s)
    
        // methode(s)
    // constructeur(s)

    // accesseur(s)

    // mutateur(s)

    // autre(s)
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(10300);
        System.out.println("Le serveur ecoute sur le port #" + serverSocket.getLocalPort());
        Socket socket = serverSocket.accept();
        System.out.println("Un client s'est connecte !");
        //ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Les flux sont crees.");
        Player unJoueur = new Player("", 1, Color.BLACK);
        unJoueur.readObject(new ObjectInputStream(socket.getInputStream()));
        System.out.println("Player received from client : " + unJoueur);
        unJoueur.setAge(32);
        unJoueur.setColor(Color.PINK);
        unJoueur.setName("Miranda");
        unJoueur.writeObject(new ObjectOutputStream(socket.getOutputStream()));
        System.out.println("Player sent back to client.");
        PrintWriter os = new PrintWriter(socket.getOutputStream());
        os.println("THANK YOU !");
        os.flush();
        
        os.close();
        os = null;
        //in.close();
        //out.close();
        socket.close();
        //in = null;
        //out = null;
    }
}