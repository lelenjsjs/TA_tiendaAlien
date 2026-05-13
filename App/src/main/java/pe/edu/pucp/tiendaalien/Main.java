package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import pe.edu.pucp.tiendaalien.bl.*;
import pe.edu.pucp.tiendaalien.bl.impl.*;
import pe.edu.pucp.tiendaalien.model.catalogo.*;
import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.ventas.DetallePed;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

record ItemCarrito(int idProducto, int cantidad) {}
// Cuando corra las pruebas se solicita colocar diferentes IDs porque sino da error de duplicado
public class Main {
    public static void main(String[] args) throws BusinessLogicException, SQLException {
        /* BUSSINESS LOGIC */
        IProductoBL productoBL = new ProductoBLImpl();

        /* "HACER UN PEDIDO */
        // 1.
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);

        List<DetallePed> listaDetallePedido = new ArrayList<>();

        List<Producto> listaProducto = new ArrayList<>();
        Producto prod1 = productoBL.cargarProductoPorId(1);
        Producto prod2 = productoBL.cargarProductoPorId(2);
        Producto prod3 = productoBL.cargarProductoPorId(3);
        Producto prod4 = productoBL.cargarProductoPorId(4);
        listaProducto.add(prod1);
        listaProducto.add(prod2);
        listaProducto.add(prod3);
        listaProducto.add(prod4);

        DetallePed det1 = new DetallePed();
        det1.setProducto(prod1);
        det1.setPrecioUnitCongelado(prod1.getPrecio());
        det1.setEsPreventaCongelado(prod1.getEsPreventa());
        det1.setCantidad(10);

    }
}
}