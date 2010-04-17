/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.model;

/**
 *
 * @author Ricardo
 */
public class Reserva {
    private Integer idReserva;
    private Integer idTipoHab;
    private Integer idCliente;
    //private String habitacion;
    private Habitacion habitacion;
    private String cliente;
    private String tipoHabitacion;
    private String codigo;
    private String fecInicio;
    private String fecFin;
    private Integer estado; // 1=Registrada, 2=Asignada, 3=Pagada, 4=Anulada

    //Sirve para la consulta
    private String descEstado;
    private String descTipoHab;
    private double Precio;
    private String nomCliente;

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    

    public String getCliente() {
        return cliente;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
   

    public Reserva()
    {}

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    
    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getDescTipoHab() {
        return descTipoHab;
    }

    public void setDescTipoHab(String descTipoHab) {
        this.descTipoHab = descTipoHab;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdTipoHab() {
        return idTipoHab;
    }

    public void setIdTipoHab(Integer idTipoHab) {
        this.idTipoHab = idTipoHab;
    }



  

    public Integer getEstado() {
        return estado;
    }

  

    public Integer getIdReserva() {
        return idReserva;
    }



    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFecFin() {
        return fecFin;
    }

    public void setFecFin(String fecFin) {
        this.fecFin = fecFin;
    }

    public String getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(String fecInicio) {
        this.fecInicio = fecInicio;
    }



    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

   




}
