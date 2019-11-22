/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteOutrasExtensoes;

/**
 *
 * @author Thiago Henrique Santos
 */
public class Paciente 
{
    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String uf;
    private String endereco;
    private String cidade;
    private String telefone;
    private String telefone2;
    private String email;
    
    public Paciente(Long _id, String _nome, String _cpf, String _dataNascimento, String _uf, String _endereco, String _cidade, String _telefone, String _telefone2, String _email) {
        this.id = _id;
        this.nome = _nome;
        this.cpf = _cpf;
        this.dataNascimento = _dataNascimento;
        this.uf = _uf;
        this.endereco = _endereco;
        this.cidade = _cidade;
        this.telefone = _telefone;
        this.telefone2 = _telefone2;
        this.email = _email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}