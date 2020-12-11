<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<base href="<%=request.getContextPath() %>/">

<link href="css/jquery/jquery-ui.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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

<title>Cinema World | Article</title>
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
					<s:a namespace="/clienti" action="loginForm" cssClass="active">Accedi / Registrati</s:a>
				</s:if>
				<s:else>
					<s:a namespace="/clienti" action="loginForm"  cssClass="active"><s:property value="#session.userData.username" /></s:a>
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
              
				<s:if test="hasActionErrors()">
				   <div class="actionMessage">
				      <s:iterator value="actionErrors">
				        <div align="center">
				        <b>Si è verificato un errore: <br /></b>
							<span class="errorMessage"><s:property escape="false" /></span>
						</div>
					  </s:iterator>
				   </div>
				   <br /><br />
				</s:if>

				<s:if test="%{#session.userData == null}">
				<h3>Accesso <span>clienti</span></h3>
					<s:form action="login" namespace="/clienti">
					<s:textfield key="username" label="Username" required="true" />
					<s:password key="password" label="Password" required="true" />
					<s:submit value="Accedi" cssClass="ui-button ui-widget ui-corner-all" />
					</s:form>
					<br/><br/><br/>
				<h3>Registrazione <span>nuovo cliente</span></h3>
				<s:form name="signupForm" action="signup" namespace="/clienti">
				<s:textfield key="username" label="Username" maxLength="40" required="true" />
				<s:password key="password" label="Password" required="true" />
				<s:textfield key="nome" label="Nome" maxLength="25" required="true" />
				<s:textfield key="cognome" label="Cognome" maxLength="25" required="true" />
				<s:textfield key="email" label="E-mail" maxLength="40" required="true" />
				<s:submit id="submit_button" value="Registrati" cssClass="ui-button ui-widget ui-corner-all"  />
				</s:form>
				</s:if>
				<s:else>
				Accesso effettuato come: <s:property value="#session.userData.username" />!
				<br />
				<div class="wrapper">
				<s:a namespace="/clienti" action="logout" cssClass="link1"><span><span>Esci</span></span></s:a>
				</div>
				</s:else>
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