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
import pe.edu.upc.dew.reservahoteles.model.Habitacion;
import pe.edu.upc.dew.reservahoteles.model.Reserva;
/**
 *
 * @author Miguel Luis
 */

public class ReservaDaoImpl implements ReservaDao {

    /*
     Método que se encarga de obtener todas las reservas; todos los estados.
     */
     public List<Reserva> listarReservasTodos(String fechaInicio, String fechaFin, int intIdTipoHab, int intIdCliente)
    {
        List<Reserva> ListReserva = new ArrayList<Reserva>();

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();
            fechaInicio = fechaInicio.substring(6,10)+ "-"+fechaInicio.substring(3,5)+"-"+fechaInicio.substring(0,2);
            fechaFin = fechaFin.substring(6, 10) + "-"+fechaFin.substring(3,5)+"-"+fechaFin.substring(0,2);
            String query = "SELECT  R.CODG_RES, R.SECC_RES,R.SECC_CLI,CL.NOMB_CLI,CL.APEL_CLI,R.SECC_TIPH,T.NOMB_TIPH,R.PRECIO,R.FLAG_ESTADO,R.FECH_INI,R.FECH_FIN";
            query = query+" FROM    RESERVA R, CLIENTE CL, TIPO_HABITACION T";
            query = query+" WHERE   R.SECC_CLI = CL.SECC_CLI AND R.SECC_TIPH = T.SECC_TIPH";
            //query = query+" AND   R.FLAG_ESTADO = 1";
            query = query+" AND ( R.FECH_INI BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"'";
            query = query+" OR R.FECH_FIN BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' )";
            if(intIdTipoHab != 0)
                query = query+" AND R.SECC_TIPH = "+intIdTipoHab;
            if(intIdCliente != 0)
                query = query+" AND R.SECC_CLI = "+intIdCliente;

            rs = st.executeQuery(query);
            Reserva reserva = null;
            while (rs.next())
            {
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("SECC_RES"));
                reserva.setNomCliente(rs.getString("NOMB_CLI")+" "+rs.getString("APEL_CLI"));
                reserva.setFecInicio(rs.getString("FECH_INI"));
                reserva.setIdTipoHab(rs.getInt("SECC_TIPH"));
                reserva.setCodigo(rs.getString("CODG_RES"));
                reserva.setFecFin(rs.getString("FECH_FIN"));
                reserva.setDescTipoHab(rs.getString("NOMB_TIPH"));
                reserva.setPrecio(rs.getDouble("PRECIO"));
                // 1=Registrada, 2=Asignada, 3=Pagada, 4=Anulada
                int intIdEstado = rs.getInt("FLAG_ESTADO");


                if(intIdEstado==1)
                    reserva.setDescEstado("Registrada");
                else if(intIdEstado==2)
                    reserva.setDescEstado("Asignada");
                else if(intIdEstado==3)
                    reserva.setDescEstado("Pagada");
                else if(intIdEstado==4)
                    reserva.setDescEstado("Anulada");

                ListReserva.add(reserva);

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return ListReserva;

    }

     /*
      Método que se encarga de obtener sólo las reservas en estado 'Registrada' para realizar el checkIn.
      */
    public List<Reserva> listarReservasCheckIn(String fechaInicio, String fechaFin, int intIdTipoHab, int intIdCliente)
    {
        List<Reserva> ListReserva = new ArrayList<Reserva>();

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();
            fechaInicio = fechaInicio.substring(6,10)+ "-"+fechaInicio.substring(3,5)+"-"+fechaInicio.substring(0,2);
            fechaFin = fechaFin.substring(6, 10) + "-"+fechaFin.substring(3,5)+"-"+fechaFin.substring(0,2);
            String query = "SELECT  R.CODG_RES, R.SECC_RES,R.SECC_CLI,CL.NOMB_CLI,CL.APEL_CLI,R.SECC_TIPH,T.NOMB_TIPH,R.PRECIO,R.FLAG_ESTADO,R.FECH_INI,R.FECH_FIN";
            query = query+" FROM    RESERVA R, CLIENTE CL, TIPO_HABITACION T";
            query = query+" WHERE   R.SECC_CLI = CL.SECC_CLI AND R.SECC_TIPH = T.SECC_TIPH";
            query = query+" AND   R.FLAG_ESTADO = 1";
            query = query+" AND ( R.FECH_INI BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"'";
            query = query+" OR R.FECH_FIN BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' )";
            if(intIdTipoHab != 0)
                query = query+" AND R.SECC_TIPH = "+intIdTipoHab;
            if(intIdCliente != 0)
                query = query+" AND R.SECC_CLI = "+intIdCliente;

            rs = st.executeQuery(query);
            Reserva reserva = null;
            while (rs.next())
            {
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("SECC_RES"));
                reserva.setNomCliente(rs.getString("NOMB_CLI")+" "+rs.getString("APEL_CLI"));
                reserva.setFecInicio(rs.getString("FECH_INI"));
                reserva.setIdTipoHab(rs.getInt("SECC_TIPH"));
                reserva.setCodigo(rs.getString("CODG_RES"));
                reserva.setFecFin(rs.getString("FECH_FIN"));
                reserva.setDescTipoHab(rs.getString("NOMB_TIPH"));
                reserva.setPrecio(rs.getDouble("PRECIO"));
                // 1=Registrada, 2=Asignada, 3=Pagada, 4=Anulada
                int intIdEstado = rs.getInt("FLAG_ESTADO");


                if(intIdEstado==1)
                    reserva.setDescEstado("Registrada");
                else if(intIdEstado==2)
                    reserva.setDescEstado("Asignada");
                else if(intIdEstado==3)
                    reserva.setDescEstado("Pagada");
                else if(intIdEstado==4)
                    reserva.setDescEstado("Anulada");

                ListReserva.add(reserva);

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return ListReserva;
        
    }

    /*
      Método que se encarga de obtener sólo las reservas en estado 'Asignada' para realizar el checkout.
      */
     public List<Reserva> listarReservasCheckOut(String nombre, String apellido, String habitacion, int intIdTipoHab, int intIdCliente)
    {
        List<Reserva> ListReserva = new ArrayList<Reserva>();

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();

            String query = "SELECT  R.CODG_RES, R.SECC_RES,R.SECC_CLI,CL.NOMB_CLI,CL.APEL_CLI,R.SECC_TIPH,T.NOMB_TIPH, H.SECC_HAB, H.CODG_HAB ,R.PRECIO,R.FLAG_ESTADO,R.FECH_INI,R.FECH_FIN";
            query = query+" FROM    RESERVA R, CLIENTE CL, TIPO_HABITACION T, HABITACION H";
            query = query+" WHERE   R.SECC_CLI = CL.SECC_CLI AND R.SECC_TIPH = T.SECC_TIPH AND H.SECC_HAB = R.SECC_HAB";
            query = query+" AND   R.FLAG_ESTADO = 2";
            if(intIdTipoHab != 0)
                query = query+" AND R.SECC_TIPH = "+intIdTipoHab;
            if(intIdCliente != 0)
                query = query+" AND R.SECC_CLI = "+intIdCliente;
             if(! nombre.equals(""))
                query = query+" AND UPPER(CL.NOMB_CLI) like '%"+nombre+"%'";
             if(! apellido.equals(""))
                query = query+" AND UPPER(CL.APEL_CLI) like '%"+apellido+"%'";
              if(! habitacion.equals(""))
                query = query+" AND UPPER(H.CODG_HAB) like '%"+habitacion+"%'";

            rs = st.executeQuery(query);
            Reserva reserva = null;
            while (rs.next())
            {
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("SECC_RES"));
                reserva.setNomCliente(rs.getString("NOMB_CLI")+" "+rs.getString("APEL_CLI"));
                reserva.setFecInicio(rs.getString("FECH_INI"));
                reserva.setIdTipoHab(rs.getInt("SECC_TIPH"));
                reserva.setCodigo(rs.getString("CODG_RES"));
                reserva.setFecFin(rs.getString("FECH_FIN"));
                reserva.setDescTipoHab(rs.getString("NOMB_TIPH"));
                reserva.setPrecio(rs.getDouble("PRECIO"));

                reserva.setHabitacion( new Habitacion(rs.getInt("SECC_HAB"), rs.getString("CODG_HAB")) );
                // 1=Registrada, 2=Asignada, 3=Pagada, 4=Anulada
                int intIdEstado = rs.getInt("FLAG_ESTADO");


                if(intIdEstado==1)
                    reserva.setDescEstado("Registrada");
                else if(intIdEstado==2)
                    reserva.setDescEstado("Asignada");
                else if(intIdEstado==3)
                    reserva.setDescEstado("Pagada");
                else if(intIdEstado==4)
                    reserva.setDescEstado("Anulada");

                ListReserva.add(reserva);

            }


        } catch (Exception e) {
            //throw e;
            e.printStackTrace();

        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return ListReserva;


    }

     /*
      Método que se encarga de registrar las reservas
      */
    public Reserva agregarReservas(Reserva reserva)
    {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();
            
            int intContador=0;
            int intContadorHab=0;
            int intContadorTotalHab=0;
            String codigo="";
            String fechaInicio = reserva.getFecInicio();
            fechaInicio = fechaInicio.substring(6,10)+ "-"+fechaInicio.substring(3,5)+"-"+fechaInicio.substring(0,2);
            String fechaFin = reserva.getFecFin();
            fechaFin = fechaFin.substring(6, 10) + "-"+fechaFin.substring(3,5)+"-"+fechaFin.substring(0,2);


            rs = st.executeQuery("select count(*) AS CONTADOR from reserva");
            if (rs.next()) intContador = rs.getInt("CONTADOR");
            rs = st.executeQuery("select count(*) AS CONTADOR from reserva where   ( FECH_INI BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' OR FECH_FIN BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' ) AND secc_tiph="+reserva.getIdTipoHab().toString());
            if (rs.next()) intContadorHab = rs.getInt("CONTADOR");
             rs = st.executeQuery("select count(*) AS CONTADOR from habitacion where secc_tiph="+reserva.getIdTipoHab().toString());
            if (rs.next()) intContadorTotalHab = rs.getInt("CONTADOR");
    
            //Validar Disponibilidad de Habitaciones
            if(intContadorTotalHab>intContadorHab)
            {

                rs = st.executeQuery("select count(*) AS CONTADOR from reserva where secc_tiph="+reserva.getIdTipoHab().toString());
                if (rs.next()) intContadorHab = rs.getInt("CONTADOR");

                intContador++;
                intContadorHab++;
                if(reserva.getIdTipoHab()==1) codigo="GOB-000"+intContadorHab;
                else if(reserva.getIdTipoHab()==2) codigo="GCL-000"+intContadorHab;
                else if(reserva.getIdTipoHab()==3) codigo="PRE-000"+intContadorHab;
                else if(reserva.getIdTipoHab()==4) codigo="SUI-000"+intContadorHab;

                //20/04/2010


                st.executeUpdate("insert into reserva values ("+intContador+","+reserva.getIdCliente()+","+reserva.getIdTipoHab()+",NULL,'"+codigo+"',"+reserva.getPrecio()+",'"+fechaInicio+"','"+fechaFin+"',"+reserva.getEstado()+")");
                reserva.setCodigo(codigo);
                reserva.setIdReserva(intContador);
            }
            else
            {
                reserva.setIdReserva(-1);
            }
             

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }
        return reserva;
    }

     /*
      Método que se encarga de realizar el checkin de las reservas.
      */
    public Reserva agregarCheckIn(int idReserva)
    {
        Reserva reserva = new Reserva();

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();

            //Asignar Habitacion Disponible
            int intHabitacion=0;
            String habitacion="";
            String query = " SELECT SECC_HAB, CODG_HAB FROM HABITACION";
            query  = query + " WHERE SECC_TIPH=(SELECT SECC_TIPH FROM RESERVA WHERE SECC_RES="+idReserva+") AND SECC_HAB NOT IN";
            query  = query + " (SELECT SECC_HAB FROM RESERVA WHERE SECC_HAB IS NOT NULL";
            query  = query + " AND FECH_INI BETWEEN (SELECT FECH_INI FROM RESERVA WHERE SECC_RES="+idReserva+") AND (SELECT FECH_FIN FROM RESERVA WHERE SECC_RES="+idReserva+")";
            query  = query + " AND FECH_FIN BETWEEN (SELECT FECH_INI FROM RESERVA WHERE SECC_RES="+idReserva+") AND (SELECT FECH_FIN FROM RESERVA WHERE SECC_RES="+idReserva+") )";
     
            rs = st.executeQuery(query);
            if (rs.next()) 
            {
                intHabitacion = rs.getInt("SECC_HAB");
                habitacion = rs.getString("CODG_HAB");
            }

            query  = "SELECT  R.CODG_RES, R.SECC_RES,R.SECC_CLI,CL.NOMB_CLI,CL.APEL_CLI,R.SECC_TIPH, " +
                     "T.NOMB_TIPH,R.PRECIO,R.FLAG_ESTADO,R.FECH_INI,R.FECH_FIN";
            query  = query + " FROM    RESERVA R, CLIENTE CL, TIPO_HABITACION T";
             query  = query + " WHERE   R.SECC_CLI = CL.SECC_CLI AND R.SECC_TIPH = T.SECC_TIPH AND R.SECC_RES = "+idReserva;

            rs = st.executeQuery(query);
             if (rs.next())
            {
                reserva.setCodigo(rs.getString("CODG_RES"));
                reserva.setFecInicio(rs.getString("FECH_INI"));
                reserva.setFecFin(rs.getString("FECH_FIN"));
                reserva.setPrecio(rs.getDouble("PRECIO"));
                reserva.setCliente(rs.getString("NOMB_CLI")+" "+rs.getString("APEL_CLI"));
                reserva.setTipoHabitacion(rs.getString("NOMB_TIPH"));
            }


            //Actualizar Datos
            st.executeUpdate("update reserva set flag_Estado=2, SECC_HAB="+intHabitacion+" where secc_res="+idReserva);
            reserva.setIdReserva(idReserva);

            reserva.setHabitacion(new Habitacion(intHabitacion,habitacion) );

            
            
            return reserva;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }

    }

     /*
      Método que se encarga de realizar el checkout de las reservas, que previamente se realizó checkIn.
      */
     public Reserva agregarCheckOut(int idReserva)
    {
        Reserva reserva = new Reserva();

        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            st = connection.createStatement();

            String query ="";

            query  = "SELECT  R.CODG_RES, R.SECC_RES,R.SECC_CLI,CL.NOMB_CLI,CL.APEL_CLI,R.SECC_TIPH, H.SECC_HAB, H.CODG_HAB," +
                     "T.NOMB_TIPH,R.PRECIO,R.FLAG_ESTADO,R.FECH_INI,R.FECH_FIN";
            query  = query + " FROM    RESERVA R, CLIENTE CL, TIPO_HABITACION T, HABITACION H";
            query  = query + " WHERE   H.SECC_HAB = R.SECC_HAB AND R.SECC_CLI = CL.SECC_CLI AND R.SECC_TIPH = T.SECC_TIPH AND R.SECC_RES = "+idReserva;

     
            rs = st.executeQuery(query);
             if (rs.next())
            {
                reserva.setCodigo(rs.getString("CODG_RES"));
                reserva.setFecInicio(rs.getString("FECH_INI"));
                reserva.setFecFin(rs.getString("FECH_FIN"));
                reserva.setPrecio(rs.getDouble("PRECIO"));
                reserva.setCliente(rs.getString("NOMB_CLI")+" "+rs.getString("APEL_CLI"));
                reserva.setTipoHabitacion(rs.getString("NOMB_TIPH"));
                reserva.setHabitacion( new Habitacion(rs.getInt("SECC_HAB"), rs.getString("CODG_HAB")) );
            }


            //Actualizar Datos
            st.executeUpdate("update reserva set flag_Estado=3 where secc_res="+idReserva);
            reserva.setIdReserva(idReserva);

            return reserva;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //Util.closeResultSet(rs);
            Util.closeStatement(st);
            Util.closeConnection(connection);
        }

    }

    
   
    
    
}