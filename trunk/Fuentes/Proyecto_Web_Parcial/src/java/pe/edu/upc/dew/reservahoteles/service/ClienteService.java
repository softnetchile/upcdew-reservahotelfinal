/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.service;
import java.util.List;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.model.TipoHabitacion;

/**
 *
 * @author Miguel Luis
 */
public interface ClienteService {

    Cliente getCliente(String username);

    List<TipoHabitacion> listarTipoHabitacion();

}