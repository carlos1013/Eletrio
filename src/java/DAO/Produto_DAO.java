package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Produto_DAO {
    
    public void inserir(Produto prod) throws Exception {
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("INSERT INTO PRODUTO (ID_CATEGORIA,NOME,DESCRICAO,VALOR) VALUES (?,?,?,?)")){
            sql.setInt(1,prod.getId_categoria());
            sql.setString(2,prod.getNome());
            sql.setString(3,prod.getDescricao());
            sql.setFloat(4,prod.getPreco());
            sql.executeUpdate();
        }
        
        catch(Exception err){
            throw new RuntimeException();
        }
        
        finally {
            conexao.encerarConexao();
        }
    }
    
    public void editar(Produto prod) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("UPDATE PRODUTO SET ID_CATEGORIA=?,NOME=?,DESCRICAO=?,VALOR=? WHERE ID=?")){
            sql.setInt(1,prod.getId_categoria());
            sql.setString(2,prod.getNome());
            sql.setString(3,prod.getDescricao());
            sql.setFloat(4,prod.getPreco());
            sql.setInt(5,prod.getId());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        finally{
            conexao.encerarConexao();
        }
    }
    
    public void remover(Produto prod) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("DELETE FROM PRODUTO WHERE ID=?")){
            sql.setInt(1,prod.getId());
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
        String aux = "PRODUTO";
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT * FROM PRODUTO")){
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
        } 
        catch (Exception ex) {
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }
        return resp;
    }
    
    public List<String> busca_tipo(){
        Adapt_Conexao conexao = new Adapt_Conexao();
        List<String> resp = new ArrayList<>();
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT NOME,DESCRICAO,VALOR FROM PRODUTO WHERE ID_CATEGORIA="+"1")){
            ResultSet res = sql.executeQuery();
            String aux;
            while(res.next()){
                aux = res.getString(1);
                for (int x=3;x<=5;x++){
                    aux = aux+"¨"+res.getString(x);
                }
                resp.add(aux);
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }
        return null;
    }
    
    public List<String> busca_nome(Produto prod){
        Adapt_Conexao conexao = new Adapt_Conexao();
        List<String> resp = new ArrayList<>();
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT ID,NOME,DESCRICAO,VALOR FROM PRODUTO WHERE NOME LIKE '%"+prod.getNome()+"%'")){
            ResultSet res = sql.executeQuery();
            String aux;
            while(res.next()){
                aux = res.getString(1);
                for (int x=3;x<=5;x++){
                    aux = aux+"¨"+res.getString(x);
                }
                resp.add(aux);
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }
        return null;
    }
    
}
