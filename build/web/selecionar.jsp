<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function operacao(linha, op) {
                var id = document.getElementById('id');
                var op = document.getElementById('op');
                id.setAttribute('value', linha);
                
                if (op.localeCompare("editar")) {
                    var linha = document.getElementById("linha"+linha);
                    linha.classList.toggle("edicao");
                    op.setAttribute('value', 'editar');
                }
                else 
                    op.setAttribute('value', 'remover');
            }
            function mostrarFormulario(nome) {
                var div = document.getElementById(nome);
                div.style.display = 'block';
            }
            function cancelar(nome) {
                var div = document.getElementById(nome);
                div.style.display = 'none';
            }
            function notNull() {
                var inputs = document.getElementsByClassName('obg');
                var i;
                var r = true;
                for (i=0; i<inputs.length; i++) {
                  if(inputs[i].value == null || inputs[i].value == "") {
                        inputs[i].style.backgroundColor = "#f7dcdc";
                        r = false;
                  }
                }
                if (!r) {
                    alert("Preencha os campos obrigatórios.");
                    return false;
                }
                else 
                    return confirm("Confirmar alterações?");
            }
        </script>
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
                <%
                    try {
                        List<String> r = (List)request.getAttribute("r");
                        String[] row = r.get(0).split(" ");
                        String nome = row[0];
                        String[] colunas = row.clone();
                        int qtAtt = Integer.parseInt(row[1]);
                        out.println("<h1>"+nome+"</h1>");
                        out.println("<form method='get' onsubmit='return notNull();'>");
                        out.println("<input name='tabela' style='display:none' value='"+nome+"'>");
                        out.println("<input id='id'  name='id' style='display:none' value=''>");
                        out.println("<input id='op' name='op' style='display:none' value=''>");
                        out.println("<table><tr>");
                        for (int i = 2; i < qtAtt+2; i++) {
                            out.println("<th>"+row[i]+"</th>");
                        }
                        out.println("<th>Editar</th><th>Remover</th></tr>");
                        
                        for (int i = 1; i < r.size(); i++) {
                            row = r.get(i).split(" ");
                            out.println("<tr>");
                            for (int j = 0; j < qtAtt; j++) {
                                out.println("<td class='linha"+i+"'>"+"<span>"+row[j]+"</span></td>");
                            }
                            %>
                            <td>
                                
                                <button id='e' type='button' onclick="operacao(<% out.print("'"+i+"', 'editar'"); %>)">Editar</button> 
                            </td>
                            <td><button id='r' type='submit' onclick="operacao(<% out.print("'"+i+"', 'remover'"); %>)">Remover</button> </td>
                            </tr>
                        <%}//for
                        out.println("</table></form>");
                        if(nome.equals("CLIENTES") || nome.equals("PRODUTO") || nome.equals("ADMINISTRADOR")) {
                        %>
                        
                            <button id='i' type='button' onClick='mostrarFormulario("<% out.print(nome); %>")'>Inserir</button>

                            <div id="inserir">
                                <div id="CLIENTES" style="display:none">
                                    <form>
                                        <input name="nome" id="nome" maxlength="50" class="large obg" type="text" onclick="bgBranco('nome');" 
placeholder="Nome:"/><br/>

                                        <input name="endereco" id="endereco" maxlength="50" type="text" class="mid left obg" 
placeholder="Endereço:" onclick="bgBranco('endereco');" />

                                        <input name="referencia" id="referencia" type="text" class="mid right" placeholder="Referência:"/><br/>

                                        <input name="bairro" id="bairro" maxlength="50" type="text" class="mid left obg" placeholder="Bairro:" onclick="bgBranco('bairro');"/>
                                        <input name="cidade" id="cidade" maxlength="50" type="text" class="mid right obg" placeholder="Cidade:" onclick="bgBranco('cidade');"/><br/>

                                        <input name="cep" id="cep" maxlength="8" type="text" class="mid obg" placeholder="CEP:" onclick="bgBranco('cep');"/>
                                        <span>Estado:</span>
                                        <select>
                                                <option>AC</option>
                                                <option>AL</option>
                                                <option>AP</option>
                                                <option>AM</option>
                                                <option>BA</option>
                                                <option>CE</option>
                                                <option>DF</option>
                                                <option>ES</option>
                                                <option>GO</option>
                                                <option>MA</option>
                                                <option>MT</option>
                                                <option>MS</option>
                                                <option>MG</option>
                                                <option>PA</option>
                                                <option>PB</option>
                                                <option>PR</option>
                                                <option>PE</option>
                                                <option>PI</option>
                                                <option>RJ</option>
                                                <option>RN</option>
                                                <option>RS</option>
                                                <option>RO</option>
                                                <option>RR</option>
                                                <option>SC</option>
                                                <option>SP</option>
                                                <option>SE</option>
                                                <option>TO</option>
                                        </select><br/>
                                        <input name="cpf" id="cpf" maxlength="11" type="text" class="mid left obg" placeholder="CPF:" 
onclick="bgBranco('cpf');"/>

                                        <input name="ri" id="ri" maxlength="9" type="text" class="mid right obg" placeholder="RI (identidade):" 
onclick="bgBranco('ri');"/><br/>

                                        <input name="tel" id="tel" maxlength="10" type="text" class="mid left" placeholder="Telefone fixo:"/>

                                        <input name="cel" id="cel" maxlength="11" type="text" class="mid right obg" placeholder="Celular:" 
onclick="bgBranco('cel');"/><br/> 

                                        <input name="numCartao" id="numCartao" maxlength="16" type="text" class="large obg" placeholder="Número 
do cartão de crédito:" onclick="bgBranco('numCartao');"/><br/>
                                <span>Bandeira do cartão:</span>

                                        <input name="tipoCartao" type="radio" value="MasterCard" checked="checked"/><img class="bandeira" 
src="imagens/master.png"/>

                                        <input name="tipoCartao" value="Visa"  type="radio"/><img class="bandeira" src="imagens/visa.png"/>

                                        <input name="tipoCartao" value="AmericanExpress"  type="radio"/><img class="bandeira" 
src="imagens/ae.png"/>

                                        <input name="tipoCartao" value="Elo"  type="radio"/><img class="bandeira" src="imagens/elo.png"/><br/>

                                        <button type="submit">Enviar</button>
                                    </form>
                                    <button type="text" onclick="cancelar('<% out.print(nome); %>')">Cancelar</button>
                                </div>
                                <div id="PRODUTO" style="display:none">
                                    <form>
                                        <select name="produto">
                                            <option value="1" selected>Eletrodomésticos</option>
                                            <option value="2">Informática</option>
                                            <option value="3">Eletroportáteis</option>
                                            <option value="4">Smartphones</option>
                                        </select>
                                        <input name="nome" type="text" id="nome" placeholder="Nome">
                                        <input name="descricao" type="text" id="descricao" placeholder="Descrição">
                                        <input name="valor" type="text" id="valor" placeholder="Valor">
                                        <button type="submit">Enviar</button>
                                    </form>
                                    <button type="text" onclick="cancelar('<% out.print(nome); %>')">Cancelar</button>
                                </div>
                                <div id="ADMINISTRADOR" style="display:none">
                                    <form>
                                        <input name="nome" type="text" id="nome" placeholder="Nome">
                                        <input name="senha" type="text" id="nome" placeholder="Senha">
                                        <button type="submit">Enviar</button>
                                    </form>
                                    <button type="text" onclick="cancelar('<% out.print(nome); %>')">Cancelar</button>
                                </div>
                            </div>
                        <%
                        }//if
                    } //try
                    catch(Exception e) {}
                %>
            </div>
        </div>
            
    </body>
</html>
