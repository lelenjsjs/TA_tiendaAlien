package pe.edu.pucp.tiendaalien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import pe.edu.pucp.tiendaalien.bl.BusinessLogicException;
import pe.edu.pucp.tiendaalien.bl.IAgenciaEnvioBL;
import pe.edu.pucp.tiendaalien.bl.ICategoriaBL;
import pe.edu.pucp.tiendaalien.bl.impl.AgenciaEnvioBLImpl;
import pe.edu.pucp.tiendaalien.bl.impl.CategoriaBLImpl;
import pe.edu.pucp.tiendaalien.dao.UbigeoDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UbigeoDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Categoria;
import pe.edu.pucp.tiendaalien.model.catalogo.Familia;
import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

import pe.edu.pucp.tiendaalien.dao.AgenciaEnvioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.AgenciaEnvioDAOImpl;
import pe.edu.pucp.tiendaalien.model.logistica.AgenciaEnvio;

import pe.edu.pucp.tiendaalien.dao.UsuarioDAO;
import pe.edu.pucp.tiendaalien.dao.impl.UsuarioDAOImpl;
import pe.edu.pucp.tiendaalien.model.usuarios.Usuario;
import pe.edu.pucp.tiendaalien.model.usuarios.Rol;

import pe.edu.pucp.tiendaalien.dao.MarcaDAO;
import pe.edu.pucp.tiendaalien.dao.impl.MarcaDAOImpl;
import pe.edu.pucp.tiendaalien.model.catalogo.Marca;




import java.util.List;


// Cuando corra las pruebas se solicita colocar diferentes IDs porque sino da error de duplicado

public class Main {

    public static void main(String[] args) throws BusinessLogicException {
        /* ////////////////////////// CATEGORIA /////////////////////////////// */
        ICategoriaBL categoriaBL = new CategoriaBLImpl();

        /* 1. Registrar una categoria: CREATE =====================================================*/
//        String nombreCategoria = "Cartas coleccionables";
//        Familia familia = Familia.ACCESORIO;
//        Categoria categoriaACrear = new Categoria(nombreCategoria,familia);
//        categoriaACrear = categoriaBL.registrarCategoria(categoriaACrear);
//
//        System.out.println(categoriaACrear);

        /* 2. Buscar agencia por ID: READ ================================================================*/
//        int idCategoria = 1;
//        Categoria categoriaPorId = categoriaBL.cargarCategoriaPorId(idCategoria);
//        System.out.println("Buscando Categoria " + "con ID = " + idCategoria + "....");
//        if(categoriaPorId == null){
//            System.out.println("Categoria no encontrada");
//        }
//        else {
//            System.out.println(categoriaPorId);
//        }

        /* 3. Actualizar una categoria de envio: UPDATE =====================================================*/
//        String nombreCategoria = "Sellado";// !!!!!!!!!!!!!
//        Categoria categoriaACambiar = categoriaBL.cargarCategoriaPorNombre(nombreCategoria);
//        System.out.println("ORIGINAL -> " + categoriaACambiar);
//
//        String nombreAColocar = "Sin abrir"; // !!!!!!!!!!!!
//        categoriaACambiar.setNombre(nombreAColocar);
//
//        categoriaACambiar = categoriaBL.modificarCategoria(categoriaACambiar);
//        System.out.println("MODIFICADO -> " + categoriaACambiar);
//
        /* 4. Desactivar la agencia de envio: "DELETE" ==================================================== */
//        String nombreCategoriaAEliminar = "Cartas Sueltas";// !!!!!!
//        Categoria categoriaAEliminar = categoriaBL.cargarCategoriaPorNombre(nombreCategoriaAEliminar);
//
//        System.out.println("Eliminare : " + categoriaAEliminar);
//        categoriaBL.eliminarCategoria(categoriaAEliminar);

//
//        /* 5. Listar todas ================================================================================ */
//        List<Categoria> listaCategorias = categoriaBL.listarCategorias();
//
//        if(listaCategorias.isEmpty()){
//            System.out.println("No hay categorias registradas");
//        }
//        else {
//            System.out.println("CATEGORIAS");
//            for (Categoria categoria : listaCategorias) {
//                System.out.println(categoria);
//            }
//        }
//
        /* 6. Buscar agencia por nombre ================================================================== */
//        String nombreCategoriaParaBuscar = "Accesorios";
//        Categoria categoriaPorNom = categoriaBL.cargarCategoriaPorNombre(nombreCategoriaParaBuscar);
//        System.out.println("Buscando Categoria:  " + nombreCategoriaParaBuscar);
//        if(categoriaPorNom == null){
//            System.out.println("Categoria no encontrada");
//        }
//        else {
//            System.out.println(categoriaPorNom);
//        }




    }
}