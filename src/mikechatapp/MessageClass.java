/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mikechatapp;

import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mothabeleng
 */
public class MessageClass {

  private String sender;
    private int messageLimit = 20;
    private int messagesSentCount = 0;

    // Primary storage of objects
    private final ArrayList<Message> sentMessages = new ArrayList<>();

    // Parallel arrays (rubric requirement)
    private final ArrayList<Long> messageIDs = new ArrayList<>();
    private final ArrayList<String> messageHashes = new ArrayList<>();
    private final ArrayList<Integer> messageNumbers = new ArrayList<>();
    private final ArrayList<String> recipients = new ArrayList<>();
    private final ArrayList<String> contents = new ArrayList<>();

    private final Random rand = new Random();
    private final JSONStorage storage = new JSONStorage("messages.json");

    public MessageClass(String sender) {
        this.sender = sender;
        // Load previously saved messages (rubric: read JSON into array)
        ArrayList<Message> loaded = storage.loadMessages();
        if (!loaded.isEmpty()) {
            for (Message m : loaded) addMessageToMemory(m, false);
            // messagesSentCount will be set by addMessageToMemory
        }
    }

    public void setMessageLimit(int limit) { this.messageLimit = limit; }

    // Validate recipient e.g. +27xxxxxxxxx or 0xxxxxxxxx
    public boolean checkRecipientCell(String recipient) {
        if (recipient == null) return false;
        if (!(recipient.startsWith("+") || recipient.startsWith("0"))) return false;
        if (recipient.startsWith("+")) return recipient.length() == 12 && recipient.substring(1).matches("\\d+");
        return recipient.length() == 10 && recipient.substring(1).matches("\\d+");
    }

    private String sanitizeWord(String w) { return w == null ? "" : w.replaceAll("^\\W+|\\W+$", ""); }

    /**
     * Create message hash using parts of ID, messageNumber and first+last words of content.
     * This uses substring and string manipulation as requested by the rubric.
     */
    public String createMessageHash(long messageID, int messageNumber, String content) {
        String idStr = Long.toString(messageID);
        String firstTwo = idStr.length() >= 2 ? idStr.substring(0,2) : idStr;
        String[] words = content.split("\\s+");
        String firstWord = words.length > 0 ? sanitizeWord(words[0]) : "";
        String lastWord = words.length > 0 ? sanitizeWord(words[words.length - 1]) : "";
        return firstTwo + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }

    /**
     * Generate a 10-digit-ish ID using currentTimeMillis + random and then substring.
     * This demonstrates string manipulation followed by substring to meet rubric.
     */
    private long generateTenDigitId() {
        // base string from time + random
        String base = Long.toString(System.currentTimeMillis()) + Integer.toString(rand.nextInt(9999));
        // ensure it's long enough; take rightmost 10 characters when possible
        String s = base.length() >= 10 ? base.substring(base.length() - 10) : String.format("%010d", Long.parseLong(base));
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            // fallback (should not normally happen)
            return Math.abs(rand.nextLong()) % 1_000_000_0000L;
        }
    }

    // Helper that centralises adding message to both object list and parallel arrays
    private void addMessageToMemory(Message m, boolean saveAfter) {
        sentMessages.add(m);
        messageIDs.add(m.getMessageID());
        messageHashes.add(m.getMessageHash());
        messageNumbers.add(m.getMessageNumber());
        recipients.add(m.getRecipient());
        contents.add(m.getContent());
        messagesSentCount = sentMessages.size();
        if (saveAfter) saveMessagesSafe();
    }

    // Add message using a while loop (rubric requires while loop)
    public void addMessageWhileLoop() {
        if (messagesSentCount >= messageLimit) {
            JOptionPane.showMessageDialog(null, "Message limit reached.");
            return;
        }
        while (messagesSentCount < messageLimit) {
            String rec = JOptionPane.showInputDialog("Enter recipient number:");
            if (rec == null) return; // user cancelled
            if (!checkRecipientCell(rec)) { JOptionPane.showMessageDialog(null, "Invalid recipient number!"); continue; }

            String content = JOptionPane.showInputDialog("Enter message content:");
            if (content == null || content.trim().isEmpty()) { JOptionPane.showMessageDialog(null, "Message cannot be empty."); continue; }

            long id = generateTenDigitId();
            int number = messagesSentCount + 1; // correct message number using counter
            String hash = createMessageHash(id, number, content);

            Message m = new Message(id, number, rec, content, hash);
            addMessageToMemory(m, true);

            JOptionPane.showMessageDialog(null, "Message Added!\n" + m.toString());
            break; // exit after adding one message (like user completes one entry)
        }
    }

    // Add a batch of messages using a for-loop (rubric requires a for loop too)
    public void addMessageForLoop(int batchCount) {
        // batchCount is number of messages user wants to add in this run
        for (int i = 0; i < batchCount && messagesSentCount < messageLimit; i++) {
            String rec = JOptionPane.showInputDialog("(For-loop) Enter recipient number:");
            if (rec == null) return;
            if (!checkRecipientCell(rec)) { JOptionPane.showMessageDialog(null, "Invalid recipient number!"); i--; continue; }

            String content = JOptionPane.showInputDialog("(For-loop) Enter message content:");
            if (content == null || content.trim().isEmpty()) { JOptionPane.showMessageDialog(null, "Message cannot be empty."); i--; continue; }

            long id = generateTenDigitId();
            int number = messagesSentCount + 1;
            String hash = createMessageHash(id, number, content);

            Message m = new Message(id, number, rec, content, hash);
            addMessageToMemory(m, true);

            JOptionPane.showMessageDialog(null, "Message Added!\n" + m.toString());
        }
    }

    // Display all
    public void displayAllSentMessages() {
        if (sentMessages.isEmpty()) { JOptionPane.showMessageDialog(null, "No messages sent."); return; }
        StringBuilder sb = new StringBuilder("All Sent Messages:\n\n");
        for (Message m : sentMessages) {
            sb.append("Sender: ").append(sender).append("\n");
            sb.append("Recipient: ").append(m.getRecipient()).append("\n");
            sb.append("Message: ").append(m.getContent()).append("\n");
            sb.append("Hash: ").append(m.getMessageHash()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Longest message (rubric)
    public void displayLongestMessage() {
        if (sentMessages.isEmpty()) { JOptionPane.showMessageDialog(null, "No messages sent."); return; }
        Message longest = sentMessages.get(0);
        for (Message m : sentMessages) if (m.getContent().length() > longest.getContent().length()) longest = m;
        JOptionPane.showMessageDialog(null,
                "Longest Message:\nSender: " + sender +
                "\nRecipient: " + longest.getRecipient() +
                "\nMessage: " + longest.getContent() +
                "\nCharacters: " + longest.getContent().length());
    }

    // Search by message ID (rubric)
    public void searchByMessageID() {
        String input = JOptionPane.showInputDialog("Enter Message ID to search:");
        if (input == null || input.isEmpty()) return;
        long id;
        try { id = Long.parseLong(input); } catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "Invalid ID."); return; }
        for (Message m : sentMessages) {
            if (m.getMessageID() == id) {
                JOptionPane.showMessageDialog(null,
                        "Message Found:\nSender: " + sender +
                        "\nRecipient: " + m.getRecipient() +
                        "\nMessage: " + m.getContent() +
                        "\nHash: " + m.getMessageHash());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    // Search by recipient (rubric requires searching array for messages to a recipient)
    public void displayMessagesForRecipient() {
        String rec = JOptionPane.showInputDialog("Enter recipient number to search:");
        if (rec == null || rec.isEmpty()) return;
        ArrayList<Message> results = new ArrayList<>();
        for (Message m : sentMessages) if (m.getRecipient().equals(rec)) results.add(m);
        if (results.isEmpty()) { JOptionPane.showMessageDialog(null, "No messages found for: " + rec); return; }
        StringBuilder sb = new StringBuilder("Messages for " + rec + ":\n\n");
        for (Message m : results) {
            sb.append("ID: ").append(m.getMessageID()).append("\n");
            sb.append("Message: ").append(m.getContent()).append("\n");
            sb.append("Hash: ").append(m.getMessageHash()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Delete by hash (rubric)
    public void deleteMessageByHash() {
        String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
        if (hash == null || hash.isEmpty()) return;
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).getMessageHash().equals(hash)) {
                // remove from objects and parallel arrays
                sentMessages.remove(i);
                messageIDs.remove(i);
                messageHashes.remove(i);
                messageNumbers.remove(i);
                recipients.remove(i);
                contents.remove(i);

                // fix message numbers: reassign to keep sequence (both objects and arrays)
                for (int j = 0; j < sentMessages.size(); j++) {
                    Message old = sentMessages.get(j);
                    Message updated = new Message(old.getMessageID(), j + 1, old.getRecipient(), old.getContent(), old.getMessageHash());
                    sentMessages.set(j, updated);
                    messageNumbers.set(j, j + 1);
                }
                messagesSentCount = sentMessages.size();
                saveMessagesSafe();
                JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message hash not found.");
    }

    // Full report (rubric)
    public void displayReport() {
        if (sentMessages.isEmpty()) { JOptionPane.showMessageDialog(null, "No messages sent."); return; }
        StringBuilder sb = new StringBuilder("FULL REPORT OF SENT MESSAGES:\n\n");
        for (Message m : sentMessages) {
            sb.append("Sender: ").append(sender).append("\n");
            sb.append("Recipient: ").append(m.getRecipient()).append("\n");
            sb.append("Message ID: ").append(m.getMessageID()).append("\n");
            sb.append("Message #: ").append(m.getMessageNumber()).append("\n");
            sb.append("Message Content: ").append(m.getContent()).append("\n");
            sb.append("Message Hash: ").append(m.getMessageHash()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Save messages safely (called after changes)
    private void saveMessagesSafe() {
        try {
            storage.saveMessages(sentMessages);
        } catch (IOException e) {
            // For first-year project we simply ignore storage errors but could log them.
        }
    }

    // Start menu
    public void startMenu() {
        String menu = "1. Add Message (while loop)\n2. Add Messages (for loop batch)\n3. Display All Sent Messages\n4. Display Longest Message\n5. Search by Message ID\n6. Search by Recipient\n7. Delete Message by Hash\n8. Display Full Report\n9. Exit";
        while (true) {
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) return;
            int choice;
            try { choice = Integer.parseInt(input); } catch (NumberFormatException e) { JOptionPane.showMessageDialog(null, "Enter a valid number."); continue; }
            switch (choice) {
                case 1 -> addMessageWhileLoop();
                case 2 -> {
                    String batch = JOptionPane.showInputDialog("How many messages to add (for-loop)?");
                    try { int b = Integer.parseInt(batch); addMessageForLoop(b); } catch (Exception e) { JOptionPane.showMessageDialog(null, "Invalid number."); }
                }
                case 3 -> displayAllSentMessages();
                case 4 -> displayLongestMessage();
                case 5 -> searchByMessageID();
                case 6 -> displayMessagesForRecipient();
                case 7 -> deleteMessageByHash();
                case 8 -> displayReport();
                case 9 -> { JOptionPane.showMessageDialog(null, "Goodbye!"); saveMessagesSafe(); return; }
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }
}