<%-- 
    Document   : alerta
    Created on : Jul 29, 2017, 6:17:27 PM
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
                <div class="col-sm-4 text-left"> 
                    <div class="panel panel-info">
                        <div class="panel-heading">Mensagem Instantânea</div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    <!--<label class="control-label">Nome</label>
                                    <input type="text" id="userName" class="form-control" name="Nome"/>
                                    <label class="control-label">Email</label>
                                    <input type="text" id="userEmail" class="form-control"/>-->
                                    <label class="control-label">Título</label>
                                    <input type="text" id="titulo" class="form-control"/>
                                    <label class="control-label">Mensagem</label>
                                    <textarea id="descricao" class="form-control"></textarea>
                                    <label class="control-label">Grupo da Notificação</label>
                                    <select class="form-control" name="grupo">
                                        <option value="alertar-1">alertar</option>
                                        <option value="teste-2">teste</option>
                                    </select>
                                    <!--<label class="control-label">Grupo</label>
                                    <select>
                                        <option>teste</option>
                                    </select>-->
                                    <!--<input type="text" id="topic" class="form-control"/> popular combo box pelo banco-->
                                    <!--<button type="button" id="userCadastrar" >Cadastrar</button>-->
                                    <!--<button type="button" id="cadastrarMensagem">Cadastrar</button> -->
                                    <button type="button" id="enviarAlerta">Enviar</button> 
                                    <button type="button" id="limpar">Limpar</button> 
                                    <div id="resultado"></div>
                                </div>                        
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">                    
                </div>
            </div>
        </div>
        <footer class="container-fluid text-center"></footer>
    </body>
</html>
