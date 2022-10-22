import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class to simulate a messenger program
 */
public class MessengerProgram {
    private Scanner in;
    private Messenger msgr;
    private String activeUser;
    private boolean startSelection = true;

    public static void main(String[] args) {
        MessengerProgram prog = new MessengerProgram();
        prog.execute();
    }

    /**
     * constructor the messenger class
     * initialize some users, and messages
     */
    public MessengerProgram(){
        this.in = new Scanner(System.in);
        this.msgr = new Messenger();
        addMultipleUser("Thomas","Sam","Percy","Chris","Admin");
        activeUser = "Admin";

        msgr.sendMessage("Thomas","Chris", "Can you give me an A+ for this asm?");
        msgr.sendMessage("Thomas","Chris", "111Can you give me an \n A+ for this asm?");

        msgr.sendMessage("Chris", "Thomas", "everything seems fine, you are getting an A+");



    }

    /**
     *
     */
    public void execute() {
       login();
       while (startSelection) {
           System.out.println("Welcome "+ activeUser);
           selectAction();
       }
    }

    /**
     * take multiple usernames and add them into the messenger
     * @param names usernames to add
     */
    public void addMultipleUser(String... names){
        for(String s:names){
            msgr.addUser(s);
        }
    }

    /**
     * prompt user to chose a username from the displayed list
     */
    public void login(){
        ArrayList<String> usrNames = msgr.getUserNames();
        int choice;
        System.out.println("Choose Active User");
        choice = inputFromDisplayMenu(usrNames);
        this.activeUser = usrNames.get(choice-1);
    }

    /**
     * prompt user to select action for the main menu
     */
    public void selectAction(){
        int choice;
        ArrayList<String> menu = new ArrayList<String>(Arrays.asList(
                "See All Messages",
                "See Unread Messages",
                "Send Message",
                "Send Smile",
                "Switch Active User",
                "See Messenger Stats",
                "Exit"));
        switch (inputFromDisplayMenu(menu)){
            case 1:
                seeMessages(null);// see all message
                break;
            case 2:
                seeMessages(Message.Status.UNREAD);
                break;
            case 3:
                sendMessage();
                break;
            case 4:
                sendSmile();
                break;
            case 5:
                switchActiveUser();
                return;
            case 6:
                seeMessengerStats();
                break;
            case 7:
                System.out.println("User chose: Exit");
                startSelection = false;
                return;
        }
        // reach here only when ending method
        return;
    }

    /**
     * a generic template for user to select choice from the menu
     * @param arrlst the selection to display
     * @return int number of the chose
     */
    public int inputFromDisplayMenu(ArrayList arrlst){
        int choice;
        System.out.println("---------------------------------------");
        for (int i = 0; i < arrlst.size() ; i++) {
            System.out.println(i+1+": "+arrlst.get(i));
        }
        System.out.println("---------------------------------------");
        while(true){
            System.out.print("Enter a number from the choices above: ");

            try{
                choice = in.nextInt();
                if(choice > 0 && choice <= arrlst.size()){
                    in.nextLine();
                    return choice;
                }
            } catch (InputMismatchException e){
                in.nextLine();
                continue;
            }
        }
    }
    /**
     * a generic template for user to select choice from the menu
     * @param arrlst the selection to display
     * @return int number of the chose
     */
    public int inputFromDisplayMenu_Message(ArrayList<Message> arrlst){
        int choice;
        System.out.println("---------------------------------------");
        for (int i = 0; i < arrlst.size() ; i++) {
            System.out.println(i+1+": "+arrlst.get(i).getPreview());
        }
        System.out.println("---------------------------------------");
        while(true){
            System.out.print("Enter a number from the choices above: ");
            try{
                choice = in.nextInt();
                if(choice > 0 && choice <= arrlst.size()){
                    in.nextLine();
                    return choice;
                }
            } catch (InputMismatchException e){
                in.nextLine();
                continue;
            }

        }
    }

    /**
     * take a Message Status then
     * display all Message for the active user which have the argument Message Status
     * or display all Message when status is null
     * then prompt action for the Message
     * @param status Message Status to have for the messages
     */
    private void seeMessages(Message.Status status) {
        int choice;
        Message targetMessage;
        ArrayList<Message> receivedMessages = msgr.getReceivedMessages(activeUser, status);
        ArrayList<String> previewMessage = getPreviewMessages(receivedMessages);
        if(status == null){
            System.out.println("User chose: See All Messages ");
            System.out.println("All message for "+ activeUser);
        }
        else {
            System.out.println("User chose: See Unread Messages");
            System.out.println("All "+ status+ " message for "+ activeUser);
        }
        if(receivedMessages.size()<= 0){
            System.out.println("====No Message To Display====");
            return;
        }
        messageAction(receivedMessages.get(inputFromDisplayMenu(previewMessage)-1));
    }

    /**
     * take a Message arraylist then create an Arraylist with their preview
     * @param arr Message arraylist
     * @return preview arraylist
     */
    public ArrayList<String> getPreviewMessages(ArrayList<Message> arr){
        ArrayList<String> out = new ArrayList<String>();
        arr.forEach((message -> out.add(message.getPreview())));
        return out;
    }

    /**
     * take selected Message and print its full form
     * then prompt user to perform action
     * @param targetMessage selected mesasge
     */
    private void messageAction(Message targetMessage) {
        int choice;
        ArrayList<String> menu = new ArrayList<String>(Arrays.asList(
                "Mark Unread",
                "Mark As Trash",
                "Reply",
                "Do Nothing"));

        System.out.println("---------------------------------------");
        System.out.println(targetMessage);
        if(targetMessage.getStatusType() == Message.Status.UNREAD){
            targetMessage.setStatusType(Message.Status.READ);
        }
        switch(inputFromDisplayMenu(menu)) {
            case 1:
                targetMessage.setStatusType(Message.Status.UNREAD);
                return;
            case 2:
                targetMessage.setStatusType(Message.Status.TRASH);
                return;
            case 3:
                replyMessage(targetMessage);
                return;
        }

    }

    /**
     * take the selected message then promt the user to input reply message
     * @param targetMessage message to reply
     */
    private void replyMessage(Message targetMessage) {
        System.out.println("type your Message");
        System.out.println("---------------------------------------");
        msgr.sendReply(targetMessage,getMultiLineInput());
        System.out.println("---------------------------------------");
    }

    /**
     * prompts user to enter recipients username and the message
     * then send a message to the recipient
     */
    private void sendMessage() {
        String receiver;
        String text="";

        System.out.println("User chose: Send Message");
        System.out.println("---------------------------------------");

        System.out.println("Who is the recipient?");
        receiver = msgr.getUserNames().get(inputFromDisplayMenu(msgr.getUserNames())-1);
        System.out.println("User chose: "+ receiver);
        System.out.println("---------------------------------------");

        System.out.println("type your Message");
        System.out.println("---------------------------------------");

        msgr.sendMessage(activeUser,receiver,getMultiLineInput());
        System.out.println("---------------------------------------");
    }

    /**
     * prompt user to select recipient
     * then send a smileyMessage to them
     */
    private void sendSmile() {
        String receiver;
        System.out.println("User chose: Send Smile ");
        System.out.println("---------------------------------------");

        System.out.println("Who is the recipient?");
        receiver = msgr.getUserNames().get(inputFromDisplayMenu(msgr.getUserNames())-1);
        System.out.println("User chose: "+ receiver);
        System.out.println("---------------------------------------");
        msgr.sendSmile(activeUser,receiver);
        System.out.println("Message sent");

    }

    /**
     * prompt user to select new activeUser
     */
    private void switchActiveUser() {
        System.out.println("User chose: Switch Active User\n");
        login();
    }

    /**
     * prompt user to enter multiline String input from user
     * then return it.
     * @return entered multiline string
     */
    public String getMultiLineInput(){
        String input = in.nextLine();
        String output = input;
        while(!input.isEmpty()|| output.isEmpty()) {
            input = in.nextLine();
            output = output.concat("\n"+input);
        }
        return output;
    }

    /**
     * print Messagenger stats in a fancy way
     */
    private void seeMessengerStats() {
        System.out.println("User chose: See Messenger Stats");
        System.out.println("---------------------------------------");
        System.out.println("Messenger Stats"+
                "\n------------------------"+
                "\nNumber of Users: " + msgr.getUserNames().size()+
                "\nmessages sent: "+ msgr.getNumberOfMessages()+
                "\nChracter sent: "+totalCharCount());
        System.out.println("---------------------------------------");
    }

    /**
     * count the characters sent in the messenger
     * @return character count
     */
    private int totalCharCount(){
        int count = 0;
        for(Message m: msgr.getReceivedMessages(null)){
            count += m.getTextOfTheMessage().length();
        }
        return count;
    }


}
