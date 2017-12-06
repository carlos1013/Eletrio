package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Categoria_DAO {
    
    public void inserir(Categoria cat) throws Exception {
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("INSERT INTO CATEGORIA (DESCRICAO) VALUES (?)")){
            sql.setString(1,cat.getDescricao());
            sql.executeUpdate();
        }
        
        catch(Exception err){
            throw new RuntimeException();
        }
        
        finally {
            conexao.encerarConexao();
        }
    }
    
    public void editar(Categoria cat) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("UPDATE CATEGORIA SET DESCRICAO=? WHERE ID=?")){
            sql.setString(1,cat.getDescricao());
            sql.setInt(2,cat.getId());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        finally{
            conexao.encerarConexao();
        }
    }
    
    public void remover(Categoria cat) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("DELETE FROM CATEGORIA WHERE ID=?")){
            sql.setInt(1,cat.getId());
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
        String aux = "CATEGORIA";
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT * FROM CATEGORIA")){
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
}
