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

    public boolean checkUserName(String username) {
        if (username == null) return false;
        // must contain underscore and be <= 5 characters (rubric)
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        // at least one digit, lower, upper, special char, min 8
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=-]).{8,}$");
    }

    public boolean checkCellPhoneNumber(String cellphone) {
        if (cellphone == null) return false;
        // South African format +27xxxxxxxxx or 0xxxxxxxxx starting 6-8
        return cellphone.matches("^(\\+27|0)[6-8][0-9]{8}$");
    }

    public void registerUser() {
        String userName, password, cell;

        do {
            userName = JOptionPane.showInputDialog(
                    "Enter username:\n- Must contain '_'\n- Max 5 characters");
        } while (!checkUserName(userName));

        do {
            password = JOptionPane.showInputDialog(
                    "Enter password:\n- Min 8 chars\n- Upper, lower, digit and special char");
        } while (!checkPasswordComplexity(password));

        do {
            cell = JOptionPane.showInputDialog("Enter cellphone number (start +27 or 0):");
        } while (!checkCellPhoneNumber(cell));

        verifiedUser = userName;
        verifiedPassword = password;
        verifiedNumber = cell;

        JOptionPane.showMessageDialog(null, "Registration Successful!");
    }

    public boolean loginUser() {
        if (verifiedUser == null) {
            JOptionPane.showMessageDialog(null, "You must register before logging in!");
            return false;
        }

        String name = JOptionPane.showInputDialog("Enter your username:");
        String pass = JOptionPane.showInputDialog("Enter your password:");

        if (name != null && pass != null && name.equals(verifiedUser) && pass.equals(verifiedPassword)) {
            JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + verifiedUser + "!");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Incorrect username or password.");
        return false;
    }

    public String getVerifiedUser() {
        return verifiedUser;
    }
}