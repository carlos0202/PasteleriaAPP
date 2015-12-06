import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LoginE extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField passwordField;
	public static LoginE _instance;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginE frame = new LoginE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the frame.
	 */
	private LoginE() {
		setIconImage(new ImageIcon(this.getClass().getResource("/Img/cakeP.png")).getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 272, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdUsuario = new JLabel("ID Usuario");
		lblIdUsuario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblIdUsuario.setBounds(19, 49, 74, 17);
		contentPane.add(lblIdUsuario);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtID.setBounds(105, 44, 137, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblPassword.setBounds(29, 80, 46, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		passwordField.setBounds(105, 74, 137, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try{
					DatosUsuario.getInstance().getUserName();
					String sql = "select *from PasteleriaDBA.Usuario where UsuarioID=? and ContrasenaUsuario=?";
					PreparedStatement pst=DerbyConnection.DbStart().prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(txtID.getText()));
					pst.setString(2,passwordField.getText());
					ResultSet rs= pst.executeQuery();
					//pst.execute();
					
					int count=0;
					while(rs.next()){
						OperacionesComunes.getInstance().setLoggedEmployee(rs.getString("NOMBREUSUARIO"));
						count=count+1;
					}
					if(count==1){
						InicioEmpleado.getInstance().setVisible(true);
						LoginE.getInstance().setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null,"Favor verificar su ID o Password");
					}
						
					
					pst.close();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Error, favor contactarse con DBA");
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnLogin.setBounds(153, 117, 89, 23);
		contentPane.add(btnLogin);
		setLocationRelativeTo(null);
	    this.getRootPane().setDefaultButton(btnLogin);
	    
	    btnSalir = new JButton("Salir");
	    btnSalir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		LoginO.getInstance().setVisible(true);
	    		LoginE.getInstance().dispose();
	    	}
	    });
	    btnSalir.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
	    btnSalir.setBounds(19, 117, 89, 23);
	    contentPane.add(btnSalir);
	    this.getRootPane().setDefaultButton(btnLogin);
	    this.setLocationRelativeTo(null);
	}
	public static LoginE getInstance(){
		if(_instance == null){
			_instance = new LoginE();
		}
		
		return _instance;
	}
}
