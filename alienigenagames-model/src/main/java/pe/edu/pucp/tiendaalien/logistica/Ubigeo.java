package pe.edu.pucp.tiendaalien.logistica;

public class Ubigeo {
    private int ubigeoId;
    private String codigo;
    private String departamento;
    private String provincia;
    private String distrito;

    public Ubigeo(){
    }

    public Ubigeo(int ubigeoId,String codigo,String departamento,
                  String provincia,String distrito){
        this.ubigeoId=ubigeoId;
        this.codigo=codigo;
        this.departamento=departamento;
        this.provincia=provincia;
        this.distrito=distrito;
    }

    public void setUbigeoId(int ubigeoId) {
        this.ubigeoId = ubigeoId;
    }
    public int getUbigeoId() {
        return ubigeoId;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getDepartamento() {
        return departamento;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getProvincia() {
        return provincia;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public String getDistrito() {
        return distrito;
    }

}