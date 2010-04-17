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
    <INPUT TYPE="hidden" NAME="accion" value="checkout">
    <table width="100%">
        <tr>
            <td align="left">
                <h4>
                    Check Out - Estadia
                </h4>
            </td>
        </tr>
    </table>
    <hr />

    <TABLE class=fieldset cellSpacing=3 cellPadding=0 border=0>
        <TBODY>

            <TR align=left>
                <TD width="150">Apellidos Cliente</TD>
                <TD width="10">:</TD>
                <TD width="150">
                    <INPUT class="cssInputText"

                           <% if(session.getAttribute("apellido") != null){%>
                                    value="<%=session.getAttribute("apellido")%>"
                                <% }
                                else{%>
                                     value=""
                                <% }%>

                     name="txtApellidoCliente" id="txtCliente" style=" width:100px; height: 15px " maxlength="10" >

                </TD>
                <TD width="150">Nombres Cliente</TD>
                <TD width="10">:</TD>
                <TD width="150">
                    <INPUT class="cssInputText"
                           <% if(session.getAttribute("nombre") != null){%>
                                    value="<%=session.getAttribute("nombre")%>"
                                <% }
                                else{%>
                                     value=""
                                <% }%>



                            name="txtNombreCliente" id="txtCliente" style=" width:100px; height: 15px " maxlength="10" >

                </TD>

            </TR>
    </TABLE>

    <table>
            
            <TR align=left>
                <TD width="150">Habitacion</TD>
                <TD width="10">:</TD>
                <TD width="150">
                    <INPUT class="cssInputText"

                           <% if(session.getAttribute("habitacion") != null){%>
                                    value="<%=session.getAttribute("habitacion")%>"
                                <% }
                                else{%>
                                     value=""
                                <% }%>

                     name="txtHabitacion" id="txtCliente" style=" width:100px; height: 15px " maxlength="10" >

                </TD>

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


</TABLE>

</TBODY>
    </FORM>

<br />

<form id="frmCheckOut" action="CheckOutServlet" method="post">
       <INPUT TYPE="hidden" NAME="accion" value="Registrarcheckout">
        <TR align=center>
            <td width="160" colspan="2" align="right"><input name="btnCheckout" type=submit class="cssInputButton" value="Check Out"></td>
        </TR>

		<table width="700" class="cssGrid" cellpadding="3" cellspacing="1" border="0">
			<tr>
				<th colspan="9">
					Listado de Estadia
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
				<th width="20%">
					Tipo de Habitaci&oacute;n
				</th>
                                <th width="15%">
					Nro. Hab.
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
                        <input type="radio"  name="idreserva"  value=${reserva.idReserva} onclick="OpenReserva('${reserva.descEstado}','${reserva.idReserva}','${reserva.codigo}','${reserva.idTipoHab}','${reserva.descTipoHab}','${reserva.fecInicio}','${reserva.fecFin}');" >
                        </td>


                        <td align="center"> ${reserva.codigo} </td>
                        <td align="left" > ${reserva.nomCliente} </td>
                        <td align="center" > ${reserva.fecInicio} </td>
                          <td align="center"> ${reserva.fecFin} </td>
                          <td> ${reserva.descTipoHab} </td>
                          <td> ${reserva.habitacion.codigo} </td>
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