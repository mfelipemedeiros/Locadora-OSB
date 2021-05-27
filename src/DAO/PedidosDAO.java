/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedidos;

/**
 *
 * @author Matheus
 */
public class PedidosDAO {
    
    private Connection conecta;
    
    public PedidosDAO(){
        this.conecta = new DAO().conecta();
    }
    
    public void cadastrar(Pedidos pedidos) {
       
       String sql = "INSERT INTO pedidos(cpf,codigo,nomefilme,dataalugado,datadevolucao) " +
                    "VALUES(?,?,?,?,?)";
       
       try{
           
           PreparedStatement stmt = conecta.prepareCall(sql);
           
           stmt.setString(1, pedidos.getCpf());
           stmt.setString(2, pedidos.getCodigo());
           stmt.setString(3, pedidos.getNomeFilme());
           stmt.setString(4, pedidos.getDataAlugado());
           stmt.setString(5, pedidos.getDataDevolucao());
           
           stmt.execute();
           stmt.close();
       }
       catch(SQLException e){
           throw new RuntimeException(e);
       }
   }
   
   public void alterar(Pedidos pedidos){
        
        String sql = "UPDATE pedidos SET datadevolucao = ?, nomefilme = ?, dataalugado = ?" + "where cpf = ? and codigo = ?";
        
        try {
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, pedidos.getDataDevolucao());
            stmt.setString(2, pedidos.getNomeFilme());
            stmt.setString(3, pedidos.getDataAlugado());     
            stmt.setString(4, pedidos.getCpf());
            stmt.setString(5, pedidos.getCodigo());
            
            stmt.execute();
            stmt.close();
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
   
   public List<Pedidos> listarTodos() {
        String sql = "select * from pedidos order by cpf";
        ResultSet resultadoBD;
        
        List<Pedidos> pedidos = new ArrayList<Pedidos>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            resultadoBD = stmt.executeQuery();
            
            while(resultadoBD.next()) {
                
                Pedidos pedido = new Pedidos();
                pedido.setCpf(resultadoBD.getString("cpf"));
                pedido.setCodigo(resultadoBD.getString("codigo"));
                pedido.setNomeFilme(resultadoBD.getString("nome"));
                pedido.setDataAlugado(resultadoBD.getString("DataAlugado"));
                pedido.setDataDevolucao(resultadoBD.getString("DataDevolucao"));
                
                pedidos.add(pedido);
                
                
                
                
            }
            resultadoBD.close();
            stmt.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return pedidos;
    }
   
   public List<Pedidos> listarPendentes(int tipo, String texto) {
        String sql = "select * from pedidos WHERE cpf ilike ? and datadevolucao = 'Pendente' ";
        ResultSet resultadoBD;
        
        List<Pedidos> pedidos = new ArrayList<Pedidos>();
        
        try {
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, texto+"%");
            resultadoBD = stmt.executeQuery();
            
            while(resultadoBD.next()) {
                
                Pedidos pedido = new Pedidos();
                pedido.setCpf(resultadoBD.getString("cpf"));
                pedido.setCodigo(resultadoBD.getString("codigo"));
                pedido.setNomeFilme(resultadoBD.getString("nomefilme"));
                pedido.setDataAlugado(resultadoBD.getString("dataalugado"));
                pedido.setDataDevolucao(resultadoBD.getString("datadevolucao"));
                
                pedidos.add(pedido);
                
                
                
                
            }
            resultadoBD.close();
            stmt.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return pedidos;
    }
   
   public List<Pedidos> pesquisa(String texto) {
        String where;

        where = "where nome ilike ? ORDER BY cpf";
        
        
        String sql = "SELECT * FROM pedidos " + where;
        
        ResultSet resultadoBD;
        
        List<Pedidos> pedidos = new ArrayList<Pedidos>();
        
        try {
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, texto+"%");
            resultadoBD = stmt.executeQuery();
            
            while(resultadoBD.next()) {
                
                Pedidos pedido = new Pedidos();
                pedido.setCpf(resultadoBD.getString("cpf"));
                pedido.setCodigo(resultadoBD.getString("codigo"));
                pedido.setNomeFilme(resultadoBD.getString("nome"));
                pedido.setDataAlugado(resultadoBD.getString("DataAlugado"));
                pedido.setDataDevolucao(resultadoBD.getString("DataDevolucao"));
                
                pedidos.add(pedido);
                
                
                
                
            }
            resultadoBD.close();
            stmt.close();
            
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        
        return pedidos;
    }
   
   
}   
    
