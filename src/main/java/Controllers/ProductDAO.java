/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controllers;

import Utils.JDBCUtil;
import Models.Product;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
*
* @author lucas
*/
public class ProductDAO {
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/main/java/Utils/configuracaobd.properties");
    private static Connection connection = null;
    String sql;
    PreparedStatement pstm;
    ResultSet rs;
    
    ArrayList<Product> list = new ArrayList<>();
    
    public ArrayList<Product> listProducts() {
        sql = "select * from products";
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(true);
            
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_description(rs.getString("product_description"));
                product.setProduct_price(rs.getFloat("product_price"));
                
                list.add(product);
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
    
    public int createProduct(Product p){
        sql = "insert into products (product_name, product_description, product_price) values (?,?,?)";
        
        if(p.getProduct_name()!= null && p.getProduct_description() != null){
            try {
                JDBCUtil.init(config_file);
                connection = JDBCUtil.getConnection();
                connection.setAutoCommit(true);
                
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1, p.getProduct_name());
                pstm.setString(2, p.getProduct_description());
                pstm.setFloat(3, p.getProduct_price());
                
                pstm.execute();
                pstm.close();
                
            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao carregar o driver JDBC." + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
        } else{
            return 1;
        }
        return 0;
    }
    
    public int updateProduct(Product p) {
        
        sql = "update products set product_name = ?, product_description = ?, product_price = ? where product_id = ?";
        
        if(p.getProduct_name()!= null && p.getProduct_description() != null){
            try {
                JDBCUtil.init(config_file);
                connection = JDBCUtil.getConnection();
                connection.setAutoCommit(true);
                
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1, p.getProduct_name());
                pstm.setString(2, p.getProduct_description());
                pstm.setFloat(3, p.getProduct_price());
                pstm.setInt(4, p.getProduct_id());
                
                pstm.execute();
                pstm.close();
                
            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao carregar o driver JDBC." + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
        } else{
            return 1;
        }
        return 0;
    }
    
    public int deleteProduct(Product p) {
        String sql = "delete from products where product_id = ?";
        
        if(p.getProduct_name()!= null && p.getProduct_description() != null){
            try {
                JDBCUtil.init(config_file);
                connection = JDBCUtil.getConnection();
                connection.setAutoCommit(true);
                
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setInt(1, p.getProduct_id());
                
                pstm.execute();
                pstm.close();
                
            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao carregar o driver JDBC." + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
        } else{
            return 1;
        }
        return 0;
    }
}
