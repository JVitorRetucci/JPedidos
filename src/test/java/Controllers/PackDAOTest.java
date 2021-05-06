package Controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Models.Pack;
import Models.Product;

public class PackDAOTest {
	@Test
	public void testListPacks(){
		PackDAO pdao = new PackDAO();
    ArrayList<Pack> packs = new ArrayList<>();

		packs = pdao.listPacks();
	}
	
	@Test
	public void testPackDao(){
		PackDAO pdao = new PackDAO();
    Pack pack = new Pack();
		Product p = new Product(20, "Produto 1", "Essa e a descricao do produto um.", 5.5f);
		Product p2 = new Product(21, "Produto 2", "Essa e a descricao do produto 2.", 5.5f);

		pack.addItem(p, 2, 10);
		pack.addItem(p2, 1, 5);

		pack.setTitle("title");
		pack.setTotal(50f);
		pack.setDescription("description");

		pdao.createPack(pack);
	}
}
