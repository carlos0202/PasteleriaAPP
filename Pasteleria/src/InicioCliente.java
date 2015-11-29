import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class InicioCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static InicioCliente _instance;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioCliente frame = new InicioCliente();
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
	private InicioCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Heleine Scutt\\workspace\\Pasteleria0\\src\\Img\\cakeP.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProductos = new JButton("Productos");
		btnProductos.setBackground(Color.WHITE);
		Image imgP = new ImageIcon(this.getClass().getResource("/Img/Prod.png")).getImage();
		btnProductos.setIcon(new ImageIcon(imgP));
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto FProducto= new Producto();
				FProducto.setVisible(true);				
			}
		});
		btnProductos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnProductos.setBounds(229, 54, 178, 63);
		contentPane.add(btnProductos);
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.setBackground(Color.WHITE);
		Image imgPd = new ImageIcon(this.getClass().getResource("/Img/list1.png")).getImage();
		btnPedido.setIcon(new ImageIcon(imgPd));
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pedido FPedido= new Pedido();
				FPedido.setVisible(true);
			}
		});
		btnPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnPedido.setBounds(229, 128, 178, 71);
		contentPane.add(btnPedido);
		
		JLabel label = new JLabel("");
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		label.setIcon(new ImageIcon(imglogo));
		label.setBounds(10, 32, 189, 191);
		contentPane.add(label);
	}
	
	public static InicioCliente getInstance(){
		if(_instance == null){
			_instance = new InicioCliente();
		}
		
		return _instance;
	}
}
