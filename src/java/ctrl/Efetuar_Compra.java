package ctrl;

import DAO.Clientes;
import DAO.Clientes_DAO;
import DAO.Compras;
import DAO.Compras_DAO;
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
        try{
            String cpf = request.getParameter("CPF");
            Clientes_DAO c_dao = new Clientes_DAO();
            c_dao.inserir((new Clientes(request.getParameter("NOME"),request.getParameter("ENDERECO"),request.getParameter("REFERENCIA"),request.getParameter("BAIRRO"),request.getParameter("CIDADE"),request.getParameter("CEP"),request.getParameter("ESTADO"),cpf,request.getParameter("IDENTIDADE"),request.getParameter("FIXO"),request.getParameter("CELULAR"),request.getParameter("CARTAO"),request.getParameter("BANDEIRA"))));
            int id_cliente = c_dao.busca_id(new Clientes(cpf));
            int[] a_prod = cookie_to_prod(request,response);
            Compras_DAO comp_dao = new Compras_DAO();
            for (int x=0;x<a_prod.length;x++){
                comp_dao.inserir(new Compras(id_cliente,a_prod[x]));
            }
            request.getRequestDispatcher("sucesso.jsp").forward(request,response);
        }
        catch (Exception ex) {
            request.getRequestDispatcher("erroBD.jsp").forward(request,response);
        }     
    }
    
    private int[] cookie_to_prod(HttpServletRequest request, HttpServletResponse response){
        Cookie[] list = request.getCookies();
        int[] a_prod = new int[list.length-1];
        for (int x=1;x<list.length;x++){
            a_prod[x-1] = Integer.parseInt(list[x].getName());
            Cookie c = list[x];
            c.setMaxAge(0);
            response.addCookie(c);
        }
        return a_prod;
    }
}