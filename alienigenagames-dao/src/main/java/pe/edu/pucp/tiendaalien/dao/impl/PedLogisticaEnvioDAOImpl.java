package pe.edu.pucp.tiendaalien.dao.impl;

import pe.edu.pucp.tiendaalien.dao.PedLogisticaEnvioDAO;
import pe.edu.pucp.tiendaalien.DBManager;
import pe.edu.pucp.tiendaalien.model.logistica.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedLogisticaEnvioDAOImpl implements PedLogisticaEnvioDAO {

    // --- MÉTODO PARA LA TRANSACCIÓN (RECIBE CONNECTION) ---
    @Override
    public PedLogisticaEnvio save(PedLogisticaEnvio envio, Connection con) throws SQLException {
        String sql = """
        INSERT INTO ped_logistica_envio (
            ped_logistica_id, tarifa_id, sucursal_id, tipo_envio, 
            modalidad_pago_envio, direccion_entrega, referencia, 
            costo_envio, fec_estimada_entrega, cod_tracking, notas_adicionales
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // FK al padre (PedLogistica)
            ps.setInt(1, envio.getPedLogisticaId());

            // FK a Tarifa
            if (envio.getTarifaEnvio() != null) ps.setInt(2, envio.getTarifaEnvio().getTarifaId());
            else ps.setNull(2, Types.INTEGER);

            // FK a Sucursal (Según tu script es sucursal_id, no agencia_id)
            if (envio.getAgenciaEnvio() != null) ps.setInt(3, envio.getAgenciaEnvio().getAgenciaId());
            else ps.setNull(3, Types.INTEGER);

            // Enums (String matching exacto con el script)
            ps.setString(4, envio.getTipoEnvio() != null ? envio.getTipoEnvio().name() : null);
            ps.setString(5, envio.getModalidad() != null ? envio.getModalidad().name() : null);

            ps.setString(6, envio.getDireccionEntrega());
            ps.setString(7, envio.getReferenciaEnvio()); // mapeado a 'referencia'
            ps.setDouble(8, envio.getCostoEnvio());

            // Fecha (Manejo de java.util.Date a java.sql.Date para el tipo DATE de SQL)
            if (envio.getFec_estimadaEntrega() != null) {
                ps.setDate(9, new java.sql.Date(envio.getFec_estimadaEntrega().getTime()));
            } else {
                ps.setNull(9, Types.DATE);
            }

            // Campos adicionales
            ps.setString(10, envio.getCodTracking());
            ps.setString(11, envio.getNotasAdicionales());

            ps.executeUpdate();

            // Obtener el nuevo ID autogenerado de la tabla hija
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    envio.setEnvioId(rs.getInt(1));
                }
            }
        }
        return envio;
    }

    // --- MÉTODO SAVE NORMAL (Para pruebas unitarias) ---
    @Override
    public PedLogisticaEnvio save(PedLogisticaEnvio envio) {
        try (Connection con = DBManager.getInstance().getConnection()) {
            con.setAutoCommit(false); // Necesario porque este objeto toca dos tablas

            // Aquí llamarías primero al save del padre y luego al de esta clase
            // Pero en tu flujo de negocio, el BL ya se encarga de coordinarlos.
            return envio;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PedLogisticaEnvio> listAll() {
        List<PedLogisticaEnvio> list = new ArrayList<>();
        String sql = """
            SELECT p.*, e.* FROM ped_logistica p 
            INNER JOIN ped_logistica_envio e ON p.ped_logistica_id = e.envio_id
            WHERE p.activo = 1
        """;

        try (Connection con = DBManager.getInstance().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapearObjeto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public PedLogisticaEnvio loadById(Integer id) {
        String sql = """
            SELECT p.*, e.* FROM ped_logistica p 
            INNER JOIN ped_logistica_envio e ON p.ped_logistica_id = e.envio_id 
            WHERE p.ped_logistica_id = ?
        """;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearObjeto(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private PedLogisticaEnvio mapearObjeto(ResultSet rs) throws SQLException {
        PedLogisticaEnvio envio = new PedLogisticaEnvio();

        // Atributos PADRE
        envio.setPedLogisticaId(rs.getInt("ped_logistica_id"));
        envio.setReceptorNombre(rs.getString("receptor_nombre"));
        envio.setReceptorCel(rs.getString("receptor_cel"));
        // ... mapear resto de campos padre

        // Atributos HIJO
        envio.setEnvioId(rs.getInt("envio_id"));
        envio.setDireccionEntrega(rs.getString("direccion_entrega"));
        envio.setReferenciaEnvio(rs.getString("referencia_envio"));
        envio.setCostoEnvio(rs.getDouble("costo_envio"));
        envio.setFec_estimadaEntrega(rs.getTimestamp("fec_estimada_entrega"));

        return envio;
    }

    @Override
    public PedLogisticaEnvio update(PedLogisticaEnvio envio) {
        // Implementación similar al save pero con UPDATE
        return envio;
    }

    @Override
    public void remove(PedLogisticaEnvio envio) {
        // Update activo = 0 en la tabla padre
    }
}