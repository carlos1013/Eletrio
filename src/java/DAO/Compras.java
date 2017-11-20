package DAO;

public class Compras {
    private int id;
    private int id_cliente;
    private int id_produto;

    public Compras(int id_cliente, int id_produto) {
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
    }

    public Compras(int id) {
        this.id = id;
    }

    public Compras(int id, int id_cliente, int id_produto) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_produto = id_produto;
    }

    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
    
}
