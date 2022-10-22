import java.util.ArrayList;

public class Messenger {
    private ArrayList<String> userNames;
    private ArrayList<Message> allMessages;

    /**
     * Construct a new Messenger instance
     */
    public Messenger() {
        this.userNames  = new ArrayList<String>();
        this.allMessages = new ArrayList<Message>();
    }

    /**
     * take a username in string and append it to the userName arraylist,without repetition
     * @param userName username to add
     */
    public void addUser(String userName){
        if(userName == null){
            throw new NullPointerException("user name cannot be null");
        }
        if(userName.length() <= 0){
            throw new IllegalArgumentException("user name cannot be empty");
        }
        if (this.userNames.contains(userName)){
            return;
        }
        this.userNames.add(userName);
    }

    /**
     * Takes username of the sender and receiver, and the text sent
     * then create a new Message, append it into the allMassage arraylist
     * throw exception when argument usernames are not in the userNames arraylist
     *
     * @param sender sender's username
     * @param receiver receiver's username
     * @param text text being sent
     */
    public void sendMessage(String sender, String receiver, String text){
        if(!this.userNames.contains(sender) || !this.userNames.contains(receiver)){
            throw new IllegalArgumentException("sender or receiver is not registered");
        }
        allMessages.add(new Message(text,sender,receiver));
    }

    public int getNumberOfMessages() {
       return this.allMessages.size();
    }

    /**
     * take receiver's username
     * return an arraylist with all message sent to the receiver
     * @param receiver receiver's username
     * @return arraylist with all the message
     */
    public ArrayList<Message> getReceivedMessages(String receiver){
        return getReceivedMessages(receiver,null);
    }
    /**
     * take receiver's username and the message status
     * return an arraylist with all messages of the status  sent to the receiver
     * @param receiver receiver's username
     * @param status message's status
     * @return arraylist with all the message
     */
    public ArrayList<Message> getReceivedMessages(String receiver, Message.Status status){
        ArrayList<Message> outArr = new ArrayList<Message>();
        if(receiver == null){
            for (Message m : this.allMessages) {
                outArr.add(m);
            }
        }
        else if (receiver.length() <= 0){
            throw new IllegalArgumentException("user name must not be empty");
        }
        else {
            for (Message m : this.allMessages) {
                if (m.getRecipientUsername().equals(receiver) && (status == null || m.getStatusType().equals(status))) {
                    outArr.add(m);
                }
            }
        }
        return outArr;
    }

    /**
     * take sender and receiver's username and send a SmileMessage
     * @param sender sender's username
     * @param receiver receiver's username
     */
    public void sendSmile(String sender, String receiver){
        if(!this.userNames.contains(sender) || !this.userNames.contains(receiver)){
            throw new IllegalArgumentException("sender or receiver is not registered");
        }
        allMessages.add(new SmileMessage(sender,receiver));
    }

    /**
     * take the original message and a reply text
     * and send a reply
     * @param original Message to reply
     * @param text reply text
     */
    public void sendReply(Message original, String text){
            allMessages.add(new Reply(text, original.getRecipientUsername(),original.getSenderUsername(),original));
    }

    /**
     * creates a copy of the username Arraylist then return it
     * @return username Arraylist
     */
    public ArrayList<String> getUserNames() {
        ArrayList<String> out = new ArrayList<String>();
        for(String str:this.userNames){
            out.add(str);
        }
        return out;
    }

}
