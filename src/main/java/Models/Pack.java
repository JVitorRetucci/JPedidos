package Models;

import java.util.ArrayList;

public class Pack {
	private int id;
	private String title;
	private float total = 0f;
	private String description;
	private ArrayList<Product> products = new ArrayList<>();
	private ArrayList<Integer> quantities = new ArrayList<>();
	private ArrayList<Integer> discounts = new ArrayList<>();
	
	public Pack() {};

	public Pack(String title, float total, String description) {
		this.title = title;
		this.total = total;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "id=" + id + 
			"\ntitle=" + title + 
			"\ntotal=" + total + 
			"\ndescription=" + description + 
			"\nproducts.size=" + products.size() + 
			"\nquantities.size=" + quantities.size() + 
			"\ndiscounts.size=" + discounts.size();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public ArrayList<Integer> getQuantities() {
		return quantities;
	}
	
	public void setQuantities(ArrayList<Integer> quantities) {
		this.quantities = quantities;
	}
	
	public ArrayList<Integer> getDiscounts() {
		return discounts;
	}
	
	public void setDiscounts(ArrayList<Integer> discounts) {
		this.discounts = discounts;
	}

	public Product getPackItemProduct(int i) throws IndexOutOfBoundsException {
		return products.get(i);
	}
	
	public int getPackItemQuantity(int i) throws IndexOutOfBoundsException {
		return quantities.get(i);
	}
	
	public int getPackItemDiscount(int i) throws IndexOutOfBoundsException {
		return discounts.get(i);
	}
	
	public void addItem(Product product, int quantity, int discount) {
		this.products.add(product);
		this.quantities.add(quantity);
		this.discounts.add(discount);
		this.total = this.total + (product.getProduct_price() - (product.getProduct_price() * ((float)discount/100)))*quantity;
	}
}
