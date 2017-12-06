package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class Administrador_DAO {
    
    private long hash(String p){
        String[] pass = p.split("");
        long ant = 5381;
        for(int x=0;x<pass.length;x++){
            if (pass[x].equals('\0')) break;
            ant = (33*ant) + (int)pass[x].charAt(0);
        }
        return ant;
    }
    
    public void inserir(Administrador admin) throws Exception {
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("INSERT INTO ADMINISTRADOR (LOGIN,SENHA) VALUES (?,?)")){
            sql.setString(1,admin.getLogin());
            sql.setLong(2,hash(admin.getSenha()));
            sql.executeUpdate();
        }
        
        catch(Exception err){
            throw new RuntimeException();
        }
        
        finally {
            conexao.encerarConexao();
        }
    }
    
    public void editar(Administrador admin) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("UPDATE ADMINISTRADOR SET LOGIN=?,SENHA=? WHERE ID=?")){
            sql.setString(1,admin.getLogin());
            sql.setLong(2,hash(admin.getSenha()));
            sql.setInt(3,admin.getId());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        finally{
            conexao.encerarConexao();
        }
    }
    
    public void remover(Administrador admin) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("DELETE FROM ADMINISTRADOR WHERE ID=?")){
            sql.setInt(1,admin.getId());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }
    }
    
    public List<String> consultar(){
        Adapt_Conexao conexao = new Adapt_Conexao();
        List<String> resp = new ArrayList<>();
        String aux = "ADMINISTRADOR";
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT * FROM ADMINISTRADOR")){
            ResultSet res = sql.executeQuery();
            ResultSetMetaData resMeta = res.getMetaData();
            int qtdCol = resMeta.getColumnCount();
            aux = aux+"¨"+Integer.toString(qtdCol);
            for (int x=1;x<=qtdCol;x++){
                aux = aux+"¨"+resMeta.getColumnName(x);
            }
            resp.add(aux);
            while(res.next()){
                aux = res.getString(1);
                for (int x=2;x<=qtdCol;x++){
                    aux = aux+"¨"+res.getString(x);
                }
                resp.add(aux);
            }
            return resp;
        } 
        catch (Exception ex) {
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }
    }
    
    public boolean verifica_existencia(Administrador admin){
        Adapt_Conexao conexao = new Adapt_Conexao();
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT * FROM ADMINISTRADOR WHERE LOGIN=? AND SENHA=?")){
            sql.setString(1,admin.getLogin());
            sql.setLong(2,hash(admin.getSenha()));
            ResultSet res = sql.executeQuery();
            return res.next();
        } 
        catch (Exception ex) {
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }
    }
}
