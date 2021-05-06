package Controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Models.Pack;
import Models.Product;
import Utils.JDBCUtil;

public class PackDAO {
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/main/java/Utils/configuracaobd.properties");
    private static Connection connection = null;
    String sql;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<Pack> packs = new ArrayList<>();
   
    public ArrayList<Pack> listPacks(){
    	packs.clear();
        Pack newPack = new Pack();
    	int pack_id = -1, index = -1;
    	String sql = "select pack.pack_id, pack_title, pack_total, pack_description, pack_products.product_id, quantity, discount, product_name, product_description, product_price from pack INNER JOIN ( pack_products inner join products on pack_products.product_id = products.product_id) on pack.pack_id = pack_products.pack_id";
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(true);

            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            

            while (rs.next()) {
            	if(rs.getInt("pack.pack_id") == pack_id) {
            		Product product = new Product(rs.getInt("pack_products.product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getFloat("product_price"));
            		int quantity = rs.getInt("quantity");
            		int discount = rs.getInt("discount");
            		
            		packs.get(index).addItem(product, quantity, discount);
            	}else{
	                newPack = new Pack();
	                newPack.setId(rs.getInt("pack.pack_id"));
	                newPack.setTitle(rs.getString("pack_title"));
	                newPack.setDescription(rs.getString("pack_description"));
            		Product product = new Product(rs.getInt("pack_products.product_id"), rs.getString("product_name"), rs.getString("product_description"), rs.getFloat("product_price"));
            		int quantity = rs.getInt("quantity");
            		int discount = rs.getInt("discount");
            		newPack.addItem(product, quantity, discount);
	                
	                index++;
	                pack_id = newPack.getId();
	                packs.add(newPack);
            	}
            }
            
            return packs;

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
    
    public void createPack(Pack pack) {
    	sql = "insert into pack (pack_title, pack_total, pack_description) VALUES (?, ?, ?)";
    	int packId = -1;
    	
    	try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(true);
            
            PreparedStatement pstm = connection.prepareStatement(sql);
            
            pstm.setString(1, pack.getTitle());
            pstm.setFloat(2, pack.getTotal());
            pstm.setString(3, pack.getDescription());
            
            pstm.execute();
            pstm.close();
            
            packId = getPackId(pack.getTitle());
            
            for(int i = 0; i < pack.getProducts().size(); i++) {
            	if(packId >= 0 ) {
            		createPackProduct(packId, pack.getPackItemProduct(i).getProduct_id(), pack.getPackItemQuantity(i), pack.getPackItemDiscount(i));
            	}
            }
            
            packs.clear();
            packs = listPacks();
    	} catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
    			
    }
    
    public void createPackProduct(int packId, int productId, int quantity, int discount){
        sql = "insert into pack_products (pack_id, product_id, quantity, discount) values (?, ?, ?, ?);";
        
            try {
                JDBCUtil.init(config_file);
                connection = JDBCUtil.getConnection();
                connection.setAutoCommit(true);
                
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setInt(1, packId);
                pstm.setInt(2, productId);
                pstm.setInt(3, quantity);
                pstm.setInt(4, discount);
                
                pstm.execute();
                pstm.close();
                
            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao carregar o driver JDBC." + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
    }
    
    public int getPackId(String title) {
    	sql = "select pack_id from pack where pack_title = ?";
    	int id = -1;
    	
    	try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(true);
            
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, title);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
            	id = rs.getInt("pack_id");
            }
            
            pstm.close();
                        
            /*pack.setId(pstm.getGeneratedKeys().getInt(1));
        	this.packs.add(pack);*/
    		
    	} catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
    	
    	return id;
    			
    }
}
