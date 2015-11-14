import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Color;

public class InicioEmpleado extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioEmpleado frame = new InicioEmpleado();
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
	public InicioEmpleado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Heleine Scutt\\workspace\\Pasteleria0\\src\\Img\\cakeP.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 303);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProducto = new JButton("Producto");
		btnProducto.setBackground(Color.WHITE);
		Image imgP = new ImageIcon(this.getClass().getResource("/Img/Prod.png")).getImage();
		btnProducto.setIcon(new ImageIcon(imgP));
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto FProducto= new Producto();
				FProducto.setVisible(true);	
			}
		});
		btnProducto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnProducto.setBounds(418, 44, 168, 50);
		contentPane.add(btnProducto);
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.setBackground(Color.WHITE);
		Image imgPd = new ImageIcon(this.getClass().getResource("/Img/list1.png")).getImage();
		btnPedido.setIcon(new ImageIcon(imgPd));
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoEA FPedido= new PedidoEA();
				FPedido.setVisible(true);
			}
		});
		btnPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnPedido.setBounds(418, 131, 168, 50);
		contentPane.add(btnPedido);
		
		JButton btnLista = new JButton("Lista");
		btnLista.setBackground(Color.WHITE);
		Image imgLis = new ImageIcon(this.getClass().getResource("/Img/list.png")).getImage();
		btnLista.setIcon(new ImageIcon(imgLis));
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnLista.setBounds(220, 44, 156, 50);
		contentPane.add(btnLista);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.setBackground(new Color(255, 255, 255));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Image imgCl = new ImageIcon(this.getClass().getResource("/Img/client.png")).getImage();
		btnCliente.setIcon(new ImageIcon(imgCl));
		btnCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnCliente.setBounds(328, 192, 140, 50);
		contentPane.add(btnCliente);
		
		JButton btnFactura = new JButton("Factura");
		btnFactura.setBackground(Color.WHITE);
		Image imgFa = new ImageIcon(this.getClass().getResource("/Img/cash.png")).getImage();
		btnFactura.setIcon(new ImageIcon(imgFa));
		btnFactura.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnFactura.setBounds(220, 131, 156, 50);
		contentPane.add(btnFactura);
		
		JLabel label = new JLabel("");
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		label.setIcon(new ImageIcon(imglogo));
		label.setBounds(10, 32, 189, 191);
		contentPane.add(label);
	}
}
