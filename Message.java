/**
 * Create a class called Message that simulates a message sent between two people.
 * @author Thomas Cheng
 */
public class Message {
    public enum Status {UNREAD, READ, TRASH};
    private static int numOfMessages = 0;
    private static int globalMessageLength = 0;
    protected final String textOfTheMessage, senderUsername, recipientUsername;

    protected Status statusType;

    /**
     * take strings to create a message instance
     * @param text text sent
     * @param senderUsername sender's username
     * @param recipientUsername recipient's user name
     * @param statusType the status of the message
     */

    public Message(String text, String senderUsername, String recipientUsername, Status statusType){
        //validation here
        if (text == null){
            throw new NullPointerException("text cannot be null");
        }
        if (text.isEmpty()){
            throw new IllegalArgumentException("message cannot be empty");
        }
        if(senderUsername == null || recipientUsername == null){
            throw new NullPointerException("user names cannot be null");
        }
        if (senderUsername.length()<= 0 || recipientUsername.length() <=0){
            throw new IllegalArgumentException("user name must not be empty");
        }
        if (statusType == null){
            throw new NullPointerException("status type cannot be null");
        }
        this.textOfTheMessage = text;
        this.senderUsername = senderUsername;
        this.recipientUsername = recipientUsername;
        this.statusType = statusType;
        numOfMessages++;
        globalMessageLength += text.length();
    }
    /**
     * take strings to create a message instance
     * @param text text sent
     * @param senderUsername sender's username
     * @param recipientUsername recipient's user name
     */
    public Message(String text, String  senderUsername, String recipientUsername){
        this(text,senderUsername,recipientUsername,Status.UNREAD);
    }

    /**
     * return message's text
     * @return text
     */
    public String getTextOfTheMessage() {
        return textOfTheMessage;
    }

    /**
     * return sender's user name
     * @return sender's user name
     */
    public String getSenderUsername() {
        return senderUsername;
    }
    /**
     * return recipient's user name
     * @return recipient's user name
     */
    public String getRecipientUsername() {
        return recipientUsername;
    }

    /**
     * return message's status
     * @return message's status
     */
    public Status getStatusType() {
        return statusType;
    }

    /**
     * take a status type then set instance's status into the status type
     * @param statusType status type
     */
    public void setStatusType(Status statusType) {
        //validation here
        if(statusType == null){
            throw new NullPointerException("status type cannot be null");
        }
        this.statusType = statusType;
    }

    /**
     * return the total length of all messages
     * @return total length of all messages
     */
    public static int getMessageLength(){
        return globalMessageLength;
    }

    @Override
    public String toString() {
        return "From: "+ this.senderUsername+
                "\nTo: "+ this.recipientUsername +
                "\nStatus: " + this.statusType+
                "\n" + this.textOfTheMessage;
    }
    public String getPreview(){
        return this.statusType + " From: "
                + this.senderUsername + ": "
                + this.textOfTheMessage.replace("\n"," ");
    }
}
