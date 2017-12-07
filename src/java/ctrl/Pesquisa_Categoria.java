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

@WebServlet(name = "Pesquisa_Categoria", urlPatterns = {"/Pesquisa_Categoria"})
public class Pesquisa_Categoria extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id_cat = Integer.parseInt(request.getParameter("item"));
            if ((id_cat<1) || (id_cat>4)){
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            Produto prod = new Produto(id_cat);
            Produto_DAO p_dao = new Produto_DAO();
            request.setAttribute("r",p_dao.busca_tipo(prod));
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        catch(Exception err){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}