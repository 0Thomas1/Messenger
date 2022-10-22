/**
 * create a Reply class extending Message
 * replying an existing message
 */
public class Reply extends Message{
    private Message toReply;
    public Reply(String text, String senderUsername, String recipientUsername, Message toReply){
        super(text,senderUsername,recipientUsername);
        this.toReply = toReply;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\n----------Replying To----------\n"+
                toReply.toString();
    }
}
