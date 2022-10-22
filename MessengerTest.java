import java.util.ArrayList;
import java.util.Scanner;

/**
 * create a testing class to test the function of the Messenger class and Message class
 * @author Thomas Cheng
 */
public class MessengerTest {
    private static Messenger myMess = new Messenger();
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        addMultipleUser("Thomas", "Chris", "random_classmate01");

        myMess.sendMessage("Thomas","Chris", "Can you give me an A+ for this asm?");
        myMess.sendMessage("Thomas","Chris", "111Can you give me an \n A+ for this asm?");

        myMess.sendMessage("Chris", "Thomas", "everything seems fine, you are getting an A+");
        myMess.sendMessage("random_classmate01","Chris", "But what about me?");

        printAllReceivedMessage("Chris");
        printAllReceivedMessage("Thomas");
        printAllReceivedMessage("random_classmate01");

        printReceivedMessage("Chris",Message.Status.READ);
        printReceivedMessage("Chris",Message.Status.UNREAD);
        System.out.println(new SmileMessage("Thomas","Sam").textOfTheMessage);
        String f = in.nextLine();
        System.out.println("s"+f+"s");

    }

    /**
     * take recipient and a Messenger instance
     * print recipient's message in a pretty way
     * @param recipient recipient's user name
     */
    public static void printAllReceivedMessage(String recipient){
        ArrayList<Message> receivedMessages = myMess.getReceivedMessages(recipient);
        System.out.println("All message for "+ recipient);
        System.out.println("---------------------------------------");
        for (Message m: receivedMessages){
            System.out.println(m.getPreview());
            System.out.println("---------------------------------------");
        }
        System.out.println("\n");
    }
    public static void printReceivedMessage(String recipient, Message.Status status){
        ArrayList<Message> receivedMessages = myMess.getReceivedMessages(recipient,status);
        System.out.println("All "+ status+ " message for "+ recipient);
        System.out.println("---------------------------------------");

        for (Message m: receivedMessages){
            System.out.println(m.getPreview());
            System.out.println("---------------------------------------");
        }
        System.out.println("\n");
    }

    public static void addMultipleUser(String... names){
        for(String s:names){
            myMess.addUser(s);
        }
    }
}
