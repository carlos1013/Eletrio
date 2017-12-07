<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="icon" href="images/favicon.png">
        <title>Loja DW | Meu Carrinho</title>
    </head>
    <body>
        <div class="conteudo">
            <div id='menu'>
                <a class="carrinho" href='carrinho.jsp'></a>
                <a class='login' href="login.jsp"><button>Área Restrita</button></a>
            </div>
            <div id="topo">
                <img src="images/logo.png">
                <h3>Minhas Compras</h3>
            </div>
            <div id="tabela">
                <%
                    try {
                        List<String> r = (List)request.getAttribute("r");
                        String[] row;
                        if(r != null && r.size()>0) {
                            float total = 0;
                            out.println("<form id='' action='' method='post'>");
                            out.println("<input id='id'  name='id' type='hidden' value=''>");
                            out.println("<table><tr>");
                            out.println("<th>Nome</th>");
                            out.println("<th>Descricao</th>");
                            out.println("<th>Valor</th>");
                            out.println("<th>Remover</th>");

                            out.println("</tr>");

                            for (int i = 0; i < r.size(); i++) {
                                row = r.get(i).split("¨");
                                out.println("<tr>");

                                for (int j = 1; j < 4; j++) 
                                    out.println("<td>"+row[j]+"</td>");
                                out.println("<td><button class='remover' type='button' onClick=''>Remover</button></td>");
                                out.println("</tr>");
                                
                                total += Float.parseFloat(row[3]);
                            }
                            out.println("</table></div></form>");
                            out.println("<b>Total: "+total+"</b>");
                            out.println("<button>Finalizar Compra</button>");
                        }
                        else {
                            out.println("<h3>Seu carrinho está vazio.</h3>");
                        }
                    }
                    catch(Exception e) {}
                %>
            </div>
            <a href='/Eletrio' class='voltar'><button>Voltar as compras</button></a>
        </div>
    </body>
</html>
