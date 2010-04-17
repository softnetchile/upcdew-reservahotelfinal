/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.service;

import java.util.List;
import pe.edu.upc.dew.reservahoteles.dao.ClienteDao;
import pe.edu.upc.dew.reservahoteles.dao.ClienteDaoImpl;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.model.TipoHabitacion;

/**
 *
 * @author Miguel Luis
 */
public class ClienteServiceImpl implements ClienteService {
    private ClienteDao clienteDao;

    public void setClienteDao(ClienteDao clienteDao) {  //esto es inversion de control
        this.clienteDao = clienteDao;     //le voy a pasar el objeto clienteDaoImpl. Principio de hollywood yo te llamo
    }


    public Cliente getCliente(String username)
    {
        return clienteDao.getCliente(username);
    }

    public List<TipoHabitacion> listarTipoHabitacion()
    {
        return clienteDao.listarTipoHabitacion();
    }
}
