package DAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class Adapt_Conexao {
    private Connection adapt;
    
    public Adapt_Conexao() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            adapt = DriverManager.getConnection("jdbc:derby://localhost:1527/Eletrio","usuario","senha");
        } 
        catch (Exception err) {
            throw new RuntimeException("Erro ao conectar com o BD");
        }
    }
    
    public Connection conectar(){
        return this.adapt;
    }
   
    public void encerarConexao(){
        try {
            this.adapt.close();
        } 
        catch (Exception err) {
            throw new RuntimeException("Erro ao tentar finalizar a conexao com o BD");
        }
    }
}
