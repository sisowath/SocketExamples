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
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author samnang.suon
 */
public class SurpriseBoxClient {
    Color maCouleur;
    String monIdentifiant;
    PrintWriter out;    
    ActionListener ecouteurBoutons = new EcouteurBoutons();
    JButton mesBoutons[] = new JButton[64];
    
    public SurpriseBoxClient(Color c, String s) {
        this.maCouleur = c;
        this.monIdentifiant = s;
        
        JFrame frame = new JFrame("SurpriseBoxClient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(8, 8));        
        for(int i=0; i < 64; i++) {
            JButton button = new JButton("Case " + (i+1));            
            button.setActionCommand(String.valueOf(i+1));
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.setForeground(Color.BLACK);
            mesBoutons[i] = button;
            /*mesBoutons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Un bouton a ete clique !" , "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            La maniere d'implementer l'ActionListener ici est utile seulement 
            lorsqu'on veut avoir le meme action ou message pour un groupe de composants;
            */
            mesBoutons[i].addActionListener( ecouteurBoutons );
            panel.add(mesBoutons[i]);
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        // source de : http://stackoverflow.com/questions/3718435/refresh-jframe-after-adding-new-components
    }
    public void run() {
        try {
            Socket socket = new Socket("localhost", 10100);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);            
            (new Adversaire(this)).start();
            while(true) {
                int n = Integer.parseInt( in.readLine() );
                this.mesBoutons[n].setBackground(Color.PINK);
            }            
        } catch (IOException ex) {
            Logger.getLogger(SurpriseBoxClient.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public static void main(String args[]) {
        String unNom = JOptionPane.showInputDialog(null, "Veuillez saisir un nom de joueur : ", "Question", JOptionPane.QUESTION_MESSAGE);
        Color uneCouleur = JColorChooser.showDialog(null, "Veuillez choisir une couleur", Color.yellow);                
        SurpriseBoxClient sc = new SurpriseBoxClient(uneCouleur, unNom);
        sc.run();
    }
    class EcouteurBoutons implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*String action = e.getActionCommand();
            JOptionPane.showMessageDialog(null, "Le bouton #" + action + " a ete clique !", "Info", JOptionPane.INFORMATION_MESSAGE);*/
            Random random = new Random();
            int operande1 = random.nextInt((10-0)+1) + 1;
            int operande2 = random.nextInt((10-0)+1) + 1;// source de : http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
            int reponse = 0;
            int resultat = 0;
            switch(random.nextInt((3-1) + 1) + 1) {
                case 1 :
                    reponse = Integer.parseInt( JOptionPane.showInputDialog(null, operande1 + " + " + operande2 + " = ? ", "Question", JOptionPane.QUESTION_MESSAGE) );
                    resultat = operande1 + operande2;
                    break;
                case 2 :
                    reponse = Integer.parseInt( JOptionPane.showInputDialog(null, operande1 + " - " + operande2 + " = ? ", "Question", JOptionPane.QUESTION_MESSAGE) );
                    resultat = operande1 - operande2;
                    break;
                case 3 :
                    reponse = Integer.parseInt( JOptionPane.showInputDialog(null, operande1 + " * " + operande2 + " = ? ", "Question", JOptionPane.QUESTION_MESSAGE) );
                    resultat = operande1 * operande2;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "WTF!!!", "Message", JOptionPane.INFORMATION_MESSAGE);                    
            }
            if(resultat == reponse) {
                JOptionPane.showMessageDialog(null, "BRAVO !!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                JButton btn = (JButton)e.getSource();
                btn.setBackground(maCouleur);
                //out.println(e.getActionCommand());
            } else {
                JOptionPane.showMessageDialog(null, "ECHEC !!", "Fail", JOptionPane.ERROR_MESSAGE);
                JButton btn = (JButton)e.getSource();
                //btn.setEnabled(false);//source de : http://stackoverflow.com/questions/13183920/how-to-grey-out-jbutton-in-java
            }
        }        
    }
    class Adversaire extends Thread {        
        SurpriseBoxClient sbc;
        public Adversaire(SurpriseBoxClient s) {
            this.sbc = s;
        }
        public void run() {
            while(true) {
                try {
                    boolean b = true;
                    if(b) {
                        this.sbc.mesBoutons[(new Random()).nextInt(64)].setBackground(Color.PINK);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SurpriseBoxClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
