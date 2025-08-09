package com.GestorTareas.GestorTareas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.GestorTareas.GestorTareas.dao.idao.WorkDAO;
import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.enums.ConnectionError;
import com.GestorTareas.GestorTareas.enums.WorkError;
import com.GestorTareas.GestorTareas.exception.ManagerException;
import com.GestorTareas.GestorTareas.mapper.GenericRowMapper;
import com.GestorTareas.GestorTareas.model.Permission;
import com.GestorTareas.GestorTareas.model.User;
import com.GestorTareas.GestorTareas.model.Work;

@Repository
public class WorkDAOImplement implements WorkDAO {

    private static final Logger logger = LoggerFactory.getLogger(WorkDAOImplement.class);
    GenericRowMapper<Work> mapper = new GenericRowMapper<>(Work.class);
    GenericRowMapper<UserDTO> mapperU = new GenericRowMapper<>(UserDTO.class);
    
    final String INSERT = "insert into works (id, name, user_id, description) values (?,?,?,?)";
    final String SELECTONEWORK = "select w.*, p.permission_id, wp.name from works as w\n" + //
                            "left join participate as p on w.id = p.work_id\n" + //
                            "left join works_permissions as wp on wp.id = p.permission_id where w.id = ? and w.user_id = ?";

    final String SELECTPARTICIPATION = "select u.* from participate as p\n" + //
                                        "left join users as u on u.id = p.user_id\n" + //
                                        "where work_id = ?";

    final String SELECTWORKSOFUSER = "select works.*, participate.permission_id, works_permissions.name as pname from works\n" + //
                "                            left join participate on works.id = participate.work_id\n" + //
                "                            left join works_permissions as works_permissions on works_permissions.id = participate.permission_id where works.user_id = ?";

        final String INSERTPERMIT = "insert into participate (user_id, work_id, permission_id) values (?, ?, ?)"; // Asignar permiso por defecto al usuario creador
    final String DELETE = "delete from works where user_id = ? and id = ?";
    final String UPDATE = "update works set name = ?, description = ? where id = ? and user_id = ?";


    @Override
    public Boolean create(Work work) {
        int rs = 0;
        try(Connection con = ConexionSql.getConexion();
        PreparedStatement pst = con.prepareStatement(INSERT)){
            pst.setString(1, work.getId());
            pst.setString(2, work.getName());
            pst.setString(3, work.getUser_id());
            pst.setString(4, work.getDescription());
            rs = pst.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("User has a work with the same name.", e);
            throw new ManagerException(WorkError.WORK_WITH_THE_SAME_NAME);
        }  
        catch (SQLException e) {
            logger.error("Work creation failed", e);
            throw new ManagerException(WorkError.WORK_CREATION_FAILED);
        }
        if (rs > 0) {
            assignPermit(work);
        }
        return rs > 0;
    }
        private void assignPermit(Work work){
            try(Connection con = ConexionSql.getConexion();
                PreparedStatement pst = con.prepareStatement(INSERTPERMIT)){
                    pst.setString(1, work.getUser_id());
                    pst.setString(2, work.getId());
                    pst.setInt(3, 1);
                    pst.executeUpdate();
                } catch (SQLException e) {
                    logger.error("Error al asignar permisos al usuario", e.getMessage());
                }
        }

    @Override // Tarea especifica
    public Work get(Work work) {
        Work result = null;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(SELECTONEWORK)){
                pst.setString(1, work.getId());
                pst.setString(2, work.getUser_id());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    result = mapper.mapRow(rs);   
                }
            } catch (SQLException e) {
                logger.error("Work not found", e.getMessage());
                throw new ManagerException(WorkError.WORK_NOT_FOUND);
            }
                
        return result;
    }
    public List<Work> get(String user_id) { // Trabajo creados por un usuario
        List<Work> result = new ArrayList<>();
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(SELECTWORKSOFUSER)){
                pst.setString(1, user_id);
                ResultSet rs = pst.executeQuery();
                Work work = new Work();
                while (rs.next()) {
                    work = mapper.mapRow(rs);
                    Permission permisssion = new Permission();
                permisssion.setId(rs.getInt("permission_id"));
                permisssion.setName(rs.getString("pname"));
                work.setPermits(permisssion);
                result.add(work);
                }
                
                
            } catch (SQLException e) {
                logger.error("Works not found", e.getMessage());
                throw new ManagerException(WorkError.WORK_NOT_FOUND);
            }   
        return result;
    }

    @Override
    public boolean delete(Work work) {
        Boolean result = false;
        try(Connection con = ConexionSql.getConexion();
            PreparedStatement pst = con.prepareStatement(DELETE)){
                pst.setString(1, work.getUser_id());
                pst.setString(2, work.getId());
                result = pst.executeUpdate() > 0 ? true : false;
            } catch (SQLException e) {
                logger.error("Work delete failed", e.getMessage());
                throw new ManagerException(WorkError.WORK_DELETE_FAILED);
            }
        return result;
    }

    @Override
    public boolean update(Work work) {
        boolean result = false;
        try (Connection con = ConexionSql.getConexion();
        PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, work.getName());
            pst.setString(2, work.getDescription());
            pst.setString(3, work.getId());
            pst.setString(4, work.getUser_id());
            result = pst.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            logger.error("Work update failed", e.getMessage());
            throw new ManagerException(WorkError.WORK_UPDATE_FAILED);
        }
        return result;
    }
    public List<UserDTO> getParticipation(String work_id){ // Usuarios que participan en un trabajo
        List<UserDTO> list = new ArrayList<UserDTO>();
        try(Connection con = ConexionSql.getConexion();
        PreparedStatement pst = con.prepareStatement(SELECTPARTICIPATION)){
            pst.setString(1, work_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                UserDTO user = mapperU.mapRow(rs);
                list.add(user);
            }
        } catch(SQLException e){
            logger.error("Error to get users", e);
        }
        return list;
    }

    @Override
    public List<Work> getItems(String id) {
        // TODO Auto-generated method stub 
        throw new UnsupportedOperationException("Unimplemented method 'getItems'");
    }

}
