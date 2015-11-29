import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OperacionesComunes {
	private static OperacionesComunes _instance;
	private ArrayList<String> permisos;
	private String usuarioLogueado;
	
	private OperacionesComunes(){
		permisos = new ArrayList<String>();
	}
	
	public void setPermisos(ArrayList<String> permisos){
		_instance.permisos = permisos;
	}
	
	public static OperacionesComunes getInstance(){
		if(_instance == null){
			_instance = new OperacionesComunes();
		}
		
		return _instance;
	}
	
	public void irMenuPrincipal(){
		if (permisos.contains("ADMINISTRADORSCHEMA")){
			 InicioAdministrador.getInstance().setVisible(true);
		 }
		 else if(permisos.contains("EMPLEADOSCHEMA")){
			 LoginE.getInstance().setVisible(true);
		 }
		 else if(permisos.contains("CLIENTESCHEMA")){
			 InicioCliente.getInstance().setVisible(true);
		 }
	}
	
	public ArrayList<String> getPermisos(){
		return permisos;
	}
	
	public boolean isAdministrador(){
		return permisos.contains("ADMINISTRADORSCHEMA");
	}
	
	public boolean isEmpleado(){
		return permisos.contains("EMPLEADOSCHEMA");
	}
	
	public boolean isCliente(){
		return permisos.contains("CLIENTESCHEMA");
	}
	
	public boolean isTipo(String tipo){
		return permisos.contains(tipo);
	}
	
	public void setUsuarioLogueado(String usuarioLogueado){
		_instance.usuarioLogueado = usuarioLogueado;
	}
	
	public String getUsuarioLogueado(){
		return _instance.usuarioLogueado;
	}
}
