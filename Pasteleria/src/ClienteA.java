import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ClienteA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdCliente;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtBusqueda;
	private JTable table;
	private JComboBox<String> comboBox;
	public static ClienteA _instance;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteA frame = new ClienteA();
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
	private ClienteA() {
		setTitle("Cliente");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DatosUsuario.getInstance().getUserName();
					String sql = "update PasteleriaDBA.Cliente set NombreCliente=?, DireccionCliente=?, TelefonoCliente=?,"+		
								 " CelularCliente=?, EmailCliente=? where ClienteID=?";
					
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtNombre.getText());
					pst.setString(2,txtDireccion.getText());
					pst.setString(3,txtTelefono.getText());
					pst.setString(4,txtCelular.getText());
					pst.setString(5,txtEmail.getText());
					pst.setInt(6,Integer.parseInt(txtIdCliente.getText()));

					pst.execute();
				
					JOptionPane.showMessageDialog(null,"Cliente Actualizado...");
				
					pst.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al actualizar Cliente...");
				}
			}
		});
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnActualizar.setBounds(542, 97, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Cliente";
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
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por id
							String Nombre="", Direccion="", Celular="",Telefono="",Email="";
							Integer ClienteId=0;
							
							String sql="select * from PasteleriaDBA.Cliente where ClienteID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							PreparedStatement cp=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							cp.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet copy = cp.executeQuery();
							while(copy.next()){
								ClienteId = copy.getInt("CLIENTEID");
								Nombre= copy.getString("NOMBRECLIENTE");
								Direccion = copy.getString("DIRECCIONCLIENTE");
								Telefono = copy.getString("TELEFONOCLIENTE");
								Celular= copy.getString("CELULARCLIENTE");
								Email = copy.getString("EMAILCLIENTE");
							}
							cp.close();
							pst.close();
							txtIdCliente.setText(ClienteId.toString());
							txtNombre.setText(Nombre);
							txtCelular.setText(Celular);
							txtDireccion.setText(Direccion);
							txtEmail.setText(Email);
							txtTelefono.setText(Telefono);
						} break;
						case 1: { // buscar por Nombre
							String sql="select * from PasteleriaDBA.Cliente where NombreCliente=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por telefono
							String sql="select * from PasteleriaDBA.Cliente where TelefonoCliente=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 3: { // buscar por celular
							String sql="select * from PasteleriaDBA.Cliente where CelularCliente=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
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
		btnBuscar.setBounds(317, 203, 89, 23);
		contentPane.add(btnBuscar);
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnLista.setBounds(235, 272, 89, 23);
		contentPane.add(btnLista);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar, Actualizar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 21, 677, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 653, 134);
		panel.add(separator);
		
		JLabel lblDireccionCliente = new JLabel("Direccion Cliente");
		lblDireccionCliente.setBounds(6, 96, 99, 23);
		panel.add(lblDireccionCliente);
		lblDireccionCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(104, 97, 399, 20);
		panel.add(txtDireccion);
		txtDireccion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtDireccion.setColumns(10);
		
		JLabel lblCelularCliente = new JLabel("Celular Cliente");
		lblCelularCliente.setBounds(6, 62, 86, 23);
		panel.add(lblCelularCliente);
		lblCelularCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtCelular = new JTextField();
		txtCelular.setBounds(94, 63, 86, 20);
		panel.add(txtCelular);
		txtCelular.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtCelular.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail Cliente");
		lblEmail.setBounds(190, 63, 86, 23);
		panel.add(lblEmail);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(271, 63, 232, 20);
		panel.add(txtEmail);
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtEmail.setColumns(10);
		
		JLabel lblClienteId = new JLabel("Cliente ID");
		lblClienteId.setBounds(6, 37, 68, 14);
		panel.add(lblClienteId);
		lblClienteId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(73, 31, 44, 20);
		panel.add(txtIdCliente);
		txtIdCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtIdCliente.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setBounds(127, 38, 86, 14);
		panel.add(lblNombreCliente);
		lblNombreCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtNombre = new JTextField();
		txtNombre.setBounds(223, 32, 86, 20);
		panel.add(txtNombre);
		txtNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtNombre.setColumns(10);
		
		JLabel lblTelefonoCliente = new JLabel("Telefono Cliente");
		lblTelefonoCliente.setBounds(319, 33, 89, 19);
		panel.add(lblTelefonoCliente);
		lblTelefonoCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(418, 32, 86, 20);
		panel.add(txtTelefono);
		txtTelefono.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtTelefono.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(525, 37, 89, 23);
		panel.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="INSERT INTO PasteleriaDBA.Cliente(NombreCliente, DireccionCliente, TelefonoCliente, CelularCliente, EmailCliente) values(?,?,?,?,?)";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtNombre.getText());
					pst.setString(2,txtDireccion.getText());
					pst.setString(3,txtTelefono.getText());
					pst.setString(4,txtCelular.getText());
					pst.setString(5,txtEmail.getText());
					
					
					pst.execute();
											
					JOptionPane.showMessageDialog(null,"Cliente Guardado...");
					
					pst.close();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al agregar Cliente...");
				}
			}
		});
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(21, 203, 120, 23);
		contentPane.add(comboBox);
		comboBox.addItem("ID Cliente");
		comboBox.addItem("Nombre Cliente");
		comboBox.addItem("Telefono Cliente");
		comboBox.addItem("Celular Cliente");
		
		txtBusqueda = new JTextField();
		txtBusqueda.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtBusqueda.setBounds(151, 203, 110, 22);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(4, 156, 677, 87);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 665, 64);
		panel_1.add(separator_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(58, 309, 572, 120);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 572, 120);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(4, 243, 677, 204);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 661, 181);
		panel_3.add(separator_2);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				_instance.setVisible(false);
				OperacionesComunes.getInstance().irMenuPrincipal();
			}
		});
		lblInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblInicio.setBounds(635, 0, 46, 25);
		contentPane.add(lblInicio);
	}
	public static ClienteA getInstance(){
		if(_instance == null){
			_instance = new ClienteA();
		}
		
		return _instance;
	}
}
