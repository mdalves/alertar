

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
        <script type="text/javascript" src="js/alerta.js"></script>
    </head>
    <body>
        <!--Menu -->
        <nav class="navbar navbar-default navbar-fixed-to">
            <header class="row">
                <div class="container">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp">ALERTAR</a>
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
                                    <li><a href="#">GRUPO DE ALERTA</a></li>-->
                        </ul>
                        </li>
                        </ul>
                    </div>
                </div>
            </header>  
        </nav>
        <!--Menu-->     

        <div role="main" class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2"></div>
                <div class="col-sm-8 text-center"> 
                    <h1>Histórico de Alertas</h1>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Sensor</th>
                                <th>Data</th>
                                <th>Substância</th>
                                <th>Título</th>
                                <th>Alerta</th>
                                <th>Local</th>
                                <th>Grupo</th>
                                <th>Usuário</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>N/A (Envio Manual)</td>
                                <td>03/08/3017 03:56</td>
                                <td>Gás Carbônico</td>
                                <td>Alerta</td>
                                <td>Vazamento de Gás Carbônico próximo à Ponta da Praia</td>
                                <td>Ponta da Praia</td>
                                <td>alertar</td>
                                <td>defesa_civil</td>
                            </tr>
                        </tbody>
                    </table>


                </div>
                <div class="col-sm-2">                    
                </div>
            </div>
        </div>
        <footer class="container-fluid text-center"></footer>
    </body>
</html>
