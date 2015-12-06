import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PedidoA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static DerbyConnection Connection;
	private JTextField txtID;
	private JTextField txtStatus;
	private JComboBox txtProducto;
	private JTextField txtCliente;
	private JTextField txtAbono;
	private JTextField txtTotal;
	private JTextField txtBusqueda;
	private JComboBox<String> comboBox;
	private JDatePickerImpl dpBusqueda;
	private JDatePickerImpl dpFechaPedido;
	private JDatePanelImpl dpBusquedaPanel;
	private JDatePanelImpl dpFechaPedidoPanel;
	private UtilDateModel dpBusquedaModel;
	private UtilDateModel dpFechaPedidoModel;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnLista;
	public static PedidoA _instance;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidoA frame = new PedidoA();
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
	private PedidoA() {
		setTitle("Pedido");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 664, 545);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 271, 468, 144);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(SystemColor.inactiveCaptionBorder);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblId.setBounds(22, 53, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setBounds(101, 53, 46, 14);
		contentPane.add(lblStatus);
		
		JLabel lblProductoId = new JLabel("Producto");
		lblProductoId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblProductoId.setToolTipText("");
		lblProductoId.setBounds(236, 53, 60, 14);
		contentPane.add(lblProductoId);
		
		JLabel lblClienteId = new JLabel("Cliente Id");
		lblClienteId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblClienteId.setBounds(406, 53, 72, 14);
		contentPane.add(lblClienteId);
		
		JLabel lblFechaPedido = new JLabel("Fecha Pedido");
		lblFechaPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblFechaPedido.setBounds(22, 82, 80, 14);
		contentPane.add(lblFechaPedido);
		
		JLabel lblAbono = new JLabel("Abono");
		lblAbono.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblAbono.setBounds(236, 82, 46, 14);
		contentPane.add(lblAbono);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblTotal.setBounds(383, 82, 46, 14);
		contentPane.add(lblTotal);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtID.setBounds(45, 51, 46, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtStatus.setText("");
		txtStatus.setBounds(139, 51, 86, 20);
		contentPane.add(txtStatus);
		txtStatus.setColumns(10);
		
		txtProducto = new JComboBox();
		txtProducto.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtProducto.setBounds(300, 51, 96, 20);
		try{
			String sql="SELECT * FROM PASTELERIADBA.PRODUCTO";
			PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
			ArrayList<models.Producto> productos = new ArrayList<models.Producto>();
			ResultSet data = pst.executeQuery();
			while(data.next()){
				productos.add(new models.Producto(
						data.getLong("PRODUCTOID"),
						data.getString("NOMBREPRODUCTO"),
						data.getLong("CANTIDADPRODUCTO"),
						data.getString("DESCRIPCIONPRODUCTO"),
						data.getDouble("PRECIOPRODUCTO")));
			}
			for(models.Producto p: productos){
				txtProducto.addItem(p);
			}
			pst.close();
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"Error al agregar pedido...");
		}
		contentPane.add(txtProducto);
		
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "A\u00f1o");
		dpBusquedaModel = new UtilDateModel();
		dpBusquedaPanel = new JDatePanelImpl(dpBusquedaModel, p);
		
		dpFechaPedidoModel = new UtilDateModel();
		dpFechaPedidoPanel = new JDatePanelImpl(dpFechaPedidoModel, p); 
		dpFechaPedido = new JDatePickerImpl(dpFechaPedidoPanel, new DateLabelFormatter());
		dpFechaPedido.setSize(121, 20);
		dpFechaPedido.setLocation(105, 78);
		contentPane.add(dpFechaPedido);
		
		txtCliente = new JTextField();
		txtCliente.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtCliente.setBounds(475, 51, 39, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtAbono = new JTextField();
		txtAbono.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtAbono.setBounds(287, 80, 86, 20);
		contentPane.add(txtAbono);
		txtAbono.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTotal.setText("");
		txtTotal.setBounds(428, 78, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					models.Producto prod = (models.Producto)txtProducto.getSelectedItem();
					txtTotal.setText("" + (prod.getPrecioProducto() - Double.parseDouble(txtAbono.getText())));
					java.util.Date selectedDate = (java.util.Date)dpFechaPedido.getModel().getValue();
					String sql="INSERT INTO PasteleriaDBA.Pedido(StatusPedido, ProductoID, ClienteID, FechaPedido, AbonoPedido, TotalPedido) values(?,?,?,?,?,?)";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtStatus.getText());
					pst.setLong(2, prod.getProductoID());
					pst.setInt(3,Integer.parseInt(txtCliente.getText()));
					pst.setDate(4,new java.sql.Date(selectedDate.getTime()));
					pst.setDouble(5,Double.parseDouble(txtAbono.getText()));
					pst.setDouble(6,Double.parseDouble(txtTotal.getText()));
					
					pst.execute();
											
					JOptionPane.showMessageDialog(null,"Pedido Guardado...");
					
					pst.close();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al agregar pedido...");
				}
			}
		});
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnAgregar.setBounds(530, 50, 89, 23);
		contentPane.add(btnAgregar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnActualizar.setBounds(530, 79, 89, 23);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
 					DatosUsuario.getInstance().getUserName();
					java.util.Date selectedDate = (java.util.Date)dpFechaPedido.getModel().getValue();
					String sql = "update PasteleriaDBA.Pedido set StatusPedido=?, ProductoID=?, ClienteID=?,"+		
							" FechaPedido=?, AbonoPedido=?, TotalPedido=? where PedidoID=?";

					models.Producto prod = (models.Producto)txtProducto.getSelectedItem();
					txtTotal.setText("" + (prod.getPrecioProducto() - Double.parseDouble(txtAbono.getText())));
 					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtStatus.getText());
					pst.setLong(2, prod.getProductoID());
					pst.setInt(3,Integer.parseInt(txtCliente.getText()));
					pst.setDate(4,new java.sql.Date(selectedDate.getTime()));
					pst.setDouble(5,Double.parseDouble(txtAbono.getText()));
					pst.setDouble(6,Double.parseDouble(txtTotal.getText()));
					pst.setInt(7, Integer.parseInt(txtID.getText()));
					pst.execute();
 				
 					JOptionPane.showMessageDialog(null,"Pedido Actualizado...");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error al actualizar datos de pedido");
				}
			}
		});
		contentPane.add(btnActualizar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar, Actualizar Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 23, 632, 108);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 616, 97);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 135, 636, 76);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 620, 53);
		panel_1.add(separator_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		comboBox.addItem("ID Pedido");
		comboBox.addItem("Fecha");
		comboBox.addItem("ID Cliente");
		comboBox.setBounds(29, 33, 109, 20);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					switch(comboBox.getSelectedIndex()){
					case 0: { // buscar por id
						dpBusqueda.setVisible(false);
						txtBusqueda.setVisible(true);
					} break;
					case 1: { // buscar por fecha
						dpBusqueda.setVisible(true);
						txtBusqueda.setVisible(false);
					} break;
					case 2: { // buscar por id cliente
						dpBusqueda.setVisible(false);
						txtBusqueda.setVisible(true);
					} break;
					default: {
						JOptionPane.showMessageDialog(null,"Opcion no valida...");
					} break;
					}
				}
			}
		});
		
		panel_1.add(comboBox);
		
		
		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(187, 33, 86, 20);
		panel_1.add(txtBusqueda);
		txtBusqueda.setText("");
		txtBusqueda.setColumns(10);
		dpBusqueda = new JDatePickerImpl(dpBusquedaPanel, new DateLabelFormatter());
		dpBusqueda.setBounds(187, 33, 176, 20);
		panel_1.add(dpBusqueda);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(407, 32, 89, 23);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por id
							String statusPedido="";
							Integer pedidoId=0, productoId=0, clienteID=0;
							Double abonoPedido=0.0, TotalPedido=0.0;
							Date fechaPedido = new Date(0);
							
							String sql="select * from PasteleriaDBA.Pedido where PedidoID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							PreparedStatement cp=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							cp.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet copy = cp.executeQuery();
							while(copy.next()){
								pedidoId = copy.getInt("PEDIDOID");
								statusPedido = copy.getString("STATUSPEDIDO");
								productoId = copy.getInt("PRODUCTOID");
								clienteID = copy.getInt("CLIENTEID");
								fechaPedido = copy.getDate("FECHAPEDIDO");
								abonoPedido = copy.getDouble("ABONOPEDIDO");
								TotalPedido = copy.getDouble("TOTALPEDIDO");
							}
							cp.close();
							pst.close();
							txtID.setText(pedidoId.toString());
							txtStatus.setText(statusPedido);
							txtCliente.setText(clienteID.toString());
							txtProducto.setSelectedItem(new models.Producto(productoId));
							dpFechaPedidoModel.setValue(new java.util.Date(fechaPedido.getTime()));
							dpFechaPedidoPanel.updateUI();
							dpFechaPedido.updateUI();
							txtAbono.setText(abonoPedido.toString());
							txtTotal.setText(TotalPedido.toString());
							
						} break;
						case 1: { // buscar por fecha
							String sql="select * from PasteleriaDBA.Pedido where Fecha=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por id cliente
							String sql="select * from PasteleriaDBA.Pedido where ClienteID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
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
		panel_1.add(btnBuscar);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 222, 636, 213);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 620, 190);
		panel_2.add(separator_2);
		
		btnLista = new JButton("Lista");
		btnLista.setBounds(250, 23, 89, 23);
		panel_2.add(btnLista);
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Pedido";
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
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				_instance.setVisible(false);
				OperacionesComunes.getInstance().irMenuPrincipal();
			}
		});
		lblInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblInicio.setBounds(587, 11, 39, 14);
		contentPane.add(lblInicio);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacionesComunes.getInstance().irMenuPrincipal();
				PedidoA.getInstance().dispose();
			}
		});
		btnSalir.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnSalir.setBounds(543, 462, 89, 23);
		contentPane.add(btnSalir);
		
		dpBusqueda.setVisible(false);
		setLocationRelativeTo(null);
	}
	public static PedidoA getInstance(){
		if(_instance == null){
			_instance = new PedidoA();
		}
		
		return _instance;
	}
	}
