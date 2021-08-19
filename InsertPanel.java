import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertPanel extends JPanel {
	private JTextField jtfName = new JTextField();
	private JSpinner jspPrice = new JSpinner(new SpinnerNumberModel(1000, 1000, 200000, 10000));
	private JSpinner jspStock = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
	private String[] type = {"Drink", "Food"};
	private JComboBox<String> jcbType = new JComboBox<String>(type);
	private JButton jbtInsert = new JButton("Submit");
	
	public InsertPanel() {
		setLayout(new BorderLayout(10, 10));
		JPanel dataPanel = createInsertLayout();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtInsert);
		
		add(dataPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Getter method for property jbtInsert.
	 *
	 * @return property value of jbtInsert
	 */
	public JButton getJbtInsert() {
		return jbtInsert;
	}

	/**
	 * Getter method for property jtfName.
	 *
	 * @return property value of jtfName
	 */
	public JTextField getJtfName() {
		return jtfName;
	}

	/**
	 * Getter method for property jspPrice.
	 *
	 * @return property value of jspPrice
	 */
	public JSpinner getJspPrice() {
		return jspPrice;
	}

	/**
	 * Getter method for property jspStock.
	 *
	 * @return property value of jspStock
	 */
	public JSpinner getJspStock() {
		return jspStock;
	}

	/**
	 * Getter method for property jcbType.
	 *
	 * @return property value of jcbType
	 */
	public JComboBox<String> getJcbType() {
		return jcbType;
	}

	private JPanel createInsertLayout() {
		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
		panel.add(new JLabel("Name"));
		panel.add(jtfName);
		panel.add(new JLabel("Type"));
		panel.add(jcbType);
		panel.add(new JLabel("Price(1 - 200000)"));
		panel.add(jspPrice);
		panel.add(new JLabel("Stock(1 - 100"));
		panel.add(jspStock);
		return panel;
	}
	
	public boolean checkName() {
		if(jtfName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Name cannot be empty!");
			return false;
		}
			
		return true;
	}
}
