/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author marco
 */
public class ClienteDAO {
    
    private Connection conecta;
   
   public ClienteDAO(){
        this.conecta = new DAO().conecta();
        
    }
   
   public void cadastrar(Cliente cliente) {
       
       String sql = "INSERT INTO cliente(nome, nascimento, cpf, telefone, email, endereco, numero, bairro)" +
                    "VALUES(?,?,?,?,?,?,?,?)";
       
       try{
           
           PreparedStatement stmt = conecta.prepareCall(sql);
           
           stmt.setString(1, cliente.getNome());
           stmt.setString(2, cliente.getNascimento());
           stmt.setString(3, cliente.getCpf());
           stmt.setString(4, cliente.getTelefone());
           stmt.setString(5, cliente.getEmail());
           stmt.setString(6, cliente.getEndereco());
           stmt.setInt(7, cliente.getNumero());
           stmt.setString(8, cliente.getBairro());
           
           stmt.execute();
           stmt.close();
       }
       catch(SQLException e){
           throw new RuntimeException(e);
       }
   }
       
    public List<Cliente> listarTodos(){
           
        String sql = "select * from cliente order by cpf";
        ResultSet resultadoBD;
        List<Cliente> clientes = new ArrayList<Cliente>();
           
        try{
               
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultadoBD = stmt.executeQuery();
            while (resultadoBD.next()){
                   
                Cliente cliente = new Cliente();
                cliente.setNome(resultadoBD.getString("nome"));
                cliente.setNascimento(resultadoBD.getString("nascimento"));
                cliente.setCpf(resultadoBD.getString("cpf"));
                cliente.setTelefone(resultadoBD.getString("telefone"));
                cliente.setEmail(resultadoBD.getString("email"));
                cliente.setEndereco(resultadoBD.getString("endereco"));
                cliente.setNumero(resultadoBD.getInt("numero"));
                cliente.setBairro(resultadoBD.getString("bairro"));
                   
                clientes.add(cliente);
            }
            resultadoBD.close();
            stmt.close();
               
        }
        catch (SQLException e){
            System.out.println(e);
        }
    return clientes;
    }
    
    public List<Cliente> pesquisa(int tipo, String texto){
        String where;
        where = "where cpf ilike ? ORDER BY nome";
        
        String sql = "SELECT * FROM cliente " + where;
        ResultSet resultadoBD;
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, texto+"%");
            resultadoBD = stmt.executeQuery();
            while (resultadoBD.next()) {
                
                Cliente cliente = new Cliente();
                cliente.setNome(resultadoBD.getString("nome"));
                cliente.setCpf(resultadoBD.getString("cPF"));
                cliente.setNascimento(resultadoBD.getString("nascimento"));
                cliente.setTelefone(resultadoBD.getString("telefone"));
                cliente.setEmail(resultadoBD.getString("email"));
                cliente.setEndereco(resultadoBD.getString("Endereco"));
                cliente.setBairro(resultadoBD.getString("bairro"));
                cliente.setNumero(resultadoBD.getInt("numero"));
                
                
                clientes.add(cliente);
            }
            resultadoBD.close();
            stmt.close();
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return clientes;
    }
    
    public void alterar(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, nascimento = ?, telefone = ?, email = ?, endereco = ?, numero = ?, bairro = ?  " + 
                        "WHERE cpf = ?";
        
        try {
        
           PreparedStatement stmt = conecta.prepareStatement(sql);
           
           stmt.setString(1, cliente.getNome());
           stmt.setString(2, cliente.getNascimento());
           stmt.setString(3, cliente.getTelefone());
           stmt.setString(4, cliente.getEmail());
           stmt.setString(4, cliente.getEndereco());
           stmt.setInt(5, cliente.getNumero());
           stmt.setString(6, cliente.getBairro());
           stmt.setString(7, cliente.getCpf());
           
           stmt.execute();
           stmt.close();
           
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    
    
}



