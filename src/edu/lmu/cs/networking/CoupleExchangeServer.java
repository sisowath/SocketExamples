package edu.lmu.cs.networking;

import java.io.*;
import java.net.*;

public class CoupleExchangeServer {
    public static void main(String args[]) throws IOException {        
        ServerSocket serverSocket = new ServerSocket(10101);
        System.out.println("Le serveur ecoute sur le port : " + serverSocket.getLocalPort());
        Socket socket = serverSocket.accept();
        System.out.println("Un client s'est connecte !");        
        OutputStream os = socket.getOutputStream();
        System.out.println("Flux crees !");       
        Person someone = new Person("Miranda", 32);
        new ObjectOutputStream(os).writeObject(someone);
        new ObjectOutputStream(os).flush();       
        os.close();
        os = null;        
        socket.close();
        serverSocket.close();
        System.out.println("Server is closed.");                           
    }
}