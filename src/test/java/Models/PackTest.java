package Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import Models.Pack;
import Models.Product;

public class PackTest {
  @Test
	public void packModelTest() {
		Pack p = new Pack("pack", 0, "description");
		Product p1 = new Product(20, "Produto 1", "Essa e a descricao do produto um.", 100f);
		Product p2 = new Product(21, "Produto 2", "Essa e a descricao do produto 2.", 100f);

		p.addItem(p2, 2, 10);
		p.addItem(p1, 1, 0);

		assertEquals("id=0" + 
		"\ntitle=pack" + 
		"\ntotal=280.0" + 
		"\ndescription=description" + 
		"\nproducts.size=2" + 
		"\nquantities.size=2" + 
		"\ndiscounts.size=2", p.toString());
	}

	@Test
	public void packTestsContructor(){
		Pack p = new Pack("pack", 0, "description");
		Product p1 = new Product(20, "Produto 1", "Essa e a descricao do produto um.", 100f);
		Product p2 = new Product(21, "Produto 2", "Essa e a descricao do produto 2.", 100f);
		ArrayList<Product> pl = new ArrayList<>();
		ArrayList<Integer> int1 = new ArrayList<>();
		ArrayList<Integer> int2 = new ArrayList<>();
		
		pl.add(p1);
		pl.add(p2);

		int1.add(2);
		int1.add(1);

		int2.add(10);
		int2.add(0);

		p.setProducts(pl);
		p.setQuantities(int1);
		p.setDiscounts(int2);
		p.getQuantities();
		p.getDiscounts();

		assertEquals("id=0" + 
		"\ntitle=pack" + 
		"\ntotal=0.0" + 
		"\ndescription=description" + 
		"\nproducts.size=2" + 
		"\nquantities.size=2" + 
		"\ndiscounts.size=2", p.toString());
	}
}
