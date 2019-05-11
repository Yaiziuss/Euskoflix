package Interfaces;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.*;

import Euskoflix.CargadorDatos;

public class SplashScreen extends JWindow {

    static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
    private static SplashScreen execute;
    private static int count;
    private static Timer timer1;

    public SplashScreen(){
        Container container = getContentPane();
        container.setLayout(null);             
        progressBar.setMaximum(50);
        progressBar.setBounds(55, 180, 250, 15);
        container.add(progressBar);
        loadProgressBar();
        setSize(370, 215);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("Cargando EuskoFlix, un momento...");
        label.setBounds(40, 69, 280, 30);
        getContentPane().add(label);
        label.setForeground(Color.RED);
        label.setFont(new Font("Verdana", Font.BOLD, 14));
        
        JLabel lblAutoresYaizaAlfredo = new JLabel("Autores: Yaiza, Alfredo y Aitor");
        lblAutoresYaizaAlfredo.setBounds(86, 115, 278, 16);
        getContentPane().add(lblAutoresYaizaAlfredo);

        
        setVisible(true);
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;
                progressBar.setValue(count);
                //System.out.println(count);
                if (count == 120) {
                    //execute.setVisible(false);
                    timer1.stop();
                }
            }
        };
        timer1 = new Timer(50, al);
        timer1.start();
    }

    public static void main(String[] args) { 
        execute = new SplashScreen();
    }
};