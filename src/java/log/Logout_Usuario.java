package log;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout_Usuario", urlPatterns = {"/Logout_Usuario"})
public class Logout_Usuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        Object logado = sessao.getAttribute("logado");
        if (logado != null) {
            String aux = (String) logado;
            if (logado.equals("true")) {
                sessao.setAttribute("usuario",null);
                sessao.setAttribute("logado","false");
            } 
        } 
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
