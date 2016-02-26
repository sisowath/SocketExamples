package edu.lmu.cs.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author samnang.suon
 */
public class PasswordGeneratorClient {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket("localhost", 10000);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mdp = input.readLine();
            JOptionPane.showMessageDialog(null, "Mot de passe recu du serveur : " + mdp, "Message", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(PasswordGeneratorClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
