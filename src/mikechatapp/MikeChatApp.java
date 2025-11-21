/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mikechatapp;

import javax.swing.JOptionPane;

/**
 *
 * @author Mothabeleng
 */
public class MikeChatApp {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {

   JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        // Ask user for full name (optional)
        String fullName = JOptionPane.showInputDialog(null, "Please enter your Name & Surname:");

        // Create register and run registration/login
        Register reg = new Register();
        reg.registerUser();
        if (!reg.loginUser()) return;

        // Start messaging system for verified user
        MessageClass mc = new MessageClass(reg.getVerifiedUser());
        mc.setMessageLimit(20); // rubric requires a message limit
        mc.startMenu();
    }
}
      