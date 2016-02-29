package edu.lmu.cs.networking;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoupleExchangeClient {
    public static void main(String args[]) throws IOException {
        InputStream is = null;;
        Socket socket = null;        
        socket = new Socket("localhost", 10101);
        is = socket.getInputStream();
        System.out.println("Flux crees !");
        System.out.println("Demande envoye au serveur.");
        Person answerFromServer = null;
        try {
            answerFromServer = (Person) new ObjectInputStream(is).readObject();
            System.out.println("Person received : " + answerFromServer);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoupleExchangeClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
        is.close();
        is = null;
        socket.close();
    }    
}