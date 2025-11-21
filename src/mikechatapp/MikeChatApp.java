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

        String fullName = JOptionPane.showInputDialog(null, "Please enter your Name & Surname:");

        Register reg = new Register();
        reg.registerUser();
        if (!reg.loginUser()) return;

        MessageClass mc = new MessageClass(reg.getVerifiedUser());
        mc.setMessageLimit(20); // max messages
        mc.startMenu();
    }
}
      