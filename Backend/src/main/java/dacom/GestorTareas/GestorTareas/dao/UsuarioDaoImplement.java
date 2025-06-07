/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dacom.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.Usuario;
import java.sql.Connection;
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
        String consulta = "insert into usuarios (id, nombre, password) values (?,?,?)";
        int resultado = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta) ){
            pst.setString(1, user.getId());
            pst.setString(2, user.getNombre());
            pst.setString(3, user.getContraseña());
            resultado = pst.executeUpdate();
        } catch(SQLException e){
            
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
            user = convertResultToUser(rs);
        } catch (Exception e) {
        }
        return user;
    }
        private Usuario convertResultToUser(ResultSet rs){
            Usuario user = null;
            try {
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                user = new Usuario();
                user.setNombre(nombre);
                user.setContraseña(password);
                user.setId(id);
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
}
