/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.model.TipoHabitacion;

/**
 *
 * @author Miguel Luis
 */

public class ClienteDaoImpl implements ClienteDao {

    /*
     Método que se encarga de obtener el cliente deacuerdo al usuario ingresado.
     */
    public Cliente getCliente(String username) {

        Cliente cliente = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("select * from cliente where codg_cli='" + username + "'");
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("SECC_CLI"));
                cliente.setCodigo(rs.getString("CODG_CLI"));
                cliente.setPassword(rs.getString("PWD_CLI"));
                cliente.setNombre(rs.getString("NOMB_CLI"));
                cliente.setApellido(rs.getString("APEL_CLI"));
                cliente.setTelefono("TELF_CLI");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return cliente;
    }

    /*
     Método que se encarga de obtener los tipos de habitación que maneja el hotel.
     */
    public List<TipoHabitacion> listarTipoHabitacion()
    {
        List<TipoHabitacion> ListTipoHabitacion = new ArrayList<TipoHabitacion>();
        TipoHabitacion oTipoHabitacion = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("select * from tipo_habitacion");
            if (rs.next()) {
                oTipoHabitacion = new TipoHabitacion();
                oTipoHabitacion.setIdTipo(rs.getInt("SECC_TIPH"));
                oTipoHabitacion.setCodigo(rs.getString("CODG_TIPH"));
                oTipoHabitacion.setNombre(rs.getString("NOMB_TIPH"));
                oTipoHabitacion.setPrecio(rs.getDouble("PRECIO_TIPH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return ListTipoHabitacion;
    }
}