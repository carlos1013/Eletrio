<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="conteudo">
            <div id="topo">
                <img src="images/logo.png">
                <h3>Bem vindo(a) à página de administração!</h3>
            </div>
            <div id="selecionar">
                <span>Selecione a informação desejada:</span>
                <form action="Tabelas" method="post">
                    <select name="tabela">
                        <option value="CATEGORIA" selected>Categorias</option>
                        <option value="CLIENTES">Clientes</option>
                        <option value="COMPRAS">Compras</option>
                        <option value="PRODUTO">Produto</option>
                        <option value="ADMINISTRADOR">Administrador</option>
                    </select>
                    <input type="submit" value="Selecionar">
                </form>
            </div>
            <div id="tabela">
                
            </div>
        </div>
            
    </body>
</html>
