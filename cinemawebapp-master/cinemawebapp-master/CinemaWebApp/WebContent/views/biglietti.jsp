<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<base href="<%=request.getContextPath() %>/">
<title>Lista spettacoli</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
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
              <s:iterator value="posto">
              	<embed src="<%=request.getContextPath() %>/TICKETS/<s:property value="email"/>/<s:property value="codice_prenotazione" />/ticket_<s:property value="codice_prenotazione" />-<s:property />.pdf" width="100%" height="365" type='application/pdf' />
              </s:iterator>

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