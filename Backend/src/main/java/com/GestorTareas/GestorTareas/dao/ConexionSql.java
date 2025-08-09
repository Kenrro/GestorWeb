/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GestorTareas.GestorTareas.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.GestorTareas.GestorTareas.enums.ConnectionError;
import com.GestorTareas.GestorTareas.exception.ManagerException;

/**
 *
 * @author kenrr
 */
public class ConexionSql {

    private static final Logger logger = LoggerFactory.getLogger(WorkDAOImplement.class);
    private static final String url = "jdbc:mysql://localhost:3306/app_db";
    private static final String user = "root";
    private static final String password = "root";
    
    public static Connection getConexion() {
        try{
            return DriverManager.getConnection(url, user, password);
            
        } catch (SQLNonTransientConnectionException e){
            logger.error("No se pudo establecer conexión con la base de datos", e);
            throw new ManagerException(ConnectionError.ERROR_CONNECTING_TO_THE_DATABASE);
        } 
        catch (SQLException e){
            throw new RuntimeException("Error en la conexion");
        }
        
        
    }
}
