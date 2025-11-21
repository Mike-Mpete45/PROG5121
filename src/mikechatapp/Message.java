/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mikechatapp;

/**
 *
 * @author Mothabeleng
 */
public class Message {
  private long messageID;
    private String messageHash;
    private int messageNumber;
    private String recipient;
    private String content;

    public Message(long messageID, int messageNumber, String recipient, String content, String messageHash) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.content = content;
        this.messageHash = messageHash;
    }

    public long getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }

    @Override
    public String toString() {
        return "Message #" + messageNumber + " | ID: " + messageID + " | To: " + recipient + " | Content: " + content;
    }
}