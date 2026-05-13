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


// Cuando corra las pruebas se solicita colocar diferentes IDs porque sino da error de duplicado
public class Main {
    public static void main(String[] args) throws Exception {
     /* BUSINESS LOGIC */
     IUsuarioBL usuarioBL = new UsuarioBLImpl();
     IProductoBL productoBL = new ProductoBLImpl();
     IAgenciaEnvioBL agenciaEnvioBL = new AgenciaEnvioBLImpl();
     ISucursalBL sucursalBL = new SucursalBLImpl(); // <--- Nueva BL
     ITarifaBL tarifaBL = new TarifaBLImpl();
     IPedidoBL pedidoBL = new PedidoBLImpl();

     /* "HACER UN PEDIDO" */
     Pedido pedido = new Pedido();
     pedido.setCodPedido("AJD83D");
     pedido.setCanalVenta(CanalVenta.WEB);
     pedido.setMetodoPago(MetodoPago.BILLETERA_DIGITAL);
     pedido.setEstadoPago(EstadoPago.PENDIENTE);
     pedido.setEstadoPedido(EstadoPedido.PENDIENTE);

// 1. USUARIO
     Usuario cliente = usuarioBL.cargarUsuarioPorId(1);
     pedido.setUsuario(cliente);
     pedido.setClienteEmail(cliente.getEmail());
     pedido.setClienteCel(cliente.getCelular());

// 2. DETALLES (Corregido error de asignación det1/det2)
     List<DetallePed> listaDetallePedido = new ArrayList<>();
     Producto prod1 = productoBL.cargarProductoPorId(1);
     DetallePed det1 = new DetallePed();
     det1.setPedido(pedido);
     det1.setProducto(prod1);
     det1.setPrecioUnitCongelado(prod1.getPrecio());
     det1.setEsPreventaCongelado(prod1.getEsPreventa());
     det1.setCantidad(2);
     listaDetallePedido.add(det1);

     Producto prod2 = productoBL.cargarProductoPorId(2);
     DetallePed det2 = new DetallePed();
     det2.setPedido(pedido); // Corregido: antes decía det1
     det2.setProducto(prod2);
     det2.setPrecioUnitCongelado(prod2.getPrecio());
     det2.setEsPreventaCongelado(prod2.getEsPreventa());
     det2.setCantidad(2);
     listaDetallePedido.add(det2);

     pedido.setDetalles(listaDetallePedido);

// 3. SUCURSALES (Añadir una sucursal antes del pedido)
// Normalmente esto vendría de una lista desplegable en el Front
     SucursalAgencia sucursalSeleccionada = sucursalBL.obtenerPorId(1);
     if (sucursalSeleccionada == null) {
      // Si no existe, la creamos para la prueba (Agencia 1, Ubigeo 1)
      sucursalSeleccionada = new SucursalAgencia();
      sucursalSeleccionada.setAgenciaId(1);
      sucursalSeleccionada.setUbigeoId(1);
      sucursalSeleccionada = sucursalBL.registrarSucursal(sucursalSeleccionada);
     }

//  4. LOGISTICA
     PedLogisticaEnvio pedLogisticaEnvio = new PedLogisticaEnvio();

// Seteamos los datos de la parte "Padre" directamente en el objeto envío
     pedLogisticaEnvio.setPedido(pedido);
     pedLogisticaEnvio.setEstadoLogistica(EstadoLogistica.PREPARANDO); // <--- ESTO EVITA EL NULL
     pedLogisticaEnvio.setReceptorNombre("Patricia Rosas");
     pedLogisticaEnvio.setReceptorCel("999333555");
     pedLogisticaEnvio.setReceptorTipoDoc("DNI");
     pedLogisticaEnvio.setReceptorNroDoc("09344035");

// --- 4.1. DATOS ESPECÍFICOS DEL ENVÍO ---
     pedLogisticaEnvio.setTipoEnvio(TipoEnvio.A_DOMICILIO_MOTORIZADO);
     pedLogisticaEnvio.setModalidad(ModalidadPago.A_COORDINAR);
     pedLogisticaEnvio.setDireccionEntrega("Av. Universitaria 1801, San Miguel");
     pedLogisticaEnvio.setReferenciaEnvio("Cerca a la puerta principal de la PUCP");
     pedLogisticaEnvio.setCostoEnvio(15.50);
     pedLogisticaEnvio.setFec_estimadaEntrega(new GregorianCalendar(2024, Calendar.MAY, 20).getTime());
     pedLogisticaEnvio.setSucursal(sucursalSeleccionada);
     pedLogisticaEnvio.setTarifaEnvio(tarifaBL.cargarTarifaPorId(2));

// ASIGNACIÓN CRUCIAL: Sucursal y Tarifa
     pedLogisticaEnvio.setSucursal(sucursalSeleccionada); // <--- Aquí usamos la sucursal
     pedLogisticaEnvio.setTarifaEnvio(tarifaBL.cargarTarifaPorId(2));

// Hago los calculos
     pedido.realizarCalculos(pedLogisticaEnvio.getTarifaEnvio().getCosto());

// 5. EJECUCIÓN
     try {
      pedidoBL.registrarPedidoCompleto(pedido, pedLogisticaEnvio);
      System.out.println("Pedido registrado con ID: " + pedido.getIdPedido());
     } catch (Exception e) {
      System.out.println("Error en la transacción: " + e.getMessage());
      e.printStackTrace();
     }

     // Ahora, se procesa el pago del pedido
     Pedido pedidoPagado = pedidoBL.obtenerPedidoPorId(1);

     pedidoPagado.setPasarelaTransaccionId("JA92K0");

     //
    }
}
