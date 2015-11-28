import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ProductoE extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBusqueda;
	private JTable table;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductoE frame = new ProductoE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductoE() {
		setTitle("Producto");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 367);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(34, 53, 110, 20);
		comboBox.addItem("ID Producto");
		comboBox.addItem("Nombre Producto");
		comboBox.addItem("Cantidad Producto");
		comboBox.addItem("Precio Producto");
		contentPane.add(comboBox);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtBusqueda.setBounds(161, 53, 86, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por Id
							String sql="select * from PasteleriaDBA.Producto where ProductoID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 1: { // buscar por Nombre
							String sql="select * from PasteleriaDBA.Producto where NombreProducto=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por Cantidad
							String sql="select * from PasteleriaDBA.Producto where CantidadProducto=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 3: { // buscar por Precio
							String sql="select * from PasteleriaDBA.Producto where PrecioProducto=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setDouble(1,Double.parseDouble(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						default: {
							JOptionPane.showMessageDialog(null,"Opcion no valida...");
						} break;
					}
										
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Usuario no se encuentra, datos invalidos...");
				}
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnBuscar.setBounds(266, 52, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 158, 422, 110);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 11, 520, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 504, 68);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(4, 101, 520, 197);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 504, 174);
		panel_1.add(separator_1);
		
		JButton btnListaDeProductos = new JButton("Lista de Productos");
		btnListaDeProductos.setBounds(210, 28, 121, 23);
		panel_1.add(btnListaDeProductos);
		btnListaDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Producto";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al obtener los datos de la bd...");
				}
			}
		});
		btnListaDeProductos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	}

}
