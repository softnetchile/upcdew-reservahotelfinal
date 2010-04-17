/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.dao;

import pe.edu.upc.dew.reservahoteles.model.Usuario;

/**
 *
 * @author Miguel Luis
 */
public interface UsuarioDao {


    Usuario getUsuario(String username);
}
