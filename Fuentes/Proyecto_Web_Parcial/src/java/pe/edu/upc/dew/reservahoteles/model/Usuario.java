
package pe.edu.upc.dew.reservahoteles.model;

/**
 *
 * @author Miguel Luis
 */

public class Usuario {
    private Integer idUsuario;
    private String codigo;
    private String password;
    private String nombre;
    private String apellido;

     public Usuario()
     {}

   public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

     public String getCodigo() {
        return codigo;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }



}
