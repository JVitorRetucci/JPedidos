/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Order;
import Models.OrderProduct;
import Models.Product;
import Models.User;
import Utils.JDBCUtil;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class OrderDAO {
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/main/java/Utils/configuracaobd.properties");
    private static Connection connection = null;
    String sql;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<Order> list = new ArrayList<>();

    public void createOrder(Order order, ArrayList<Product> productList){
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(true);
            
            sql = "insert into orders(order_customer_name, order_customer_phone, user_id, order_total, order_status) values(?, ?, ?, ?, ?)";
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, order.getOrder_customer_name());
            pstm.setString(2, order.getOrder_customer_phone());
            pstm.setInt(3, order.getUser_id());
            pstm.setFloat(4, order.getOrder_total());
            pstm.setString(5, "opened");
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }   
            System.out.println("Inserted record's ID: " + generatedKey);
            pstm.close();
            
            for(int n=0;n<productList.size();n++){
                sql = "insert into order_product(order_id, product_id) values(?,?)";
                pstm = connection.prepareStatement(sql);
                pstm.setInt(1, generatedKey);
                pstm.setInt(2, productList.get(n).getProduct_id());
                pstm.execute();
                pstm.close();
            }
            

        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
    }
    
    public ArrayList<Order> listOrders() {
        String sql = "select * from orders";
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(true);

            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setUser_id(rs.getInt("user_id"));
                // order.setCreated_at(rs.getString("created_at"));
                // order.setClosed_at(rs.getString("closed_at"));
                order.setOrder_status(rs.getString("order_status"));
                // order.setOrder_amount(rs.getInt("order_amount"));
                order.setOrder_customer_name(rs.getString("order_customer_name"));
                order.setOrder_customer_phone(rs.getString("order_customer_phone"));
                order.setOrder_id(rs.getInt("order_id"));
                order.setOrder_total(rs.getFloat("order_total"));
                order.setCreated_at(rs.getTimestamp("created_at"));

                list.add(order);
            }

            return list;

        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
            return null;
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            return null;
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
            return null;
        }
    }
}
