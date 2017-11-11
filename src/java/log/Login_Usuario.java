package log;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="Login_Usuario", urlPatterns = {"/Login_Usuario"})
public class Login_Usuario extends HttpServlet {
    Connection db = null;
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            db = DriverManager.getConnection("jdbc:derby://localhost:1527/Eletrio","usuario","senha");
        } 
        catch (Exception ex) {
            //asdnfoas
        }
    }
    
    @Override
    public void destroy() {
        try {
            db.close();
        }
        catch (Exception ex) {
            //lmagfpm
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PreparedStatement sql = db.prepareStatement("SELECT * FROM ADMINISTRADOR WHERE LOGIN=? AND SENHA=?")){
            String us = request.getParameter("nome");
            sql.setString(1,request.getParameter("nome"));
            sql.setLong(2,hash(request.getParameter("psw")));
            ResultSet res = sql.executeQuery();
            if (res.next()){
                HttpSession session = request.getSession();
                session.setAttribute("usuario",us);
                session.setAttribute("logado","true");
                RequestDispatcher resp = request.getRequestDispatcher("selecionar.jsp");
                resp.forward(request, response);
            }
            else{
                request.setAttribute("message", "mensagem('Usuário ou senha inválido.');");
                request.getRequestDispatcher("index.jsp").forward(request,response);  
            }
        } catch (Exception ex) {
            
        }
    }
    
    private long hash(String p){
        String[] pass = p.split("");
        long ant = 5381;
        for(int x=0;x<pass.length;x++){
            if (pass[x].equals('\0')) break;
            ant = (33*ant) + (int)pass[x].charAt(0);
        }
        return ant;
    }
}
