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
        <script>
            function removerCarrinho(id) {
                var id_produto = document.getElementById("id");
                id_produto.setAttribute('value', id);
            }
        </script>
    </head>
    <body>
        <div class="conteudo">
            <div id='menu'>
                <form action="Retorna_Produtos" method="post"><button onclick="this.form.submit" class="carrinho"></button></form>
                <a class='login' href="login.jsp"><button>Área Restrita</button></a>
            </div>
            <div id="topo">
                <img src="images/logo.png">
                <h3>Minhas Compras</h3>
            </div>
            <div id="tabela">
                <form id='form' action='Retorna_Produtos' method="post"></form>
                <%
                    try {
                        List<String> r = (List)request.getAttribute("r");
                        String[] row;
                        if(r != null && r.size()>0) {
                            float total = 0;
                            out.println("<form action='Remover_Carrinho' method='post'>");
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
                                out.println("<td><button class='remover' type='button' onClick='removerCarrinho(\""+row[0]+"\"); this.form.submit();'>Remover</button></td>");
                                out.println("</tr>");
                                
                                total += Float.parseFloat(row[3]);
                            }
                            out.println("</table></form>");
                            out.println("<h4>Total: "+total+"</h4><br/>");
                            out.println("<a href='cadastro.jsp'><button class='compra'>Finalizar Compra</button></a>");
                            out.println("<a href='/Eletrio' class='voltar'><button>Voltar as compras</button></a>");
                        }
                        else {
                            out.println("<h3>Seu carrinho está vazio.</h3>");
                            out.println("<a href='/Eletrio' class='voltar'><button>Voltar as compras</button></a>");
                        }
                    }
                    catch(Exception e) {}
                %>
            </div>
        </div>
    </body>
</html>
