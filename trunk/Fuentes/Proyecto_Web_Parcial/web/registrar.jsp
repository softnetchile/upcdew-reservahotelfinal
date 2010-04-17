<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.upc.dew.reservahoteles.model.TipoHabitacion" %>
<%@ page import="pe.edu.upc.dew.reservahoteles.model.Reserva" %>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- 
    Document   : registrar
    Created on : 18/02/2010, 09:37:33 PM
    Author     : Miguel Luis
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>

    <HEAD>
        <LINK href="css/styles.css" rel="stylesheet" type="text/css" >
        <LINK href="calendario/calendar-blue.css" type="text/css" rel="stylesheet">
        <script src="calendario/calendar.js"></script>
        <script src="calendario/calendar-es.js"></script>
        <script src="js/fecha.js"></script>
        <script language="javascript" type="text/javascript">


        function SetearPrecio()
        {
            var vTipo = document.getElementById("tipo_habitacion").value;
            var strStDate = document.getElementById('txtDesde').value;
            var strEndDate = document.getElementById('txtHasta').value;

            var dtStDate = new Date(strStDate.substring(3,5)+"/"+strStDate.substring(0,2)+"/"+strStDate.substring(6,10));
            var dtEndDate = new Date(strEndDate.substring(3,5)+"/"+strEndDate.substring(0,2)+"/"+strEndDate.substring(6,10));

            diff  = new Date();

            diff.setTime(Math.abs(dtStDate.getTime() - dtEndDate.getTime()));
            timediff = diff.getTime();
            vCantDias = Math.floor(timediff / (1000 * 60 * 60 * 24));
            timediff -= vCantDias * (1000 * 60 * 60 * 24);

            vCantDias = vCantDias+1;
            
            //alert(vCantDias);

            if(vTipo == "1")
            {
                document.getElementById("txtPrecio").value = 800*vCantDias;
                document.getElementById("txtTarifa").value = 800;
            }
            else if(vTipo == "2")
            {
                document.getElementById("txtPrecio").value = 900*vCantDias;
                document.getElementById("txtTarifa").value = 900;
            }
            else if(vTipo == "3")
            {
                 document.getElementById("txtPrecio").value = 1100*vCantDias;
                 document.getElementById("txtTarifa").value = 1100;
            }
            else if(vTipo == "4")
            {
                 document.getElementById("txtPrecio").value = 1300*vCantDias;
                 document.getElementById("txtTarifa").value = 1300;
            }
        }
  	function RegistrarReserva()
  	{
                //var myForm = document.getElementById('frmRegistrarReserva');
                //alert(myForm);
                //myForm.submit();
         
  	}
        function SetFechaFin()
  	{
                calendario('txtHasta','dd/mm/y');
                SetearPrecio();
  	}
        function SetFechaInicio()
  	{
                calendario('txtDesde','dd/mm/y');
                SetearPrecio();
  	}

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
            SetearPrecio();
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
        </script>
    </HEAD>

<body>
    <form name="frmRegistrarReserva"  id="frmRegistrarReserva" action="ReservaServlet" method="post">
        <INPUT TYPE="hidden" NAME="accion" value="registrar">
    <table width="100%">
        <tr>
            <td align="left">
                <h4>
                    Registrar Reserva
                </h4>
            </td>
        </tr>
    </table>
    <hr />

    <TABLE class=fieldset cellSpacing=3 cellPadding=0 border=0>
        <TBODY>
            <TR align=left>
                <TD width="150">Cod. Reserva</TD>
                <TD width="10">:</TD>
                <TD width="300" class="Aviso">
                <% if( session.getAttribute("SS_RESERVA") != null && ((Reserva)session.getAttribute("SS_RESERVA")).getFecInicio()!= null ){
                    Reserva reserva  = (Reserva)session.getAttribute("SS_RESERVA");%>
                     <% if(reserva.getCodigo() != null){ %>
                        <%=reserva.getCodigo()%>
                    <% }  else{%>
                       -
                    <% }%>
                <% }
                else{%>
                   -
                <% }%>

                </TD>
            </TR>

           
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
                    <a href="javascript:SetFechaInicio();">
                        <img src="calendario/cal.gif" width="16" height="16" border="0" alt="Fecha en formato día/mes/año)">
                    </a>
                </TD>
            </TR>
            <TR align=left>
                <TD width="150">Fecha final</TD>
                <TD width="10">:</TD>
                <TD width="300">
                    <INPUT class="cssInputText"

                            <% if(session.getAttribute("SS_RESERVA") != null){
                                    Reserva reserva  = (Reserva)session.getAttribute("SS_RESERVA");%>
                                    value="<%=reserva.getFecFin()%>"
                                <% }
                                else{%>
                                     value="01/04/2010"
                                <% }%>


                           name="txtHasta" id="txtHasta" style=" width:100px; height: 15px " maxlength="10" onKeyUp = "this.value=formateafecha(this.value);">
                    <a href="javascript:SetFechaFin();">
                        <img src="calendario/cal.gif" width="16" height="16" border="0" alt="Fecha en formato día/mes/año)">
                    </a>
                </TD>

            </TR>
            <TR align=left>
                <TD width="150">Tipo Habitacion</TD>
                <TD width="10">:</TD>
                <TD width="300">
                    <SELECT id="tipo_habitacion" name="tipo_habitacion" onchange="SetearPrecio();" >
                       
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

            <TR align=left>
                <TD width="150">Tarifa X Día</TD>
                <TD width="10">:</TD>
                <TD width="300">$
                    <INPUT class="cssInputText"
                            value="800"
                           name="txtTarifa" id="txtTarifa" readonly="true" style=" width:100px; height: 15px; border-width: 0px " >
                </TD>
            </TR>

            <TR align=left>
                <TD width="150">Precio Total</TD>
                <TD width="10">:</TD>
                <TD width="300">$
                    <INPUT class="cssInputText"

                            <% if(session.getAttribute("precio") != null){ %>
                                value="<%=session.getAttribute("precio")%>"
                                <% }
                                else{%>
                                     value="800"
                                <% }%>






                           name="txtPrecio" id="txtPrecio" readonly="true" style=" width:100px; height: 15px; border-width: 0px " >
                </TD>
            </TR>


            


        <br>

        
        <TR align=center>
            <td width="160" colspan="2">

                <INPUT name="submit"  type="submit" class="cssInputButton"  value="Grabar" onclick="RegistrarReserva();">

            </td>

            <td>
                <INPUT name="reset" type=reset class="cssInputButton" value="Cancelar">
            </td>
        </TR>
      

        <table>
            <tr>
                        <td>
                                <% if(session.getAttribute("registromensaje") != null){ %>
                                <span style="color:red"><%=session.getAttribute("registromensaje")%></span>
                                <% } %>

                        </td>
                </tr>
        </table>


    </TBODY>
</TABLE>
    </form>


</body>
</html>