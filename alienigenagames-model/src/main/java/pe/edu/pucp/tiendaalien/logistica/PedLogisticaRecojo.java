package pe.edu.pucp.tiendaalien.logistica;

import java.util.Date;

public class PedLogisticaRecojo extends PedLogistica {
    private int recojoId;
    private Date fecLimRecojo;
    private Date fecRecojoReal;

    public PedLogisticaRecojo() {
        super();
    }

    public int getRecojoId() { return recojoId; }
    public void setRecojoId(int recojoId) { this.recojoId = recojoId; }

    public Date getFecLimRecojo() { return fecLimRecojo; }
    public void setFecLimRecojo(Date fecLimRecojo) { this.fecLimRecojo = fecLimRecojo; }

    public Date getFecRecojoReal() { return fecRecojoReal; }
    public void setFecRecojoReal(Date fecRecojoReal) { this.fecRecojoReal = fecRecojoReal; }
}
