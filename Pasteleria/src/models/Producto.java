package models;

public class Producto {
	private long productoID;
	private String nombreProducto;
	private long cantidadProducto;
	private String descripcionProducto;
	private double precioProducto;

	public Producto(long productoID){
		this.productoID = productoID;
	}
	
	public Producto(String nombreProducto, long cantidadProducto, String descripcionProducto, double precioProducto) {
		super();
		this.nombreProducto = nombreProducto;
		this.cantidadProducto = cantidadProducto;
		this.descripcionProducto = descripcionProducto;
		this.precioProducto = precioProducto;
	}
	public Producto(long productoID, String nombreProducto, long cantidadProducto, String descripcionProducto,
			double precioProducto) {
		super();
		this.productoID = productoID;
		this.nombreProducto = nombreProducto;
		this.cantidadProducto = cantidadProducto;
		this.descripcionProducto = descripcionProducto;
		this.precioProducto = precioProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public long getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(long cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	public long getProductoID() {
		return productoID;
	}
	@Override
	public String toString() {
		return nombreProducto + "-" + precioProducto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (productoID ^ (productoID >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (productoID == other.productoID)
			return true;
		return false;
	}
	
	
}
