package edu.lmu.cs.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samnang.suon
 */
public class SurpriseBoxServer {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10100);
            System.out.println("Le serveur est a l'ecoute...");      
            Socket socket = serverSocket.accept();
            System.out.println("Un client s'est connecté !");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);                
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                
            }
        } catch (IOException ex) {
            Logger.getLogger(PasswordGeneratorServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            serverSocket.close();
        }        
    }
}
