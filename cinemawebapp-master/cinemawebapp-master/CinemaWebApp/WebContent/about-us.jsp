<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>About Us</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<body id="page2">
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
            <li><s:a action="about" namespace="/" cssClass="active">About</s:a></li>
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
                <h3>About <span>Us</span></h3>
                <div style="text-align: justify;">
                Il sistema software per la gestione dei cinema nasce come progetto relativo al corso di <b>Basi di Dati</b>, per il corso di laurea in Ingegneria Informatica, tenuto dal prof. <i>Vincenzo Moscato</i> dell'Università degli Studi di Napoli Federico II durante l'anno accademico 2016/2017.<br/>
                </div>
                <br/><br/>
                <div style="text-align: center;">
                Per la realizzazione dell'applicazione web si è fatto uso dei seguenti software, tecnologie e librerie:
                </div><br/>
                <ol>
                <li>● <b>Apache Struts</b> per la scrittura delle servlet Java EE</li>
                <li>● la tecnologia <b>JavaServer Pages</b> per lo sviluppo della <i>logica di presentazione</i> delle interfacce web </li>
                <li>● <b>Apache Tomcat</b> come piattaforma software per l'esecuzione dell'applicazione Web</li>
                <li>● <b>JDBC</b> come driver per la comunicazione dell'applicazione web con il DBMS <i>Oracle 11g XE</i></li>
                <li>● <b>iTEXT</b> per l'elaborazione dei file PDF (biglietti)</li>
                <li>● <b>Eclipse Java EE IDE</b> come ambiente di sviluppo</li>
                <li>● <b>GIMP</b> per l'elaborazione grafica delle immagini</li>
                </ol>
                
                <br/><br/>
                <h3>Il nostro <span>Team</span></h3>
		          <ul class="list">
		            <li><img src="images/3page-img3.jpg" alt="" /><B><BR/>Giuseppe D'Alterio <BR/> N46002736</B></li>
		          </ul>
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