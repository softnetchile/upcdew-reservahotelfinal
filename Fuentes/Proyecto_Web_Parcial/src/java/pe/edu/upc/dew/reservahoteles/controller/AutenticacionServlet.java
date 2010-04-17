/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.service.UsuarioService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import pe.edu.upc.dew.reservahoteles.model.TipoHabitacion;
import pe.edu.upc.dew.reservahoteles.model.Usuario;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pe.edu.upc.dew.reservahoteles.service.ClienteService;

/**
 *
 * @author Miguel Luis
 * Servlet que se encargar de validar el usuario y contraseña ingresados.
 * El usuario puede ser de dos tipo Cliente / Empleado.
 */
public class AutenticacionServlet extends HttpServlet {

    
    private UsuarioService usuarioService;
    private ClienteService clienteService;

    @Override
    public void init() throws ServletException {
       WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
       this.usuarioService = (UsuarioService) context.getBean("usuarioService");
       this.clienteService = (ClienteService) context.getBean("clienteService");
    }





     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
         //Recuperamos la sesion activa
         HttpSession session=req.getSession(true);
         
         String strTipoUsuario = req.getParameter("tipo_usuario").toString();
         String strUsuario = req.getParameter("txt_Usuario").toString().toUpperCase();
         String strPassword = req.getParameter("txt_Password").toString();
   
         //Existen 2 tipos de usuarios: clientes (tabla cliente) o empleados (tabla usuario)
         //Dependiendo de cada tipo de usuario carga un menu con diferentes opciones
         if(strTipoUsuario.equals("EMPLEADO"))
         {

            Usuario usuario = usuarioService.getUsuario(strUsuario);
            //Verifica si existe el usuario
            if(usuario != null)
            {
                //Verifica que sea el password correcto
                if(usuario.getPassword().equals(strPassword))
                {
                        session.setAttribute("SS_USUARIO", usuario);
                     session.setAttribute("SS_CLIENTE", null);
                     session.setAttribute("varUserName", usuario.getNombre()+" "+usuario.getApellido());
                    req.getRequestDispatcher("indexAdmin.jsp").forward(req, resp);
                }
                else
                {
                    //Enviar mensaje al login de password incorrecto
                    //Pasa variables por la session al login.jsp; refrescar datos ingresados por el usuario
                    //de lo contrario se pierden estos datos
                    session.setAttribute("usuario", strUsuario);
                    session.setAttribute("password", strPassword);
                    session.setAttribute("selecempleado", "selected");
                    session.setAttribute("errorMessage","Contraseña Incorrecta");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
                
            }
            else
            {
                //Enviar mensaje al login -- usuario no existe
                //Pasa variables por la session al login.jsp; refrescar datos ingresados por el usuario
                //de lo contrario se pierden estos datos
                session.setAttribute("usuario", strUsuario);
                session.setAttribute("password", strPassword);
                session.setAttribute("selecempleado", "selected");
                session.setAttribute("errorMessage","Empleado No Existe");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

         }
         else if(strTipoUsuario.equals("CLIENTE"))
         {
            Cliente cliente = clienteService.getCliente(strUsuario);
            //Verifica si existe el cliente
            if(cliente != null)
            {
                //Verifica que sea el password correcto
                if(cliente.getPassword().equals(strPassword))
                {
                    List<TipoHabitacion> ListTipoHabitacion = clienteService.listarTipoHabitacion();
                    session.setAttribute("SS_LIST_TIPO_HABITACION", ListTipoHabitacion);
                    session.setAttribute("SS_CLIENTE", cliente);
                    session.setAttribute("varUserName", cliente.getNombre()+" "+cliente.getApellido());
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
                else
                {
                    //Enviar mensaje al login de password incorrecto
                    //Pasa variables por la session al login.jsp; refrescar datos ingresados por el usuario
                    //de lo contrario se pierden estos datos
                    session.setAttribute("usuario", strUsuario);
                    session.setAttribute("password", strPassword);
                    session.setAttribute("selecempleado", "");
                    session.setAttribute("errorMessage","Contraseña Incorrecta");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }

            }
            else
            {
                //Enviar mensaje al login -- usuario no existe
                //Pasa variables por la session al login.jsp; refrescar datos ingresados por el usuario
                //de lo contrario se pierden estos datos
                session.setAttribute("usuario", strUsuario);
                session.setAttribute("password", strPassword);
                session.setAttribute("selecempleado", "");
                session.setAttribute("errorMessage","Cliente No Existe");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

         }
         

    }


    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AutenticacionServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AutenticacionServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
