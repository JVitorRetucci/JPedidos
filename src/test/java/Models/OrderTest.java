package Models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import Models.OrderProduct;

public class OrderTest {
  
  //Order
  @Test
  public void orderConstructorTest(){
    // Order o = new Order(0, 2, "Joao da Silva", "(16)99999-9999", 5);
    Order o = new Order(1, "Joao da Silva", "99999-9999", 1, "open", Timestamp.valueOf("2021-03-25 10:10:10"), Timestamp.valueOf("2021-03-25 13:30:38"));
    
    assertEquals(
    "1\n" + 
    "Joao da Silva\n" + 
    "99999-9999\n" +
    "1\n" +
    "open\n" +
    "2021-03-25 10:10:10.0\n" +
    "2021-03-25 13:30:38.0\n"
    , o.toString()
    );
  }
  
  @Test
  public void orderSettersTest(){
    Order o = new Order();
    
    o.setOrder_id(1);
    o.setOrder_customer_name("Joao da Silva");
    o.setOrder_customer_phone("99999-9999");
    o.setOrder_total(500f);
    o.setUser_id(5);
    o.setOrder_status("closed");
    o.setCreated_at(Timestamp.valueOf("2021-03-25 10:10:10"));
    o.setClosed_at(Timestamp.valueOf("2021-03-25 13:30:38"));

    assertEquals(
    "1\n" + 
    "Joao da Silva\n" + 
    "99999-9999\n" +
    "5\n" +
    "closed\n" +
    "2021-03-25 10:10:10.0\n" +
    "2021-03-25 13:30:38.0\n"
    , o.toString()
    );

    assertEquals(500f, o.getOrder_total());
  } 
}
