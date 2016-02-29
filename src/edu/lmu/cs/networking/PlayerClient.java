package edu.lmu.cs.networking;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerClient {
        // attribut(s)
    
        // methode(s)
    // constructeur(s)

    // accesseur(s)

    // mutateur(s)

    // autre(s)
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 10300);
        System.out.println("Connexion etablie !");
        //ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Les flux sont crees.");
        Player unJoueur = new Player("Samnang", 23, Color.YELLOW);
        //out.writeObject( unJoueur );
        unJoueur.writeObject(new ObjectOutputStream(socket.getOutputStream()));
        System.out.println("Player sent !");
        unJoueur.readObject(new ObjectInputStream(socket.getInputStream()));
        System.out.println("Player received from server : " + unJoueur);
        System.out.println("Message from server : " + new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine());
        //in.close();
        //out.close();
        socket.close();
        //in = null;
        //out = null;
    }
}