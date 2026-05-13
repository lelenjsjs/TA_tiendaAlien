package pe.edu.pucp.tiendaalien.model.usuarios;

public class DireccionUsuario {
    private Integer id;
    private Usuario usuario;
    private Ubigeo ubigeo;

    private String direccion;
    private Boolean esPrincipal;
    private String referencia;

    private Boolean esActivo;





    // Constructor
    public DireccionUsuario() {
        usuario = new Usuario();
        ubigeo = new Ubigeo();
    }

    public DireccionUsuario(Usuario usuario, Ubigeo ubigeo, String direccion, Boolean esPrincipal, String referencia, Boolean esActivo) {
        this.usuario = usuario;
        this.ubigeo = ubigeo;
        this.direccion = direccion;
        this.esPrincipal = esPrincipal;
        this.referencia = referencia;
        this.esActivo = esActivo;
        usuario = new Usuario();
        ubigeo = new Ubigeo();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public void setIdUsuario(int id){
        this.usuario.setUsuarioId(id);
    }


    public int getIdUsuario() {
        return usuario.getUsuarioId();
    }

    public void setIdUbigeo(int id){
        this.ubigeo.setUbigeoId(id);
    }

    public int getIdUbigeo() {
        return ubigeo.getUbigeoId();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean principal) {
        this.esPrincipal = principal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Direccion = " + direccion + ", EsPrincipal = " + esPrincipal + ", Referencia = " + referencia + "\n";
    }
}
