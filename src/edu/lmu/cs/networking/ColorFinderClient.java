package edu.lmu.cs.networking;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

public class ColorFinderClient {
        // attribut(s)
    
        // methode(s)
    // constructeur(s)

    // accesseur(s)

    // mutateur(s)

    // autre(s)
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost", 10200);
        System.out.println("Connexion etablie !");
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        Color couleur = JColorChooser.showDialog(null, "Choisi une couleur", Color.WHITE);
        out.writeObject(couleur);
        out.flush();
        System.out.println("Couleur envoyee !");
        JOptionPane.showMessageDialog(null, "Couleur envoye !", "Info", JOptionPane.INFORMATION_MESSAGE);
        
        out.close();
        out = null;
        socket.close();
    }
}