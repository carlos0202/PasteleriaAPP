package models;

public class Producto {
	private long productoID;
	private String nombreProducto;
	private long cantidadProducto;
	private String descripcionProducto;
	private double precioProducto;

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
	
	
}
