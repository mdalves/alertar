<%-- 
    Document   : listaAlertaAtivo
    Created on : 15/08/2017, 05:14:04
    Author     : Mari
--%>

<%@page import="com.senai.alertar.dao.AlertaDAO"%>
<%@page import="com.senai.alertar.model.Alerta"%>
<%@page import="com.senai.alertar.model.DataBaseObject"%>
<%@page import="java.util.List"%>
<%@page import="com.senai.alertar.util.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
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
        <!--<script type="text/javascript" src="js/lista.js"></script>-->
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
                        </ul>
                    </div>
                </div>
            </header>  
        </nav>
        <!--Menu-->
        <%  Connection conn = ConnectionFactory.getConnection();
            AlertaDAO alertaDAO = new AlertaDAO();
            List<? extends DataBaseObject> lista = alertaDAO.listarAtivo(conn);
            if (lista.isEmpty()) {
        %>

        <% } else { %>

        <form ACTION="resolverAlerta" METHOD="post">
        <div role="main" class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2"></div>
                <div class="col-sm-8 text-center"> 
                    <h1 class="text-center">Lista de Alertas</h1>
                    <!--Selecione qual dos Alertas abaixo você deseja marcar como resolvido:<br>
                    Não há notificações ativas no momento!-->
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th></th>
                                <th>ID</th>
                                <th>Título</th>
                                <th>Descrição</th>
                                <th>Data</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Object o : lista) {
                                        Alerta al = (Alerta) o;%>
                            <tr>
                                <td>                            <input type="radio" NAME="rad_alerta" VALUE=<%= al.getId()%>></td>
                                <td><%out.print(al.getId());%></td>
                                <td>
                                    <%out.print(al.getTitulo());%><br>
                                </td>
                                <td>
                                    <%out.print(al.getCorpo());%>
                                </td>
                                <td>
                                    <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");%>
                                    <%out.print(df.format(al.getDataCriacao()));%>
                                </td>
                            </tr>
                            <%          } %>
                        </tbody>
                    </table>
                    <input type="submit" id="confirmar" VALUE="Confirmar" class="btn btn-default">
                    <div class="col-sm-2"></div>
                </div>
            </div>
        </div>

                </form>
        <% }%>
    </body>
</html>
