package DAO;


public class Categoria {
    private int id;
    private String descricao;
    
    public Categoria(String descricao){
        this.descricao = descricao;
    }
    
    public Categoria(int id,String descricao){
        this.descricao = descricao;
        this.id = id;
    }
    
    public Categoria(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
