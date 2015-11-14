import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.proteanit.sql.DbUtils;

import java.awt.Component;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Producto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static DerbyConnection Dbc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Producto frame = new Producto();
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
	public Producto() {
		setTitle("Productos");
		getContentPane().setFont(new Font("Times New Roman", Font.BOLD| Font.ITALIC, 18));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 76, 498, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Dbc= new DerbyConnection();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 498, 182);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioCliente FInicioCl=new InicioCliente();
				FInicioCl.setVisible(true);
			}
		});
		btnInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnInicio.setBounds(20, 269, 90, 46);
		contentPane.add(btnInicio);
					
		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select * from PasteleriaDBA.Producto";
					PreparedStatement pst=Dbc.DbStart().prepareStatement(sql);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al obtener los datos de la bd...");
				}
			}
		});
		btnProductos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnProductos.setBounds(207, 24, 124, 27);
		contentPane.add(btnProductos);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, scrollPane, table, btnInicio, btnProductos}));
	}
}
