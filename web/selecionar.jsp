<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="icon" href="images/favicon.png">

        <title>Loja DW | Admin</title>
        <script>
            function operacao(ID, operacao, linha, colunas) {
                var id = document.getElementById('id');
                var op = document.getElementById('op');
                id.setAttribute('value', ID);
                op.setAttribute('value', operacao);
                var e = document.getElementsByClassName("editando")[0];
                if (e) {
                    e.setAttribute("class","");
                    cancelarEdicao(e.getAttribute("id"),colunas,e.getAttribute("idRow"));
                }
                if(!operacao.localeCompare('editar')) {
                    formularioEdicao(linha, colunas, ID);
                }
            }
            
            function formularioEdicao(linha, colunas, id) {
                var tds = document.getElementsByClassName(linha);
                var c = colunas.split("¨");
                var i;
                for (i=0; i<tds.length; i++) {
                    if(!c[i+3].localeCompare('ESTADO'))
                        tds[i].innerHTML = estados();
                    else
                        tds[i].innerHTML = "<input name='"+c[i+3]+"' value='"+tds[i].innerHTML+"' class='"+c[i+3]+" obg'>";
                }
                
                if(!c[0].localeCompare('CLIENTES')) {
                    limit('CEP',8);
                    limit('CPF',11);
                    limit('IDENTIDADE',9);
                    limit('CARTAO',16);
                    limit('FIXO',10);
                    limit('CELULAR',11);
                }
                
                var l = document.getElementById(linha);
                l.setAttribute("class","editando");
                l.getElementsByClassName('btns')[0].innerHTML = "<button id='i' type='submit'>Enviar</button> <button type='button' onclick='cancelarEdicao(\""+linha+"\",\""+colunas+"\",\""+id+"\")'>Cancelar</button>";
            }
            
            function estados() {
                return("<select name='ESTADO'>"+
                            "<option value='AC'>AC</option>"+
                            "<option value='AL'>AL</option>"+
                            "<option value='AP'>AP</option>"+
                            "<option value='AM'>AM</option>"+
                            "<option value='BA'>BA</option>"+
                            "<option value='CE'>CE</option>"+
                            "<option value='DF'>DF</option>"+
                            "<option value='ES'>ES</option>"+
                            "<option value='GO'>GO</option>"+
                            "<option value='MA'>MA</option>"+
                            "<option value='MT'>MT</option>"+
                            "<option value='MS'>MS</option>"+
                            "<option value='MG'>MG</option>"+
                            "<option value='PA'>PA</option>"+
                            "<option value='PB'>PB</option>"+
                            "<option value='PR'>PR</option>"+
                            "<option value='PE'>PE</option>"+
                            "<option value='PI'>PI</option>"+
                            "<option value='RJ'>RJ</option>"+
                            "<option value='RN'>RN</option>"+
                            "<option value='RS'>RS</option>"+
                            "<option value='RO'>RO</option>"+
                            "<option value='RR'>RR</option>"+
                            "<option value='SC'>SC</option>"+
                            "<option value='SP'>SP</option>"+
                            "<option value='SE'>SE</option>"+
                            "<option value='TO'>TO</option>"+
                        "</select>");
            }
            
            function cancelarEdicao(linha, colunas, id) {
                var tds = document.getElementsByClassName(linha);
                var i;
                for (i=0; i<tds.length; i++) 
                    tds[i].innerHTML = tds[i].getElementsByTagName('input')[0].defaultValue;
                
                var l = document.getElementById(linha);
                l.setAttribute("class","");
                l.getElementsByClassName('btns')[0].innerHTML = "<button id='e' type='button' onclick='document.getElementById(\"er\").setAttribute(\"action\", \"Editar\");operacao(\""+id+"\", \"editar\", \""+linha+"\",\""+colunas+"\")'>Editar</button>";
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
            
            function limit(name, n) {
                var c = document.getElementsByClassName(name);
                var i;
                for (i=0; i<c.length; i++)
                    c[i].setAttribute("maxlength", n);
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

            function campoNum(form, campo, num) {
                var c = document.getElementById(form).getElementsByClassName(campo)[0];
                if(c) {
                    var exp = /[^0-9]/g;
                    if (c.value.length < num || exp.test(c.value)) {
                            c.style.backgroundColor = "#f7dcdc";
                            alert("Preencha o campo "+campo+" corretamente.");
                            return false;
                    }
                }
                return true;
            }
            
            function validarCliente(form) {
                return (campoNum(form,'CEP',8) && campoNum(form,'CPF',11) && campoNum(form,'IDENTIDADE',9) && campoNum(form,'CARTAO',16) && campoNum(form,'FIXO',10) && campoNum(form,'CELULAR',11));
            }
            
            function validarValor(form) {
                var v = document.getElementById(form).getElementsByClassName("VALOR")[0];
                if (v) {
                    var exp = /^\d+(\.\d{2})?$/g;
                    var valido = exp.test(v.value);
                    if (!valido) {
                        v.style.backgroundColor = "#f7dcdc";
                        alert("Preencha o campo VALOR corretamente.");
                        return false;
                    }
                }
                return true;
            }
            
            function validarCategoria(form) {
                var v = document.getElementById(form).getElementsByClassName("ID_CATEGORIA")[0];
                if(v) {
                    var exp = /[^1-4]/g;
                    if(exp.test(v.value)) {
                        v.style.backgroundColor = "#f7dcdc";
                        alert("Escolha uma categoria válida.");
                        return false;
                    }
                }
                return true;
            }
            
            function validarFormulario(tabela, form) {
                if(!tabela.localeCompare("CLIENTES")) {
                    return (validarCliente(form) && notNull(form));alert('valida');}
                else if (!tabela.localeCompare("PRODUTO"))
                    return (validarValor(form) && validarCategoria(form) && notNull(form));
                else
                    return notNull(form);
            }

        </script>
    </head>
    <body>
        <form id="sair" action='Logout_Usuario' method='post'>
            <input type="hidden" value="sair">
            <button>Sair</button>
        </form>
        <div class="conteudo">
            <div id="topo">
                <img src="images/logo.png">
                <h3>Bem vindo(a) à página de administração!</h3>
            </div>
            <div id="selecionar">
                <span>Selecione a informação desejada:</span>
                <form action="Consultar_Completo" method="post">
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
                        String[] row = colunas.split("¨");
                        String nome = row[0];
                        int qtAtt = Integer.parseInt(row[1]);
                        out.println("<h1>"+nome+"</h1>");
                        out.println("<form id='er' action='' method='post' onsubmit='return validarFormulario(\""+nome+"\", \"ru\");'><div id='ru'>");
                        out.println("<input name='tabela' style='display:none' value='"+nome+"'>");
                        out.println("<input id='id'  name='id' style='display:none' value=''>");
                        out.println("<input id='op' name='op' class='obg' style='display:none' value=''>");
                        out.println("<table><tr>");
                        for (int i = 3; i < qtAtt+2; i++) {
                            out.println("<th>"+row[i]+"</th>");
                        }
                        
                        if(nome.equals("CLIENTES") || nome.equals("PRODUTO") || nome.equals("ADMINISTRADOR"))
                            out.println("<th>Editar</th><th>Remover</th>");
                        else if (nome.equals("COMPRAS"))
                            out.println("<th>Remover</th>");
                        
                        out.println("</tr>");
                        
                        for (int i = 1; i < r.size(); i++) {
                            row = r.get(i).split("¨");
                            out.println("<tr id='linha"+i+"' idRow='"+row[0]+"'>");

                            for (int j = 1; j < qtAtt; j++) {
                                out.println("<td class='linha"+i+"'>"+row[j]+"</td>");
                            }                            
                            if(nome.equals("CLIENTES") || nome.equals("PRODUTO") || nome.equals("ADMINISTRADOR")) {
                                out.print("<td class='btns'><button id='e' type='button' onclick='document.getElementById(\"er\").setAttribute(\"action\", \"Editar\");operacao(\""+row[0]+"\", \"editar\",\"linha"+i+"\", \""+colunas+"\")'>Editar</button></td>");
                                out.print("<td><button id='r' type='submit' onclick='document.getElementById(\"er\").setAttribute(\"action\", \"Remover\");operacao(\""+row[0]+"\", \"remover\",\"linha"+i+"\", \""+colunas+"\")'>Remover</button></td>");
                            }
                            else if (nome.equals("COMPRAS"))
                                out.print("<td><button id='r' type='submit' onclick='document.getElementById(\"er\").setAttribute(\"action\", \"Remover\");operacao(\""+row[0]+"\", \"remover\",\"linha"+i+"\", \""+colunas+"\")'>Remover</button></td>");

                            out.println("</tr>");
                        }//for
                        
                        out.println("</table></div></form>");
                        if(nome.equals("CLIENTES")) {
                        %>
                            <button id='i' type='button' onClick='mostrarFormulario("inserir")'>Inserir</button>
                            <div id="inserir" style="display:none">
                                <form action='Inserir' method='post' onsubmit='return validarFormulario("CLIENTES", "inserir");'>
                                    <input name='tabela' style='display:none' value='CLIENTES'>
                                    <input name='op' style='display:none' value='inserir'>
                                    <input name="NOME" id="nome" maxlength="50" class="large obg" type="text" placeholder="Nome:"/><br/>
                                    <input name="ENDERECO" id="endereco" maxlength="50" type="text" class="mid left obg" placeholder="Endereço:"  />
                                    <input name="REFERENCIA" id="referencia" type="text" class="mid right obg" placeholder="Referência:"referencia');" /><br/>
                                    <input name="BAIRRO" id="bairro" maxlength="50" type="text" class="mid left obg" placeholder="Bairro:"/>
                                    <input name="CIDADE" id="cidade" maxlength="50" type="text" class="mid right obg" placeholder="Cidade:"/><br/>
                                    <input name="CEP" id="cep" maxlength="8" type="text" class="CEP mid obg" placeholder="CEP:"/>
                                    <span>Estado:</span><script>document.write(estados());</script><br/>
                                    <input name="CPF" id="cpf" maxlength="11" type="text" class="CPF mid left obg" placeholder="CPF:"/>
                                    <input name="IDENTIDADE" id="ri" maxlength="9" type="text" class="IDENTIDADE mid right obg" placeholder="RI (identidade):" /><br/>
                                    <input name="FIXO" id="tel" maxlength="10" type="text" class="FIXO mid left obg" placeholder="Telefone fixo:" />
                                    <input name="CELULAR" id="cel" maxlength="11" type="text" class="CELULAR mid right obg" placeholder="Celular:" /><br/> 
                                    <input name="CARTAO" id="numCartao" maxlength="16" type="text" class="CARTAO large obg" placeholder="Número do cartão de crédito:" /><br/>
                                    <span>Bandeira do cartão:</span>
                                    <input name="BANDEIRA" type="radio" value="MasterCard" checked="checked"/><img class="bandeira" src="images/master.png"/>
                                    <input name="BANDEIRA" value="Visa"  type="radio"/><img class="bandeira" src="images/visa.png"/>
                                    <input name="BANDEIRA" value="AmericanExpress"  type="radio"/><img class="bandeira" src="images/ae.png"/>
                                    <input name="BANDEIRA" value="Elo"  type="radio"/><img class="bandeira" src="images/elo.png"/><br/>
                                    <button type="submit">Enviar</button>
                                    <button type="button" onClick="this.form.reset();cancelar('inserir');">Cancelar</button>
                                </form>
                            </div>
                        <%
                        }//if CLIENTES
                        else if (nome.equals("PRODUTO")) { 
                        %>
                            <button id='i' type='button' onClick='mostrarFormulario("inserir")'>Inserir</button>
                            <div id="inserir" style="display:none">
                                <form action='Inserir' method='post' onsubmit='return validarFormulario("PRODUTO", "inserir");'>
                                    <input name='tabela' style='display:none' value='PRODUTO'>
                                    <input name='op' style='display:none' value='inserir'>
                                    <select name="ID_CATEGORIA">
                                        <option value="1" selected>Eletrodomésticos</option>
                                        <option value="2">Informática</option>
                                        <option value="3">Eletroportáteis</option>
                                        <option value="4">Smartphones</option>
                                    </select>
                                    <input name="NOME" type="text" id="nome" class='obg' placeholder="Nome" >
                                    <input name="DESCRICAO" type="text" id="descricao" class='obg' placeholder="Descrição" >
                                    <input name="VALOR" type="text" id="valor" class='VALOR obg' placeholder="Valor"  >
                                    <button type="submit">Enviar</button>
                                    <button type="button" onClick="this.form.reset();cancelar('inserir');">Cancelar</button>
                                </form>
                            </div>
                        <% 
                        }  //if PRODUTO
                        else if (nome.equals("ADMINISTRADOR")) {
                        %>
                            <button id='i' type='button' onClick='mostrarFormulario("inserir")'>Inserir</button>
                            <div id="inserir" style="display:none">
                                <form action='Inserir' method='post' onsubmit='return validarFormulario("inserir", "inserir");'>
                                    <input name='tabela' style='display:none' value='ADMINISTRADOR'>
                                    <input name='op' style='display:none' value='inserir'>
                                    <input name="LOGIN" type="text" id="nome" class='obg' placeholder="Nome">
                                    <input name="SENHA" type="password" id="senha" class='SENHA obg' placeholder="Senha">
                                    <button type="submit">Enviar</button>
                                    <button type="button" onClick="this.form.reset();cancelar('inserir');">Cancelar</button>
                                </form>
                            </div>
                        <%
                        }//if ADMINISTRADOR
                    } //try
                    catch(Exception e) {}
                %>
            </div>
        </div>
            
    </body>
</html>