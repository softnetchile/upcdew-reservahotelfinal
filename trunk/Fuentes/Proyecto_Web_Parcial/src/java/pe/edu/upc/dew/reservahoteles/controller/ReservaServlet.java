/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.upc.dew.reservahoteles.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.derby.client.am.DateTime;
import pe.edu.upc.dew.reservahoteles.model.Cliente;
import pe.edu.upc.dew.reservahoteles.model.Reserva;
import pe.edu.upc.dew.reservahoteles.service.ReservaService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * @author Ricardo
 * 
 */
public class ReservaServlet extends HttpServlet {
   
     private ReservaService reservaService;


    @Override
    public void init() throws ServletException { 
       WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
       this.reservaService = (ReservaService) context.getBean("reservaService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 

        HttpSession session=req.getSession(true);

        if(req.getParameter("accion").toString().equals("registrar"))
        {
            try {
                agregarReserva(session, req, resp);
            } catch (ParseException ex) {
                Logger.getLogger(ReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(req.getParameter("accion").toString().equals("buscarTodos"))
        {
            listarReservasTodos(session, req, resp);
        }
    
        else if(req.getParameter("accion").toString().equals("checkin"))
        {
            listarReservasCheckIn(session, req, resp);
        }

        else if(req.getParameter("accion").toString().equals("checkout"))
        {
            listarReservasCheckOut(session, req, resp);
        }


    }

    /*
      Método encargado de registrar la reserva de los clientes. Se crea en estado 'Registrada'.
      Además, realiza la validación de fechas y disponibilidad de habitaciones.
      El importe total es calculado de acuerdo a la cantidad de días y el precio de la habitación.
    */

    public void agregarReserva(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException
    {
            Cliente cliente = new Cliente();

            if(session.getAttribute("SS_CLIENTE")!=null)
                cliente =  (Cliente)session.getAttribute("SS_CLIENTE");

            int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
            String strFechaInicio = req.getParameter("txtDesde").toString();
            String strFechaFin = req.getParameter("txtHasta").toString();

            Reserva reserva = new Reserva();
            reserva.setIdCliente(cliente.getIdCliente());
            reserva.setIdTipoHab(intTipoHab);
            reserva.setPrecio(Double.parseDouble(req.getParameter("txtPrecio").toString()));
            reserva.setFecInicio(strFechaInicio);
            reserva.setFecFin(strFechaFin);
            reserva.setEstado(1);//Registrada

            DateFormat formatter ;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateInicio, dateFin ;
            dateInicio = (Date)formatter.parse(strFechaInicio);
            dateFin = (Date)formatter.parse(strFechaFin);

            if(dateFin.before(dateInicio))
            {
                    session.setAttribute("registromensaje","La fecha final no puede ser menor a la fecha inicial");
                    reserva.setCodigo("-");
                    session.setAttribute("SS_RESERVA",reserva);
                    req.getRequestDispatcher("registrar.jsp").forward(req, resp);
            }
            else
            {
                reserva = reservaService.agregarReserva(reserva);

                session.setAttribute("precio", req.getParameter("txtPrecio"));

                if(reserva.getIdReserva() == -1)
                {
                    session.setAttribute("registromensaje","No hay habitaciones disponibles.");
                    reserva.setCodigo("-");
                }
                else
                {
                    session.setAttribute("registromensaje","Reserva Registrada");

                }

                session.setAttribute("SS_RESERVA",reserva);
                session.setAttribute("selecgob","");
                session.setAttribute("selecgcl","");
                session.setAttribute("selecpre","");
                session.setAttribute("selecsui","");


                if(intTipoHab==1)
                    session.setAttribute("selecgob","selected");
                else if(intTipoHab==2)
                    session.setAttribute("selecgcl","selected");
                else if(intTipoHab==3)
                    session.setAttribute("selecpre","selected");
                else if(intTipoHab==4)
                    session.setAttribute("selecsui","selected");

                req.getRequestDispatcher("registrar.jsp").forward(req, resp);
            }
    }

     /*
      Se encarga de obtener las reservas sin importar el estado (total de reservas).
      Si el usuario logueado es un administrador se muestra todas las reservas registradas de los clientes.
      Si el usuario logueado es un cliente se muestra sólo las reservas de este cliente.
    */
     public void listarReservasTodos(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //ReservaService reservaService = new ReservaServiceImpl();

        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String strFechaInicio = req.getParameter("txtDesde").toString();
        String strFechaFin = req.getParameter("txtHasta").toString();

        int intIdCliente = 0;

         if(session.getAttribute("SS_CLIENTE")!=null)
            intIdCliente =  ((Cliente)session.getAttribute("SS_CLIENTE")).getIdCliente();

            Reserva reserva = new Reserva();
            reserva.setIdTipoHab(intTipoHab);
            reserva.setFecInicio(strFechaInicio);
            reserva.setFecFin(strFechaFin);



            List<Reserva> ListReserva = reservaService.listarReservasTodos(strFechaInicio, strFechaFin, intTipoHab, intIdCliente);

            session.setAttribute("SS_RESERVA",reserva);
            session.setAttribute("selecgob","");
            session.setAttribute("selecgcl","");
            session.setAttribute("selecpre","");
            session.setAttribute("selecsui","");

            if(intTipoHab==1)
                session.setAttribute("selecgob","selected");
            else if(intTipoHab==2)
                session.setAttribute("selecgcl","selected");
            else if(intTipoHab==3)
                session.setAttribute("selecpre","selected");
            else if(intTipoHab==4)
                session.setAttribute("selecsui","selected");

            req.setAttribute("ListReserva", ListReserva);

        req.getRequestDispatcher("buscar.jsp").forward(req, resp);
    }

 

   /*
      Se encarga de obtener las reservas sólo las que se encuentran en estado 'Registrada'.
      (se usa cuando se va a realizar el check int de las reservas.

    */
    public void listarReservasCheckIn(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //ReservaService reservaService = new ReservaServiceImpl();

        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String strFechaInicio = req.getParameter("txtDesde").toString();
        String strFechaFin = req.getParameter("txtHasta").toString();

        int intIdCliente = 0;
         if(session.getAttribute("SS_CLIENTE")!=null)
            intIdCliente =  ((Cliente)session.getAttribute("SS_CLIENTE")).getIdCliente();

        Reserva reserva = new Reserva();
        reserva.setIdTipoHab(intTipoHab);
        reserva.setFecInicio(strFechaInicio);
        reserva.setFecFin(strFechaFin);


        List<Reserva> ListReserva = reservaService.listarReservasCheckIn(strFechaInicio, strFechaFin, intTipoHab, intIdCliente);

        session.setAttribute("SS_RESERVA",reserva);
        session.setAttribute("selecgob","");
        session.setAttribute("selecgcl","");
        session.setAttribute("selecpre","");
        session.setAttribute("selecsui","");

        if(intTipoHab==1)
            session.setAttribute("selecgob","selected");
        else if(intTipoHab==2)
            session.setAttribute("selecgcl","selected");
        else if(intTipoHab==3)
            session.setAttribute("selecpre","selected");
        else if(intTipoHab==4)
            session.setAttribute("selecsui","selected");

        req.setAttribute("ListReserva", ListReserva);
        req.getRequestDispatcher("checkin.jsp").forward(req, resp);
    }

/*
      Se encarga de obtener las reservas sólo las que se encuentran en estado 'Asignada'.
      (se usa cuando se va a realizar el check out de las reservas.

    */
    public void listarReservasCheckOut(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String strNombreCliente = req.getParameter("txtNombreCliente").toString().toUpperCase();
        String strApellidoCliente = req.getParameter("txtApellidoCliente").toString().toUpperCase();
        int intTipoHab = Integer.parseInt(req.getParameter("tipo_habitacion").toString());
        String habitacion = req.getParameter("txtHabitacion").toString();

        int intIdCliente = 0;
        Reserva reserva = new Reserva();
        reserva.setIdTipoHab(intTipoHab);
     

        List<Reserva> ListReserva = reservaService.listarReservasCheckOut(strNombreCliente, strApellidoCliente, habitacion, intTipoHab, intIdCliente);
        session.setAttribute("SS_RESERVA",reserva);
        session.setAttribute("nombre",strNombreCliente);
        session.setAttribute("apellido",strApellidoCliente);
        session.setAttribute("habitacion",habitacion);
        session.setAttribute("selecgob","");
        session.setAttribute("selecgcl","");
        session.setAttribute("selecpre","");
        session.setAttribute("selecsui","");

        if(intTipoHab==1)
            session.setAttribute("selecgob","selected");
        else if(intTipoHab==2)
            session.setAttribute("selecgcl","selected");
        else if(intTipoHab==3)
            session.setAttribute("selecpre","selected");
        else if(intTipoHab==4)
            session.setAttribute("selecsui","selected");

        req.setAttribute("ListReserva", ListReserva);
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);

        
    }
}
