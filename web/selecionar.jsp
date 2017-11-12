<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <script>
            function operacao(ID, operacao) {
                var id = document.getElementById('id');
                var op = document.getElementById('op');
                id.setAttribute('value', ID);
                op.setAttribute('value', operacao);
            }
            
            function formularioEdicao(linha, colunas, id) {
                var tds = document.getElementsByClassName("linha"+linha);
                var c = colunas.split(" ");
                var i;
                for (i=0; i<tds.length; i++)
                    tds[i].innerHTML = "<input name='"+c[i+2]+"' value='"+tds[i].innerHTML+"' class='obg'>";
                
                document.getElementById("editar"+linha).innerHTML = "<button type='submit' onclick='operacao(\""+id+"\", \"editar\")'>Enviar</button> <button 
type='button' onclick='cancelarEdicao(\""+linha+"\",\""+colunas+"\",\""+id+"\")'>Cancelar</button>";
            }
            
            function cancelarEdicao(linha, colunas, id) {
                var tds = document.getElementsByClassName("linha"+linha);
                var i;
                for (i=0; i<tds.length; i++) {
                    tds[i].innerHTML = tds[i].getElementsByTagName('input')[0].defaultValue;
                }
                document.getElementById("editar"+linha).innerHTML = "<button id='e' type='button' onclick='formularioEdicao(\""+linha+"\",\""+colunas+"\",\""+id
+"\")'>Editar</button>";
            }
            
            function mostrarFormulario(nome) {
                var div = document.getElementById(nome);
                div.style.display = 'block';
            }
            function cancelar(nome) {
                var div = document.getElementById(nome);
                var inputs = div.getElementsByClassName('obg');
                var i;
                for (i=0; i<inputs.length; i++) {
                  if(inputs[i].value == null || inputs[i].value == "") {
                        inputs[i].style.backgroundColor = "#fff";
                  }
                }
                div.style.display = 'none';
            }
            function notNull(form) {
                var inputs = document.getElementById(form).getElementsByClassName('obg');
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
                    <input name='op' style='display:none' value='consultar'>
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
                        String colunas = r.get(0);
                        String[] row = colunas.split(" ");
                        String nome = row[0];
                        int qtAtt = Integer.parseInt(row[1]);
                        out.println("<h1>"+nome+"</h1>");
                        out.println("<form action='Tabelas' method='post' onsubmit='return notNull(\"ru\");'><div id='ru'>");
                        out.println("<input name='tabela' style='display:none' value='"+nome+"'>");
                        out.println("<input id='id'  name='id' style='display:none' value=''>");
                        out.println("<input id='op' name='op' class='obg' style='display:none' value=''>");
                        out.println("<table><tr>");
                        for (int i = 2; i < qtAtt+2; i++) {
                            out.println("<th>"+row[i]+"</th>");
                        }
                        out.println("<th>Editar</th><th>Remover</th></tr>");
                        
                        for (int i = 1; i < r.size(); i++) {
                            row = r.get(i).split(" ");
                            out.println("<tr id='linha"+i+"'>");
                            for (int j = 0; j < qtAtt; j++) {
                                out.println("<td class='linha"+i+"'>"+row[j]+"</td>");
                            }
                            %>
                            <td id="<% out.print("editar"+i); %>">
                                <button id='e' type='button' onclick="formularioEdicao(<% out.print("'"+i+"', '"+colunas+"','"+row[0]+"'"); %>)">Editar</button> 
                            </td>
                            <td><button id='r' type='submit' onclick="operacao(<% out.print("'"+row[0]+"', 'remover'"); %>)">Remover</button> </td>
                            </tr>
                        <%}//for
                        out.println("</table></div></form>");
                        if(nome.equals("CLIENTES") || nome.equals("PRODUTO") || nome.equals("ADMINISTRADOR")) {
                        %>
                        
                            <button id='i' type='button' onClick='mostrarFormulario("<% out.print(nome); %>")'>Inserir</button>

                            <div id="inserir">
                                <div id="CLIENTES" style="display:none">
                                    <form action='Tabelas' method='post' onsubmit='return notNull("clientes");'><div id='clientes'>
                                        <input name="NOME" id="nome" maxlength="50" class="large obg" type="text" placeholder="Nome:"/><br/>
                                        <input name="ENDERECO" id="endereco" maxlength="50" type="text" class="mid left obg" placeholder="Endereço:"  />
                                        <input name="REFERENCIA" id="referencia" type="text" class="mid right obg" placeholder="Referência:"referencia');" /><br/>
                                        <input name="BAIRRO" id="bairro" maxlength="50" type="text" class="mid left obg" placeholder="Bairro:"/>
                                        <input name="CIDADE" id="cidade" maxlength="50" type="text" class="mid right obg" placeholder="Cidade:"/><br/>
                                        <input name="CEP" id="cep" maxlength="8" type="text" class="mid obg" placeholder="CEP:"/>
                                        <span>Estado:</span>
                                        <select name='ESTADO'>
                                                <option value='AC'>AC</option>
                                                <option value='AL'>AL</option>
                                                <option value='AP'>AP</option>
                                                <option value='AM'>AM</option>
                                                <option value='BA'>BA</option>
                                                <option value='CE'>CE</option>
                                                <option value='DF'>DF</option>
                                                <option value='ES'>ES</option>
                                                <option value='GO'>GO</option>
                                                <option value='MA'>MA</option>
                                                <option value='MT'>MT</option>
                                                <option value='MS'>MS</option>
                                                <option value='MG'>MG</option>
                                                <option value='PA'>PA</option>
                                                <option value='PB'>PB</option>
                                                <option value='PR'>PR</option>
                                                <option value='PE'>PE</option>
                                                <option value='PI'>PI</option>
                                                <option value='RJ'>RJ</option>
                                                <option value='RN'>RN</option>
                                                <option value='RS'>RS</option>
                                                <option value='RO'>RO</option>
                                                <option value='RR'>RR</option>
                                                <option value='SC'>SC</option>
                                                <option value='SP'>SP</option>
                                                <option value='SE'>SE</option>
                                                <option value='TO'>TO</option>
                                        </select><br/>
                                        <input name="CPF" id="cpf" maxlength="11" type="text" class="mid left obg" placeholder="CPF:"/>
                                        <input name="IDENTIDADE" id="ri" maxlength="9" type="text" class="mid right obg" placeholder="RI (identidade):" /><br/>
                                        <input name="FIXO" id="tel" maxlength="10" type="text" class="mid left obg" placeholder="Telefone fixo:" />
                                        <input name="CELULAR" id="cel" maxlength="11" type="text" class="mid right obg" placeholder="Celular:" /><br/> 
                                        <input name="CARTAO" id="numCartao" maxlength="16" type="text" class="large obg" placeholder="Número do cartão de crédito:" 
/><br/>
                                        <span>Bandeira do cartão:</span>
                                        <input name="BANDEIRA" type="radio" value="MasterCard" checked="checked"/><img class="bandeira" src="imagens/master.png"/>
                                        <input name="BANDEIRA" value="Visa"  type="radio"/><img class="bandeira" src="imagens/visa.png"/>
                                        <input name="BANDEIRA" value="AmericanExpress"  type="radio"/><img class="bandeira" src="imagens/ae.png"/>
                                        <input name="BANDEIRA" value="Elo"  type="radio"/><img class="bandeira" src="imagens/elo.png"/><br/>
                                        <button type="submit">Enviar</button>
                                        <button type="button" onClick="this.form.reset();cancelar('<% out.print(nome); %>');">Cancelar</button>
                                    </div></form>
                                </div>
                                <div id="PRODUTO" style="display:none">
                                    <form action='Tabelas' method='post' onsubmit='return notNull("produto");'><div id='produto'>
                                        <select name="ID_CATEGORIA">
                                            <option value="1" selected>Eletrodomésticos</option>
                                            <option value="2">Informática</option>
                                            <option value="3">Eletroportáteis</option>
                                            <option value="4">Smartphones</option>
                                        </select>
                                        <input name="NOME" type="text" id="nome" class='obg' placeholder="Nome" >
                                        <input name="DESCRICAO" type="text" id="descricao" class='obg' placeholder="Descrição" >
                                        <input name="VALOR" type="text" id="valor" class='obg' placeholder="Valor"  >
                                        <button type="submit">Enviar</button>
                                        <button type="button" onClick="this.form.reset();cancelar('<% out.print(nome); %>');">Cancelar</button>
                                    </div></form>
                                </div>
                                <div id="ADMINISTRADOR" style="display:none">
                                    <form action='Tabelas' method='post' onsubmit='return notNull("admin");'><div id='admin'>
                                        <input name="LOGIN" type="text" id="nome" class='obg' placeholder="Nome">
                                        <input name="SENHA" type="password" id="senha" class='obg' placeholder="Senha">
                                        <button type="submit">Enviar</button>
                                        <button type="button" onClick="this.form.reset();cancelar('<% out.print(nome); %>');">Cancelar</button>
                                    </div></form>
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