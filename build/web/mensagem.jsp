<%-- 
    Document   : mensagem
    Created on : 04/08/2017, 20:18:48
    Author     : Mari
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
        <script type="text/javascript" src="js/mensagem.js"></script>
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
                <div class="col-sm-2"></div>
                <div class="col-sm-8 text-left"> 

                    <h1 class="text-center">Mensagem</h1>

                    <!-- Table -->
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
                            <!--<tr>
                                <td>N/A (Envio Manual)</td>
                                <td>03/08/3017 03:56</td>
                                <td>Gás Carbônico</td>
                                <td>Alerta</td>
                                <td>Vazamento de Gás Carbônico próximo à Ponta da Praia</td>
                                <td>Ponta da Praia</td>
                                <td>alertar</td>
                                <td>defesa_civil</td>
                            </tr>-->
                        </tbody>
                    </table>                    
                    <!-- Table -->

                    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Nova Mensagem</button>
                    <div>
                        <h1 class="text-center">Simulação do Sensor</h1>
                    </div>
                    <fieldset >
                        <legend id="carbono">Dióxido de Carbono</legend>
                        <button type="button" id="dioxidoCarbono1" name="1" class="btn btn-primary">Nível 1</button>
                        <button type="button" id="dioxidoCarbono2" name="2" class="btn btn-warning">Nível 2</button>
                        <button type="button" id="dioxidoCarbono3" name="3" class="btn btn-danger">Nível 3</button>


                    </fieldset>

                    <!-- Modal -->
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Cadastrar Mensagem</h4>
                                </div>
                                <div class="modal-body">

                                    <div class="panel-body">
                                        <form role="form">
                                            <div class="form-group">
                                                <label class="control-label">Título</label>
                                                <input type="text" id="titulo" class="form-control" required/>
                                                <label class="control-label">Mensagem</label>
                                                <textarea id="descricao" class="form-control" required></textarea>
                                                <label class="control-label">Substância</label>
                                                <select class="form-control" name="substancia">
                                                    <option value="1">Amônia</option>
                                                    <option value="2">Gás Carbônico</option>
                                                    <option value="3">GLP</option>
                                                    <option value="4">Cloro</option>
                                                </select>
                                                <label class="control-label">Grupo da Notificação</label>
                                                <select class="form-control" name="grupo">
                                                    <option value="alertar">alertar</option>
                                                </select>
                                                <label class="control-label">Nível de Alerta</label>
                                                <div class="radio">
                                                    <label><input name="nivel" value="1" type="radio">Baixo</label>
                                                    <label><input name="nivel" value="2" type="radio">Médio</label>
                                                    <label><input name="nivel" value="3" type="radio">Alto</label>
                                                </div>
                                                <!--<button type="button" id="userCadastrar" >Cadastrar</button>-->
                                                <div>   
                                                    <div class="col-sm-10 form-inline"></div>
                                                    <!--    <div class="col-sm-2"> -->
                                                    <button type="button" id="cadastrarMensagem" class="btn btn-info btn-lg">Cadastrar</button> 
                                                    <!-- </div> -->
                                                    <!--  <div class="col-sm-2">-->
                                                    <!-- <button type="button" id="enviarMensagem" class="btn btn-info btn-lg">Enviar</button> -->
                                                    <!-- </div> -->
                                                </div>
                                                <div id="resultado" class="alert">

                                                </div>
                                            </div>
                                    </div>                        
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal -->

            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>

    <footer class="container-fluid text-center" style="background-color:blue;"></footer>
</body>
</html>