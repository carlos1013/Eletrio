/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String nomeTabela = request.getParameter("tabela");
        String query = "SELECT * FROM "+nomeTabela;
        try (PreparedStatement sql = db.prepareStatement(query)){
            ResultSet res = sql.executeQuery();
            if (res.next()){
                request.setAttribute("resp", res);
                request.getRequestDispatcher("crud.jsp").forward(request, response);
            }
            else{
                request.setAttribute("resp", "Erro.");
                request.getRequestDispatcher("crud.jsp").forward(request,response);  
            }
        } catch (Exception ex) {
            
        }
    }
}
