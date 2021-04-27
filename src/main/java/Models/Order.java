/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author henrique
 */
public class Order {
    
    private int order_id;
    private String order_customer_name;
    private String order_customer_phone;
    private int user_id;
    private float order_total;
    private String order_status;
    private Timestamp created_at;
    private Timestamp closed_at;
    
    public Order(){
    }
    
    public Order(int id, String customer_name, String customer_phone, int user_id, String status, Timestamp created_at, Timestamp closed_at){
        this.order_id = id;
        this.order_customer_name = customer_name;
        this.order_customer_phone = customer_phone;
        this.user_id = user_id;
        this.order_status = status;
        this.created_at = created_at;
        this.closed_at = closed_at;
    }
    
    public String toString() {
        String resposta = this.getOrder_id()        + "\n";
        resposta += this.getOrder_customer_name()   + "\n";
        resposta += this.getOrder_customer_phone()  + "\n";
        resposta += this.getUser_id()               + "\n";
        resposta += this.getOrder_status()          + "\n";
        resposta += this.getCreated_at()            + "\n";
        resposta += this.getClosed_at()             + "\n";
        return resposta;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getOrder_customer_name() {
        return order_customer_name;
    }

    public String getOrder_customer_phone() {
        return order_customer_phone;
    }

    public int getUser_id() {
        return user_id;
    }
    
    public float getOrder_total() {
        return order_total;
    }

    public String getOrder_status() {
        return order_status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getClosed_at() {
        return closed_at;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setOrder_customer_name(String order_customer_name) {
        this.order_customer_name = order_customer_name;
    }

    public void setOrder_customer_phone(String order_customer_phone) {
        this.order_customer_phone = order_customer_phone;
    }
    
    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setClosed_at(Timestamp closed_at) {
        this.closed_at = closed_at;
    }
    
    public String getFormatedDate() {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    	try {
    	    Date date = (Date) formatter.parse(this.created_at.toString());
    	    String data = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(date);
    	    
    	    return data;
    	} catch (ParseException e) {
    	    e.printStackTrace();
    	}
    	
    	return null;
    }
    
    
}
