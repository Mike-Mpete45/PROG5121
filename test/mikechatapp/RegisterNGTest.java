/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package mikechatapp;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Mothabeleng
 */
public class RegisterNGTest {
    
    public RegisterNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "";
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "";
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellphone = "";
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Register instance = new Register();
        instance.registerUser();
        fail("The test case is a prototype.");
    }

    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.loginUser();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetVerifiedUser() {
        System.out.println("getVerifiedUser");
        Register instance = new Register();
        String expResult = "";
        String result = instance.getVerifiedUser();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }
    
}
