package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controllers.PackDAO;
import Controllers.ProductDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Pack extends JFrame {

	private JPanel contentPane;
    private JPanel jPanel1;
	private JTable createdPacksTable;
	private JTextField textFieldPackName;
	private JTextArea textAreaPackDescription;
	private ArrayList<ProductItem> productItemList = new ArrayList<>();
    ProductDAO productController = new ProductDAO();
    PackDAO packDAO = new PackDAO();
    ArrayList<Models.Product> productsList = productController.listProducts();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pack frame = new Pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Pack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane createdPacksHolder = new JScrollPane();
		createdPacksHolder.setBounds(0, 0, 382, 254);
		contentPane.add(createdPacksHolder);
		
		createdPacksTable = new JTable();
		createdPacksTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Pack Name", "Description", "Total"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		createdPacksHolder.setViewportView(createdPacksTable);
		
		JLabel lblNome = new JLabel("Pack Name:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setBounds(10, 265, 84, 23);
		contentPane.add(lblNome);
		
		textFieldPackName = new JTextField();
		textFieldPackName.setBounds(104, 265, 275, 23);
		contentPane.add(textFieldPackName);
		textFieldPackName.setColumns(10);
		
		JButton btnAddPack = new JButton("Add pack");
		btnAddPack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPack(e);
			}
		});
		btnAddPack.setBounds(389, 293, 238, 50);
		contentPane.add(btnAddPack);

		JScrollPane productsHolder = new JScrollPane();
		productsHolder.setBounds(389, 28, 480, 260);
		contentPane.add(productsHolder);
		
		JButton btnAlterarPacote = new JButton("Update pack");
		btnAlterarPacote.setEnabled(false);
		btnAlterarPacote.setBounds(389, 349, 480, 50);
		contentPane.add(btnAlterarPacote);
		
		JButton btnExcluirPacote = new JButton("Delete pack");
		btnExcluirPacote.setEnabled(false);
		btnExcluirPacote.setBounds(389, 404, 480, 50);
		contentPane.add(btnExcluirPacote);
		
		jPanel1 = new JPanel();
        productsHolder.setViewportView(jPanel1);
        
        JPanel panel = new JPanel();
        panel.setBounds(389, 0, 515, 27);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Quantity");
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel = new JLabel("Discount(%)");
        panel.add(lblNewLabel);
        
        JButton btnPreview = new JButton("Preview pack");
        btnPreview.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		previewPack(e);
        	}
        });
        btnPreview.setBounds(631, 293, 238, 50);
        contentPane.add(btnPreview);
        
        JLabel lblDescription = new JLabel("Pack Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDescription.setBounds(10, 299, 168, 23);
        contentPane.add(lblDescription);
        
        textAreaPackDescription = new JTextArea();
        textAreaPackDescription.setBounds(10, 324, 372, 130);
        contentPane.add(textAreaPackDescription);
        insertProduct(productsList);
        refreshPacksTable();
	}
	
	public void addPack(ActionEvent e) {
		Models.Pack p = new Models.Pack();
		p.setTitle(this.textFieldPackName.getText());
		p.setDescription(this.textAreaPackDescription.getText());
		
		for (ProductItem pi : productItemList) {
			if (pi.getSprQttValue() > 0) {
				p.addItem(pi.getItem(), pi.getSprQttValue(), pi.getSprDiscValue());
			}
		}
		
		
		System.out.println(p.toString());
		packDAO.createPack(p);
		refreshPacksTable();
		insertProduct(productsList);
		this.textFieldPackName.setText("");
		this.textAreaPackDescription.setText("");
	}
	
	public void previewPack(ActionEvent e) {
		Models.Pack p = new Models.Pack();
		p.setTitle(this.textFieldPackName.getText());
		p.setDescription(this.textAreaPackDescription.getText());
		
		for (int i = 0; i < productItemList.size(); i++) {
			if (productItemList.get(i).getSprQttValue() > 0) {
				p.addItem(productItemList.get(i).getItem(), productItemList.get(i).getSprQttValue(), productItemList.get(i).getSprDiscValue());
			}
		}
	}
	
	public void insertProduct(ArrayList<Models.Product> productsList) {
		jPanel1.removeAll();
		if (productsList.size() > 0) {
        	jPanel1.setLayout(new GridLayout(productsList.size(), 1, 0, 0));    
			for (int i = 0; i < productsList.size(); i++) {
				ProductItem pi = new ProductItem();

				pi.setItem(productsList.get(i));
				pi.setLblName(productsList.get(i).getProduct_name());
				
				productItemList.add(pi);
				jPanel1.add(pi);
			}
		}
	}
		
	public void refreshPacksTable() {
        DefaultTableModel model = (DefaultTableModel) createdPacksTable.getModel();
        

        ArrayList<Models.Pack> packList = packDAO.listPacks();
        
        model.setRowCount(0);
        model.getDataVector().removeAllElements();

       for (int i = 0; i < packList.size(); i++) {
            model.addRow(new Object[]{
                packList.get(i).getTitle(),
                packList.get(i).getDescription(),
                packList.get(i).getTotal()
            });
        }

       	model.fireTableDataChanged();
        packList.clear();
    }
}
