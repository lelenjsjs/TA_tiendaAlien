package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import pe.edu.pucp.tiendaalien.bl.*;
import pe.edu.pucp.tiendaalien.bl.impl.*;
import pe.edu.pucp.tiendaalien.dao.PedLogisticaDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.*;
import pe.edu.pucp.tiendaalien.model.logistica.*;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.ventas.*;

record ItemCarrito(int idProducto, int cantidad) {}
// Cuando corra las pruebas se solicita colocar diferentes IDs porque sino da error de duplicado
public class Main {
    public static void main(String[] args) throws BusinessLogicException, SQLException {
       IUsuarioBL usuarioBL = new UsuarioBLImpl();
       IProductoBL productoBL = new ProductoBLImpl();
       IAgenciaEnvioBL agenciaEnvioBL = new AgenciaEnvioBLImpl();
       ITarifaBL tarifaBL = new TarifaBLImpl();

        /* "HACER UN PEDIDO */
        // Creo el pedido
        Pedido pedido = new Pedido();
        pedido.setCodPedido("AJD83D");
        pedido.setCanalVenta(CanalVenta.WEB);
        pedido.setMetodoPago(MetodoPago.BILLETERA_DIGITAL);
        pedido.setEstadoPago(EstadoPago.PENDIENTE);

        // 1. USUARIO
        Usuario cliente = new Usuario();
        cliente = usuarioBL.cargarUsuarioPorId(1);

        // ...continuo llenando Pedido
        pedido.setClienteEmail(cliente.getEmail());
        pedido.setClienteCel(cliente.getCelular());
        pedido.setUsuario(cliente);



        // 2. LISTA DE DETALLE
        List<DetallePed> listaDetallePedido = new ArrayList<>();

            // Creo mi lista de productos
        List<Producto> listaProducto = new ArrayList<>();
        Producto prod1 = productoBL.cargarProductoPorId(1);
        Producto prod2 = productoBL.cargarProductoPorId(2);
        Producto prod3 = productoBL.cargarProductoPorId(3);

            // Añado cada producto a mi detalle + datos extra
        DetallePed det1 = new DetallePed();
        det1.setPedido(pedido);
        det1.setProducto(prod1);
        det1.setPrecioUnitCongelado(prod1.getPrecio());
        det1.setEsPreventaCongelado(prod1.getEsPreventa());
        det1.setCantidad(10);
        listaDetallePedido.add(det1);

        DetallePed det2 = new DetallePed();
        det1.setPedido(pedido);
        det1.setProducto(prod2);
        det1.setPrecioUnitCongelado(prod2.getPrecio());
        det1.setEsPreventaCongelado(prod2.getEsPreventa());
        det1.setCantidad(15);
        listaDetallePedido.add(det2);

        DetallePed det3 = new DetallePed();
        det3.setPedido(pedido);
        det3.setProducto(prod3);
        det3.setPrecioUnitCongelado(prod3.getPrecio());
        det3.setEsPreventaCongelado(prod3.getEsPreventa());
        det3.setCantidad(8);
        listaDetallePedido.add(det3);


        // 3. LOGISTICA PEDIDO
        /* En el front estos datos se reciben */
        PedLogistica pedLogistica = new PedLogistica();
        pedLogistica.setEstadoLogistica(EstadoLogistica.PREPARANDO);
        pedLogistica.setReceptorNombre("Patricia Rosas");
        pedLogistica.setReceptorCel("999333555");
        pedLogistica.setReceptorTipoDoc("DNI");
        pedLogistica.setReceptorNroDoc("09344035");

            //  3.1. LOGISTICA PEDIDO ENVIO
        PedLogisticaEnvio pedLogisticaEnvio = new PedLogisticaEnvio();

        pedLogisticaEnvio.setTipoEnvio(TipoEnvio.A_DOMICILIO_MOTORIZADO);
        pedLogisticaEnvio.setModalidad(ModalidadPago.A_COORDINAR);
        pedLogisticaEnvio.setDireccionEntrega("Av. Universitaria 1801, San Miguel");
        pedLogisticaEnvio.setReferenciaEnvio("Cerca a la puerta principal de la PUCP");
        pedLogisticaEnvio.setCostoEnvio(15.50);
        Date fechaEstimada = new GregorianCalendar(2024, Calendar.MAY, 20).getTime();
        pedLogisticaEnvio.setFec_estimadaEntrega(fechaEstimada);
        pedLogisticaEnvio.setPedLogistica(pedLogistica);
        pedLogisticaEnvio.setAgenciaEnvio(agenciaEnvioBL.cargarAgenciaPorId(1));
        pedLogisticaEnvio.setTarifaEnvio(tarifaBL.cargarTarifaPorId(1));


        // Actualizo montos de pedido
        double monto=0;
        for(DetallePed d : listaDetallePedido){
            monto += d.getPrecioUnitCongelado() * d.getCantidad();
        }
        monto += pedLogisticaEnvio.getTarifaEnvio().getCosto();

        pedido.setSubtotal(monto);


        pedido.realizarCalculos();






    }
}
