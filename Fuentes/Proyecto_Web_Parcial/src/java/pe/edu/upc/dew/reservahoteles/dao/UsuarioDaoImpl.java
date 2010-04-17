/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import pe.edu.upc.dew.reservahoteles.model.Usuario;

/**
 *
 * @author Miguel Luis
 */

public class UsuarioDaoImpl implements UsuarioDao {

     /*
     Método que se encarga de obtener el usuario de acuerdo al usuario ingresado.
     */
    public Usuario getUsuario(String username) {

        Usuario usuario = null;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("select * from usuario where codg_usu='" + username + "'");
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("SECC_USU"));
                usuario.setCodigo(rs.getString("CODG_USU"));
                usuario.setPassword(rs.getString("PWD_USU"));
                usuario.setNombre(rs.getString("NOMB_USU"));
                usuario.setApellido(rs.getString("APEL_USU"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return usuario;
    }
}