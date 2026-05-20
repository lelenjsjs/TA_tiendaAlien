package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import pe.edu.pucp.tiendaalien.bl.*;
import pe.edu.pucp.tiendaalien.bl.impl.*;
import pe.edu.pucp.tiendaalien.dao.PedLogisticaDAO;
import pe.edu.pucp.tiendaalien.model.catalogo.*;
import pe.edu.pucp.tiendaalien.model.facturacion.ComprobantePago;
import pe.edu.pucp.tiendaalien.model.facturacion.EstadoSunat;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoComprobante;
import pe.edu.pucp.tiendaalien.model.facturacion.TipoDocIdentidad;
import pe.edu.pucp.tiendaalien.model.logistica.*;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.ventas.*;


/* Se trató de colocar la mayor cantidad de pruebas como comentario
* dado de que no se especificó en la rúbrica cuantas se tenian que hacer o el formato
*
* Una disculpa por el desorden
*
* */

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
     // Prueba general de pedido
//     /* "HACER UN PEDIDO" */
//     Pedido pedido = new Pedido();
//     pedido.setCodPedido("AJD83D");
//     pedido.setCanalVenta(CanalVenta.WEB);
//     pedido.setMetodoPago(MetodoPago.BILLETERA_DIGITAL);
//     pedido.setEstadoPago(EstadoPago.PENDIENTE);
//     pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
//
//// 1. USUARIO
//     Usuario cliente = usuarioBL.cargarUsuarioPorId(1);
//     pedido.setUsuario(cliente);
//     pedido.setClienteEmail(cliente.getEmail());
//     pedido.setClienteCel(cliente.getCelular());
//
//// 2. DETALLES (Corregido error de asignación det1/det2)
//     List<DetallePed> listaDetallePedido = new ArrayList<>();
//     Producto prod1 = productoBL.cargarProductoPorId(1);
//     DetallePed det1 = new DetallePed();
//     det1.setPedido(pedido);
//     det1.setProducto(prod1);
//     det1.setPrecioUnitCongelado(prod1.getPrecio());
//     det1.setEsPreventaCongelado(prod1.getEsPreventa());
//     det1.setCantidad(2);
//     listaDetallePedido.add(det1);
//
//     Producto prod2 = productoBL.cargarProductoPorId(2);
//     DetallePed det2 = new DetallePed();
//     det2.setPedido(pedido); // Corregido: antes decía det1
//     det2.setProducto(prod2);
//     det2.setPrecioUnitCongelado(prod2.getPrecio());
//     det2.setEsPreventaCongelado(prod2.getEsPreventa());
//     det2.setCantidad(2);
//     listaDetallePedido.add(det2);
//
//     pedido.setDetalles(listaDetallePedido);
//
//// 3. SUCURSALES (Añadir una sucursal antes del pedido)
//// Normalmente esto vendría de una lista desplegable en el Front
//     SucursalAgencia sucursalSeleccionada = sucursalBL.obtenerPorId(1);
//     if (sucursalSeleccionada == null) {
//      // Si no existe, la creamos para la prueba (Agencia 1, Ubigeo 1)
//      sucursalSeleccionada = new SucursalAgencia();
//      sucursalSeleccionada.setAgenciaId(1);
//      sucursalSeleccionada.setUbigeoId(1);
//      sucursalSeleccionada = sucursalBL.registrarSucursal(sucursalSeleccionada);
//     }
//
////  4. LOGISTICA
//     PedLogisticaEnvio pedLogisticaEnvio = new PedLogisticaEnvio();
//
//// Seteamos los datos de la parte "Padre" directamente en el objeto envío
//     pedLogisticaEnvio.setPedido(pedido);
//     pedLogisticaEnvio.setEstadoLogistica(EstadoLogistica.PREPARANDO); // <--- ESTO EVITA EL NULL
//     pedLogisticaEnvio.setReceptorNombre("Patricia Rosas");
//     pedLogisticaEnvio.setReceptorCel("999333555");
//     pedLogisticaEnvio.setReceptorTipoDoc("DNI");
//     pedLogisticaEnvio.setReceptorNroDoc("09344035");
//
//// --- 4.1. DATOS ESPECÍFICOS DEL ENVÍO ---
//     pedLogisticaEnvio.setTipoEnvio(TipoEnvio.A_DOMICILIO_MOTORIZADO);
//     pedLogisticaEnvio.setModalidad(ModalidadPago.A_COORDINAR);
//     pedLogisticaEnvio.setDireccionEntrega("Av. Universitaria 1801, San Miguel");
//     pedLogisticaEnvio.setReferenciaEnvio("Cerca a la puerta principal de la PUCP");
//     pedLogisticaEnvio.setCostoEnvio(15.50);
//     pedLogisticaEnvio.setFec_estimadaEntrega(new GregorianCalendar(2024, Calendar.MAY, 20).getTime());
//     pedLogisticaEnvio.setSucursal(sucursalSeleccionada);
//     pedLogisticaEnvio.setTarifaEnvio(tarifaBL.cargarTarifaPorId(2));
//
//// ASIGNACIÓN CRUCIAL: Sucursal y Tarifa
//     pedLogisticaEnvio.setSucursal(sucursalSeleccionada); // <--- Aquí usamos la sucursal
//     pedLogisticaEnvio.setTarifaEnvio(tarifaBL.cargarTarifaPorId(2));
//
//// Hago los calculos
//     pedido.realizarCalculos(pedLogisticaEnvio.getTarifaEnvio().getCosto());
//
//// 5. EJECUCIÓN
//     try {
//      pedidoBL.registrarPedidoCompleto(pedido, pedLogisticaEnvio);
//      System.out.println("Pedido registrado con ID: " + pedido.getIdPedido());
//     } catch (Exception e) {
//      System.out.println("Error en la transacción: " + e.getMessage());
//      e.printStackTrace();
//     }
      // Prueba comprobante de pago, tipo y tipo doc
     try {
      // --- 1. INICIALIZACIÓN DE SERVICIOS (BL) ---
      ITipoComprobanteBL tipoCompBL = new TipoComprobanteBLImpl();
      ITipoDocIdentidadBL tipoDocBL = new TipoDocIdentidadBLImpl();
      IComprobanteBL comprobanteBL = new ComprobanteBLImpl();
      // pedido registrado con ID (1)
      Pedido pedidoExistente = pedidoBL.obtenerPedidoPorId(1);
      System.out.println(pedidoExistente);

      // --- 2. CREATE (Inserción de Catálogos y Comprobante) ---
      System.out.println("--- Iniciando inserciones ---");

      // a. Insertar Tipos de Comprobante
      TipoComprobante tc1 = new TipoComprobante();
      tc1.setCodigoSunat("01");
      tc1.setDescripcion("FACTURA ELECTRÓNICA");
      tipoCompBL.registrarTipoComprobante(tc1);

      TipoComprobante tc2 = new TipoComprobante();
      tc2.setCodigoSunat("03");
      tc2.setDescripcion("BOLETA DE VENTA ELECTRÓNICA");
      tipoCompBL.registrarTipoComprobante(tc2);

      // b. Insertar Tipos de Documento
      TipoDocIdentidad td1 = new TipoDocIdentidad();
      td1.setCodigoSunat("6");
      td1.setDescripcion("RUC");
      tipoDocBL.registrarTipoDocIdentidad(td1);

      TipoDocIdentidad td2 = new TipoDocIdentidad();
      td2.setCodigoSunat("1");
      td2.setDescripcion("DNI");
      tipoDocBL.registrarTipoDocIdentidad(td2);

      // c. Insertar Comprobante Principal
      ComprobantePago cp = new ComprobantePago();
      cp.setPedido(pedidoExistente);
      cp.setTipoComprobante(tc1); // Factura
      cp.setTipoDocIdentidad(td1); // RUC
      cp.setNroSerie("F001");
      cp.setCorrelativo("000001");
      cp.setClienteNroDoc("20601234567");
      cp.setClienteDenominacion("TIENDA ALIEN S.A.C.");
      cp.setDireccionFiscal("Av. El espacio 51, Lima");

      // Cálculos
      double total = pedidoExistente.getMontoTotal();
      System.out.println(pedidoExistente.getMontoTotal());
      cp.setMontoTotal(total);
      cp.setMontoGravado(total / 1.18);
      cp.setMontoIgv(total - (total / 1.18));

      comprobanteBL.registrarComprobantePago(cp);
      System.out.println("Comprobante registrado con ID: " + cp.getComprobanteId());

      // --- 3. READ (Lectura/Listado) ---
      System.out.println("\n--- Listando Tipos de Comprobante ---");
      for (TipoComprobante t : tipoCompBL.listarTiposComprobante()) {
       System.out.println("- " + t.getDescripcion() + " (SUNAT: " + t.getCodigoSunat() + ")");
      }

      // --- 4. UPDATE (Actualización) ---
      System.out.println("\n--- Actualizando correlativo del comprobante ---");
      cp.setCorrelativo("000002");
      cp.setEstadoSunat(EstadoSunat.ACEPTADO); // Supongamos que ya se validó
      comprobanteBL.modificarComprobantePago(cp);
      System.out.println("Comprobante actualizado a correlativo: " + cp.getCorrelativo());

      // --- 5. DELETE (Eliminación) ---
      // Nota: Probaremos borrar un tipo de documento que NO esté en uso
      System.out.println("\n--- Eliminando Tipo de Documento temporal ---");
      TipoDocIdentidad tdTemp = new TipoDocIdentidad();
      tdTemp.setCodigoSunat("0");
      tdTemp.setDescripcion("DOC.TEMPORAL");
      tipoDocBL.registrarTipoDocIdentidad(tdTemp);

      tipoDocBL.eliminarTipoDocIdentidad(tdTemp); // Borrado físico según tu DAO
      System.out.println("Tipo de documento temporal eliminado.");

     } catch (Exception e) {
      System.err.println("Error en las pruebas: " + e.getMessage());
      e.printStackTrace();
     }

     // Diferentes Pruebas : Una disculpa por el desorden
     /*
     // 1. UBIGEO
     IUbigeoBL ubigeoBL = new UbigeoBLImpl();
//        List<Ubigeo> listaUbigeo = new ArrayList<>();
//
//        // Datos de ejemplo (ubigeoId, codigo, departamento, provincia, distrito)
//        listaUbigeo.add(new Ubigeo(1, "150101", "LIMA", "LIMA", "LIMA"));
//        listaUbigeo.add(new Ubigeo(2, "150104", "LIMA", "LIMA", "BARRANCO"));
//        listaUbigeo.add(new Ubigeo(3, "150113", "LIMA", "LIMA", "JESUS MARIA"));
//        listaUbigeo.add(new Ubigeo(4, "150122", "LIMA", "LIMA", "MIRAFLORES"));
//        listaUbigeo.add(new Ubigeo(5, "150130", "LIMA", "LIMA", "SANTIAGO DE SURCO"));
//        listaUbigeo.add(new Ubigeo(6, "040101", "AREQUIPA", "AREQUIPA", "AREQUIPA"));
//        listaUbigeo.add(new Ubigeo(7, "130101", "LA LIBERTAD", "TRUJILLO", "TRUJILLO"));
//        listaUbigeo.add(new Ubigeo(8, "200101", "PIURA", "PIURA", "PIURA"));
//
//        // Ejemplo de cómo recorrerla para generar un INSERT (informativo)
//        for (Ubigeo u : listaUbigeo) {
//            ubigeoBL.registrarUbigeo(u);
//        }

     // 2. Creo usuarios
     IUsuarioBL usuarioBL = new UsuarioBLImpl();
//        List<Usuario> listaUsuarios = new ArrayList<>();
//
//        // Formato: usuarioId, nombres, apellidos, email, contraHash, celular, rol, fechaCreacion, esActivo
//        listaUsuarios.add(new Usuario(1, "Juan Carlos", "Pérez Ramos", "juan.perez@email.com", "$2a$12$eImiTXuWVxjM72ujmWDADe", "987654321", Rol.ADMIN, new Date(),null));
//        listaUsuarios.add(new Usuario(2, "María Fernanda", "Alva García", "m.alva@email.com", "$2a$12$K9vBfyvA5zNPe9W.G8BuAe", "955123456", Rol.CLIENTE, new Date(), null));
//        listaUsuarios.add(new Usuario(3, "Roberto", "Jiménez Solís", "roberto.j@email.com", "$2a$12$Lh9.z8A9A9A9A9A9A9A9A9", "912345678", Rol.CLIENTE, new Date(), null));
//        listaUsuarios.add(new Usuario(4, "Lucía", "Mendoza Torres", "lucia.men@email.com", "$2a$12$R.S.T.U.V.W.X.Y.Z.1.2.3", "944555666", Rol.CLIENTE, new Date(), null));
//        listaUsuarios.add(new Usuario(5, "Andrés", "Castro Soto", "a.castro@email.com", "$2b$10$8.dfghjkl987654321asdf", null, Rol.CLIENTE, new Date(), null));
//
//        for (Usuario u : listaUsuarios) {
//            usuarioBL.registrarUsuario(u);
//        }

     // 3. Añadir direcciones
     IDireccionUsuarioBL direccionUsuarioBL = new DireccionUsuarioBLImpl();
     // "A la cuenta con juan.perez@gmail.com le añadire una direccion"
     String email = "juan.perez@email.com";
     Usuario usuario = usuarioBL.cargarUsuarioPorId(1);

     Ubigeo ubigeo = ubigeoBL.cargarUbigeoPorId(1);

     DireccionUsuario direccionUsuario = new DireccionUsuario();
     direccionUsuario.setUsuario(usuario);
     direccionUsuario.setUbigeo(ubigeo);
     direccionUsuario.setDireccion("Av Mariano Cornejo 835 Pueblo Libre");
     direccionUsuario.setEsPrincipal(true);
     direccionUsuario.setReferencia("A tres cuadras de la plaza de la bandera");

     // Añado la direccion
     direccionUsuarioBL.registrarDireccion(direccionUsuario);
     // Y al usuario:
     usuario.anadirDireccion(direccionUsuario);

     // 2. Franquicia
//        IFranquiciaBL franquiciaBL = new FranquiciaBLImpl();
//
//        List<Franquicia> listaFranquicias = new ArrayList<>();
//
//        // Formato: franquiciaId, nombre, esActivo
//        listaFranquicias.add(new Franquicia("Pokémon"));
//        listaFranquicias.add(new Franquicia("Yu-Gi-Oh!"));
//        listaFranquicias.add(new Franquicia("Magic: The Gathering"));
//        listaFranquicias.add(new Franquicia("One Piece Card Game"));
//        listaFranquicias.add(new Franquicia("Dragon Ball Super"));
//        listaFranquicias.add(new Franquicia("Digimon Card Game"));
//        listaFranquicias.add(new Franquicia("Franquicia Descontinuada"));
//
//        for(Franquicia franquicia : listaFranquicias){
//            franquiciaBL.registrarFranquicia(franquicia);
//        }

     // 3. Coleccion
//        IColeccionBL coleccionBL = new ColeccionBLImpl();
//
//        List<Coleccion> listaColecciones = new ArrayList<>();
//
//        // Formato: coleccionId, nombre
//        listaColecciones.add(new Coleccion( "Scarlet & Violet: 151"));
//        listaColecciones.add(new Coleccion("Crown Zenith"));
//        listaColecciones.add(new Coleccion( "Pharaoh's Servant"));
//        listaColecciones.add(new Coleccion( "Modern Horizons III"));
//        listaColecciones.add(new Coleccion( "Romance Dawn"));
//        listaColecciones.add(new Coleccion("Legendary Collection"));
//        listaColecciones.add(new Coleccion( "Evolving Skies"));
//
//        for (Coleccion coleccion : listaColecciones) {
//            coleccionBL.registrarColeccion(coleccion);
//        }
// CATEGORIA
     List<Categoria> listaCategorias = new ArrayList<>();

     listaCategorias.add(new Categoria("Fundas Protectoras", Familia.ACCESORIO));
     listaCategorias.add(new Categoria("Cargadores Rápidos", Familia.ACCESORIO));

     ICategoriaBL categoriaBL = new CategoriaBLImpl();
     for (Categoria categoria : listaCategorias) {
      categoriaBL.registrarCategoria(categoria);
     }


     // 4. Marca
//        IMarcaBL marcaBL = new MarcaBLImpl();
//        List<Marca> listaMarcas = new ArrayList<>();
//
//        // Formato: marcaId, nombre (esActivo se asigna como true en tu constructor)
//        listaMarcas.add(new Marca("Nintendo"));
//        listaMarcas.add(new Marca("The Pokémon Company"));
//        listaMarcas.add(new Marca("Konami"));
//        listaMarcas.add(new Marca("Wizards of the Coast"));
//        listaMarcas.add(new Marca("Bandai Namco"));
//        listaMarcas.add(new Marca("Ultra Pro"));
//        listaMarcas.add(new Marca("Gamegenic"));
//
//        for(Marca marca : listaMarcas){
//            marcaBL.registrarMarca(marca);
//        }


//     /* PRODUCTO */
//     IProductoBL productoBL = new ProductoBLImpl();
//     IMarcaBL marcaBL = new MarcaBLImpl();
//     IFranquiciaBL franquiciaBL = new FranquiciaBLImpl();
//     IColeccionBL coleccionBL = new ColeccionBLImpl();
//     ICategoriaBL categoriaBL = new CategoriaBLImpl();
//
//     Producto producto = new Producto();
//     producto.setNombre("Producto");
//     producto.setDescripcion("Producto");
//     producto.setSku("92JR82EJ");
//     producto.setStock(100);
//     producto.setPrecio(140.50);
//     producto.setIdioma("Japones");
//     producto.setTamano("Grande");
//     producto.setEsPreventa(false);
//
//     Marca marca = marcaBL.cargarMarcaPorId(1);
//     Franquicia franquicia = franquiciaBL.cargarFranquiciaPorId(1);
//     Coleccion coleccion = coleccionBL.cargarColeccionPorId(1);
//     Categoria categoria = categoriaBL.cargarCategoriaPorId(1);
//     producto.setMarca(marca);
//     producto.setFranquicia(franquicia);
//     producto.setCategoria(categoria);
//     producto.setColeccion(coleccion);
//     Date fechaLanzamiento = new GregorianCalendar(2024, 2, 15).getTime();
//     producto.setFecLanzamiento(fechaLanzamiento);
//
//     // Ya podria agregarlo
//     productoBL.agregarProducto(producto);
//
//
//
//
//
//
//
//     /* BUSSINESS LOGIC */
//     IProductoBL productoBL = new ProductoBLImpl();
//     IUsuarioBL usuarioBL = new UsuarioBLImpl();
//     IAgenciaEnvioBL agenciaEnvioBL = new AgenciaEnvioBLImpl();
//
//     // 1. Instanciamos las Agencias
//     AgenciaEnvio olva = new AgenciaEnvio();
//     olva.setAgenciaId(1);
//     olva.setNombre("Olva Courier");
//     olva.setUrlTracking("https://www.olvacourier.com/tracking");
//     olva.setEsActivo(true);
//     agenciaEnvioBL.registrarAgencia(olva);
//
//     AgenciaEnvio shalom = new AgenciaEnvio();
//     shalom.setAgenciaId(2);
//     shalom.setNombre("Shalom Bus");
//     shalom.setUrlTracking("https://shalom.pe/rastreo");
//     shalom.setEsActivo(true);
//     agenciaEnvioBL.registrarAgencia(shalom);
//
//// 2. Simulamos los objetos Ubigeo (basados en tu imagen de la BD)
//     Ubigeo uLima = new Ubigeo(1, "150101", "LIMA", "LIMA", "LIMA");
//     Ubigeo uArequipa = new Ubigeo(6, "040101", "AREQUIPA", "AREQUIPA", "AREQUIPA");
//     Ubigeo uTrujillo = new Ubigeo(7, "130101", "LA LIBERTAD", "TRUJILLO", "TRUJILLO");
//     Ubigeo uPiura = new Ubigeo(8, "200101", "PIURA", "PIURA", "PIURA");
//
//// 3. Creamos la lista de Tarifas
//     List<TarifaEnvio> listaTarifas = new ArrayList<>();
//
//// --- Tarifas para LIMA (Olva) ---
//     TarifaEnvio t1 = new TarifaEnvio();
//     t1.setTarifaId(1);
//     t1.setAgenciaEnvio(olva);
//     t1.setUbigeo(uLima);
//     t1.setTipoEnvio(TipoEnvio.RECOJO_AGENCIA);
//     t1.setModalidad(ModalidadPago.PAGO_DESTINO);
//     t1.setCosto(15.00);
//     t1.setDiasEstimados(2);
//     t1.setActivo(true);
//     listaTarifas.add(t1);
//
//// --- Tarifas para AREQUIPA (Shalom) ---
//     TarifaEnvio t2 = new TarifaEnvio();
//     t2.setTarifaId(2);
//     t2.setAgenciaEnvio(shalom);
//     t2.setUbigeo(uArequipa);
//     t2.setTipoEnvio(TipoEnvio.RECOJO_AGENCIA);
//     t2.setModalidad(ModalidadPago.PAGO_WEB);
//     t2.setCosto(25.50);
//     t2.setDiasEstimados(4);
//     t2.setActivo(true);
//     listaTarifas.add(t2);
//
//// --- Tarifas para TRUJILLO (Olva) ---
//     TarifaEnvio t3 = new TarifaEnvio();
//     t3.setTarifaId(3);
//     t3.setAgenciaEnvio(olva);
//     t3.setUbigeo(uTrujillo);
//     t3.setTipoEnvio(TipoEnvio.A_DOMICILIO_AGENCIA);
//     t3.setModalidad(ModalidadPago.PAGO_DESTINO);
//     t3.setCosto(20.00);
//     t3.setDiasEstimados(3);
//     t3.setActivo(true);
//     listaTarifas.add(t3);
//
//// --- Tarifas para PIURA (Shalom) ---
//     TarifaEnvio t4 = new TarifaEnvio();
//     t4.setTarifaId(4);
//     t4.setAgenciaEnvio(shalom);
//     t4.setUbigeo(uPiura);
//     t4.setTipoEnvio(TipoEnvio.RECOJO_AGENCIA);
//     t4.setModalidad(ModalidadPago.PAGO_DESTINO);
//     t4.setCosto(30.00);
//     t4.setDiasEstimados(5);
//     t4.setActivo(true);
//     listaTarifas.add(t4);
//
//// 4. Prueba de impresión
//     ITarifaBL tarifaBL = new TarifaBLImpl();
//     for(TarifaEnvio t : listaTarifas) {
//      tarifaBL.agregarTarifa(t);
//     }

    }
}
