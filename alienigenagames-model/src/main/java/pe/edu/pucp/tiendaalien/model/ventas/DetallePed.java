package pe.edu.pucp.tiendaalien.model.ventas;

import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

public class DetallePed {

    private Integer detallePedId;
    private String nombreCongelado;
    private Double precioUnCongelado;
    private boolean esPreventaCongelado;
    private Integer cantidad;

    // Un detalle tiene un producto
    private Producto producto;

    public DetallePed() {}

    public Integer getDetallePedId() {
        return detallePedId;
    }
    public void setDetallePedId(Integer detallePedId) {
        this.detallePedId = detallePedId;
    }

    public String getNombreCongelado() {
        return nombreCongelado;
    }
    public void setNombreCongelado(String nombreCongelado) {
        this.nombreCongelado = nombreCongelado;
    }

    public Double getPrecioUnCongelado() {
        return precioUnCongelado;
    }
    public void setPrecioUnCongelado(Double precioUnCongelado) {
        this.precioUnCongelado = precioUnCongelado;
    }

    public boolean isEsPreventaCongelado() {
        return esPreventaCongelado;
    }

    public void setEsPreventaCongelado(boolean esPreventaCongelado) {
        this.esPreventaCongelado = esPreventaCongelado;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    //Multiplica precioUnCongelado * cantidad
    public Double calcularSubtotalDetalle(){
        return 0.00;
    }
    //Verifica si el producto tiene stock suficiente
    public boolean validarDisponibilidadStock(){
        return true;
    }

}