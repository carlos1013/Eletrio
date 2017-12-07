package ctrl;

import DAO.Clientes;
import DAO.Clientes_DAO;
import DAO.Produto;
import DAO.Produto_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Efetuar_Compra", urlPatterns = {"/Efetuar_Compra"})
public class Efetuar_Compra extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //(new Clientes_DAO()).inserir((new Clientes(request.getParameter("NOME"),request.getParameter("ENDERECO"),request.getParameter("REFERENCIA"),request.getParameter("BAIRRO"),request.getParameter("CIDADE"),request.getParameter("CEP"),request.getParameter("ESTADO"),request.getParameter("CPF"),request.getParameter("IDENTIDADE"),request.getParameter("FIXO"),request.getParameter("CELULAR"),request.getParameter("CARTAO"),request.getParameter("BANDEIRA"))));
                    
    }
    
    //private Produto[] d(){
        
    //}
}

