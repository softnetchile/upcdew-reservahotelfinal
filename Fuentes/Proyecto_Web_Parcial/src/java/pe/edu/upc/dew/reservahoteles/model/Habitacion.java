/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.model;

/**
 *
 * @author Ricardo
 */
public class Habitacion {
    private Integer idHabitacion;
    private Integer estado;
    private String codigo;

    public Habitacion(Integer idHabitacion, String codigo)
    {
        this.idHabitacion = idHabitacion;
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo; 
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public Habitacion() {}

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }


}
