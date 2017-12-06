package DAO;

public class Produto {
    private int id;
    private int id_categoria;
    private String nome;
    private String descricao;
    private float preco;

    public Produto(int id, int id_categoria, String nome, String descricao, float preco) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(int id_categoria, String nome, String descricao, float preco) {
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(int id) {
        this.id = id;
    }
    
    public Produto(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
}
