import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ListaA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtProducto;
	private JTextField txtPedido;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtBusqueda;
	private JTable table;
	private JComboBox<String> comboBox;
	private static ListaA _instance;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaA frame = new ListaA();
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
	private ListaA() {
		setTitle("Lista");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 563, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar, Actualizar Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 25, 530, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 518, 100);
		panel.add(separator);
		
		JLabel lblListaId = new JLabel("Lista ID");
		lblListaId.setBounds(31, 34, 46, 14);
		panel.add(lblListaId);
		lblListaId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtID = new JTextField();
		txtID.setBounds(76, 31, 46, 20);
		panel.add(txtID);
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtID.setColumns(10);
		
		JLabel lblProductoId = new JLabel("Producto ID");
		lblProductoId.setBounds(132, 34, 76, 14);
		panel.add(lblProductoId);
		lblProductoId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtProducto = new JTextField();
		txtProducto.setBounds(196, 31, 46, 20);
		panel.add(txtProducto);
		txtProducto.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtProducto.setColumns(10);
		
		JLabel lblPedidoId = new JLabel("Pedido Id");
		lblPedidoId.setBounds(252, 34, 62, 14);
		panel.add(lblPedidoId);
		lblPedidoId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtPedido = new JTextField();
		txtPedido.setBounds(306, 31, 46, 20);
		panel.add(txtPedido);
		txtPedido.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtPedido.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="INSERT INTO PasteleriaDBA.Lista(ProductoID, PedidoID, CantidadVendida, PrecioVenta) values(?,?,?,?)";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(txtProducto.getText()));
					pst.setInt(2,Integer.parseInt(txtPedido.getText()));
					pst.setInt(3,Integer.parseInt(txtCantidad.getText()));
					pst.setDouble(4,Double.parseDouble(txtPrecio.getText()));
					
					pst.execute();
											
					JOptionPane.showMessageDialog(null,"lista Guardada...");
					
					pst.close();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al agregar lista...");
				}
			}
		});
		btnAgregar.setBounds(376, 30, 89, 23);
		panel.add(btnAgregar);
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblCantidadVendida = new JLabel("Cantidad Vendida");
		lblCantidadVendida.setBounds(31, 61, 102, 20);
		panel.add(lblCantidadVendida);
		lblCantidadVendida.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(132, 61, 55, 20);
		panel.add(txtCantidad);
		txtCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtCantidad.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setBounds(196, 63, 76, 17);
		panel.add(lblPrecioVenta);
		lblPrecioVenta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(262, 61, 86, 20);
		panel.add(txtPrecio);
		txtPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtPrecio.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(376, 70, 89, 23);
		panel.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
 					DatosUsuario.getInstance().getUserName();
					String sql = "update PasteleriaDBA.Lista set PedidoID=?, ProductoID=?, CantidadVendida=?, PrecioVenta=? where ListaID=?";

 					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
 					pst.setInt(1,Integer.parseInt(txtProducto.getText()));
 					pst.setInt(2,Integer.parseInt(txtPedido.getText())); 					
 					pst.setInt(3,Integer.parseInt(txtCantidad.getText()));
					pst.setDouble(4,Double.parseDouble(txtPrecio.getText()));
					pst.setInt(5, Integer.parseInt(txtID.getText()));
					pst.execute();
 				
 					JOptionPane.showMessageDialog(null,"Lista Actualizado...");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error al actualizar datos de Lista");
				}
			}
		});
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(36, 194, 132, 25);
		contentPane.add(comboBox);
		comboBox.addItem("ID Lista");
		comboBox.addItem("Pedido ID");
		comboBox.addItem("Producto ID");
		comboBox.addItem("Cantidad");
		comboBox.addItem("Precio");
		
		
		txtBusqueda = new JTextField();
		txtBusqueda.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtBusqueda.setBounds(188, 196, 86, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por id
							Integer ListaID=0, PedidoId=0, ProductoId=0, Cantidad=0;
							Double Precio=0.0;
							String sql="select * from PasteleriaDBA.Lista where ListaID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							PreparedStatement cp=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							cp.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet copy = cp.executeQuery();
							while(copy.next()){
								ListaID = copy.getInt("LISTAID");
								ProductoId = copy.getInt("PRODUCTOID");
								PedidoId = copy.getInt("PEDIDOID");								
								Cantidad = copy.getInt("CANTIDADVENDIDA");
								Precio= copy.getDouble("PRECIOVENTA");
							}
							cp.close();
							pst.close();
							txtID.setText(ListaID.toString());
							txtProducto.setText(ProductoId.toString());
							txtPedido.setText(PedidoId.toString());							
							txtCantidad.setText(Cantidad.toString());
							txtPrecio.setText(Precio.toString());
							
						} break;
						case 1: { // buscar por Pedido
							String sql="select * from PasteleriaDBA.Lista where PedidoID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por Producto
							String sql="select * from PasteleriaDBA.Lista where ProductoID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 3: { // buscar por Cantidad
							String sql="select * from PasteleriaDBA.Lista where CantidadVendida=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 4: { // buscar por Precio
							String sql="select * from PasteleriaDBA.Lista where PrecioVenta=?";
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
					JOptionPane.showMessageDialog(null,"Pedido no se encuentra, datos invalidos...");
				}
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnBuscar.setBounds(331, 195, 89, 23);
		contentPane.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 149, 536, 103);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 524, 80);
		panel_1.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 298, 448, 114);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(4, 253, 532, 184);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 516, 161);
		panel_2.add(separator_2);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Lista";
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
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnLista.setBounds(231, 22, 89, 23);
		panel_2.add(btnLista);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				_instance.setVisible(false);
				OperacionesComunes.getInstance().irMenuPrincipal();
			}
		});
		lblInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblInicio.setBounds(491, 0, 45, 27);
		contentPane.add(lblInicio);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacionesComunes.getInstance().irMenuPrincipal();
				ListaA.getInstance().dispose();
			}
		});
		btnSalir.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnSalir.setBounds(447, 450, 89, 23);
		contentPane.add(btnSalir);
		
		setLocationRelativeTo(null);
	}
	
	
	public static ListaA getInstance(){
		if(_instance == null){
			_instance = new ListaA();
		}
		
		return _instance;
	}
}
