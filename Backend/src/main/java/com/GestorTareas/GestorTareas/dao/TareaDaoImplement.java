/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import com.GestorTareas.GestorTareas.model.Tarea;
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
public class TareaDaoImplement implements TareaDao {

    
        private Tarea convertResultsetToTarea(ResultSet rs){
            Tarea tarea = new Tarea();
            try {
            tarea.setId(rs.getString("id"));
            tarea.setId_usuario(rs.getString("usuario_id"));
            tarea.setDescripcion(rs.getString("descripcion"));
            tarea.setNombre(rs.getString("nombre"));
            tarea.setFech_creacion(rs.getDate("fech-creacion"));
            
                tarea.setEstado(rs.getBoolean("completada"));
            } catch (SQLException ex) {
                Logger.getLogger(TareaDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tarea;
        }
    @Override
    public boolean createTarea(Tarea tarea) {
        String consulta = "insert into tareas (id, usuario_id, nombre, descripcion) values (?, ?, ?, ?)";
        int resultado = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, tarea.getId());
            pst.setString(2, tarea.getId_usuario());
            pst.setString(3, tarea.getNombre());
            pst.setString(4, tarea.getDescripcion());
            resultado = pst.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TareaDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado > 0;
    }

    @Override
    public Tarea getTarea(String id) {
        String consulta = "select * from tareas where id = ?";
        Tarea tarea = null;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tarea = convertResultsetToTarea(rs);
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TareaDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return tarea;
    }

    @Override
    public boolean deleteTarea(String id) {
        String consulta = "delete from tareas where id = ?";
        int rs = 0;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, id);
            rs = pst.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TareaDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rs > 0;
    }

    @Override
    public boolean updateTarea(Tarea tarea) {
        String consulta = "update tareas set descripcion = ?, completada = ? where id = ?";
        int rs = 0;
        try (Connection con = ConexionSql.getConexion();
             PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, tarea.getDescripcion());
            pst.setBoolean(2, tarea.isEstado());
            pst.setString(3, tarea.getId());
            rs = pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TareaDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return rs > 0;
    }

    @Override
    public List<Tarea> getTareas(String id) {
        String consulta = "select * from tareas where usuario_id = ?";
        List<Tarea> lista = new ArrayList<>();
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(consulta)){
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               Tarea tarea = convertResultsetToTarea(rs);
               lista.add(tarea);
            }
            System.out.println(lista.size());
            
        } catch(SQLException e){
            e.printStackTrace();
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(TareaDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        return lista;
    }
    
    
}
