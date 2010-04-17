<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.upc.dew.reservahoteles.model.TipoHabitacion" %>
<%@ page import="pe.edu.upc.dew.reservahoteles.model.Reserva" %>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<HTML>

    <HEAD>
        <LINK href="css/styles.css" rel="stylesheet" type="text/css" >
        <LINK href="calendario/calendar-blue.css" type="text/css" rel="stylesheet">
        <script src="calendario/calendar.js"></script>
        <script src="calendario/calendar-es.js"></script>
        <script src="js/fecha.js"></script>



    </HEAD>


    <form id="frmBuscarReserva" >

    <table width="100%">
        <tr>
            <td align="left">
                <h4>
                    Confirmación de Check In
                </h4>
            </td>
        </tr>
    </table>
    <br />
    
    <body>
         <h3> Codigo        : ${varCodigo}</H3>
         <H3> Cliente       : ${varCliente}</H3>
         <H3> Tipo Habitacion  : ${varTipoHab}</H3>
         <H3> Habitacion    : ${varHabitacion}</H3>
         <H3> F. Inicio     : ${varFechaInicio}</H3>
         <H3> F. Fin        : ${varFechaFin}</H3>
         
         


    </body>
    </form>




</HTML>