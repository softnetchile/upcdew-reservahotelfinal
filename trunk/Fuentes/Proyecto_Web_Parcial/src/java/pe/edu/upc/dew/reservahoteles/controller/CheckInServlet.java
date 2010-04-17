/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pe.edu.upc.dew.reservahoteles.model.Reserva;
import pe.edu.upc.dew.reservahoteles.service.ReservaService;
/**
 *
 * @author Ricardo
 */
public class CheckInServlet extends HttpServlet {
   
    private ReservaService reservaService;


    @Override
    public void init() throws ServletException {  
       WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
       this.reservaService = (ReservaService) context.getBean("reservaService");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos los parametros del jsp

        HttpSession session=req.getSession(true);

        if(req.getParameter("accion").toString().equals("Registrarcheckin"))
        {
            agregarCheckIn(session, req, resp);
        }
    }

    /*
     Método que se encarga de realizar el checkin de una reserva; en el cual se asigna el número de habitación
     de acuerdo al tipo de habitación.
     */
    public void agregarCheckIn(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

           int intIdReserva = Integer.parseInt(req.getParameter("idreserva").toString());
            Reserva reserva = reservaService.agregarCheckIn(intIdReserva);

            req.setAttribute("varRes", intIdReserva);
            req.setAttribute("varCodigo", reserva.getCodigo());
            req.setAttribute("varHabitacion", reserva.getHabitacion().getCodigo());
            req.setAttribute("varFechaInicio", reserva.getFecInicio());
            req.setAttribute("varFechaFin", reserva.getFecFin());
            req.setAttribute("varPrecio",    reserva.getPrecio());
            req.setAttribute("varCliente",    reserva.getCliente());
            req.setAttribute("varTipoHab",    reserva.getTipoHabitacion());

            req.getRequestDispatcher("registrarCheckIn.jsp").forward(req, resp);


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
            out.println("<title>Servlet CheckInServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckInServlet at " + request.getContextPath () + "</h1>");
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
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
