package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Clientes_DAO {
    
    public void inserir(Clientes clnt) throws Exception {
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("INSERT INTO CLIENTES (NOME,ENDERECO,REFERENCIA,BAIRRO,CIDADE,CEP,ESTADO,CPF,IDENTIDADE,FIXO,CELULAR,CARTAO,BANDEIRA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)")){
            sql.setString(1,clnt.getNome());
            sql.setString(2,clnt.getEndereco());
            sql.setString(3,clnt.getReferencia());
            sql.setString(4,clnt.getBairro());
            sql.setString(5,clnt.getCidade());
            sql.setString(6,clnt.getCep());
            sql.setString(7,clnt.getEstado());
            sql.setString(8,clnt.getCpf());
            sql.setString(9,clnt.getIdentidade());
            sql.setString(10,clnt.getFixo());
            sql.setString(11,clnt.getCelular());
            sql.setString(12,clnt.getCartao());
            sql.setString(13,clnt.getBandeira());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        
        finally {
            conexao.encerarConexao();
        }
    }
    
    public void editar(Clientes clnt) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("UPDATE CLIENTES SET NOME=?,ENDERECO=?,REFERENCIA=?,BAIRRO=?,CIDADE=?,CEP=?,ESTADO=?,CPF=?,IDENTIDADE=?,FIXO=?,CELULAR=?,CARTAO=?,BANDEIRA=? WHERE ID=?")){
            sql.setString(1,clnt.getNome());
            sql.setString(2,clnt.getEndereco());
            sql.setString(3,clnt.getReferencia());
            sql.setString(4,clnt.getBairro());
            sql.setString(5,clnt.getCidade());
            sql.setString(6,clnt.getCep());
            sql.setString(7,clnt.getEstado());
            sql.setString(8,clnt.getCpf());
            sql.setString(9,clnt.getIdentidade());
            sql.setString(10,clnt.getFixo());
            sql.setString(11,clnt.getCelular());
            sql.setString(12,clnt.getCartao());
            sql.setString(13,clnt.getBandeira());
            sql.setInt(14,clnt.getId());
            sql.executeUpdate();
        }
        catch(Exception err){
            throw new RuntimeException();
        }
        finally{
            conexao.encerarConexao();
        }
    }
    
    public void remover(Clientes clnt) throws Exception{
        Adapt_Conexao conexao = new Adapt_Conexao();
        try(PreparedStatement sql = conexao.conectar().prepareStatement("DELETE FROM CLIENTES WHERE ID=?")){
            sql.setInt(1,clnt.getId());
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
        String aux = "CLIENTES";
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT * FROM CLIENTES")){
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
    
    public int busca_id (Clientes clnt){
        Adapt_Conexao conexao = new Adapt_Conexao();
        try (PreparedStatement sql = conexao.conectar().prepareStatement("SELECT ID FROM CLIENTES WHERE CPF="+clnt.getCpf())){
            ResultSet res = sql.executeQuery();
            int aux = -1;
            while(res.next()){
                aux = Integer.parseInt(res.getString(1));
            }
            return aux;
        }
        catch (Exception ex) {
            throw new RuntimeException();
        }
        finally {
            conexao.encerarConexao();
        }   
    }
}
