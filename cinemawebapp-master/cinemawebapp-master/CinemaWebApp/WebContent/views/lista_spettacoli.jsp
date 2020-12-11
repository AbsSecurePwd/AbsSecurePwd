<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<base href="<%=request.getContextPath() %>/">
<title>Lista spettacoli</title>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/js/jquery.quicksearch.js"></script>

		

<script type="text/javascript">

//--
$(function () {
				$('input#id_search').quicksearch('table#table_example tbody tr');				
			});
//--

function filtraCinema(e) {
	insertParam("nomeCinema", e);
}

function insertParam(key, value)
{
    key = encodeURI(key); value = encodeURI(value);

    var kvp = document.location.search.substr(1).split('&');

    var i=kvp.length; var x; while(i--) 
    {
        x = kvp[i].split('=');

        if (x[0]==key)
        {
            x[1] = value;
            kvp[i] = x.join('=');
            break;
        }
    }

    if(i<0) {kvp[kvp.length] = [key,value].join('=');}

    //this will reload the page, it's likely better to store this until finished
    document.location.search = kvp.join('&'); 
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/jquery-1.4.2.min.js" type="text/javascript"></script> -->
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script src="js/Gill_Sans_400.font.js" type="text/javascript"></script>
<script src="js/script.js" type="text/javascript"></script>
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
            <li><s:a action="listaSpettacoli" namespace="/" cssClass="active">Lista Spettacoli</s:a></li>
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
              		<h3>Lista <span>spettacoli</span></h3>
              		<s:if test="%{listaCinema != null}">
              			<s:select emptyOption="true" list="%{listaCinema}" label="Filtra per cinema" onchange="filtraCinema(this.value);" value="%{#request.nomeCinema}" />
              		</s:if>
              		

				<form action="#">
							<fieldset>
								Ricerca libera: <input type="text" name="search" value="" id="id_search" placeholder="Ricerca..." autofocus="true" />
							</fieldset>
				</form>
              		
				<div class="datagrid">
					<table id="table_example">
					<thead>
					<tr>
					<th>Film</th>
					<th>Cinema</th>
					<th>Data e ora</th>
					<th>Durata</th>
					<th>Prezzo</th>
					<th>3D</th>
					<th>Prenota</th>
					</tr>
					</thead>
					<tbody>
					<s:if test="%{listaSpettacoli != null}">
					<s:iterator value="listaSpettacoli" var="spettacolo">
					<tr>
					<td><s:property value="%{#spettacolo.titolo_film}" /></td>
					<td><s:property value="%{#spettacolo.nome_cinema}" /></td>
					<td><s:property value="%{#spettacolo.data_e_ora}" /></td>
					<td><s:property value="%{#spettacolo.durata}" /> min</td>
					<td>€ <s:property value="%{#spettacolo.prezzo}" /></td>
					<td><s:property value="%{#spettacolo.tipo_3d}" /></td>
					<td><s:a action="compraBiglietto?codice_spettacolo=%{#spettacolo.codice}" namespace="/clienti">Acquista</s:a></td>
					</tr>
					</s:iterator>
					</s:if>
					<s:else>
					<tr>
					<td colspan="7">NESSUNO SPETTACOLO</td>
					</tr>
					</s:else>
					</tbody>
					</table>
				</div>
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