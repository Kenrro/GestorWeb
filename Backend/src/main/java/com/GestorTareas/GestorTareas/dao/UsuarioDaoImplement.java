/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kenrr
 */
public class UsuarioDaoImplement implements UsuarioDao {

    @Override
    public boolean createUser(Usuario user) {
        String consulta = "insert into usuarios (id, username, password, nombre, apellido) values (?,?,?,?,?)";
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta) ){
            pst.setString(1, user.getId());
            pst.setString(2, user.getUserName());
            pst.setString(3, user.getContrasena());
            pst.setString(4, user.getNombre());
            pst.setString(5, user.getApellido());
            resultado = pst.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
    }

    @Override
    public Usuario getUser(String id) {
        String consulta = "select * from usuarios where id = ?";
        Usuario user = null;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(consulta)){ 
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("simon");
                user = convertResultToUser(rs);
            }
        } catch (Exception e) {
            System.out.println("aca esta el error");
            e.printStackTrace();
        }
        return user;
    }
    // Sobre carga para el login
    public Usuario getUser(Usuario user){
        System.out.println(user.getUserName()+ user.getContrasena());
        String consulta = "select * from usuarios where username = ? AND password = ?";
        Usuario userretornar = null;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getContrasena());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                userretornar = convertResultToUser(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userretornar;
    }
        private Usuario convertResultToUser(ResultSet rs){
            Usuario user = null;
            try {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fech = rs.getDate("fech-creacion");
                user = new Usuario();
                user.setUserName(username);
                user.setContrasena(password);
                user.setId(id);
                user.setNombre(nombre);
                user.setApellido(apellido);
                user.setFechCreacion(fech);
            } catch (SQLException ex) {
                throw new RuntimeException("Error de conversion");
            }
            return user;
        }

    @Override
    public boolean deleteUser(String id) {
        String consulta = "delete from usuarios where id = ?";
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, id);
            resultado = pst.executeUpdate();
            
        } catch (SQLException e){
            throw new RuntimeException("Problema al eliminar usuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
            
    }
    @Override
    public List<Usuario> getUsers() {
        String consulta = "select * from usuarios";
        List<Usuario> lista = new ArrayList<>();
        
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(consulta)){
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuario user = convertResultToUser(rs);          
                lista.add(user);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public boolean updateUser(Usuario user) {
        String consulta = "update usuarios set nombre = ?, password = ? where id = ?";
        int resultado = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getContrasena());
            pst.setString(3, user.getId());
            resultado = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Error al acualizar al usuario");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
    }
}
