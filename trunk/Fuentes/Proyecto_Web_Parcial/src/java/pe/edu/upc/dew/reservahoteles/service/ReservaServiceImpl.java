/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.service;

import java.util.List;
import pe.edu.upc.dew.reservahoteles.dao.ReservaDao;
import pe.edu.upc.dew.reservahoteles.dao.ReservaDaoImpl;
import pe.edu.upc.dew.reservahoteles.model.Reserva;

/**
 *
 * @author Miguel Luis
 */
public class ReservaServiceImpl implements ReservaService {
    private ReservaDao reservaDao;

    public void setReservaDao(ReservaDao reservaDao) {  //esto es inversion de control
        this.reservaDao = reservaDao;     //le voy a pasar el objeto clienteDaoImpl. Principio de hollywood yo te llamo
    }


    public List<Reserva> listarReservasCheckIn(String fechaInicio, String fechaFin, int intIdTipoHab, int intIdCliente)
    {
        return reservaDao.listarReservasCheckIn(fechaInicio, fechaFin, intIdTipoHab, intIdCliente);
    }

    public List<Reserva> listarReservasTodos(String fechaInicio, String fechaFin, int intIdTipoHab, int intIdCliente)
    {
        return reservaDao.listarReservasTodos(fechaInicio, fechaFin, intIdTipoHab, intIdCliente);
    }


    public Reserva agregarReserva(Reserva res)
    {
        return reservaDao.agregarReservas(res);
    }

    public Reserva agregarCheckIn(int idReserva)
    {
        return reservaDao.agregarCheckIn(idReserva);
    }

     public List<Reserva> listarReservasCheckOut(String nombre, String apellido, String habitacion, int intIdTipoHab, int intIdCliente)
    {
        return reservaDao.listarReservasCheckOut(nombre, apellido, habitacion, intIdTipoHab, intIdCliente);
    }

     public Reserva agregarCheckOut(int idReserva)
    {
        return reservaDao.agregarCheckOut(idReserva);
    }


}
