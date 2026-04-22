package pe.edu.pucp.tiendaalien.model.logistica;
import java.util.Date;

public class PedLogisticaEnvio extends PedLogistica{

    private int envioId;
    private TipoEnvio tipoEnvio;
    private ModalidadPago modalidad;
    private String direccionEntrega;
    private String referenciaEnvio;
    private String codTracking;
    private double costoEnvio;
    private Date fec_estimadaEntrega;
    private String notasAdicionales;
    private Date fecEntregaReal;

    private AgenciaEnvio agenciaEnvio;
    private TarifaEnvio tarifaEnvio;

    public PedLogisticaEnvio() {}

    // Getters y Setters

    public int getEnvioId() {
        return envioId;
    }

    public void setEnvioId(int envioId) {
        this.envioId = envioId;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public ModalidadPago getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadPago modalidad) {
        this.modalidad = modalidad;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getReferenciaEnvio() {
        return referenciaEnvio;
    }

    public void setReferenciaEnvio(String referenciaEnvio) {
        this.referenciaEnvio = referenciaEnvio;
    }

    public String getCodTracking() {
        return codTracking;
    }

    public void setCodTracking(String codTracking) {
        this.codTracking = codTracking;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Date getFec_estimadaEntrega() {
        return fec_estimadaEntrega;
    }

    public void setFec_estimadaEntrega(Date fec_estimadaEntrega) {
        this.fec_estimadaEntrega = fec_estimadaEntrega;
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
    }

    public Date getFecEntregaReal() {
        return fecEntregaReal;
    }

    public void setFecEntregaReal(Date fecEntregaReal) {
        this.fecEntregaReal = fecEntregaReal;
    }

    public AgenciaEnvio getAgenciaEnvio() {
        return agenciaEnvio;
    }

    public void setAgenciaEnvio(AgenciaEnvio agenciaEnvio) {
        this.agenciaEnvio = agenciaEnvio;
    }

    public TarifaEnvio getTarifaEnvio() {
        return tarifaEnvio;
    }

    public void setTarifaEnvio(TarifaEnvio tarifaEnvio) {
        this.tarifaEnvio = tarifaEnvio;
    }
}