<%-- 
    Document   : login
    Created on : 18/02/2010, 09:06:55 PM
    Author     : Miguel Luis
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <title>.:Sistema de Reserva de Hotel - Inicie Sesión:.</title>

  <LINK href="css/styles.css" rel="stylesheet" type="text/css" >
  <SCRIPT>
  	function doFocus()
  	{


            document.getElementById('txt_Usuario').focus();
  	}
        function Logear()
        {
            //window.location="index.html";
            document.getElementById('frmLogin').submit();
        }
  </SCRIPT>
</head>
<body onload="doFocus()">

     <form id="frmLogin" action="AutenticacionServlet" method="post">
<TABLE cellSpacing=0 cellPadding=0 width=760 border=0>
	<TR>
		<TD><IMG src="images/logo.jpg" border=0></TD>
	</TR>
</TABLE>
<br>
<table cellpadding="0" cellspacing="20" border="0" height=400>
	<tr>
		<td width=250 style="border-right: 1px solid gainsboro" align="right">
			<h3>Inicie Sesi&oacute;n&nbsp;&nbsp;</h3>
		</td>
		<td height=150>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td>

						<table cellpadding="0" cellspacing="0" border="0" width=100%>
							<tr>
								<td align="right"><img src="images/frame_l.gif"></td>
								<td height="20" width="250" style="color:white"
									background="images/frame_m.gif" nowrap="nowrap">Login<td>
								<td align="right"><img src="images/frame_r.gif"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td style="BORDER: 1px solid #E2AF59">
						<table cellpadding="5" cellspacing="0" border=0 bgcolor="#F2F2F2" width="100%">
                                                    <tr>
                                                        <td align="right">Tipo</td>
                                                        <td>
                                                         <SELECT id="tipo_habitacion" name="tipo_usuario">
                                                             <OPTION value="CLIENTE" selected>Cliente</OPTION>
                                                             <OPTION value="EMPLEADO"

                                                                     <% if(session.getAttribute("selecempleado") != null){ %>
                                                                            <%=session.getAttribute("selecempleado")%>
                                                                            <% } %>

                                                                     >Empleado</OPTION>
                                                        </SELECT>
                                                        </td>
                                                    </tr>
							<tr>
								<td align="right">Usuario</td>
								<td><input class="cssInputText" type="text" id="txt_Usuario" name="txt_Usuario"

                                                                            <% if(session.getAttribute("usuario") != null){ %>
                                                                            value="<%=session.getAttribute("usuario")%>"
                                                                            <% } %>





                                                                           /></td>
							</tr>
							<tr>
								<td align="right">Contrase&ntilde;a</td>
								<td><input class="cssInputText" type="password" name="txt_Password"
                                                                           <% if(session.getAttribute("password") != null){ %>
                                                                            value="<%=session.getAttribute("password")%>"
                                                                            <% } %>

                                                                           /></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input class="cssInputButton" onclick="Logear();" name="btn_Ingresar" value="Ingresar"/></td>
							</tr>

                                                        <tr>
                                                                <td>
                                                                        <% if(session.getAttribute("errorMessage") != null){ %>
                                                                        <span style="color:red"><%=session.getAttribute("errorMessage")%></span>
                                                                        <% } %>
                                                                       
                                                                </td>
                                                        </tr>
						</table>

					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
<br>
