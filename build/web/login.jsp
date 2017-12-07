<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Loja DW</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="icon" href="images/favicon.png">

        <script>
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
                if (!r) 
                        alert("Preencha os campos obrigatórios.");
                return r;
            }
            function bgBranco(campo) {
                var c = document.getElementById(campo);
                c.style.backgroundColor = "#fff";
            }
            function mensagem(msg) {
                if (msg !== 'null') {
                    window.location.replace('/Eletrio');
                    alert(msg);
                }
            }
        </script>
    </head>
    <body>
        <div class="conteudo">
            <div id='menu'>
                <a class='loja' href="/Eletrio"><button>Voltar para a loja</button></a>
            </div>
        </div>
        <div id="login">
            <img src="images/logo.png"/>
            <script><%= request.getAttribute("message")%>;</script>
            <form action="Login_User" method="post" onsubmit="return notNull();">
                <input name="nome" type="text" class="nome obg" id="nome" placeholder="Usuario" onclick="bgBranco('nome');">
                <input name="psw" type="password" class="psw obg" id="psw" placeholder="Senha" onclick="bgBranco('psw');">
                <input type="submit" class="submit" id='submit' value="Entrar">
            </form>
        </div>
    </body>
</html>
