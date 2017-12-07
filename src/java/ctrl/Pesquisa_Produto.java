package ctrl;

import DAO.Produto;
import DAO.Produto_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Pesquisa_Produto", urlPatterns = {"/Pesquisa_Produto"})
public class Pesquisa_Produto extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String texto = request.getParameter("text");
        if (texto == null){
            Produto_DAO p = new Produto_DAO();
            request.setAttribute("r",p.consultar());
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        else{
            Produto prod = new Produto(texto);
            Produto_DAO p_dao = new Produto_DAO();
            request.setAttribute("r",p_dao.busca_nome(prod));
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
