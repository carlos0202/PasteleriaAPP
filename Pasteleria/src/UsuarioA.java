import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class UsuarioA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTipo;
	private JTable table;
	private JTextField txtBusqueda;
	private JComboBox<String> comboBox;
	public static UsuarioA _instance;
	private JLabel ID ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioA frame = new UsuarioA();
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
	private UsuarioA() {
		setTitle("Usuarios");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 468, 489);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select UsuarioID, NombreUsuario, TipoUsuario from PasteleriaDBA.Usuario";
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
		btnLista.setBounds(150, 244, 89, 23);
		contentPane.add(btnLista);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 278, 396, 161);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 396, 161);
		panel_3.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar, Actualizar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 21, 427, 94);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 396, 110);
		panel.add(separator);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(313, 26, 89, 23);
		panel.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="INSERT INTO PasteleriaDBA.Usuario(NombreUsuario, TipoUsuario) values(?,?)";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtNombre.getText());
					pst.setString(2,txtTipo.getText());
					
					pst.execute();
											
					JOptionPane.showMessageDialog(null,"Usuario Guardado...");
					
					pst.close();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al agregar Usuario...");
				}
			}
		});
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(313, 60, 89, 23);
		panel.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
 					DatosUsuario.getInstance().getUserName();
					String sql = "update PasteleriaDBA.Usuario set NombreUsuario=?, TipoUsuario=? where UsuarioID=?";

 					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtNombre.getText());
					pst.setString(2,txtTipo.getText());
					pst.setInt(3, Integer.parseInt(ID.getText()));
					pst.execute();
 				
 					JOptionPane.showMessageDialog(null,"Usuario Actualizado...");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error al actualizar datos de Usuario");
				}
			}
		});
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtNombre = new JTextField();
		txtNombre.setBounds(190, 27, 86, 20);
		panel.add(txtNombre);
		txtNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtNombre.setColumns(10);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(73, 26, 107, 23);
		panel.add(lblNombreDeUsuario);
		lblNombreDeUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtTipo = new JTextField();
		txtTipo.setBounds(190, 61, 86, 20);
		panel.add(txtTipo);
		txtTipo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtTipo.setColumns(10);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		lblTipoDeUsuario.setBounds(73, 60, 89, 23);
		panel.add(lblTipoDeUsuario);
		lblTipoDeUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		ID = new JLabel("");
		ID.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		ID.setBounds(32, 26, 31, 18);
		panel.add(ID);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 117, 421, 94);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 396, 71);
		panel_1.add(separator_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(309, 31, 89, 23);
		panel_1.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por id
							Integer UsuarioID=0;
							String Nombre="", Tipo="";
							String sql="select UsuarioID, NombreUsuario, TipoUsuario from PasteleriaDBA.Usuario where UsuarioID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							PreparedStatement cp=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							cp.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet copy = cp.executeQuery();
							while(copy.next()){
								UsuarioID = copy.getInt("USUARIOID");
								Nombre = copy.getString("NOMBREUSUARIO");
								Tipo = copy.getString("TIPOUSUARIO");
							}
							cp.close();
							pst.close();
							ID.setText(UsuarioID.toString());
							txtNombre.setText(Nombre.toString());
							txtTipo.setText(Tipo.toString());
						} break;
						case 1: { // buscar por nombre
							String sql="select UsuarioID, NombreUsuario, TipoUsuario from PasteleriaDBA.Usuario where NombreUsuario=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por tipo
							String sql="select UsuarioID, NombreUsuario, TipoUsuario from PasteleriaDBA.Usuario where TipoUsuario=?";
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
					JOptionPane.showMessageDialog(null,"Usuario no se encuentra, datos invalidos...");
				}
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		
		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(191, 32, 86, 20);
		panel_1.add(txtBusqueda);
		txtBusqueda.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtBusqueda.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(10, 32, 155, 20);
		panel_1.add(comboBox);
		comboBox.addItem("ID Usuario");
		comboBox.addItem("Nombre de Usuario");
		comboBox.addItem("Tipo de Usuario");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(4, 213, 427, 244);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 415, 221);
		panel_2.add(separator_2);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperacionesComunes.getInstance().irMenuPrincipal();
				UsuarioA.getInstance().dispose();
			}
		});
		lblInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblInicio.setBounds(383, 0, 46, 23);
		contentPane.add(lblInicio);
		setLocationRelativeTo(null);
	}
	public static UsuarioA getInstance(){
		if(_instance == null){
			_instance = new UsuarioA();
		}
		
		return _instance;
	}
}
