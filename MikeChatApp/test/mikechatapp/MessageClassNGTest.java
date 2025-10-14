/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package mikechatapp;

import java.util.ArrayList;
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
public class MessageClassNGTest {
    
    public MessageClassNGTest() {
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

    /**
     * Test of setMessageLimit method, of class MessageClass.
     */
    @Test
    public void testSetMessageLimit() {
        System.out.println("setMessageLimit");
        int limit = 0;
        MessageClass instance = null;
        instance.setMessageLimit(limit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageID method, of class MessageClass.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        long messageID = 0L;
        MessageClass instance = null;
        boolean expResult = false;
        boolean result = instance.checkMessageID(messageID);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class MessageClass.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String recipient = "";
        MessageClass instance = null;
        boolean expResult = false;
        boolean result = instance.checkRecipientCell(recipient);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class MessageClass.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        long messageID = 0L;
        int messageNumber = 0;
        String content = "";
        MessageClass instance = null;
        String expResult = "";
        String result = instance.createMessageHash(messageID, messageNumber, content);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class MessageClass.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        MessageClass instance = null;
        instance.sendMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class MessageClass.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        MessageClass instance = null;
        instance.printMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class MessageClass.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        MessageClass instance = null;
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeMessages method, of class MessageClass.
     */
    @Test
    public void testStoreMessages() {
        System.out.println("storeMessages");
        ArrayList<Message> list = null;
        String filename = "";
        MessageClass instance = null;
        instance.storeMessages(list, filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
