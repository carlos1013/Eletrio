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
    
    private void erro(HttpServletResponse response){
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
    
    private long hash(String p){
        String[] pass = p.split("");
        long ant = 5381;
        for(int x=0;x<pass.length;x++){
            if (pass[x].equals('\0')) break;
            ant = (33*ant) + (int)pass[x].charAt(0);
        }
        return ant;
    }
    
    private void inserir(String nome,HttpServletResponse response,HttpServletRequest request){

        switch (nome) {
            case "ADMINISTRADOR":
                try(PreparedStatement sql = db.prepareStatement("INSERT INTO ADMINISTRADOR (LOGIN,SENHA) VALUES (?,?)")){
                    sql.setString(1,request.getParameter("LOGIN"));
                    sql.setLong(2,hash(request.getParameter("SENHA")));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            case "CATEGORIA":
                try(PreparedStatement sql = db.prepareStatement("INSERT INTO CATEGORIA (DESCRICAO) VALUES (?)")){
                    sql.setString(1,request.getParameter("DESCRICAO"));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            case "CLIENTES":
                try(PreparedStatement sql = db.prepareStatement("INSERT INTO CLIENTES (NOME,ENDERECO,REFERENCIA,BAIRRO,CIDADE,CEP,ESTADO,CPF,IDENTIDADE,FIXO,CELULAR,CARTAO,BANDEIRA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)")){
                    sql.setString(1,request.getParameter("NOME"));
                    sql.setString(2,request.getParameter("ENDERECO"));
                    sql.setString(3,request.getParameter("REFERENCIA"));
                    sql.setString(4,request.getParameter("BAIRRO"));
                    sql.setString(5,request.getParameter("CIDADE"));
                    sql.setString(6,request.getParameter("CEP"));
                    sql.setString(7,request.getParameter("ESTADO"));
                    sql.setString(8,request.getParameter("CPF"));
                    sql.setString(9,request.getParameter("IDENTIDADE"));
                    sql.setString(10,request.getParameter("FIXO"));
                    sql.setString(11,request.getParameter("CELULAR"));
                    sql.setString(12,request.getParameter("CARTAO"));
                    sql.setString(13,request.getParameter("BANDEIRA"));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            case "PRODUTO":
                try(PreparedStatement sql = db.prepareStatement("INSERT INTO PRODUTO (ID_CATEGORIA,NOME,DESCRICAO,VALOR) VALUES (?,?,?,?)")){
                    sql.setInt(1,Integer.parseInt(request.getParameter("ID_CATEGORIA")));
                    sql.setString(2,request.getParameter("NOME"));
                    sql.setString(3,request.getParameter("DESCRICAO"));
                    sql.setFloat(4,Float.parseFloat(request.getParameter("VALOR")));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            default:
                erro(response);
                break;
        }
    }
    
    private void editar(String nome,HttpServletResponse response,HttpServletRequest request){
        switch (nome) {
            case "ADMINISTRADOR":
                try(PreparedStatement sql = db.prepareStatement("UPDATE ADMINISTRADOR SET LOGIN=?,SENHA=? WHERE ID=?")){
                    sql.setString(1,request.getParameter("LOGIN"));
                    sql.setLong(2,hash(request.getParameter("SENHA")));
                    sql.setInt(3,Integer.parseInt(request.getParameter("id")));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            case "CATEGORIA":
                try(PreparedStatement sql = db.prepareStatement("UPDATE CATEGORIA SET DESCRICAO=? WHERE ID=?")){
                    sql.setString(1,request.getParameter("DESCRICAO"));
                    sql.setInt(2,Integer.parseInt(request.getParameter("id")));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            case "CLIENTES":
                try(PreparedStatement sql = db.prepareStatement("UPDATE CLIENTES SET NOME=?,ENDERECO=?,REFERENCIA=?,BAIRRO=?,CIDADE=?,CEP=?,ESTADO=?,CPF=?,IDENTIDADE=?,FIXO=?,CELULAR=?,CARTAO=?,BANDEIRA=? WHERE ID=?")){
                    sql.setString(1,request.getParameter("NOME"));
                    sql.setString(2,request.getParameter("ENDERECO"));
                    sql.setString(3,request.getParameter("REFERENCIA"));
                    sql.setString(4,request.getParameter("BAIRRO"));
                    sql.setString(5,request.getParameter("CIDADE"));
                    sql.setString(6,request.getParameter("CEP"));
                    sql.setString(7,request.getParameter("ESTADO"));
                    sql.setString(8,request.getParameter("CPF"));
                    sql.setString(9,request.getParameter("IDENTIDADE"));
                    sql.setString(10,request.getParameter("FIXO"));
                    sql.setString(11,request.getParameter("CELULAR"));
                    sql.setString(12,request.getParameter("CARTAO"));
                    sql.setString(13,request.getParameter("BANDEIRA"));
                    sql.setInt(14,Integer.parseInt(request.getParameter("id")));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            case "PRODUTO":
                try(PreparedStatement sql = db.prepareStatement("UPDATE PRODUTO SET ID_CATEGORIA=?,NOME=?,DESCRICAO=?,VALOR=? WHERE ID=?")){
                    sql.setInt(1,Integer.parseInt(request.getParameter("ID_CATEGORIA")));
                    sql.setString(2,request.getParameter("NOME"));
                    sql.setString(3,request.getParameter("DESCRICAO"));
                    sql.setFloat(4,Float.parseFloat(request.getParameter("VALOR")));                    
                    sql.setInt(5,Integer.parseInt(request.getParameter("id")));
                    sql.executeUpdate();
                }
                catch(Exception err){
                    erro(response);
                }
                break;
            default:
                erro(response);
                break;   
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
            erro(response);
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
            erro(response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("op"),nomeTabela =  request.getParameter("tabela");
        System.out.println(operation);
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
