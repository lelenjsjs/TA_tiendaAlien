package pe.edu.pucp.tiendaalien.usuarios;
import pe.edu.pucp.tiendaalien.logistica.Ubigeo;

public class DireccionUsuario {

    private int direccionId;
    private String direccion;
    private Boolean esPrincipal;
    private String referencia;
    private Ubigeo ubigeo;

    private Usuario usuario;

    public DireccionUsuario() {
    }

    public DireccionUsuario(int direccionId, String direccion, Boolean esPrincipal,
                            String referencia, Ubigeo ubigeo,Usuario usuario) {
        this.direccionId = direccionId;
        this.direccion = direccion;
        this.esPrincipal = esPrincipal;
        this.referencia = referencia;
        this.ubigeo = ubigeo;
        this.usuario = usuario;
    }

    // GETTERS
    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }
    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getReferencia() {
        return referencia;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }
    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }


}
