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
        <script>
            function adicionarCarrinho(id, nome) {
                var id_produto = document.getElementById("id");
                var nome_produto = document.getElementById("nome");
                id_produto.setAttribute('value', id);
                nome_produto.setAttribute('value', nome);
            }
        </script>
    </head>
    <body>
        <div class="conteudo">
            <div id='menu'>
                <a class="carrinho" href='carrinho.jsp'></a>
                <a class='login' href="login.jsp"><button>Área Restrita</button></a>
            </div>
            <div id="topo">
                <img src="images/logo.png">
                <h3>Realize suas compras!</h3>
            </div>
            <div id="listar">
                <form id='tipo' action="Pesquisa_Categoria" method="post">
                    <input id='eletrod' name='item' type='radio' value="1" checked='checked' onClick='this.form.submit();'>
                    <label for='eletrod'>Eletrodomésticos</label>
                    <input id='info' name='item' type='radio' value="2" onClick='this.form.submit();'>
                    <label for='info'>Informática</label>
                    <input id='eletrop' name='item' type='radio' value="3" onClick='this.form.submit();'>
                    <label for='eletrop'>Eletroportáteis</label>
                    <input id='smart' name='item' type='radio' value="4" onClick='this.form.submit();'>
                    <label for='smart'>Smartphones</label>
                </form>
                <form action="Pesquisa_Produto" method="post" id='pesquisa'>
                    <input type="text" name="text" placeholder='Pesquise aqui!' value=''>
                    <input type="submit" value='PESQUISAR'>
                </form>
            </div>
            <div id="tabela">
                <%
                    try {
                        List<String> r = (List)request.getAttribute("r");
                        String[] row;
                        if(r != null && r.size()>0) {
                            out.println("<form action='Adicionar_Carrinho' method='post'>");
                            out.println("<input id='id'  name='id' type='hidden' value=''>");
                            out.println("<input id='nome'  name='nome' type='hidden' value=''>");
                            out.println("<table><tr>");
                            out.println("<th>Nome</th>");
                            out.println("<th>Descricao</th>");
                            out.println("<th>Valor</th>");
                            out.println("<th>Comprar</th>");

                            out.println("</tr>");

                            for (int i = 0; i < r.size(); i++) {
                                row = r.get(i).split("¨");
                                out.println("<tr>");

                                for (int j = 1; j < 4; j++) 
                                    out.println("<td>"+row[j]+"</td>");
                                out.println("<td><button class='compra' type='button' onClick='adicionarCarrinho(\""+row[0]+"\", \""+row[1]+"\"); this.form.submit()'>Comprar</button></td>");
                                out.println("</tr>");
                            }
                            out.println("</table></div></form>");
                        }
                    }
                    catch(Exception e) {}
                %>
            </div>
        </div>
    </body>
</html>
