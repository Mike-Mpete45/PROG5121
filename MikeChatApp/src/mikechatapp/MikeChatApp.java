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

        JOptionPane.showMessageDialog(null,"Welcome to QuickChat!");

        Register register = new Register();
        boolean isLoggedIn = false;

        String[] options={"Register","Login","Exit"};
        int choice = JOptionPane.showOptionDialog(null,"Please choose an option:","QuickChat Menu",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);

        if(choice==0){ register.registerUser(); isLoggedIn=register.loginUser(); }
        else if(choice==1){ isLoggedIn=register.loginUser(); }
        else{ JOptionPane.showMessageDialog(null,"Thank you for using QuickChat!"); System.exit(0); }

        MessageClass messageClass = new MessageClass(isLoggedIn);

        // Message limit
        int messageLimit=0;
        while(messageLimit<=0){
            String input = JOptionPane.showInputDialog("Enter number of messages you want to send:");
            if(input==null) System.exit(0);
            try{ messageLimit=Integer.parseInt(input.trim()); }
            catch(Exception e){ JOptionPane.showMessageDialog(null,"Enter a valid number."); }
        }
        messageClass.setMessageLimit(messageLimit);

        boolean running=true;
        while(running){
            String menu="Please choose an option:\n1) Send Messages\n2) View Sent Messages\n3) Quit";
            String option=JOptionPane.showInputDialog(menu);
            if(option==null) break;
            switch(option){
                case "1" -> messageClass.sendMessage();
                case "2" -> JOptionPane.showMessageDialog(null,"Coming soon.");
                case "3" -> { JOptionPane.showMessageDialog(null,"Goodbye!"); running=false; }
                default -> JOptionPane.showMessageDialog(null,"Invalid choice, please try again.");
            }
        }
    }
}