<%-- 
    Document   : index
    Created on : Jul 29, 2017, 6:19:18 PM
    Author     : MARI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alertar</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <link type="text/css" href="css/bootstrap-theme.css" rel="stylesheet"/>
        <link type="text/css" href="css/style.css" rel="stylesheet" />
        <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>        
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
    </head>
    <body>
        <!--Menu -->
        <nav class="navbar navbar-default navbar-fixed-to">
            <header class="row">
                <div class="container">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp">ALERTAR</a>
                    </div>
                    <div class="navbar-header">
                        <img src="image/logo.png" alt="Alertar" class="resize"/>
                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="login.jsp">LOGIN</a></li>
                            <li><a href="alerta.jsp">ENVIAR ALERTA</a></li>  
                            <li><a href="sensor.jsp">SENSOR</a></li>
                            <li><a href="listaAlertaAtivo.jsp">ALERTAS</a></li>
                            <!--<li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">CADASTRAR<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="sensor.jsp">SENSOR</a></li>
                                    <li><a href="mensagem.jsp">MENSAGEM</a></li>
                                    <li><a href="#">SUBSTÂNCIA</a></li>
                                    <li><a href="#">GRUPO DE ALERTA</a></li>
                                </ul>
                            </li>-->
                        </ul>
                    </div>
                </div>
            </header>  
        </nav>
        <!--Menu-->     

        <div role="main" class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-4"></div>
                <div class="col-sm-4 text-center"> 
                    <h1> Alertar</h1>
                </div>
                <div class="col-sm-4"></div>
            </div>
        </div>
        <footer class="container-fluid text-center"></footer>
    </body>
</html>
