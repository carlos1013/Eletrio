package DAO;

public class Clientes {
    private int id;
    private String nome;
    private String endereco;
    private String referencia;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;
    private String cpf;
    private String identidade;
    private String fixo;
    private String celular;
    private String cartao;
    private String bandeira;

    public Clientes(String nome, String endereco, String referencia, String bairro, String cidade, String cep, String estado, String cpf, String identidade, String fixo, String celular, String cartao, String bandeira) {
        this.nome = nome;
        this.endereco = endereco;
        this.referencia = referencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.cpf = cpf;
        this.identidade = identidade;
        this.fixo = fixo;
        this.celular = celular;
        this.cartao = cartao;
        this.bandeira = bandeira;
    }

    public Clientes(int id) {
        this.id = id;
    }
    
    public Clientes(String cpf) {
        this.cpf = cpf;
    }

    public Clientes(int id, String nome, String endereco, String referencia, String bairro, String cidade, String cep, String estado, String cpf, String identidade, String fixo, String celular, String cartao, String bandeira) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.referencia = referencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.cpf = cpf;
        this.identidade = identidade;
        this.fixo = fixo;
        this.celular = celular;
        this.cartao = cartao;
        this.bandeira = bandeira;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getCpf() {
        return cpf;
    }

    public String getIdentidade() {
        return identidade;
    }

    public String getFixo() {
        return fixo;
    }

    public String getCelular() {
        return celular;
    }

    public String getCartao() {
        return cartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    
    
}
