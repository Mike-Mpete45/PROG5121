/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mikechatapp;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mothabeleng
 */
public class MessageClass {

    private boolean loggedIn;
    private int messageLimit;
    private int messagesSentCount = 0;
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();
    private Random rand = new Random();

    public MessageClass(boolean loggedIn) { this.loggedIn = loggedIn; }
    public void setMessageLimit(int limit) { this.messageLimit = limit; }

    public boolean checkMessageID(long messageID) { return Long.toString(messageID).length() <= 10; }

    public boolean checkRecipientCell(String recipient) {
        return (recipient.startsWith("+") || recipient.startsWith("0"))
                && recipient.length() <= 10 && recipient.substring(1).matches("\\d+");
    }

    public String createMessageHash(long messageID, int messageNumber, String content) {
        String firstTwo = Long.toString(messageID).substring(0,2);
        String[] words = content.split("\\s+");
        String firstWord = words.length >= 1 ? sanitizeWord(words[0]) : "";
        String lastWord = words.length >= 1 ? sanitizeWord(words[words.length-1]) : "";
        return firstTwo + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }

    private String sanitizeWord(String w) { return w.replaceAll("^\\W+|\\W+$",""); }

    public void sendMessage() {
        if (!loggedIn) { JOptionPane.showMessageDialog(null,"You must log in to send messages."); return; }
        if (messagesSentCount >= messageLimit) { JOptionPane.showMessageDialog(null,"Message limit reached!"); return; }

        int remaining = messageLimit - messagesSentCount;
        String s = JOptionPane.showInputDialog("You can send up to "+remaining+" messages. How many now?");
        if (s==null) return;

        int toSend;
        try { toSend = Integer.parseInt(s.trim()); } 
        catch(NumberFormatException e) { JOptionPane.showMessageDialog(null,"Invalid number."); return; }
        if(toSend>remaining) toSend=remaining;

        for(int i=0;i<toSend;i++){
            long messageID = generateTenDigitId();
            int messageNumber = messagesSentCount+1;

            String recipient = JOptionPane.showInputDialog("Enter recipient phone number (+ or 0):");
            if(recipient==null || !checkRecipientCell(recipient)){
                JOptionPane.showMessageDialog(null,"Invalid recipient. Must start with + or 0 and <=10 chars."); continue;
            }

            String content = JOptionPane.showInputDialog("Enter message (max 250 chars):");
            if(content==null || content.length()>250){ JOptionPane.showMessageDialog(null,"Please enter a message of less than 250 characters."); continue; }

            String messageHash = createMessageHash(messageID,messageNumber,content);
            Message msg = new Message(messageID,messageNumber,recipient,content,messageHash);

            String[] options={"Send Message","Disregard Message","Store Message"};
            int choice = JOptionPane.showOptionDialog(null,"Choose an action for this message:","Message Options",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);

            if(choice==0){ // Send
                sentMessages.add(msg); messagesSentCount++;
                storeMessages(sentMessages,"messages.json");
                JOptionPane.showMessageDialog(null,
                        "Message Sent!\n\n"+
                        "Message ID: "+msg.getMessageID()+"\n"+
                        "Message Hash: "+msg.getMessageHash()+"\n"+
                        "Recipient: "+msg.getRecipient()+"\n"+
                        "Message: "+msg.getContent()
                );
            } else if(choice==1){ JOptionPane.showMessageDialog(null,"Message disregarded."); }
            else if(choice==2){ storedMessages.add(msg); storeMessages(storedMessages,"stored_messages.json"); JOptionPane.showMessageDialog(null,"Message stored for later."); }

            if(messagesSentCount>=messageLimit){
                JOptionPane.showMessageDialog(null,"Message limit reached.\nTotal messages sent: "+messagesSentCount);
                break;
            }
        }
    }

    public void printMessages() {
        if(sentMessages.isEmpty()){ JOptionPane.showMessageDialog(null,"No messages sent yet."); return; }
        StringBuilder sb = new StringBuilder();
        for(Message m: sentMessages){
            sb.append("Message ID: ").append(m.getMessageID()).append("\n")
              .append("Message Hash: ").append(m.getMessageHash()).append("\n")
              .append("Recipient: ").append(m.getRecipient()).append("\n")
              .append("Message: ").append(m.getContent()).append("\n----------------\n");
        }
        JOptionPane.showMessageDialog(null,sb.toString());
    }

    public int returnTotalMessages(){ return messagesSentCount; }

    private long generateTenDigitId(){ return (long)(rand.nextDouble()*9_000_000_000L)+1_000_000_000L; }

    public void storeMessages(ArrayList<Message> list, String filename){
        try(FileWriter fw=new FileWriter(filename)){
            fw.write("[\n");
            for(int i=0;i<list.size();i++){
                Message m = list.get(i);
                fw.write("  {\n");
                fw.write("    \"messageID\": \""+m.getMessageID()+"\",\n");
                fw.write("    \"messageNumber\": "+m.getMessageNumber()+",\n");
                fw.write("    \"recipient\": \""+escapeJson(m.getRecipient())+"\",\n");
                fw.write("    \"content\": \""+escapeJson(m.getContent())+"\",\n");
                fw.write("    \"messageHash\": \""+escapeJson(m.getMessageHash())+"\"\n");
                fw.write("  }"+(i<list.size()-1?",":"")+"\n");
            }
            fw.write("]\n");
        } catch(IOException e){ JOptionPane.showMessageDialog(null,"Error saving messages: "+e.getMessage()); }
    }

    private String escapeJson(String s){
        if(s==null) return "";
        return s.replace("\\","\\\\").replace("\"","\\\"").replace("\n","\\n");
    }
}