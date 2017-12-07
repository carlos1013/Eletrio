<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="icon" href="images/favicon.png">
        <title>Loja DW | Cadastro</title>
        <script>

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
                return (campoNum(form,'CEP',8) && campoNum(form,'CPF',11) && campoNum(form,'IDENTIDADE',9) && campoNum(form,'CARTAO',16) && campoNum(form,'FIXO',10) && campoNum(form,'CELULAR',11) && notNull(form));
            }
            
        </script>
    </head>
    <body>
        <div class="conteudo">
            <div id="topo">
                <img src="images/logo.png">
                <h3>Realize seu cadastro.</h3>
            </div>
            <div id="inserir">
                <form action='Efetuar_Compra' method='post' id='cliente' onsubmit='return validarCliente("cliente");'>
                    <input name='tabela' style='display:none' value='CLIENTES'>
                    <input name='op' style='display:none' value='inserir'>
                    <input name="NOME" id="nome" maxlength="50" class="large obg" type="text" placeholder="Nome:"/><br/>
                    <input name="ENDERECO" id="endereco" maxlength="50" type="text" class="mid left obg" placeholder="Endereço:"  />
                    <input name="REFERENCIA" id="referencia" type="text" class="mid right obg" placeholder="Referência:"referencia');" /><br/>
                    <input name="BAIRRO" id="bairro" maxlength="50" type="text" class="mid left obg" placeholder="Bairro:"/>
                    <input name="CIDADE" id="cidade" maxlength="50" type="text" class="mid right obg" placeholder="Cidade:"/><br/>
                    <input name="CEP" id="cep" maxlength="8" type="text" class="CEP mid obg" placeholder="CEP:"/>
                    <span>Estado:</span>
                    <select name="ESTADO"><option value="AC">AC</option>
                        <option value="AL">AL</option>
                        <option value="AP">AP</option>
                        <option value="AM">AM</option>
                        <option value="BA">BA</option>
                        <option value="CE">CE</option>
                        <option value="DF">DF</option>
                        <option value="ES">ES</option>
                        <option value="GO">GO</option>
                        <option value="MA">MA</option>
                        <option value="MT">MT</option>
                        <option value="MS">MS</option>
                        <option value="MG">MG</option>
                        <option value="PA">PA</option>
                        <option value="PB">PB</option>
                        <option value="PR">PR</option>
                        <option value="PE">PE</option>
                        <option value="PI">PI</option>
                        <option value="RJ">RJ</option>
                        <option value="RN">RN</option>
                        <option value="RS">RS</option>
                        <option value="RO">RO</option>
                        <option value="RR">RR</option>
                        <option value="SC">SC</option>
                        <option value="SP">SP</option>
                        <option value="SE">SE</option>
                        <option value="TO">TO</option>
                    </select><br/>
                    <input name="CPF" id="cpf" maxlength="11" type="text" class="CPF mid left obg" placeholder="CPF:"/>
                    <input name="IDENTIDADE" id="ri" maxlength="9" type="text" class="IDENTIDADE mid right obg" placeholder="RI (identidade):" /><br/>
                    <input name="FIXO" id="tel" maxlength="10" type="text" class="FIXO mid left obg" placeholder="Telefone fixo:" />
                    <input name="CELULAR" id="cel" maxlength="11" type="text" class="CELULAR mid right obg" placeholder="Celular:" /><br/> 
                    <input name="CARTAO" id="numCartao" maxlength="16" type="text" class="CARTAO large obg" placeholder="Número do cartão de crédito:" /><br/>
                    <span>Bandeira do cartão:</span>
                    <input name="BANDEIRA" type="radio" value="MasterCard" checked="checked"/><img class="bandeira" src="images/master.png"/>
                    <input name="BANDEIRA" value="Visa"  type="radio"/><img class="bandeira" src="images/visa.png"/>
                    <input name="BANDEIRA" value="American"  type="radio"/><img class="bandeira" src="images/ae.png"/>
                    <input name="BANDEIRA" value="Elo"  type="radio"/><img class="bandeira" src="images/elo.png"/><br/>
                    <button type="submit">Enviar</button>
                    <a href='index.jsp' class='cancelar'><button type="button">Cancelar</button></a>
                </form>
            </div>
        </div>
    </body>
</html>
