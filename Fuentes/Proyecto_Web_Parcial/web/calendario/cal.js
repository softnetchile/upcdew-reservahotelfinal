var targetField = null;
var arr_months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
		"Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"];
var week_days = ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"];
var calHead = "<html>\n"+
		"<head>\n"+
		"	<title>Calendario</title>\n"+
		"</head>\n"+
    "<link href=\"css/calendar.css\" rel=\"stylesheet\">\n"+
		"<body bgcolor=\"White\" topmargin=\"2\" leftmargin=\"2\">\n"+
		"<table class=\"clsOTable\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n"+
		"<tr><td bgcolor=\"#4682B4\">\n"+
		"<table cellspacing=\"1\" cellpadding=\"3\" border=\"0\" width=\"100%\">\n";


function show_calendar(str_target, str_datetime) {
	var n_weekstart = 1;

  targetField = str_target
	var dt_datetime = (str_datetime == null || str_datetime =="" ?  new Date() : str2dt(str_datetime));
	var dt_prev_month = new Date(dt_datetime);
	dt_prev_month.setMonth(dt_datetime.getMonth()-1);
	var dt_next_month = new Date(dt_datetime);
	dt_next_month.setMonth(dt_datetime.getMonth()+1);
	var dt_firstday = new Date(dt_datetime);
	dt_firstday.setDate(1);
	dt_firstday.setDate(1-(7+dt_firstday.getDay()-n_weekstart)%7);
	var dt_lastday = new Date(dt_next_month);
	dt_lastday.setDate(0);
	
  
	var str_buffer = new String (calHead+
		"<tr>\n	<td bgcolor=\"#4682B4\"><a href=\"javascript:parent.opener.show_calendar('"+
		str_target+"', '"+ dt2dtstr(dt_prev_month)+"');\">"+
		"<img src=\"images/prev.gif\" width=\"16\" height=\"16\" border=\"0\""+
		" alt=\"mes anterior\"></a></td>\n"+
		"	<td bgcolor=\"#4682B4\" colspan=\"4\" nowrap>"+
		"<font color=\"white\" face=\"tahoma, verdana\" size=\"2\">"
		+arr_months[dt_datetime.getMonth()]+" "+dt_datetime.getFullYear()+"</font></td>\n"+
    "<td bgcolor=\"#4682B4\" align=\"right\">"+
    "<A STYLE=\"color:white;font-size:9pt;font-family:tahoma,Verdana\" HREF=\"javascript:parent.opener.escoge('"+dt2dtstr(new Date())+"');window.close();\">Hoy</A></td>"+   
		"<td bgcolor=\"#4682B4\" align=\"right\"><a href=\"javascript:parent.opener.show_calendar('"
		+str_target+"', '"+dt2dtstr(dt_next_month)+"');\">"+
		"<img src=\"images/next.gif\" width=\"16\" height=\"16\" border=\"0\""+
		" alt=\"mes siguiente\"></a></td>\n</tr>\n"
	);

	var dt_current_day = new Date(dt_firstday);
	// Imprime los titulos de los dias de la semana
	str_buffer += "<tr>\n";
	for (var n=0; n<7; n++)
		str_buffer += "	<td class=\"diasemana\">"+week_days[(n_weekstart+n)%7]+"</td>\n";
	// imprime la tabla del calendario
	str_buffer += "</tr>\n";
	while (dt_current_day.getMonth() == dt_datetime.getMonth() ||
		dt_current_day.getMonth() == dt_firstday.getMonth()) {
		// inicio de una fila
		str_buffer += "<tr>\n";
		for (var n_current_wday=0; n_current_wday<7; n_current_wday++) {
      var estilo = null;
			if (dt_current_day.getDate() == dt_datetime.getDate() &&
					dt_current_day.getMonth() == dt_datetime.getMonth()){
					// Dia actual
					estilo = "\"da\"";
			} else if (dt_current_day.getDay() == 0 || dt_current_day.getDay() == 6){
					// fines de semana
        if (dt_current_day.getMonth() == dt_datetime.getMonth())
          estilo = "\"dl\"";
        else
          estilo = "\"dlmd\"";
			} else {
					// dias laborales del mes actual
        if (dt_current_day.getMonth() == dt_datetime.getMonth())
			    estilo = "\"dn\"";
        else 
          // dias laborales del mes anterior y posterior
          estilo = "\"dmd\"";
      }
      str_buffer += "<td class="+estilo+">";
      str_buffer += "<a href=\"javascript:parent.opener.escoge('"+dt2dtstr(dt_current_day)+"');window.close();\">";
      str_buffer += "<font class="+estilo+">"+dt_current_day.getDate()+"</font></a></td>\n";
			dt_current_day.setDate(dt_current_day.getDate()+1);
		}
		// Cierre de la fila
		str_buffer += "</tr>\n";
	}
	// Pie de pagina del calendario
	str_buffer +=
		"<tr><td colspan=\"7\" bgcolor=\"#87CEFA\" align=\"center\"><form name=\"cal\" onsubmit=\"return false\">"+
		"<font color=\"White\" face=\"tahoma, verdana\" size=\"2\">"+
    "<select name=\"mes\" size=\"1\" onchange=\"parent.opener.saltames('"+dt2dtstr(dt_datetime)+"',this.options[this.selectedIndex].value);\">";
  for (i=0;i<12;i++){
    str_buffer += "<option value=\"" + (i+1) + "\" " + selectedmonth((i+1), dt_datetime) +">" + arr_months[i];
  }
  str_buffer += "</select>"+
		"<input type=\"text\" name=\"anho\" size=\"4\" maxlength=\"4\" value=\""+dt_datetime.getFullYear()+"\" onmouseout=\"parent.opener.saltaanho('"+dt2dtstr(dt_datetime)+"',this.value);\" onchange=\"parent.opener.saltaanho('"+dt2dtstr(dt_datetime)+"',this.value);\">"+
    "</font>"+
    "</form></td></tr>" +
		"</table>\n" +
		"</td>\n</tr>\n</table>\n" +
		"</body>\n" +
		"</html>\n";
	var vWinCal = window.open("", "Calendar", 
		"width=230,height=265,status=no,resizable=yes,top=200,left=200");
	vWinCal.opener = self;
	var calc_doc = vWinCal.document;
	calc_doc.write (str_buffer);
	calc_doc.close();
}

function str2dt (str_datetime) {
	var re_date = /^(\d+)\/(\d+)\/(\d+)$/;
	if (!re_date.exec(str_datetime))
		return alert("Formato de fecha-hora invalido: "+ str_datetime);
	return (new Date (RegExp.$3, RegExp.$2-1, RegExp.$1));
}
function dt2dtstr (dt_datetime) {
	return (new String (
		//makeTwoDigit(dt_datetime.getDate())+"/"+makeTwoDigit((dt_datetime.getMonth()+1))+"/"+dt_datetime.getFullYear()));
		makeTwoDigit((dt_datetime.getMonth()+1))+"/"+makeTwoDigit(dt_datetime.getDate())+"/"+dt_datetime.getFullYear()));
}
function selectedmonth(val, dt_current_day){
  return (val == dt_current_day.getMonth()+1?"SELECTED":"");
}
function saltames(str_datetime, nuevomes){
  dt_new_month = str2dt(str_datetime);
  dt_new_month.setMonth(nuevomes-1);
  show_calendar(targetField, dt2dtstr(dt_new_month));
}
function saltaanho(str_datetime, nuevoanho){
  dt_new_month = str2dt(str_datetime);
  dt_new_month.setYear(nuevoanho);
  show_calendar(targetField, dt2dtstr(dt_new_month));
}
function escoge(str_datetime){
  eval(targetField+".value='"+str_datetime+"'");
  eval(targetField+".focus();");


}
function makeTwoDigit(inValue) {
  var numVal = parseInt(inValue, 10); 
  return(numVal<10?"0" + numVal:numVal)
}