/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mikechatapp;

import javax.swing.JOptionPane;

/**
 *
 * @author Mothabeleng
 */
public class Register {
    private String verifiedUser;
    private String verifiedPassword;
    private String verifiedNumber;

    // Username validation
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Username must contain '_' and be <=5 characters.");
            return false;
        }
    }

    // Password validation
    public boolean checkPasswordComplexity(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=-])(?=\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(null, "Password successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Password must be 8+ chars, include capital letter, number, and special character.");
            return false;
        }
    }

    // Cellphone validation
    public boolean checkCellPhoneNumber(String cellphone) {
        if (cellphone.matches("^(\\+27|0)[6-8][0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added");
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Cellphone must start with +27 or 0 and be 10 digits long.");
            return false;
        }
    }

    // Registration
    public void registerUser() {
        String userName, userPassword, userCellNumber;

        do { userName = JOptionPane.showInputDialog("Enter username:"); } 
        while (!checkUserName(userName));

        do { userPassword = JOptionPane.showInputDialog("Enter password:"); } 
        while (!checkPasswordComplexity(userPassword));

        do { userCellNumber = JOptionPane.showInputDialog("Enter cell number:"); } 
        while (!checkCellPhoneNumber(userCellNumber));

        verifiedUser = userName;
        verifiedPassword = userPassword;
        verifiedNumber = userCellNumber;

        JOptionPane.showMessageDialog(null, "Registration Successful!");
    }

    // Login
    public boolean loginUser() {
        if (verifiedUser == null || verifiedPassword == null) {
            JOptionPane.showMessageDialog(null, "You need to register first!");
            return false;
        }

        String userName = JOptionPane.showInputDialog("Enter username:");
        String userPassword = JOptionPane.showInputDialog("Enter password:");

        if (userName.equals(verifiedUser) && userPassword.equals(verifiedPassword)) {
            JOptionPane.showMessageDialog(null, "Login successful! Welcome to ChatApp.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login failed! Incorrect credentials.");
            return false;
        }
    }
}