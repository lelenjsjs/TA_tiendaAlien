package pe.edu.pucp.tiendaalien.ventas;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Pedido {

    private Integer pedidoId;
    private String codPedido;
    private String clienteEmail;
    private String clienteCel;
    private CanalVenta canalVenta;
    private MetodoPago metodoPago; // Representado como enum
    private EstadoPago estadoPago; // Representado como enum
    private Double subtotal;
    private Double cargoServicio;
    private double montoAdelanto;
    private Double montoTotal;
    private String pasarelaTransaccionId;
    private Date fecCreacion;
    private EstadoPedido estadoPedido;

    // Relaciones de Asociación y Multiplicidad
    private Usuario usuario;              // Un pedido tiene 1 usuario
    private Ubigeo ubigeo;                // Un pedido puede tener o no tener ubigeo
    private PedLogistica pedLogistica;
    private ComprobantePago comprobante;  // Un pedido puede tener o no un comprobante

    private List<DetallePed> detalles;
    private List<HistorialEstadoPed> historial;

    public Pedido() {
        this.detalles = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.fecCreacion = new Date();
    }


    // Getters y Setters
    public Integer getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getCodPedido() {
        return codPedido;
    }
    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public CanalVenta getCanalVenta() {
        return canalVenta;
    }
    public void setCanalVenta(CanalVenta canalVenta) {
        this.canalVenta = canalVenta;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }


    public String getClienteEmail() {
        return clienteEmail;
    }
    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteCel() {
        return clienteCel;
    }
    public void setClienteCel(String clienteCel) {
        this.clienteCel = clienteCel;
    }

    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getCargoServicio() {
        return cargoServicio;
    }
    public void setCargoServicio(Double cargoServicio) {
        this.cargoServicio = cargoServicio;
    }

    public double getMontoAdelanto() {
        return montoAdelanto;
    }

    public void setMontoAdelanto(double montoAdelanto) {
        this.montoAdelanto = montoAdelanto;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getPasarelaTransaccionId() {
        return pasarelaTransaccionId;
    }
    public void setPasarelaTransaccionId(String pasarelaTransaccionId) {
        this.pasarelaTransaccionId = pasarelaTransaccionId;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }
    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    // Getters y Setters de Relaciones
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }
    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public PedLogistica getPedLogistica() {
        return pedLogistica;
    }
    public void setPedLogistica(PedLogistica pedLogistica) {
        this.pedLogistica = pedLogistica;
    }

    public ComprobantePago getComprobante() {
        return comprobante;
    }
    public void setComprobante(ComprobantePago comprobante) {
        this.comprobante = comprobante;
    }

    public List<DetallePed> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetallePed> detalles) {
        this.detalles = detalles;
    }

    public List<HistorialEstadoPed> getHistorial() {
        return historial;
    }
    public void setHistorial(List<HistorialEstadoPed> historial) {
        this.historial = historial;
    }

}