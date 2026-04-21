package pe.edu.pucp.tiendaalien.model.usuarios;

import java.util.Date;
import java.util.List;

public class Usuario {
    private int usuarioId;
    private String nombres;
    private String apellidos;
    private String email;
    private String contraHash;
    private String celular;
    private Rol rol;
    private Date fechaCreacion;

    private List<DireccionUsuario> direcciones;

    public Usuario(){
    }

    public Usuario(int usuarioId,String nombres,String apellidos,String contraHash,String email,
                   String celular,Rol rol, Date fechaCreacion,List<DireccionUsuario> direcciones){
        this.usuarioId=usuarioId;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.email=email;
        this.contraHash=contraHash;
        this.celular=celular;
        this.rol=rol;
        this.fechaCreacion=fechaCreacion;
        this.direcciones=direcciones;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getNombres() {
        return nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getApellidos() {
        return apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setContraHash(String contraHash) {
        this.contraHash = contraHash;
    }
    public String getContraHash() {
        return contraHash;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getCelular() {
        return celular;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public Rol getRol() {
        return this.rol;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setDirecciones(List<DireccionUsuario> direcciones) {
        this.direcciones = direcciones;
    }
    public List<DireccionUsuario> getDirecciones() {
        return direcciones;
    }


}
