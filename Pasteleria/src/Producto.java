import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.proteanit.sql.DbUtils;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Producto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static DerbyConnection Dbc;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
*/
	/**
	 * Create the frame.
	 */
	public Producto() {
		setTitle("Productos");
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		getContentPane().setFont(new Font("Times New Roman", Font.BOLD| Font.ITALIC, 18));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 534, 354);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
				InicioCliente.getInstance().setVisible(true);
				contentPane.getTopLevelAncestor().setVisible(false);
			}
		});
		btnInicio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnInicio.setBounds(20, 269, 71, 27);
		contentPane.add(btnInicio);
					
		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql="select NombreProducto, DescripcionProducto, PrecioProducto from PasteleriaDBA.Producto";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error al obtener los datos de la bd...");
				}
			}
		});
		btnProductos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnProductos.setBounds(208, 38, 99, 27);
		contentPane.add(btnProductos);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, scrollPane, table, btnInicio, btnProductos}));
		setLocationRelativeTo(null);
	}
}
