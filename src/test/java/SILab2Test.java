import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    private final User user = new User("anastasija","pass","anastasijagmailcom");
    private List<User> createList(User...elems){
        return new ArrayList<>(Arrays.asList(elems));
    }
    @Test
     void multipleCondition(){
        //if (user==null || user.getPassword()==null || user.getEmail()==null)
        RuntimeException ex;
            // T || F || T
        ex=assertThrows(RuntimeException.class,()->SILab2.function(new User(null,"finki123",null),createList(new User("neshto","password","mail@gmail,comn"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
            // F || T || X
        ex=assertThrows(RuntimeException.class,()->SILab2.function(new User("anastasija13",null,"mail@gmail.com"),createList(new User("neshto","password","mail@gmail,comn"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
            // F || F || T
        ex=assertThrows(RuntimeException.class,()->SILab2.function(new User("anastasija13","finki123",null),createList(new User("neshto","password","mail@gmail,comn"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));
            // F || F || F
        assertTrue(SILab2.function(new User("marija","pass!$#%()@","marija@gmail.com"),createList(new User("anastasija","skopje$%$%$%","razlmail@gmail.com"),new User("elena","veles*+,-./s","razlichenmail@gmail.com"))));

    }
    @Test
    void everyBranch(){
        //1,2-3 3-27
        RuntimeException ex;
        ex=assertThrows(RuntimeException.class,()->SILab2.function(new User(null,null,null),createList(new User("anastasija","finki500","dimovska@gmail,comn"),new User("elena","feit234","elena@gmail.com"))));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //vrakja false od linija 20 so ist username i password
        //1,2-4 4-6 6-7 7-8 8-9.1 9.1-9.2 9.2-10 10-11 11-12 12-13 13-14 14-15 15-9.3 9.3-9.2 9.2-16 16-17 17-18 18-19 19-20
        assertFalse(SILab2.function(user,createList(new User("anastasija","neshto","ane@gmail.com"),new User("anastasija","neshto","ane@gmail.com"))));

        //linija24 da vrati same==0 t.e proverka za passwordot dali e
        //1,2-4 4-6 6-7 7-16 16-17 17-18 18-19 19-21 21-22.1 22.1-22.2 22.2-26 22.2-23 23-24 24-25 25-22.3 22.3-22.2
        assertTrue(SILab2.function(new User("marija","pass!$#%()@","marija@gmail.com"),createList(new User("anastasija","skopje$%$%$%","razlmail@gmail.com"),new User("elena","veles*+,-./s","razlichenmail@gmail.com"))));

        //password da ima prazno mesto linija 26 vrakja false
        //1,2-4 4-6 6-7 7-8 8-9.1 9.1-9.2 9.2-10 10-11 11-13 13-15 15-9.3 9.3-9.2 9.2-16 16-17 17-18 18-19 19-21 21-26 26-27
        assertFalse(SILab2.function(new User("ana","nov pasword so prazno mesto","mail@gmail.com"),createList(new User("anastasija","skopje$%$%$%","razlmail@gmail.com"),new User("elena","veles*+,-./s","razlichenmail@gmail.com"))));

        //linija 26 vadi false username null i nema specijalni znaci pass
        //1,2-4 4-5 5-6 6-7 7-16 16-17 17-18 18-19 19-21 21-22.1 22.1-22.2 22.2-23 23-25 25-22.3 22.3-22.2 26-27
        assertFalse(SILab2.function(new User(null,"bitolaMojRodenKraj","anegmailcom"),createList(new User("ivica","kakovbilo","ivica@gmail.com"))));
    }
}
