import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;



public class Login {
	private JFrame FLogin;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.FLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the application.
	 */
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	public static DerbyConnection login;
	private JButton btnSalir;
	
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		
		FLogin = new JFrame("Inicio de Sesion");
		FLogin.getContentPane().setBackground(Color.WHITE);
		FLogin.setForeground(Color.WHITE);
		FLogin.setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		FLogin.getContentPane().setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		FLogin.setBounds(100, 100, 526, 339);
		FLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		FLogin.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblUsuario.setBounds(232, 69, 77, 25);
		FLogin.getContentPane().add(lblUsuario);
		
		login =new DerbyConnection();
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(232, 105, 89, 22);
		FLogin.getContentPane().add(lblPassword);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(15, 23, 205, 198);
		Image imglogo = new ImageIcon(this.getClass().getResource("/Img/Pas.jpg")).getImage();
		lblLogo.setIcon(new ImageIcon(imglogo));
		FLogin.getContentPane().add(lblLogo);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtUsuario.setBounds(319, 70, 156, 21);
		FLogin.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		passwordField.setBounds(319, 108, 156, 21);
		FLogin.getContentPane().add(passwordField);
		 		
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
					//FLogin.dispose();
					FLogin.setVisible(false);
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
		btnIniciar.setBounds(351, 152, 124, 25);
		FLogin.getContentPane().add(btnIniciar);
		FLogin.getRootPane().setDefaultButton(btnIniciar);
		SwingUtilities.getRootPane(btnIniciar).setDefaultButton(btnIniciar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Gracias por usar nuestros servicios...");
				DerbyConnection.StopServer();
				System.exit(0);;
			}
		});
		btnSalir.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(399, 254, 77, 25);
		FLogin.getContentPane().add(btnSalir);
		FLogin.setLocationRelativeTo(null);
	}
}
