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

    @Test
    public void testSetMessageLimit() {
        System.out.println("setMessageLimit");
        int limit = 0;
        MessageClass instance = null;
        instance.setMessageLimit(limit);
        
    }

    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String recipient = "";
        MessageClass instance = null;
        boolean expResult = false;
        boolean result = instance.checkRecipientCell(recipient);
        assertEquals(result, expResult);
        
    }

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
        
    }

    @Test
    public void testAddMessageWhileLoop() {
        System.out.println("addMessageWhileLoop");
        MessageClass instance = null;
        instance.addMessageWhileLoop();
        
    }

    @Test
    public void testAddMessageForLoop() {
        System.out.println("addMessageForLoop");
        int batchCount = 0;
        MessageClass instance = null;
        instance.addMessageForLoop(batchCount);
        
    }

    @Test
    public void testDisplayAllSentMessages() {
        System.out.println("displayAllSentMessages");
        MessageClass instance = null;
        instance.displayAllSentMessages();
        
    }

    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        MessageClass instance = null;
        instance.displayLongestMessage();
        
    }

    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        MessageClass instance = null;
        instance.searchByMessageID();
        
    }

    @Test
    public void testDisplayMessagesForRecipient() {
        System.out.println("displayMessagesForRecipient");
        MessageClass instance = null;
        instance.displayMessagesForRecipient();
        
    }

    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        MessageClass instance = null;
        instance.deleteMessageByHash();
        
    }

    @Test
    public void testDisplayReport() {
        System.out.println("displayReport");
        MessageClass instance = null;
        instance.displayReport();
        
    }

    @Test
    public void testStartMenu() {
        System.out.println("startMenu");
        MessageClass instance = null;
        instance.startMenu();
        
    }
    
}
