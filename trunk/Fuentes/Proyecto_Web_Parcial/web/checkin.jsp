<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
    Document   : checkin.jsp
    Created on : 07/04/2010, 09:35:15 PM
    Author     : Ricardo Gálvez
--%>

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


    <form id="frmBuscarReserva" action="ReservaServlet" method="post">
    <INPUT TYPE="hidden" NAME="accion" value="checkin">
    <table width="100%">
        <tr>
            <td align="left">
                <h4>
                    Check In - Busqueda de Reserva
                </h4>
            </td>
        </tr>
    </table>
    <hr />

    <TABLE class=fieldset cellSpacing=3 cellPadding=0 border=0>
        <TBODY>

            <TR align=left>
                <TD width="150">Fecha inicial</TD>
                <TD width="10">:</TD>
                <TD width="300">
                    <INPUT class="cssInputText"

                                <% if( session.getAttribute("SS_RESERVA") != null && ((Reserva)session.getAttribute("SS_RESERVA")).getFecInicio()!= null ){
                                    Reserva reserva  = (Reserva)session.getAttribute("SS_RESERVA");%>
                                    value="<%=reserva.getFecInicio()%>"
                                <% }
                                else{%>
                                     value="01/04/2010"
                                <% }%>



                           name="txtDesde" id="txtDesde" style=" width:100px; height: 15px " maxlength="10" onKeyUp = "this.value=formateafecha(this.value);">
                    <a href="javascript:calendario('txtDesde',%20'dd/mm/y')">
                        <img src="calendario/cal.gif" width="16" height="16" border="0" alt="Fecha en formato día/mes/año)">
                    </a>
                </TD>
            </TR>
            <TR align=left>
                <TD width="150">Fecha final</TD>
                <TD width="10">:</TD>
                <TD width="300">
                    <INPUT class="cssInputText"

                            <% if( session.getAttribute("SS_RESERVA") != null && ((Reserva)session.getAttribute("SS_RESERVA")).getFecInicio()!= null ){
                                    Reserva reserva  = (Reserva)session.getAttribute("SS_RESERVA");%>
                                    value="<%=reserva.getFecFin()%>"
                                <% }
                                else{%>
                                     value="01/04/2010"
                                <% }%>


                           name="txtHasta" id="txtHasta" style=" width:100px; height: 15px " maxlength="10" onKeyUp = "this.value=formateafecha(this.value);">
                    <a href="javascript:calendario('txtHasta',%20'dd/mm/y')">
                        <img src="calendario/cal.gif" width="16" height="16" border="0" alt="Fecha en formato día/mes/año)">
                    </a>
                </TD>

            </TR>
            <TR align=left>
                <TD width="150">Tipo Habitacion</TD>
                <TD width="10">:</TD>
                <TD width="300">
                   <SELECT id="tipo_habitacion" name="tipo_habitacion" >
                        <OPTION value="0">[Todos]</OPTION>
                        <OPTION value="1"
                                <% if(session.getAttribute("selecgob") != null){ %>
                                <%=session.getAttribute("selecgob")%>
                                <% } %>

                                >Gobernador</OPTION>
                        <OPTION value="2"
                                <% if(session.getAttribute("selecgcl") != null){ %>
                                <%=session.getAttribute("selecgcl")%>
                                <% } %>

                                >Grand Clase</OPTION>
                        <OPTION value="3"
                                 <% if(session.getAttribute("selecpre") != null){ %>
                                <%=session.getAttribute("selecpre")%>
                                <% } %>
                                >Presidencial</OPTION>
                        <OPTION value="4"
                                 <% if(session.getAttribute("selecsui") != null){ %>
                                <%=session.getAttribute("selecsui")%>
                                <% } %>

                                >Suite</OPTION>


                    </SELECT>
                </TD>
            </TR>


        <br> 

        <TR align=center>
            <td width="160" colspan="2">

                <INPUT name="submit" type=submit class="cssInputButton" value="Buscar">
            </td>

        </TR>


    </TBODY>
</TABLE>

<br />


		
    </form>

    <form id="frmBuscarReserva2" action="CheckInServlet" method="post">
       <INPUT TYPE="hidden" NAME="accion" value="Registrarcheckin">
       <TR align=center>
            <td width="160" colspan="2" align="right"><input name="submit2" type=submit class="cssInputButton" value="Check In"></td>

        </TR>
       <table width="700" class="cssGrid" cellpadding="3" cellspacing="1" border="0">
			<tr>
				<th colspan="8">
					Reservas
				</th>
			</tr>
			<tr>
				<th width="5%" style="text-align: center"></th>
				<th width="15%">
					C&oacute;digo
				</th>
                                <th width="20%">
					Cliente
				</th>
				<th width="18%">
					Fecha Inicial
				</th>
				<th width="18%">
					Fecha Final
				</th>
				<th width="30%">
					Tipo de Habitaci&oacute;n
				</th>
				<th width="15%">
					Subtotal
				</th>
                                <th width="15%">
					Estado
				</th>
                            </tr>

             <c:forEach items="${ListReserva}" var="reserva">
                <tr>
                        <td style="text-align: center">
                        <input type="radio"  name="idreserva"  value=${reserva.idReserva}  >
                        </td>


                        <td align="center"> ${reserva.codigo} </td>
                        <td align="left" > ${reserva.nomCliente} </td>
                        <td align="center" > ${reserva.fecInicio} </td>
                          <td align="center"> ${reserva.fecFin} </td>
                          <td> ${reserva.descTipoHab} </td>
                          <td align="right"> ${reserva.precio} </td>
                          <td align="center"> ${reserva.descEstado}</td>
                </tr>


            </c:forEach>

	</table>
       
    </form>



<SCRIPT>
    // This function gets called when the end-user clicks on some date.
    function selected(cal, date) {
        cal.sel.value = date; // just update the date in the input field.
        cal.callCloseHandler();
    }

    // And this gets called when the end-user clicks on the _selected_ date,
    // or clicks on the "Close" button.  It just hides the calendar without
    // destroying it.
    function closeHandler(cal) {
        cal.hide();                        // hide the calendar
    }

    function calendario(id, formato) {
        var el = document.getElementById(id);
        if (calendar != null) {
            // we already have some calendar created
            calendar.hide();                 // so we hide it first.
        } else {
            // first-time call, create the calendar.
            var cal = new Calendar(true, null, selected, closeHandler);
            // uncomment the following line to hide the week numbers
            // cal.weekNumbers = false;
            calendar = cal;                  // remember it in the global var
            cal.setRange(1900, 2070);        // min/max year allowed.
            cal.create();
        }
        calendar.setDateFormat(formato);    // set the specified date format
        calendar.parseDate(el.value);      // try to parse the text in field
        calendar.sel = el;                 // inform it what input field we use
        calendar.showAtElement(el);        // show the calendar below it
    }
</SCRIPT>


</HTML>