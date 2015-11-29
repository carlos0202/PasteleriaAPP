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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 489);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
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
		btnAgregar.setBounds(253, 46, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnActualizar.setBounds(253, 80, 89, 23);
		contentPane.add(btnActualizar);
		
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
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNombreDeUsuario.setBounds(10, 46, 107, 23);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario");
		lblTipoDeUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblTipoDeUsuario.setBounds(10, 71, 89, 23);
		contentPane.add(lblTipoDeUsuario);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtNombre.setBounds(127, 47, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtTipo.setBounds(127, 72, 86, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
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
		panel.setBounds(4, 11, 408, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 396, 110);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 117, 408, 94);
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
							String sql="select * from PasteleriaDBA.Usuario where UsuarioID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 1: { // buscar por nombre
							String sql="select * from PasteleriaDBA.Usuario where NombreUsuario=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setString(1,txtBusqueda.getText());
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por tipo
							String sql="select * from PasteleriaDBA.Usuario where TipoUsuario=?";
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
	}
	public static UsuarioA getInstance(){
		if(_instance == null){
			_instance = new UsuarioA();
		}
		
		return _instance;
	}
}
