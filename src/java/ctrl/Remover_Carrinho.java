package ctrl;
import DAO.Produto;
import DAO.Produto_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Remover_Carrinho", urlPatterns = {"/Remover_Carrinho"})
public class Remover_Carrinho extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Cookie[] list = request.getCookies();
        Cookie c;
        if(list!=null){
            for (int i = 0; i < list.length; i++) {
               c = list[i];
               if (c.getName().equals(id)){
                   c.setMaxAge(0);
                   response.addCookie(c);
               }
            }
        }
        request.setAttribute("msg", "mensagem('Produto removido com sucesso.');");
        request.getRequestDispatcher("carrinho.jsp").forward(request,response);
    }
}
