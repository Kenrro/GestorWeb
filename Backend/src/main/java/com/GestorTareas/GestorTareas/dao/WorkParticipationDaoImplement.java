package com.GestorTareas.GestorTareas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.GestorTareas.GestorTareas.dao.idao.WorkParticipationDao;
import com.GestorTareas.GestorTareas.mapper.GenericRowMapper;
import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.Work;

@Repository
public class WorkParticipationDaoImplement implements WorkParticipationDao{
    final String INSERT = "insert into participate (user_id, work_id, permission_id) values (?, ?, ?)"; // Asignar permiso por defecto al usuario creador
    final String SELECT = "select * from works_permissions where user_id = ? and work_id = ?"; // Asignar permiso por defecto al usuario creador
    final String DELETE = "delete from works_permissions where user_id = ? and work_id = ?";
    final String UPDATE = "update participate set permission_id = ? where user_id = ? and work_id = ?";

    private static final Logger logger = LoggerFactory.getLogger(WorkDAOImplement.class);
    GenericRowMapper<Permission> mapper = new GenericRowMapper<>(Permission.class);
 
    @Override
    public Permission get(Work work, String User) {
        Permission result = null;
        try(Connection con = ConexionSql.getConexion();
                PreparedStatement pst = con.prepareStatement(SELECT)){
                    pst.setString(1, work.getUser_id());
                    pst.setString(2, work.getId());
                    ResultSet rs =pst.executeQuery();
                    if (rs.next()) {
                        result = mapper.mapRow(rs);
                    }
                } catch (SQLException e) {
                    logger.error("Error al asignar permisos al usuario", e.getMessage());
                }
        return result;
    }
    //     private Permission convertResultToPermit(ResultSet rs){
    //     Permission permission = null;
    //     try {
    //             permission = new Permission();
    //             permission.setId(rs.getInt("permission_id"));
    //             permission.setName(rs.getString("name"));
    //     } catch (SQLException e) {
    //         logger.error("Error processing to the query result", e.getMessage());
    //         throw new ManagerException(ConnectionError.ERROR_PROCESSING_TO_THE_QUERY_RESULT);
    //     }
    //     return permission;
    // }

    @Override
    public Permission update(Work work, String User, Permission permission) {
        int result = 0;
        try(Connection con = ConexionSql.getConexion();
                PreparedStatement pst = con.prepareStatement(UPDATE)){
                    pst.setInt(1, permission.getId());
                    pst.setString(2, User);
                    pst.setString(3, work.getId());
                    result = pst.executeUpdate();
                } catch (SQLException e) {
                    logger.error("Error al asignar permisos al usuario", e.getMessage());
                }
        return result > 0 ? permission : null;
    }
    @Override
    public boolean delete(String work_id, String user_id) {
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETE)){
                pst.setString(1, user_id);
                pst.setString(2, work_id);
                result = pst.executeUpdate() > 0;
            } 
            catch(SQLException e){
                e.printStackTrace();
            }
        return result;
    }
    public boolean set(Work work, String user_id, Permission permission){
        boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(INSERT)){
                pst.setString(1, user_id);
                pst.setString(2, work.getId());
                pst.setInt(3, permission.getId());
                result = pst.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Error al asignar permisos al usuario", e.getMessage());
            }
        return result;
    }
    

}
