/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import model.Filmes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class FilmesDAO {
    
    private Connection conecta;
    
    public FilmesDAO(){
        this.conecta = new DAO().conecta();
    }
    
    public void cadastrar(Filmes filmes){
        
        String sql = "INSERT INTO filmes (codigo, nome, genero, sinopse, quantidade, quantidadealugado, quantidadeatual) " + 
                    "VALUES(?,?,?,?,?,?,?)";
        try{
            
            PreparedStatement stmt = conecta.prepareCall(sql);
            
            stmt.setInt(1,filmes.getCodigo());
            stmt.setString(2, filmes.getNome());
            stmt.setString(3, filmes.getGenero());
            stmt.setString(4, filmes.getSinopse());
            stmt.setInt(5, filmes.getQuantidade());
            stmt.setInt(6, filmes.getQuantidadealugado());
            stmt.setInt(7, filmes.getQuantidadeatual());
            
            stmt.execute();
            stmt.close();
            
        }catch (SQLException e){
            
            throw new RuntimeException(e);
            
        }
        
    }
     
    public List<Filmes> listarTodos(){
        
        String sql = "select * from filmes order by codigo";
        ResultSet resultadoBD;
        List<Filmes> filmes = new ArrayList<Filmes>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultadoBD = stmt.executeQuery();
            while (resultadoBD.next()){
                
                Filmes filme = new Filmes();
                filme.setCodigo(resultadoBD.getInt("codigo"));                
                filme.setNome(resultadoBD.getString("nome"));
                filme.setGenero(resultadoBD.getString("genero"));
                filme.setSinopse(resultadoBD.getString("sinopse"));
                filme.setQuantidade(resultadoBD.getInt("quantidade"));
                filme.setQuantidadeatual(resultadoBD.getInt("quantidadeatual"));
                
                filmes.add(filme);
            }
            resultadoBD.close();
            stmt.close();
            
        }catch (SQLException e){
            System.out.println(e);
        }
        return filmes;
    }
    
    public List<Filmes> pesquisa(Integer texto){
            String where = "where codigo =  ? ORDER BY nome";
        
            String sql = "SELECT * FROM filmes " + where;
            ResultSet resultadoBD;
            List<Filmes> filmes = new ArrayList<Filmes>();
        
            try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, texto);
            resultadoBD = stmt.executeQuery();
            while (resultadoBD.next()) {
                
                Filmes filme = new Filmes();
                filme.setCodigo(resultadoBD.getInt("codigo"));
                filme.setNome(resultadoBD.getString("nome"));
                filme.setGenero(resultadoBD.getString("genero"));
                filme.setQuantidade(resultadoBD.getInt("quantidade"));
                filme.setQuantidadeatual(resultadoBD.getInt("quantidadeatual"));
                
                filmes.add(filme);
            }
            resultadoBD.close();
            stmt.close();
            
            }
            catch(SQLException e){
                System.out.println(e);
            }
        return filmes;
        }
    
    public void alterarquantidade(Filmes filmes){

        String sql = "UPDATE filmes SET nome =?, genero = ?, sinopse = ?, quantidade = ?, quantidadealugado = ?, quantidadeatual = ? WHERE codigo =?";
        
        try {
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, filmes.getNome());
            stmt.setString(2, filmes.getGenero());
            stmt.setString(3, filmes.getSinopse());
            stmt.setInt(4, filmes.getQuantidade());
            stmt.setInt(5, filmes.getQuantidadealugado());
            stmt.setInt(6, filmes.getQuantidadeatual());
            stmt.setInt(7, filmes.getCodigo());
            
            stmt.execute();
            stmt.close();
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public List<Filmes> quantidadee(){
        String sql = "select * from filmes order by codigo";
        ResultSet resultadoBD;
        List<Filmes> filmes = new ArrayList<Filmes>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultadoBD = stmt.executeQuery();
            while (resultadoBD.next()){
                
                Filmes filmess = new Filmes();
                filmess.setCodigo(resultadoBD.getInt("codigo"));                
                filmess.setNome(resultadoBD.getString("nome"));
                filmess.setGenero(resultadoBD.getString("genero"));
                filmess.setSinopse(resultadoBD.getString("sinopse"));
                filmess.setQuantidade(resultadoBD.getInt("quantidade"));
                filmess.setQuantidadealugado(resultadoBD.getInt("quantidadealugado"));
                filmess.setQuantidadeatual(resultadoBD.getInt("quantidadeatual"));
                
                filmes.add(filmess);
            }
            resultadoBD.close();
            stmt.close();
            
        }catch (SQLException e){
            System.out.println(e);
        }
        return filmes;
        
    }
    

        
}
    
    

