import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ProductoA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtCantidad;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JSeparator separator;
	private JPanel panel;
	private JComboBox<String> comboBox;
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private JSeparator separator_1;
	private JPanel panel_1;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JButton btnLista;
	private JSeparator separator_2;
	private JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductoA frame = new ProductoA();
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
	public ProductoA() {
		setTitle("Producto");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 485);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProductoId = new JLabel("Producto ID");
		lblProductoId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblProductoId.setBounds(28, 55, 75, 20);
		contentPane.add(lblProductoId);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtID.setBounds(98, 55, 36, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNombreProducto = new JLabel("Nombre Producto");
		lblNombreProducto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNombreProducto.setBounds(144, 57, 96, 17);
		contentPane.add(lblNombreProducto);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtNombre.setBounds(239, 55, 116, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad de Producto");
		lblCantidad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblCantidad.setBounds(365, 57, 116, 17);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtCantidad.setBounds(477, 55, 46, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion Producto");
		lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblDescripcion.setBounds(28, 86, 125, 20);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDescripcion.setBounds(147, 86, 226, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblPrecioProducto = new JLabel("Precio Producto");
		lblPrecioProducto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblPrecioProducto.setBounds(383, 86, 96, 20);
		contentPane.add(lblPrecioProducto);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtPrecio.setBounds(466, 86, 57, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{					
					String sql="INSERT INTO PasteleriaDBA.Producto(NombreProducto, CantidadProducto, DescripcionProducto, PrecioProducto) values(?,?,?,?)";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtNombre.getText());
					pst.setInt(2,Integer.parseInt(txtCantidad.getText()));
					pst.setString(3,txtDescripcion.getText());
					pst.setDouble(4,Double.parseDouble(txtPrecio.getText()));
					
					pst.execute();
											
					JOptionPane.showMessageDialog(null,"Pedido Guardado...");
					
					pst.close();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al agregar pedido...");
				}
			}
		});
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnAgregar.setBounds(537, 54, 89, 23);
		contentPane.add(btnAgregar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnActualizar.setBounds(537, 85, 89, 23);
		contentPane.add(btnActualizar);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar, Actualizar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 17, 646, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		separator = new JSeparator();
		separator.setBounds(6, 16, 634, 95);
		panel.add(separator);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(31, 179, 122, 20);
		contentPane.add(comboBox);
		comboBox.addItem("Producto ID");
		comboBox.addItem("Nombre de Producto");
		comboBox.addItem("Cantidad Producto");
		comboBox.addItem("Precio Producto");
		
		txtBusqueda = new JTextField();
		txtBusqueda.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtBusqueda.setBounds(175, 179, 86, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
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
					JOptionPane.showMessageDialog(null,"Producto no se encuentra, datos invalidos...");
				}
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnBuscar.setBounds(342, 178, 89, 23);
		contentPane.add(btnBuscar);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Busqueda de Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 138, 644, 86);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 628, 63);
		panel_1.add(separator_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(56, 270, 559, 129);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 559, 129);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 240, 650, 205);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 638, 182);
		panel_3.add(separator_2);
		
		btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Producto";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					ResultSet rs= pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al obtener listado...");
				}
			}
		});
		btnLista.setBounds(267, 171, 89, 23);
		panel_3.add(btnLista);
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	}

}
