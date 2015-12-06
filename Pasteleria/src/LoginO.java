import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginO extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	public static DerbyConnection login;
	public static LoginO _instance;
	private JPanel contentPane;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginO frame = new LoginO();
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
	private LoginO() {
		setTitle("Inicio Sesion");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		getContentPane().setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblUsuario.setBounds(203, 69, 77, 25);
		getContentPane().add(lblUsuario);
		
		login =new DerbyConnection();
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(203, 105, 89, 22);
		getContentPane().add(lblPassword);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(15, 23, 205, 198);
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		lblLogo.setIcon(new ImageIcon(imglogo));
		getContentPane().add(lblLogo);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtUsuario.setBounds(290, 70, 124, 21);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		passwordField.setBounds(290, 108, 124, 21);
		getContentPane().add(passwordField);
		 		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBackground(Color.WHITE);
		Image imgInicio = new ImageIcon(this.getClass().getResource("/Img/cake.png")).getImage();
		btnIniciar.setIcon(new ImageIcon(imgInicio));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				String user = txtUsuario.getText();
				DatosUsuario.getInstance().setUserName(user);
				String pass = String.valueOf(passwordField.getPassword());
				boolean success = login.MLogin(user, pass);
				if(success){
					LoginO.getInstance().setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Error al iniciar sesion. Revise sus datos...");
				}
				
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Error al conectar..");
				}
			}
		});
		btnIniciar.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnIniciar.setBounds(230, 163, 124, 25);
		getContentPane().add(btnIniciar);
		getRootPane().setDefaultButton(btnIniciar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DerbyConnection.StopServer();
				if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir?") == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(null, "Saliendo...");
					System.exit(0);
				}
			}
		});
		btnSalir.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(337,220,77,20);
		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
	}

	public static LoginO getInstance(){
		if(_instance == null){
			_instance = new LoginO();
		}
		
		return _instance;
	}
	
}
