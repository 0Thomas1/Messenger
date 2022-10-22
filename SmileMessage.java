/**
 * create a SmileMessage Class extending Message that send a smiley face
 * @author Thomas Cheng
 */
public class SmileMessage extends Message{

    public SmileMessage(String  senderUsername, String recipientUsername){
        super( " @  @ \n"+
                "       \n"+
                "@    @\n"+
                " @  @  \n" +
                "  @@  \n",senderUsername,recipientUsername);
    }

    @Override
    public String getPreview() {
        return this.statusType + "|" +
                this.senderUsername + "|" +
                ":)";
    }
}
