import java.util.ArrayList;

public class OperacionesComunes {
	private static OperacionesComunes _instance;
	private ArrayList<String> Permisos;
	private String usuarioLogueado;
	private ArrayList<String> Nombres;
	private int ID;
	private String loggedEmployee;
	private int loginUsed; //1 administrador, 2 empleado, 3 cliente
	
	private OperacionesComunes(){
		Permisos = new ArrayList<String>();
	}
	
	public void setPermisos(ArrayList<String> permisos){
		_instance.Permisos = permisos;
	}
	public void setNombres(ArrayList<String> Nombres){
		_instance.Nombres = Nombres;
	}
	public void setID(int ID){
		_instance.ID = ID;
	}
	
	public static OperacionesComunes getInstance(){
		if(_instance == null){
			_instance = new OperacionesComunes();
		}
		
		return _instance;
	}
	
	public void irMenuPrincipal(){
		if (Permisos.contains("ADMINISTRADORSCHEMA") || loginUsed == 1){
			 InicioAdministrador.getInstance().setVisible(true);
		 }
		 else if(Permisos.contains("EMPLEADOSCHEMA") || loginUsed == 2){
			 InicioEmpleado.getInstance().setVisible(true);
		 }
		 else if(Permisos.contains("CLIENTESCHEMA") || loginUsed == 3){
			 InicioCliente.getInstance().setVisible(true);
		 }
	}
	
	public ArrayList<String> getPermisos(){
		return Permisos;
	}
	public ArrayList<String> getNombres(){
		return Nombres;
	}
	public int getID(){
		return ID;
	}
	public boolean isAdministrador(){
		return Permisos.contains("ADMINISTRADORSCHEMA");
	}
	
	public boolean isEmpleado(){
		return Permisos.contains("EMPLEADOSCHEMA");
	}
	
	public boolean isCliente(){
		return Permisos.contains("CLIENTESCHEMA");
	}
	
	public boolean isTipo(String tipo){
		return Permisos.contains(tipo);
	}
	
	public void setUsuarioLogueado(String usuarioLogueado){
		_instance.usuarioLogueado = usuarioLogueado;
	}
	
	public String getUsuarioLogueado(){
		return _instance.usuarioLogueado;
	}
	
	public void setLoggedEmployee(String loggedEmployee){
		_instance.loggedEmployee = loggedEmployee;
	}
	
	public String getLoggedEmployee(){
		return _instance.loggedEmployee;
	}
	
	public void setLoginUsed(int loginUsed){
		this.loginUsed = loginUsed;
	}
	
	public int getLoginUsed(){
		return loginUsed;
	};
}
