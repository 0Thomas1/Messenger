import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void testMessage(){
        //-Normal case---------------------------
        Message m1 = new Message("goodnight","Sam", "Thomas");
        //-Null and empty text-------------------
        try{
            Message m2 = new Message(null,"Sam", "Thomas");
            fail("NullPointerException Expected");
        } catch (NullPointerException e){
            assertEquals("text cannot be null",e.getMessage());
        }
        try {
            Message m3 = new Message("", "Sam", "Thomas");
            fail("IllegalArgumentException Expected");
        } catch(IllegalArgumentException e){
            assertEquals("message cannot be empty",e.getMessage());
        }

        //-Null and empty usernames-------------------
        try {
            Message m4 = new Message("goodnight", null, "Thomas");
            fail("NullPointerException expected");
        } catch (NullPointerException e){
            assertEquals("user names cannot be null", e.getMessage());
        }
        try {
            Message m5 = new Message("goodnight", "", "Thomas");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e){
            assertEquals("user name must not be empty", e.getMessage());
        }
        //------------
        try {
            Message m6 = new Message("goodnight", "Sam", null);
            fail("NullPointerException expected");
        } catch (NullPointerException e){
            assertEquals("user names cannot be null", e.getMessage());
        }
        try {
            Message m7 = new Message("goodnight", "Sam", "");
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e){
            assertEquals("user name must not be empty", e.getMessage());
        }
        //-Null status type---------------------
        try {
            Message m8 = new Message("goodnight", "Sam", "Thomas",null);
            fail("NullPointerException expected");
        } catch (NullPointerException e){
            assertEquals("status type cannot be null", e.getMessage());
        }
    }
    @Test
    void setStatusType() {
        Message m1 = new Message("Thomas","Sam", "goodnight");
        m1.setStatusType(Message.Status.READ);
        assertEquals(Message.Status.READ,m1.getStatusType());

        m1.setStatusType(Message.Status.UNREAD);
        assertEquals(Message.Status.UNREAD,m1.getStatusType());

        m1.setStatusType(Message.Status.TRASH);
        assertEquals(Message.Status.TRASH,m1.getStatusType());

        try{
            m1.setStatusType(null);
            fail("NullPointerException Expected");
        } catch (NullPointerException e){
            assertEquals("status type cannot be null",e.getMessage());
        }


    }
}