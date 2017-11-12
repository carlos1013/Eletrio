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
    
    private String hash(String p){
        String[] pass = p.split("");
        long ant = 5381;
        for(int x=0;x<pass.length;x++){
            if (pass[x].equals('\0')) break;
            ant = (33*ant) + (int)pass[x].charAt(0);
        }
        return Long.toString(ant);
    }
    
    private void inserir(String nome,HttpServletResponse response,HttpServletRequest request){
        String id = request.getParameter("id"),pe;
        switch (nome) {
            case "ADMINISTRADOR":
                pe = "INSERT INTO ADMINISTRADOR (LOGIN,SENHA) VALUES ("+request.getParameter("LOGIN");
                pe+= ","+hash(request.getParameter("SENHA"));
                pe+= ")";
                break;
            case "CATEGORIA":
                pe = "INSERT INTO CATEGORIA (DESCRICAO) VALUES ("+request.getParameter("DESCRICAO")+")";
                break;
            case "CLIENTES":
                pe = "INSERT INTO CLIENTES (NOME,ENDERECO,REFERENCIA,BAIRRO,CIDADE,CEP,ESTADO,CPF,IDENTIDADE,FIXO,CELULAR,CARTAO,BANDEIRA) VALUES ("+request.getParameter("NOME");
                pe += ","+request.getParameter("ENDERECO");
                pe += ","+request.getParameter("REFERENCIA");
                pe += ","+request.getParameter("BAIRRO");
                pe += ","+request.getParameter("CIDADE");
                pe += ","+request.getParameter("CEP");
                pe += ","+request.getParameter("ESTADO");
                pe += ","+request.getParameter("CPF");
                pe += ","+request.getParameter("IDENTIDADE");
                pe += ","+request.getParameter("FIXO");
                pe += ","+request.getParameter("CELULAR");
                pe += ","+request.getParameter("CARTAO");
                pe += ","+request.getParameter("BANDEIRA")+")";
                break;
            case "PRODUTO":
                pe = "INSERT INTO PRODUTO (ID_CATEGORIA,NOME,DESCRICAO,VALOR) VALUES ("+request.getParameter("ID_CATEGORIA");
                pe += ","+request.getParameter("NOME");
                pe += ","+request.getParameter("DESCRICAO");
                pe += ","+request.getParameter("VALOR")+")";
                break;
            default:
                pe = " ";
                break;
        }
        try(PreparedStatement sql = db.prepareStatement(pe)){
            sql.executeUpdate();
        }
        catch(Exception err){
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>DEU MERDA</h3>");
                out.println("<br>");
                out.println("</body>");
                out.println("</html>");
            }
            catch(IOException error){
                
            }
        }
    }
    
    private void editar(String nome,HttpServletResponse response,HttpServletRequest request){
        String id = request.getParameter("id"),pe;
        switch (nome) {
            case "ADMINISTRADOR":
                pe = "UPDATE ADMINISTRADOR SET LOGIN="+ request.getParameter("LOGIN")+",SENHA="+hash(request.getParameter("SENHA"))+ " WHERE ID="+id;
                break;
            case "CATEGORIA":
                pe = "UPDATE CATEGORIA SET DESCRICAO="+ request.getParameter("DESCRICAO")+ " WHERE ID="+id;
                break;
            case "CLIENTES":
                pe = "UPDATE CLIENTES SET NOME="+ request.getParameter("NOME")+",ENDERECO="+request.getParameter("ENDERECO")+",REFERENCIA=" +request.getParameter("REFERENCIA")+",BAIRRO="+request.getParameter("BAIRRO")+",CIDADE="+request.getParameter("CIDADE")+",CEP="+request.getParameter("CEP")+",ESTADO="+request.getParameter("ESTADO")+",CPF="+request.getParameter("CPF")+",IDENTIDADE="+request.getParameter("IDENTIDADE")+",FIXO="+request.getParameter("FIXO")+",CELULAR="+request.getParameter("CELULAR")+",CARTAO="+request.getParameter("CARTAO")+",BANDEIRA="+request.getParameter("BANDEIRA")+" WHERE ID="+id;
                break;
            case "PRODUTO":
                pe = "UPDATE PRODUTO SET ID_CATEGORIA="+ request.getParameter("ID_CATEGORIA")+",NOME="+request.getParameter("NOME")+",DESCRICAO="+request.getParameter("DESCRICAO")+",VALOR="+request.getParameter("VALOR") +" WHERE ID="+id;
                break;
            default:
                pe = " ";
                break;   
        }
        try(PreparedStatement sql = db.prepareStatement(pe)){
            sql.executeUpdate();
        }
        catch(Exception err){
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>DEU MERDA</h3>");
                out.println("<br>");
                out.println("</body>");
                out.println("</html>");
            }
            catch(IOException error){
                
            }
        }
    }
    
    private void remover(String nome,HttpServletResponse response,HttpServletRequest request){
        String t = request.getParameter("id");
        System.out.println(t);
        int id = Integer.parseInt(t);
        try(PreparedStatement sql = db.prepareStatement("DELETE FROM " + nome + " WHERE ID=?")){
            sql.setInt(1,id);
            sql.executeUpdate();
        }
        catch(Exception err){
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>DEU MERDA</h3>");
                out.println("<br>");
                out.println("</body>");
                out.println("</html>");
            }
            catch(IOException error){
                
            }
        }
    }
    
    private void consultar(String nome,HttpServletResponse response,HttpServletRequest request){
        List<String> resp = new ArrayList<>();
        String aux = nome;
        try (PreparedStatement sql = db.prepareStatement("SELECT * FROM "+nome)){
            ResultSet res = sql.executeQuery();
            ResultSetMetaData resMeta = res.getMetaData();
            int qtdCol = resMeta.getColumnCount();
            aux = aux+" "+Integer.toString(qtdCol);
            for (int x=1;x<=qtdCol;x++){
                aux = aux+" "+resMeta.getColumnName(x);
            }
            resp.add(aux);
            while(res.next()){
                aux = res.getString(1);
                for (int x=2;x<=qtdCol;x++){
                    aux = aux+" "+res.getString(x);
                }
                resp.add(aux);
            }
            request.setAttribute("r", resp);
            request.getRequestDispatcher("selecionar.jsp").forward(request,response);
        } 
        catch (Exception ex) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>DEU MERDA</h3>");
                out.println("<br>");
                out.println("</body>");
                out.println("</html>");
            }
            catch(IOException error){
                
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("op"),nomeTabela =  request.getParameter("tabela");
        if (operation.equals("consultar")){
            consultar(nomeTabela,response,request);
        }
        else{
            switch (operation) {
                case "inserir":
                    inserir(nomeTabela,response,request);
                    break;
                case "editar":
                    editar(nomeTabela,response,request);
                    break;
                case "remover":
                    remover(nomeTabela,response,request);
                    break;
                default:
                    break;
            }
        }
    }
}
