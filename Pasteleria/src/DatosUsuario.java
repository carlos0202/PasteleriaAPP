import java.util.ArrayList;

public class DatosUsuario {
	private static DatosUsuario _instance;
	private String userName;
	private ArrayList<String> permisos;


	private DatosUsuario(){
		this.permisos = new ArrayList<String>();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<String> getPermisos() {
		return permisos;
	}
	public void setPermisos(ArrayList<String> permisos) {
		this.permisos = permisos;
	}
	
	public static DatosUsuario getInstance(){
		if(_instance == null){
			_instance = new DatosUsuario();
		}
		
		return _instance;
	}
	
}


