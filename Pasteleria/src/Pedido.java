import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Pedido extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblStatus;
	private JLabel lblFecha;
	private JLabel lblFechaPedido;
	private JLabel lblTotal;
	public static DerbyConnection Dbc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedido frame = new Pedido();
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
	public Pedido() {
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 447, 349);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		textField.setBounds(209, 64, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
				
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboBox.setBounds(209, 33, 202, 20);
		contentPane.add(comboBox);
		comboBox.addItem("ID Pedido");
		
		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblStatus.setBounds(209, 95, 115, 20);
		contentPane.add(lblStatus);
		
		Dbc =new DerbyConnection();
		
		lblFecha = new JLabel("");
		lblFecha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblFecha.setBounds(209, 115, 115, 20);
		contentPane.add(lblFecha);
		
		lblFechaPedido = new JLabel("");
		lblFechaPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblFechaPedido.setBounds(209, 135, 115, 20);
		contentPane.add(lblFechaPedido);
		
		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblTotal.setBounds(209, 154, 115, 20);
		contentPane.add(lblTotal);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="select * from PasteleriaDBA.Pedido where PedidoID=?";
					PreparedStatement pst= DerbyConnection.DbStart().prepareStatement(query);
					pst.setString(1,(String)textField.getText());
					ResultSet rs= pst.executeQuery();
					while(rs.next()){
						lblStatus.setText(rs.getString("StatusPedido"));
						lblFecha.setText(rs.getString("Fecha"));
						lblFechaPedido.setText(rs.getString("FechaPedido"));
						lblTotal.setText(rs.getString("TotalPedido"));
					
					}
					pst.close();
				} catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al obtener los datos de la bd...");
			}
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnBuscar.setBounds(316, 62, 95, 20);
		contentPane.add(btnBuscar);
		

		
		JButton btnInicio = new JButton("Inicio");
		Image imgInicio = new ImageIcon(this.getClass().getResource("/Img/Inicio.png")).getImage();
		btnInicio.setIcon(new ImageIcon(imgInicio));
		btnInicio.setBackground(Color.WHITE);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioCliente.getInstance().setVisible(true);
				contentPane.getTopLevelAncestor().setVisible(false);
			}
		});
		btnInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnInicio.setBounds(175, 247, 138, 52);
		contentPane.add(btnInicio);
		
		JLabel label = new JLabel("");
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		label.setIcon(new ImageIcon(imglogo));
		label.setBounds(10, 23, 199, 192);
		contentPane.add(label);
		setLocationRelativeTo(null);
	}
}
