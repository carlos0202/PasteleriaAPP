import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.proteanit.sql.DbUtils;

import java.awt.Component;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class PedidoEA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static DerbyConnection Connection;
	private JTextField txtID;
	private JTextField txtStatus;
	private JTextField txtProducto;
	private JTextField txtCliente;
	private JTextField txtAbono;
	private JTextField txtTotal;
	private JTextField txtBusqueda;
	private JComboBox comboBox;
	private int dia=0,mes=0;
	private JFormattedTextField ftxtFecha=null;
	private JFormattedTextField ftxtFPedido=null;
	

	public void MascaraFecha(){
		MaskFormatter Mascara=null;
		try{
			Mascara= new MaskFormatter("##-##-####");
			Mascara.setPlaceholderCharacter('_');
			Mascara.install(ftxtFecha);
			Mascara.install(ftxtFPedido);
		}catch(ParseException e){
			JOptionPane.showMessageDialog(null,"Formato de fecha incorrecta...");
		}
		
	}
/*	public void Actualizar(){
		try{
			String sql="select * from PasteleriaDBA.Pedido";
			PreparedStatement pst=Dbc.DbStart().prepareStatement(sql);
			ResultSet rs= pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"Error al actualizar los datos de la bd...");
		}
	}*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidoEA frame = new PedidoEA();
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
	public PedidoEA() {
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Heleine Scutt\\workspace\\Pasteleria0\\src\\Img\\cakeP.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 478);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 215, 517, 210);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBackground(SystemColor.inactiveCaptionBorder);
		
		Connection= new DerbyConnection();
				
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error...");
				}
			}
		});
		Image imgbtnI = new ImageIcon(this.getClass().getResource("/Img/inicio.png")).getImage();
		btnInicio.setIcon(new ImageIcon(imgbtnI));
		btnInicio.setBackground(SystemColor.inactiveCaptionBorder);
		btnInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnInicio.setBounds(10, 388, 134, 51);
		contentPane.add(btnInicio);
		
		JButton btnListaDePedido = new JButton("Lista de Pedido");
		btnListaDePedido.setBackground(SystemColor.inactiveCaptionBorder);
		Image imgbtnLP = new ImageIcon(this.getClass().getResource("/Img/list.png")).getImage();
		btnListaDePedido.setIcon(new ImageIcon(imgbtnLP));
		btnListaDePedido.addActionListener(new ActionListener() {
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
		btnListaDePedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnListaDePedido.setBounds(598, 79, 196, 47);
		contentPane.add(btnListaDePedido);
		
		MascaraFecha();
		
		JButton btnAgregarPedido = new JButton("Agregar Pedido");
		btnAgregarPedido.setBackground(SystemColor.inactiveCaptionBorder);
		Image imgAP = new ImageIcon(this.getClass().getResource("/Img/addP.png")).getImage();
		btnAgregarPedido.setIcon(new ImageIcon(imgAP));
		btnAgregarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					dia=Integer.parseInt(ftxtFecha.getText().substring(0,2));
					dia=Integer.parseInt(ftxtFPedido.getText().substring(0,2));
					mes=Integer.parseInt(ftxtFecha.getText().substring(3,5));
					mes=Integer.parseInt(ftxtFPedido.getText().substring(3,5));
					
					if(dia>0 && dia<=31 && mes>0 && mes<=12){
						
					}else{
						JOptionPane.showMessageDialog(null,"Fecha Incorrecta...");
					}
					String sql="INSERT INTO PasteleriaDBA.Pedido(StatusPedido, ProductoID, ClienteID, FechaPedido, AbonoPedido, TotalPedido) values(?,?,?,?,?,?)";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setString(1,txtStatus.getText());
					pst.setString(2,txtProducto.getText());
					pst.setString(3,txtCliente.getText());
					pst.setString(4,ftxtFPedido.getText());
					pst.setString(5,txtAbono.getText());
					pst.setString(6,txtTotal.getText());
					
					pst.executeUpdate(sql);
											
					JOptionPane.showMessageDialog(null,"Pedido Guardado...");
					
					pst.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Error al agregar pedido...");
				}
			}
		});
		btnAgregarPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnAgregarPedido.setBounds(328, 79, 196, 47);
		contentPane.add(btnAgregarPedido);
		
		JButton btnBuscarPedido = new JButton("Buscar Pedido");
		btnBuscarPedido.setBackground(SystemColor.inactiveCaptionBorder);
		btnBuscarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(e.getSource()==comboBox){
						String sql="select * from PasteleriaDBA.Pedido where PedidoID=?";
						PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
						pst.setString(1,(String)txtBusqueda.getText());
						ResultSet rs= pst.executeQuery();
						
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					else if (e.getSource()==comboBox){
						String sql="select * from PasteleriaDBA.Pedido where Fecha=?";
						PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
						pst.setString(1,(String)txtBusqueda.getText());
						ResultSet rs= pst.executeQuery();
						
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					else{
						JOptionPane.showMessageDialog(null,"Opcion no valida...");
					}
										
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Pedido no se encuentra...");
				}
			}
		});
		Image imgbtnBus = new ImageIcon(this.getClass().getResource("/Img/buscar.png")).getImage();
		btnBuscarPedido.setIcon(new ImageIcon(imgbtnBus));
		btnBuscarPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnBuscarPedido.setBounds(328, 11, 196, 47);
		contentPane.add(btnBuscarPedido);
		
		JButton btnActualizarPedido = new JButton("Actualizar Pedido");
		btnActualizarPedido.setBackground(SystemColor.inactiveCaptionBorder);
		btnActualizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DatosUsuario.getInstance().getUserName();
					String sql="update PasteleriaDBA.Pedido set PedidoID='"+txtID.getText()+
							   "' ,StatusPedido ='"+txtStatus.getText()+"' ,ProductoID='"+txtProducto.getText()+
							   "' ,ClienteID='"+txtCliente.getText()+"',FechaPedido='"+ftxtFPedido.getText()+
							   "' ,AbonoPedido='"+txtAbono.getText()+"' ,TotalPedido='"+txtTotal.getText()+"' where PedidoID='"+txtID.getText()+"' ";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.executeUpdate();
				
					JOptionPane.showMessageDialog(null,"Pedido Actualizado...");
				
					pst.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al actualizar pedido...");
				}
			}
		});
		btnActualizarPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		Image imgbtnAct = new ImageIcon(this.getClass().getResource("/Img/Update.png")).getImage();
		btnActualizarPedido.setIcon(new ImageIcon(imgbtnAct));
		btnActualizarPedido.setBounds(598, 11, 196, 47);
		contentPane.add(btnActualizarPedido);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblId.setBounds(10, 215, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblStatus.setBounds(10, 240, 46, 14);
		contentPane.add(lblStatus);
		
		JLabel lblProductoId = new JLabel("Producto Id");
		lblProductoId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblProductoId.setToolTipText("");
		lblProductoId.setBounds(10, 265, 72, 14);
		contentPane.add(lblProductoId);
		
		JLabel lblClienteId = new JLabel("Cliente Id");
		lblClienteId.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblClienteId.setBounds(10, 290, 72, 14);
		contentPane.add(lblClienteId);
		
		JLabel lblFechaPedido = new JLabel("Fecha Pedido");
		lblFechaPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblFechaPedido.setBounds(10, 315, 80, 14);
		contentPane.add(lblFechaPedido);
		
		JLabel lblAbono = new JLabel("Abono");
		lblAbono.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblAbono.setBounds(10, 340, 46, 14);
		contentPane.add(lblAbono);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblTotal.setBounds(10, 365, 46, 14);
		contentPane.add(lblTotal);
		
		txtID = new JTextField();
		txtID.setBounds(106, 209, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setText("");
		txtStatus.setBounds(106, 238, 86, 20);
		contentPane.add(txtStatus);
		txtStatus.setColumns(10);
		
		txtProducto = new JTextField();
		txtProducto.setText("");
		txtProducto.setBounds(106, 263, 86, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(106, 288, 86, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtAbono = new JTextField();
		txtAbono.setBounds(106, 338, 86, 20);
		contentPane.add(txtAbono);
		txtAbono.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setText("");
		txtTotal.setBounds(106, 363, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel label = new JLabel("");
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		label.setIcon(new ImageIcon(imglogo));
		label.setBounds(22, 11, 214, 168);
		contentPane.add(label);
		
		comboBox = new JComboBox();
		comboBox.setBounds(247, 151, 109, 20);
		contentPane.add(comboBox);
		comboBox.addItem("ID Pedido");
		comboBox.addItem("Fecha");
		comboBox.addItem("ID Cliente");
		
		ftxtFPedido = new JFormattedTextField();
		ftxtFPedido.setBounds(105, 313, 87, 20);
		contentPane.add(ftxtFPedido);
		
	}
}
