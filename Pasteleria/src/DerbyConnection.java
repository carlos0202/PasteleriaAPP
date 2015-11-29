import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class DerbyConnection {
	private Connection connection;
	int index=0;
	
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
					 InicioAdministrador.getInstance().setVisible(true);
				 }
				 else if(Usuario.contains("EMPLEADOSCHEMA")){
					 loginSuccess = true;
					 LoginE.getInstance().setVisible(true);
				 }
				 else if(Usuario.contains("CLIENTESCHEMA")){
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
		
