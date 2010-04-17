/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.service;

import pe.edu.upc.dew.reservahoteles.dao.UsuarioDao;
import pe.edu.upc.dew.reservahoteles.dao.UsuarioDaoImpl;
import pe.edu.upc.dew.reservahoteles.model.Usuario;

/**
 *
 * @author Miguel Luis
 */
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioDao usuarioDao;

 
    public void setUsuarioDao(UsuarioDao usuarioDao) {  //esto es inversion de control
        this.usuarioDao = usuarioDao;     //le voy a pasar el objeto clienteDaoImpl. Principio de hollywood yo te llamo
    }

    public Usuario getUsuario(String username) {
        return usuarioDao.getUsuario(username);
    }
}
