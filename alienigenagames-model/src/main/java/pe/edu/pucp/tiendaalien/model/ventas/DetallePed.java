package pe.edu.pucp.tiendaalien.model.ventas;

import pe.edu.pucp.tiendaalien.model.catalogo.Producto;

public class DetallePed {

    private Integer id;
    private Pedido pedido;
    private Producto producto;
    private Double precioUnitCongelado;
    private boolean esPreventaCongelado;
    private Integer cantidad;

    // Un detalle tiene un producto
    private DetallePed(Pedido pedido, Producto producto, Double precioUnitCongelado,
                              boolean esPreventaCongelado, Integer cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.precioUnitCongelado = precioUnitCongelado;
        this.esPreventaCongelado = esPreventaCongelado;
        this.cantidad = cantidad;
    };

    public DetallePed() {}


    //Multiplica precioUnCongelado * cantidad
    public Double calcularSubtotalDetalle(){
        return 0.00;
    }
    //Verifica si el producto tiene stock suficiente
    public boolean validarDisponibilidadStock(){
        return true;
    }

    public Double getPrecioUnitCongelado() {
        return precioUnitCongelado;
    }

    public void setPrecioUnitCongelado(Double precioUnitCongelado) {
        this.precioUnitCongelado = precioUnitCongelado;
    }

    public boolean isEsPreventaCongelado() {
        return esPreventaCongelado;
    }

    public void setEsPreventaCongelado(boolean esPreventaCongelado) {
        this.esPreventaCongelado = esPreventaCongelado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}