<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="icon" href="images/favicon.png">
        <title>Loja DW</title>
    </head>
    <body>
        <div class="conteudo">
            <div id='menu'>
                <a class="carrinho" href='carrinho.jsp'></a>
                <a class='login' href="index.jsp"><button>Área Restrita</button></a>
            </div>
            <div id="topo">
                <img src="images/logo.png">
                <h3>Realize suas compras!</h3>
            </div>
            <div id="listar">
                <form id='tipo' action="" method="post">
                    <input id='eletrod' name='item' type='radio' value="1" checked='checked' onClick='this.form.submit();'>
                    <label for='eletrod'>Eletrodomésticos</label>
                    <input id='info' name='item' type='radio' value="2" onClick='this.form.submit();'>
                    <label for='info'>Informática</label>
                    <input id='eletrop' name='item' type='radio' value="3" onClick='this.form.submit();'>
                    <label for='eletrop'>Eletroportáteis</label>
                    <input id='smart' name='item' type='radio' value="4" onClick='this.form.submit();'>
                    <label for='smart'>Smartphones</label>
                </form>
                <form id='pesquisa'>
                    <input type="text" placeholder='Pesquise aqui!' value=''>
                    <input type="submit" value='PESQUISAR'>
                </form>
            </div>
            <div id="tabela">
                <%
                    try {
                        List<String> r = (List)request.getAttribute("r");
                        String[] row;
                        out.println("<form id='' action='' method='post'>");
                        out.println("<input name='tabela' style='display:none' value=''>");
                        out.println("<input id='id'  name='id' type='hidden' value=''>");
                        out.println("<table><tr>");
                        out.println("<th>Nome</th>");
                        out.println("<th>Descricao</th>");
                        out.println("<th>Valor</th>");
                        out.println("<th>Comprar</th>");
                        
                        out.println("</tr>");
                        
                        for (int i = 0; i < r.size(); i++) {
                            row = r.get(i).split("¨");
                            out.println("<tr id='linha"+i+"' idRow='"+row[0]+"'>");

                            for (int j = 1; j < 3; j++) 
                                out.println("<td class='linha"+i+"'>"+row[j]+"</td>");
                            out.println("<td><button type='button' onClick=''>Comprar</button></td>");
                            out.println("</tr>");
                        }
                        out.println("</table></div></form>");
                    }
                    catch(Exception e) {}
                        %>
        </div>
    </body>
</html>
