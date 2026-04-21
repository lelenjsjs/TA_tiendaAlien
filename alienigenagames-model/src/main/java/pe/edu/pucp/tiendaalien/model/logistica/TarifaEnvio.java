package pe.edu.pucp.tiendaalien.model.logistica;

import pe.edu.pucp.tiendaalien.model.usuarios.Ubigeo;

public class TarifaEnvio {

    private int tarifaId;
    private Ubigeo ubigeo;     // FK hacia Ubigeo
    private AgenciaEnvio agencia;    // FK hacia AgenciaEnvio
    private ModalidadPago modalidad;
    private TipoEnvio tipoEnvio;
    private double costo;
    private int diasEstimados;
    private boolean activo;


    public TarifaEnvio() {
    }

    public TarifaEnvio(int tarifaId, Ubigeo ubigeo, AgenciaEnvio agencia, ModalidadPago modalidad,
                       TipoEnvio tipoEnvio, double costo,
                       int diasEstimados, boolean activo) {
        this.tarifaId = tarifaId;
        this.ubigeo = ubigeo;
        this.agencia = agencia;
        this.modalidad = modalidad;
        this.tipoEnvio=tipoEnvio;
        this.costo = costo;
        this.diasEstimados = diasEstimados;
        this.activo = activo;
    }

    public int getTarifaId() {
        return tarifaId;
    }

    public void setTarifaId(int tarifaId) {
        this.tarifaId = tarifaId;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }
    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setAgenciaEnvio(AgenciaEnvio agencia) {
        this.agencia = agencia;
    }
    public AgenciaEnvio getAgenciaEnvio() {
        return agencia;
    }

    public ModalidadPago getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadPago modalidad) {
        this.modalidad = modalidad;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    public double getCosto() {
        return costo;
    }

    public void setDiasEstimados(int diasEstimados) {
        this.diasEstimados = diasEstimados;
    }
    public int getDiasEstimados() {
        return diasEstimados;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public boolean getActivo() {
        return activo;
    }


}