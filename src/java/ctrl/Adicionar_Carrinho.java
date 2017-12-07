package ctrl;
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

@WebServlet(name = "Adicionar_Carrinho", urlPatterns = {"/Adicionar_Carrinho"})
public class Adicionar_Carrinho extends HttpServlet{
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"),nome = request.getParameter("nome");
        if ((id == null) || (nome==null) || (id.equals("")) || (id.equals(""))){
            request.getRequestDispatcher("carrinho.jsp").forward(request,response);
        }
        Cookie c = new Cookie(id,nome);
        response.addCookie(c);
        request.setAttribute("id",null);
        request.setAttribute("nome",null);
        request.getRequestDispatcher("carrinho.jsp").forward(request,response);
    }
}