import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.derby.drda.NetworkServerControl;

public class DerbyConnection {
	private Connection connection;
	private static NetworkServerControl server;
	int index=0;
	
	public static void StartServer(){
		try{
			PrintWriter print = new PrintWriter(System.out);
			System.setProperty("derby.drda.startNetworkServer","true");
			server = new NetworkServerControl();
			server.start(print);
			JOptionPane.showMessageDialog(null,"Verificando conexion del servidor de derby!");
			for (int i = 0; i < 5 ; i ++)
			{
				try {
					Thread.currentThread();
					Thread.sleep(1000);
					server.ping();
				}
				catch (Exception e)
				{
					if (i == 9)
					{
						JOptionPane.showMessageDialog(null,"Tiempo de espera para iniciar servidor agotado!");
						throw e;
					}
				}
			}
			//Process p = Runtime.getRuntime().exec("./startnetwork.bat"); 
			JOptionPane.showMessageDialog(null,"Servidor iniciado...");
		} catch(Exception ex){
			JOptionPane.showMessageDialog(null,"No se ha podido iniciar el servidor. salidendo");
			System.exit(0);
		}
	}
	
	public static void StopServer(){
		try {
			server.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.exit(0);
		}
	}
	public static Connection DbStart()
	{
		try{
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				Connection conn= DriverManager.getConnection("jdbc:derby://localhost:1527/PasteleriaDB;user=PasteleriaDBA;password=123");
				return conn;
			} catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error al conectar a BD");
				return null;
			}
	}
	public boolean LoginEmpleado(int ID, String pass){
		boolean loginSuccess = false;
		try{
			String schema ="select *from PasteleriaDBA.Usuario where UsuarioID='"+ID+"' and ContrasenaUsuario='"+pass+"'";
			PreparedStatement pst = connection.prepareStatement(schema);
			ResultSet rs = pst.executeQuery();
			ArrayList<String> Nombres = new ArrayList<String>();
			
			while(rs.next()){
					Nombres.add(rs.getString(1));
					index++;
				}
			OperacionesComunes.getInstance().setNombres(Nombres);
			OperacionesComunes.getInstance().setID(ID);
				rs.close();
				return loginSuccess;
		} catch(Exception ex){
			return false;
		}

	}
	public boolean MLogin(String user, String pass){
		boolean loginSuccess = false;
		try{
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			String conn = "jdbc:derby://localhost:1527/PasteleriaDB;user=" + user + ";password=" + pass;
			connection = DriverManager.getConnection(conn);
			String schema = "select schemaname from sys.sysschemas where authorizationid = '"+ user.toUpperCase()+"'";
			PreparedStatement pst = connection.prepareStatement(schema);
			ResultSet rs = pst.executeQuery();
			ArrayList<String> Usuario = new ArrayList<String>();
			
			while(rs.next()){
					Usuario.add(rs.getString(1));
					index++;
				}
			OperacionesComunes.getInstance().setPermisos(Usuario);
			OperacionesComunes.getInstance().setUsuarioLogueado(user);
				if (Usuario.contains("ADMINISTRADORSCHEMA")){
					 JOptionPane.showMessageDialog (null, "Bienvenido Administrador");
					 loginSuccess = true;
					 OperacionesComunes.getInstance().setLoginUsed(1);
					 InicioAdministrador.getInstance().setVisible(true);
				 }
				 else if(Usuario.contains("EMPLEADOSCHEMA")){
					 loginSuccess = true;
					 OperacionesComunes.getInstance().setLoginUsed(2);
					 LoginE.getInstance().setVisible(true);
				 }
				 else if(Usuario.contains("CLIENTESCHEMA")){
					 OperacionesComunes.getInstance().setLoginUsed(3);
					 JOptionPane.showMessageDialog(null, "Bienvenido Cliente");
					 loginSuccess = true;
					 InicioCliente.getInstance().setVisible(true);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Contactarse con DBA usuario no existe.");
				 }
				rs.close();
				return loginSuccess;
		} catch(Exception ex){
			return false;
		}

	}
	public ArrayList<String> getTablesData(){
		ArrayList<String> tableNames = new ArrayList<String>();
		String user=DatosUsuario.getInstance().getUserName();
		try{
		 
			String query = "select sc.schemaname || '.' || st.tablename tbl " +
			  				"from sys.systableperms tp, sys.systables st, sys.sysschemas sc "+
			  				"where ((tp.grantee='"+user.toUpperCase() + "' and tp.selectpriv='y' and st.tableid = tp.tableid and st.schemaid = sc.schemaid) or sc.schemaname = '"+user.toUpperCase()+"' and st.schemaid = sc.schemaid) ";
					//"select distinct(sc.schemaname || '.' || st.tablename) tbl " +
					//"  from sys.systableperms tp, sys.systables st, sys.sysschemas sc " +
					//"   where ((tp.grantee='"+user.toUpperCase()+"' and tp.selectpriv='y' and st.tableid = tp.tableid and st.schemaid " +
					//" = sc.schemaid) or sc.schemaname = '"+user.toUpperCase()+"' and st.schemaid = sc.schemaid)";
					
									
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
						
			while(rs.next()){
				tableNames.add(rs.getString(1));
				index++;
			}

			rs.close();
		} catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Error al obtener los datos de la bd...");
			System.exit(1);
		}
	return tableNames;
	}
}
		
