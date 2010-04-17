/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.model;

/**
 *
 * @author Ricardo
 */
public class Cliente {
    private Integer idCliente;
    private String codigo;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;

     public Cliente()
     {}

   public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

}
