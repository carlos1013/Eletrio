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

@WebServlet(name = "Retorna_Produtos", urlPatterns = {"/Retorna_Produtos"})
public class Retorna_Produtos extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] list = request.getCookies();
        Cookie c;
        List<String> resp = new ArrayList<>();
        System.out.println(list.length);
        if(list!=null){
            for (int i = 0; i < list.length-1; i++) {
               c = list[i];
               Produto p = (new Produto_DAO()).busca_produto((new Produto(Integer.parseInt(c.getName()))));
               String aux = Integer.toString(p.getId_categoria())+"¨";
               aux = aux + p.getNome()+"¨"+p.getDescricao()+"¨"+Float.toString(p.getPreco());
               resp.add(aux);
            }
        }
        System.out.println(resp);
        request.setAttribute("r",resp);
        request.getRequestDispatcher("carrinho.jsp").forward(request,response);
    }
}
