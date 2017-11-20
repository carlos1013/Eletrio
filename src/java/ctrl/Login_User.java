package ctrl;

import DAO.Administrador;
import DAO.Administrador_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Login_User", urlPatterns = {"/Login_User"})
public class Login_User extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String user =  request.getParameter("nome");
            if ((new Administrador_DAO()).verifica_existencia((new Administrador(user,request.getParameter("psw"))))){
                HttpSession session = request.getSession();
                session.setAttribute("usuario",user);
                session.setAttribute("logado","true");
                request.getRequestDispatcher("selecionar.jsp").forward(request, response);
            }
            else{
                request.setAttribute("message", "mensagem('Usuário ou senha inválido.');");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }
        catch(IOException | ServletException err){
            
        }
    }
}
