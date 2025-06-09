/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kenrr
 */
public class ConexionSql {
    private static final String url = "jdbc:mysql://localhost:3306/app_db";
    private static final String user = "root";
    private static final String password = "root";
    
    public static Connection getConexion() throws ClassNotFoundException{
        try{
            return DriverManager.getConnection(url, user, password);
            
        } catch (SQLException e){
            throw new RuntimeException("Error en la conexion");
        }
        
        
    }
}
