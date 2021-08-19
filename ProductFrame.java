import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ProductFrame extends JFrame {

	private final JTable table = new JTable();
	private final DefaultTableModel dtm = new DefaultTableModel();

	// label
	private final JLabel titleLabel = new JLabel("Product");

	// connection
	private final Connect connect = new Connect();
	private ResultSet resultSet = null;
	private ResultSetMetaData resultSetMetaData = null;
	private final Vector<String> headerTable = new Vector<String>();
	private final Vector<Vector<String>> dataTable = new Vector<Vector<String>>();

	private final InsertPanel insertPanel = new InsertPanel();
	private final JFrame insertFrame = new JFrame();
	
	public ProductFrame() {
		// init products table
		connect.executeUpdate("CREATE TABLE IF NOT EXISTS products (\n" +
				"   id INTEGER PRIMARY KEY,\n" +
				"   name TEXT NOT NULL,\n" +
				"   type TEXT NOT NULL,\n" +
				"   price INTEGER NOT NULL,\n" +
				"   stock INTEGER NOT NULL\n" +
				");");

		// set product form
		this.setTitle("Product");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// init frame
		insertFrame.setTitle("Insert");
		insertFrame.setSize(400, 200);
		insertFrame.add(titleLabel, BorderLayout.NORTH);
		insertFrame.add(insertPanel, BorderLayout.CENTER);

		this.add(titleLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();

		JButton jbtInsert = new JButton("Insert");
		panel.add(jbtInsert);

		this.add(panel, BorderLayout.SOUTH);

		scrollPane.setViewportView(table);

		// get list products
		getListProducts();

		this.setVisible(true);

		jbtInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertFrame.setVisible(true);
				insertPanel.getJbtInsert().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (insertPanel.checkName()) {
							String queryInsert = String.format("INSERT INTO products " +
											"(name, type, price, stock) VALUES('%s', '%s', %d, %d)",
									insertPanel.getJtfName().getText(), String.valueOf(insertPanel.getJcbType().getSelectedItem()),
									(int) insertPanel.getJspPrice().getValue(),(int) insertPanel.getJspStock().getValue());
							connect.executeUpdate(queryInsert);
							JOptionPane.showMessageDialog(null,"Insert success!");
							getListProducts();
						}
					}
				});
			}
		});
	}

	public void getListProducts() {
		try {
			resultSet = connect.getStatement().executeQuery("SELECT * FROM products");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		try {
			resultSetMetaData = resultSet.getMetaData();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			getColumn();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			getDataTable();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		createTableModel();
		table.setModel(dtm);
	}

	private void getColumn() throws SQLException{
		headerTable.clear();
			for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
				headerTable.add(resultSetMetaData.getColumnName(i));
			
	}
	
	private void getDataTable() throws SQLException {
		dataTable.clear();
		
		while(resultSet.next()) {
			Vector<String> rowData = new Vector<String>();
				for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
					if(resultSet.getObject(i) instanceof Integer)
						rowData.add(String.valueOf(resultSet.getObject(i)));
					else rowData.add((String) resultSet.getObject(i));
				}
					
				dataTable.add(rowData);
		}
		
	}
	
	private void createTableModel() {
		dtm.setColumnCount(0);
		dtm.setRowCount(0);
		for(String column : headerTable)
			dtm.addColumn(column);
		
		for(Vector<String> rowsData : dataTable)
			dtm.addRow(rowsData);
	}
	
	public static void main(String args[]) {
		new ProductFrame();
	}
}

