package log;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Tabelas", urlPatterns = {"/Tabelas"})
public class Tabelas extends HttpServlet {
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
        List<String> resp = new ArrayList<>();
        String nomeTabela = request.getParameter("tabela"),aux = "";
        String query = "SELECT * FROM "+nomeTabela;
        try (PreparedStatement sql = db.prepareStatement(query)){
            ResultSet res = sql.executeQuery();
            ResultSetMetaData resMeta = res.getMetaData();
            int qtdCol = resMeta.getColumnCount();
            aux = aux+Integer.toString(qtdCol);
            for (int x=1;x<=qtdCol;x++){
                aux = aux+" "+resMeta.getColumnName(x);
            }
            resp.add(aux);
            while(res.next()){
                aux = "";
                for (int x=1;x<=qtdCol;x++){
                    aux = aux+" "+res.getString(x);
                }
                resp.add(aux);
            }
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                for (int x=0;x<resp.size();x++){
                    out.println("<h3>"+ resp.get(x) + "</h3>");
                    out.println("<br>");
                }
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            
        }
    }
}
