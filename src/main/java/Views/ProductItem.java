package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import Models.Product;

@SuppressWarnings("serial")
public class ProductItem extends JPanel {

	private Product item;
	private JPanel contentPane;
	JLabel lblName;
	JSpinner sprQtt;
	JSpinner sprDisc;

	/**
	 * Create the frame.
	 */
	public ProductItem() {
		setBounds(100, 100, 450, 37);
		setLayout(new GridLayout(0, 1, 0, 0));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.setSize(515, 40);
		
		this.lblName = new JLabel("New label");
		contentPane.add(lblName);
		
		this.sprQtt = new JSpinner();
		contentPane.add(sprQtt);
		
		this.sprDisc = new JSpinner();
		contentPane.add(sprDisc);
		
		this.add(contentPane);
	}
	
	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}
	
	public int getSprQttValue() {
		return (int) this.sprQtt.getValue();
	}
	
	public int getSprDiscValue() {
		return (int) this.sprDisc.getValue();
	}
	
	public void setSprQttValue(int value) {
		this.sprQtt.setValue(value);
	}

	
	public void setSprDiscValue(int value) {
		this.sprDisc.setValue(value);
	}
	
	public void setLblName(String name) {
		this.lblName.setText(name);
	}

}
