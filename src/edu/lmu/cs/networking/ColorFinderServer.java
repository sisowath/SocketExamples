package edu.lmu.cs.networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorFinderServer {
        // attribut(s)
    
        // methode(s)
    // constructeur(s)
    
    // accesseur(s)

    // mutateur(s)

    // autre(s)
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(10200);
        System.out.println("Le serveur est a l'ecoute sur le port #" + serverSocket.getLocalPort());
        Socket socket = serverSocket.accept();
        System.out.println("Un client s'est connecte !");
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        System.out.println("Les flux sont crees.");
        Color couleur = (Color)in.readObject();
        System.out.println("La couleur recu est : " + couleur.toString()); 
        JFrame frame = new JFrame("Couleur recue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(couleur);
        frame.add(panel, BorderLayout.CENTER);
                
        in.close();
        out.close();
        in = null;
        out = null;
        socket.close();
    }
}