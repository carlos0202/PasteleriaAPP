import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioAdministrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static InicioAdministrador _instance;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioAdministrador frame = new InicioAdministrador();
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
	private InicioAdministrador() {
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 312);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnProducto = new JButton("Producto");
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoA.getInstance().setVisible(true);
			}
		});
		btnProducto.setBackground(Color.WHITE);
		Image imgPro = new ImageIcon(this.getClass().getResource("/Img/Prod.png")).getImage();
		btnProducto.setIcon(new ImageIcon(imgPro));
		btnProducto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnProducto.setBounds(431, 20, 162, 49);
		contentPane.add(btnProducto);
		
		JButton btnPedido = new JButton("Pedido");
		btnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoA.getInstance().setVisible(true);
			}
		});
		btnPedido.setBackground(Color.WHITE);
		Image imgPd = new ImageIcon(this.getClass().getResource("/Img/list1.png")).getImage();
		btnPedido.setIcon(new ImageIcon(imgPd));
		btnPedido.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnPedido.setBounds(431, 98, 162, 49);
		contentPane.add(btnPedido);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteA.getInstance().setVisible(true);
			}
		});
		btnCliente.setBackground(Color.WHITE);
		Image imgcl = new ImageIcon(this.getClass().getResource("/Img/client.png")).getImage();
		btnCliente.setIcon(new ImageIcon(imgcl));
		btnCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnCliente.setBounds(431, 176, 162, 49);
		contentPane.add(btnCliente);
		
		JButton btnFacturas = new JButton("Facturas");
		btnFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacturaA.getInstance().setVisible(true);
			}
		});
		btnFacturas.setBackground(Color.WHITE);
		Image imgFac= new ImageIcon(this.getClass().getResource("/Img/cash.png")).getImage();
		btnFacturas.setIcon(new ImageIcon(imgFac));
		btnFacturas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnFacturas.setBounds(219, 20, 162, 49);
		contentPane.add(btnFacturas);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaA.getInstance().setVisible(true);
			}
		});
		btnLista.setBackground(Color.WHITE);
		Image imgLis = new ImageIcon(this.getClass().getResource("/Img/list.png")).getImage();
		btnLista.setIcon(new ImageIcon(imgLis));
		btnLista.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnLista.setBounds(219, 98, 162, 49);
		contentPane.add(btnLista);
		
		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioA.getInstance().setVisible(true);
			}
		});
		btnUsuario.setBackground(Color.WHITE);
		Image imgUs = new ImageIcon(this.getClass().getResource("/Img/Users.png")).getImage();
		btnUsuario.setIcon(new ImageIcon(imgUs));
		btnUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnUsuario.setBounds(219, 176, 162, 49);
		contentPane.add(btnUsuario);
		
		JLabel label = new JLabel("");
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		label.setIcon(new ImageIcon(imglogo));
		label.setBounds(10, 20, 199, 195);
		contentPane.add(label);
	}

	public static InicioAdministrador getInstance(){
		if(_instance == null){
			_instance = new InicioAdministrador();
		}
		
		return _instance;
	}
}
