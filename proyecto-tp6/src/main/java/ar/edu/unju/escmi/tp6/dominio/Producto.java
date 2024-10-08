package ar.edu.unju.escmi.tp6.dominio;

public class Producto {

	private long codigo;
    private String descripcion;
    private double precioUnitario;
    private String origenFabricacion;
    private boolean cuotas;

    public Producto() {

    }

    public Producto(long codigo, String descripcion, double precioUnitario, String origenFabricacion, boolean cuotas) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.origenFabricacion = origenFabricacion;
        this.cuotas=cuotas;
    }

    
    public boolean isCuotas() {
		return cuotas;
	}

	public void setCuotas(boolean cuotas) {
		this.cuotas = cuotas;
	}

	public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getOrigenFabricacion() {
        return origenFabricacion;
    }

    public void setOrigenFabricacion(String origenFabricacion) {
        this.origenFabricacion = origenFabricacion;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + " Descripcion: " + descripcion + " Precio Unitario: " + precioUnitario
                + " Origen fabricacion: " + origenFabricacion + " Elegible para cuotas: " + (cuotas ? "Sí" : "No");
    }
}
