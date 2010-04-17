/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.dao;

import java.util.List;
import pe.edu.upc.dew.reservahoteles.model.Reserva;

/**
 *
 * @author Ricardo
 */
public interface ReservaDao
{
    List<Reserva> listarReservasCheckIn(String fechaInicio, String fechaFin, int intIdTipoHab, int intIdCliente);
    List<Reserva> listarReservasTodos(String fechaInicio, String fechaFin, int intIdTipoHab, int intIdCliente);
    Reserva agregarReservas(Reserva res);
    Reserva agregarCheckIn(int idReserva);
    List<Reserva> listarReservasCheckOut(String nombre, String apellido, String habitacion, int intIdTipoHab, int intIdCliente);
    Reserva agregarCheckOut(int idReserva);
}
