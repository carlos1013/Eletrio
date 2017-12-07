package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class Compras_DAO {
    public void inserir(Compras comp) throws Exception {
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("INSERT INTO COMPRAS (ID_CLIENTE,ID_PRODUTO) VALUES (?,?)")){
            sql.setInt(1,comp.getId_cliente());
            sql.setInt(2,comp.getId_produto());
            sql.executeUpdate();
        }
        
        catch(Exception err){
            throw new RuntimeException();
        }
        
        finally {
            conexao.encerarConexao();
        }
    }
    
    public void editar(Compras comp) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("UPDATE COMPRAS SET ID_CLIENTE=?,ID_PRODUTO=? WHERE ID=?")){
            sql.setInt(1,comp.getId_cliente());
            sql.setInt(2,comp.getId_produto());
            sql.setInt(3,comp.getId());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        finally{
            conexao.encerarConexao();
        }
    }
    
    public void remover(Compras comp) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("DELETE FROM COMPRAS WHERE ID=?")){
            sql.setInt(1,comp.getId());
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
        String aux = "COMPRAS";
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT * FROM COMPRAS")){
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
