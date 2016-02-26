package edu.lmu.cs.networking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author samnang.suon
 */
public class ChocolateBoxServer {
    public static void main(String args[]) throws IOException {
        JFrame frame = new JFrame("ChocolateBoxServer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3, 3));
        JButton mesBoutons[] = new JButton[9];
        for(int i=0; i < 9; i++) {
            mesBoutons[i] = new JButton("Case " + (i+1));
            mesBoutons[i].setActionCommand(String.valueOf(i+1));            
            panel.add(mesBoutons[i]);
        }
        frame.add(panel, BorderLayout.CENTER);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10010);
            System.out.println("Le serveur est a l'ecoute...");                        
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Un client s'est connecté !");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);                
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("Veuillez choisir un nombre entre 1 et 9 : ");
                String reponseClient = in.readLine();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Color couleurClient = (Color) ois.readObject();
                switch(Integer.parseInt(reponseClient)) {
                    case 1 :
                    case 2 :
                    case 3 :
                    case 4 :
                    case 5 :
                    case 6 :
                    case 7 :
                    case 8 :
                    case 9 :    mesBoutons[Integer.parseInt(reponseClient)-1].setBackground(couleurClient);
                                mesBoutons[Integer.parseInt(reponseClient)-1].setFont(new Font("Arial", Font.BOLD, 14));
                                mesBoutons[Integer.parseInt(reponseClient)-1].setForeground(Color.WHITE);// source de : 
                                break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PasswordGeneratorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChocolateBoxServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            serverSocket.close();
        }        
    }
}
