package model;

/**
 *
 * @author ALEFF
 */
public class Funcionario {

    private int id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String funcao;

    public Funcionario() {
    }

    public Funcionario(int id) {
        this.id = id;
    }

    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public Funcionario(String nome, String sobrenome, String sexo, String funcao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.funcao = funcao;
    }

    //CONSTRUTOR PARA BUSCAR NO BANCO DE DADOS!
    public Funcionario(int id, String nome, String sobrenome, String sexo, String funcao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.funcao = funcao;
    }

    public Funcionario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

//    public Funcionario(String nome, String sobrenome, String sexo, LocalDate dataNascimento) {
//        super(nome, sobrenome, sexo, dataNascimento);
//    }
//
//    public Funcionario(int id, String funcao, String nome, String sobrenome, String sexo) {
//        super(nome, sobrenome, sexo);
//        this.id = id;
//        this.funcao = funcao;
//    }
//
//    public Funcionario(String funcao, String nome, String sobrenome, String sexo) {
//        super(nome, sobrenome, sexo);
//        this.funcao = funcao;
//    }
//
//    public Funcionario(int id, String nome, String sobrenome,  Date dataNascimento,String sexo, String funcao ) {
//        super(nome, sobrenome, sexo, dataNascimento.toLocalDate());
//        this.id = id;
//        this.funcao = funcao;
//    }
//
//    public Funcionario(String funcao, String nome, String sobrenome, String sexo, LocalDate dataNascimento) {
//        super(nome, sobrenome, sexo, dataNascimento);
//        this.funcao = funcao;
//    }
//    
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
