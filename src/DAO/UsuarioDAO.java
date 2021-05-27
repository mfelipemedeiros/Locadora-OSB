/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import model.Usuario;



/**
 *
 * @author marco
 */
public class UsuarioDAO {
    
   private Connection conecta;
   
    public UsuarioDAO(){
        this.conecta = new DAO().conecta();
    }
    
    public void cadastrar(Usuario usuario){
        
        String sql = "INSERT INTO usuario(nome, cpf, usuario, senha) " + 
                    "VALUES(?,?,?,?)";
        
        try{
            
            PreparedStatement stmt = conecta.prepareCall(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getUsuario());
            stmt.setString(4, usuario.getSenha());
            
            stmt.execute();
            stmt.close();
            
        }catch (SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public Usuario validaLogin(String usuario, String senha) {
        
        String sql = "Select * FROM usuario " + 
                " WHERE usuario = ? and senha = ? ";
        
        ResultSet objeto;
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            objeto = stmt.executeQuery();
            Usuario u  = new Usuario(); 
            
            if (objeto.next()){
                
                u.setNome(objeto.getString("nome"));
                u.setCpf(objeto.getString("cpf"));
                u.setUsuario(objeto.getString("usuario"));
                u.setSenha(objeto.getString("senha"));
                
                return u;
                
            }
            else {
                return null;
            }
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        return null;
    }
}
    

    
   