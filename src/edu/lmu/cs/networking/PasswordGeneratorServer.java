package edu.lmu.cs.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samnang.suon
 */
public class PasswordGeneratorServer {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10000);
            System.out.println("Le serveur est a l'ecoute...");
            while(true) {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);                
                Random random = new Random();
                String password = "";
                for(int i=0; i < 4; i++) {
                    int chiffre = random.nextInt((57-48)+1) + 48;
                    System.out.println(chiffre + " ");
                    int lettre = random.nextInt((90-65)+1) + 65;
                    System.out.println(lettre + " ");
                    password += (char)chiffre; password += (char)lettre;// source de : http://stackoverflow.com/questions/5328996/java-change-int-to-ascii                    
                }
                out.println(password);
                System.out.println(password);
                socket.close();                
            }
        } catch (IOException ex) {
            Logger.getLogger(PasswordGeneratorServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            serverSocket.close();
        }        
    }
}