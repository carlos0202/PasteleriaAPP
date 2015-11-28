import java.awt.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class FacturaA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtPedido;
	private JTextField txtDetalle;
	private JTextField txtDEscuento;
	private JTextField txtTotal;
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
					FacturaA frame = new FacturaA();
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
	public FacturaA() {
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdFactura = new JLabel("ID Factura");
		lblIdFactura.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblIdFactura.setBounds(31, 40, 73, 20);
		contentPane.add(lblIdFactura);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtID.setBounds(93, 40, 35, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblIdPedido = new JLabel("ID Pedido");
		lblIdPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblIdPedido.setBounds(134, 43, 73, 17);
		contentPane.add(lblIdPedido);
		
		txtPedido = new JTextField();
		txtPedido.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtPedido.setBounds(191, 40, 35, 20);
		contentPane.add(txtPedido);
		txtPedido.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Detalle ");
		lblDetalle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblDetalle.setBounds(31, 75, 46, 14);
		contentPane.add(lblDetalle);
		
		txtDetalle = new JTextField();
		txtDetalle.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDetalle.setBounds(81, 71, 374, 20);
		contentPane.add(txtDetalle);
		txtDetalle.setColumns(10);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblDescuento.setBounds(236, 43, 63, 17);
		contentPane.add(lblDescuento);
		
		txtDEscuento = new JTextField();
		txtDEscuento.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtDEscuento.setBounds(293, 40, 57, 20);
		contentPane.add(txtDEscuento);
		txtDEscuento.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblTotal.setBounds(360, 43, 46, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtTotal.setBounds(392, 40, 63, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DatosUsuario.getInstance().getUserName();
					String sql = "update PasteleriaDBA.Factura set PedidoID=?, DetalleFacturado=?, Descuento=?,"+		
							" TotalFacturado=? where FacturaID='"+txtID.getText()+"'";
					/*String sql="update PasteleriaDBA.Pedido set PedidoID='"+txtID.getText()+
							   "' ,StatusPedido ='"+txtStatus.getText()+"' ,ProductoID='"+txtProducto.getText()+
							   "' ,ClienteID='"+txtCliente.getText()+"',FechaPedido='"+ftxtFPedido.getText()+
							   "' ,AbonoPedido='"+txtAbono.getText()+"' ,TotalPedido='"+txtTotal.getText()+"' where PedidoID='"+txtID.getText()+"' ";*/
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					
					pst.setInt(1,Integer.parseInt(txtPedido.getText()));
					pst.setString(2,txtDetalle.getText());
					pst.setDouble(3,Double.parseDouble(txtDEscuento.getText()));
					pst.setDouble(4,Double.parseDouble(txtTotal.getText()));

					pst.execute();
				
					JOptionPane.showMessageDialog(null,"Pedido Actualizado...");
				
					pst.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al actualizar pedido...");
				}
			}
		});
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnActualizar.setBounds(489, 55, 89, 23);
		contentPane.add(btnActualizar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Actualizar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 12, 599, 99);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 587, 76);
		panel.add(separator);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(31, 162, 126, 23);
		comboBox.addItem("ID Factura");
		comboBox.addItem("ID Pedido");
		comboBox.addItem("ID Usuario");
		comboBox.addItem("Fecha");
		contentPane.add(comboBox);
		
		txtBusqueda = new JTextField();
		txtBusqueda.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		txtBusqueda.setBounds(191, 163, 86, 20);
		contentPane.add(txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					switch(comboBox.getSelectedIndex()){
						case 0: { // buscar por id
							String sql="select * from PasteleriaDBA.Factura where FacturaID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 1: { // buscar por id Pedido
							String sql="select * from PasteleriaDBA.Factura where PedidoID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 2: { // buscar por id Usuario
							String sql="select * from PasteleriaDBA.Factura where UsuarioID=?";
							PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
							pst.setInt(1,Integer.parseInt(txtBusqueda.getText()));
							ResultSet rs= pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
						} break;
						case 3: { // buscar por Fecha
							String sql="select * from PasteleriaDBA.Factura where FechaFacturado=?";
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
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnBuscar.setBounds(353, 162, 89, 23);
		contentPane.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(4, 119, 595, 99);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 16, 583, 76);
		panel_1.add(separator_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(6, 241, 593, 201);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 16, 583, 201);
		panel_2.add(separator_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 59, 499, 118);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Factura";
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
		btnLista.setBounds(258, 25, 89, 23);
		panel_2.add(btnLista);
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	}
}
