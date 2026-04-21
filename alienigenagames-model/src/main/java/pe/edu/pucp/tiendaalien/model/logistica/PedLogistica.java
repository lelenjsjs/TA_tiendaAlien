package pe.edu.pucp.tiendaalien.model.logistica;

public class PedLogistica {
    private int pedLogisticaId;
    private EstadoLogistica estadoLogistica;
    private String receptorNombre;
    private String receptorTipoDoc;
    private String receptorNroDoc;
    private String receptorCel;

    public PedLogistica() {}

    public int getPedLogisticaId() { return pedLogisticaId; }
    public void setPedLogisticaId(int pedLogisticaId) { this.pedLogisticaId = pedLogisticaId; }

    public EstadoLogistica getEstadoLogistica() { return estadoLogistica; }
    public void setEstadoLogistica(EstadoLogistica estadoLogistica) { this.estadoLogistica = estadoLogistica; }

    public String getReceptorNombre() { return receptorNombre; }
    public void setReceptorNombre(String receptorNombre) { this.receptorNombre = receptorNombre; }

    public String getReceptorTipoDoc() { return receptorTipoDoc; }
    public void setReceptorTipoDoc(String receptorTipoDoc) { this.receptorTipoDoc = receptorTipoDoc; }

    public String getReceptorNroDoc() { return receptorNroDoc; }
    public void setReceptorNroDoc(String receptorNroDoc) { this.receptorNroDoc = receptorNroDoc; }

    public String getReceptorCel() { return receptorCel; }
    public void setReceptorCel(String receptorCel) { this.receptorCel = receptorCel; }
}
