import java.awt.EventQueue;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public ClienteA() {
		setTitle("Cliente");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 479);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClienteId = new JLabel("Cliente ID");
		lblClienteId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblClienteId.setBounds(21, 48, 68, 14);
		contentPane.add(lblClienteId);
		
		JButton btnAgregar = new JButton("Agregar");
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
		btnAgregar.setBounds(542, 44, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DatosUsuario.getInstance().getUserName();
					String sql = "update PasteleriaDBA.Cliente set NombreCliente=?, DireccionCliente=?, TelefonoCliente=?,"+		
							" CelularCliente=?, EmailCliente=? where ClienteID='"+txtIdCliente.getText()+"'";
					
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtNombre.getText());
					pst.setString(2,txtDireccion.getText());
					pst.setString(3,txtTelefono.getText());
					pst.setString(4,txtCelular.getText());
					pst.setString(5,txtEmail.getText());

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
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por id
							String sql="select * from PasteleriaDBA.Cliente where ClienteID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
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
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnLista.setBounds(235, 272, 89, 23);
		contentPane.add(btnLista);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtIdCliente.setBounds(88, 45, 44, 20);
		contentPane.add(txtIdCliente);
		txtIdCliente.setColumns(10);
		
		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNombreCliente.setBounds(142, 48, 86, 14);
		contentPane.add(lblNombreCliente);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtNombre.setBounds(238, 45, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccionCliente = new JLabel("Direccion Cliente");
		lblDireccionCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblDireccionCliente.setBounds(21, 97, 99, 23);
		contentPane.add(lblDireccionCliente);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtDireccion.setBounds(119, 100, 399, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelefonoCliente = new JLabel("Telefono Cliente");
		lblTelefonoCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblTelefonoCliente.setBounds(339, 48, 89, 19);
		contentPane.add(lblTelefonoCliente);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtTelefono.setBounds(432, 47, 86, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblCelularCliente = new JLabel("Celular Cliente");
		lblCelularCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblCelularCliente.setBounds(21, 73, 86, 23);
		contentPane.add(lblCelularCliente);
		
		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtCelular.setBounds(109, 73, 86, 20);
		contentPane.add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail Cliente");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblEmail.setBounds(205, 73, 86, 23);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtEmail.setBounds(287, 76, 232, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar, Actualizar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 665, 140);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 653, 134);
		panel.add(separator);
		
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
		panel_3.setBounds(4, 243, 671, 204);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 677, 181);
		panel_3.add(separator_2);
	}
}
