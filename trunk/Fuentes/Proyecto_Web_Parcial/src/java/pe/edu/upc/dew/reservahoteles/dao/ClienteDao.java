/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.dao;
import java.util.List;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.model.TipoHabitacion;

/**
 *
 * @author Miguel Luis
 */
public interface ClienteDao {


    Cliente getCliente(String codigo);

    List<TipoHabitacion> listarTipoHabitacion();

}
