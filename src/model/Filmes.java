/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author marco
 */
public class Filmes {
    
    private int codigo;
    private String nome;
    private String genero;
    private String sinopse;
    private int quantidade;
    private int quantidadealugado;
    private int quantidadeatual;
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getSinopse(){
        return sinopse;
    }
    
    public void setSinopse(String sinopse){
        this.sinopse = sinopse;
    }
    
    public int getQuantidadealugado(){
        return quantidadealugado;
    }
    
    public void setQuantidadealugado( int quantidadealugado){
        this.quantidadealugado = quantidadealugado;
    }

    public int getQuantidadeatual() {
        return quantidadeatual;
    }

    public void setQuantidadeatual(int quantidadeatual) {
        this.quantidadeatual = quantidadeatual;
    }
    
    
   

    
    
    
    
    
    
}
