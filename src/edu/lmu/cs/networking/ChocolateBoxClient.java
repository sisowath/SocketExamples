/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lmu.cs.networking;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author samnang.suon
 */
public class ChocolateBoxClient {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket("localhost", 10010);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            String questionFromServer = input.readLine();
            String reponseAEnvoyer = JOptionPane.showInputDialog(null, questionFromServer, "Message", JOptionPane.QUESTION_MESSAGE);            
            output.println(reponseAEnvoyer);
            Color couleurAEnvoyer = null;
            while((couleurAEnvoyer = JColorChooser.showDialog(null, "Choisissez une couleur ", Color.white)) != null && couleurAEnvoyer == Color.WHITE) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir une autre couleur que le blanc.", "Info", JOptionPane.INFORMATION_MESSAGE);
            };
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(couleurAEnvoyer);
            oos.flush();
            
            input.close();
            output.close();
            oos.close();
            input = null;
            output = null;
            oos = null;
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(PasswordGeneratorClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}