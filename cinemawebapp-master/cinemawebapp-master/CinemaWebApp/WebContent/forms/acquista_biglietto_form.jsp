<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="tesina.data.Posto" %>
<html>
<head>
<base href="<%=request.getContextPath() %>/">
<link href="css/jquery/jquery-ui.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
	function aggiornaTotale(posto) {
			
		var prezzo = document.getElementById("prezzo").value;
		var old_totale = document.getElementById("totale").value;
		
		if (posto.checked==true) {
			document.getElementById("totale").value = eval(old_totale)+eval(prezzo);
		} else {
			document.getElementById("totale").value = eval(old_totale)-eval(prezzo);
		}
	}
</script>

<title>Acquisto biglietti</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />  
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script src="js/Gill_Sans_400.font.js" type="text/javascript"></script>
<script src="js/script.js" type="text/javascript"></script> 

<script>
$( function() {
    $('input[type="checkbox"]').checkboxradio({
      icon: false
    });
  } );
  
$( function() {
    $('input[type="radio"]').checkboxradio({    });
  } );
  
$( function() {
    $('input[type="button"]').checkboxradio({    });
  } );
  </script>
  
  

<!--[if lt IE 7]>
<script type="text/javascript" src="js/ie_png.js"></script>
<script type="text/javascript">ie_png.fix('.png, .link1 span, .link1');</script>
<link href="css/ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->


</head>
<body id="page4">
<!-- START PAGE SOURCE -->
<div class="tail-top">
  <div class="tail-bottom">
    <div id="main">
      <div id="header">
        <div class="row-1">
          <div class="fleft"><a href="#">Cinema <span>World</span></a></div>
          <ul>
            <li><a href="#"><img src="images/icon1.gif" alt="" /></a></li>
            <li><a href="#"><img src="images/icon2.gif" alt="" /></a></li>
            <li><a href="#"><img src="images/icon3.gif" alt="" /></a></li>
          </ul>
        </div>
        <div class="row-2">
          <ul>
            <li><s:a action="home" namespace="/">Home</s:a></li>
            <li><s:a action="listaSpettacoli" namespace="/">Lista Spettacoli</s:a></li>
            <li><s:a action="about" namespace="/">About</s:a></li>
            <li class="last">
   	            <s:if test="%{#session.userData == null}">
					<s:a namespace="/clienti" action="loginForm">Accedi / Registrati</s:a>
				</s:if>
				<s:else>
					<s:a namespace="/clienti" action="loginForm"><s:property value="#session.userData.username" /></s:a>
				</s:else>
            </li>
          </ul>
        </div>
      </div>
      <div id="content">
        <div class="line-hor"></div>
        <div class="box">
          <div class="border-right">
            <div class="border-left">
              <div class="inner">
              			<h3><span>Acquisto biglietti</span> per la visione dello spettacolo</h3>
						<s:if test="hasActionErrors()">
						   <div class="errori">
						      <s:iterator value="actionErrors">
						        <div align="center">
									<span><s:property escape="false" /></span>
								</div>
							  </s:iterator>
						   </div>
						   <br/>
						</s:if>
						<s:form name="bigliettoForm" action="acquisto" namespace="/clienti" method="POST">
						<s:hidden key="codice_spettacolo" value="%{spettacolo.codice}" />						
						<s:textfield cssStyle="width: 100%; border: none; color: #858585; background: transparent;" key="titolo_film" label="Film" value="%{spettacolo.titolo_film}" disabled="true" />
						<s:textfield cssStyle="border: none; color: #858585; background: transparent;" key="nome_cinema" label="Cinema" value="%{spettacolo.nome_cinema}" disabled="true" />
						<s:textfield cssStyle="border: none; color: #858585; background: transparent;" key="data_e_ora" label="Data e ora" value="%{spettacolo.data_e_ora}" disabled="true" />
						<s:textfield cssStyle="border: none; color: #858585; background: transparent;" key="durata" label="Durata" value="%{spettacolo.durata} min" disabled="true" />
						<s:textfield cssStyle="border: none; color: #858585; background: transparent;" id="prezzo" key="prezzo" label="Prezzo €" value="%{spettacolo.prezzo}" disabled="true" />
						<s:textfield cssStyle="border: none; color: #858585; background: transparent;" key="tipo_3d" label="Tipo" value="%{spettacolo.tipo_3d}" disabled="true" />
										
						  <fieldset>
						  <table>
						  <tr><th colspan="100">SCHERMO</th></tr>
						  <%
							Posto[] posti = (Posto[]) request.getAttribute("posti");
							String fila;
							if(posti!=null) {
								fila = posti[0].getFila();
								%> <tr><td style="width: 30px;"><%=fila %></td> <%
								for(int i=0; i<posti.length; i++) {
									if(posti[i].isOccupato()) {
							%>
<td><label style="width: 10px; font-size: 10px; background-color: #FF0000;" for="<%=posti[i].getCodice() %>"><%=posti[i].getNumero() %>
      <input onchange="aggiornaTotale(this);" type="checkbox" name="posto" id="<%=posti[i].getCodice() %>" value="<%=posti[i].getCodice() %>" disabled />
</label></td>							
									<% } else { %>
<td><label style="width: 10px; font-size: 10px;" for="<%=posti[i].getCodice() %>"><%=posti[i].getNumero() %>
      <input onchange="aggiornaTotale(this);" type="checkbox" name="posto" id="<%=posti[i].getCodice() %>" value="<%=posti[i].getCodice() %>" />
</label></td>	
									<% }	
									if(i<posti.length-1 && !posti[i+1].getFila().equals(fila)) {
										fila = posti[i+1].getFila();
									%> </tr><tr><td style="width: 30px;"><%=fila %></td><%
									}
								}
							}
						%>
						</tr>
						</table>
  						</fieldset>
  						
  						<s:textfield cssStyle="border: none; color: #858585; background: transparent;" id="totale" label="Prezzo totale €" value="0.00" readonly="true" disabled="true" />
  						
  						<br/>
  						<br/>
  						
  						<div id="radioset">
  						Tipologia biglietti:
		<input type="radio" id="radio1" name="tipo" value="1" checked="checked"  /><label style="font-size:11px;" for="radio1">Prevendita</label>
		<input type="radio" id="radio2" name="tipo" value="0" /><label style="font-size:11px;" for="radio2">Prenotazione</label>
						</div>		
						
						<s:if test="%{#session.userData == null}">
						<br/>
							Accesso non effettuato:<br/>
							per procedere all'acquisto, inserisci un valido indirizzo email oppure <s:a namespace="/clienti" action="loginForm">registrati ora</s:a>!<br/>
							<s:textfield label="E-mail" key="email" maxLength="40" required="true" />
						</s:if>
						<s:else>
							<s:hidden key="email" value="%{#session.userData.email}" />
						</s:else>
  						
  						<s:submit id="submit_button" value="Acquista" cssClass="ui-button ui-widget ui-corner-all" />						
						</s:form>
						<br/><br/>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="footer">
        <div class="left">
          <div class="right">
            <div class="footerlink">
              <p class="lf">Tesina progetto Basi di Dati <br>Prof. Vincenzo Moscato aa 2016/2017</p>
              <p class="rf" style="text-align: right;">Giuseppe D'Alterio N46002736</p>
              <div style="clear:both;"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript"> Cufon.now(); </script>
<!-- END PAGE SOURCE -->
</body>
</html>