package com.GestorTareas.GestorTareas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GestorTareas.GestorTareas.dao.idao.WorkDAO;
import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.model.Work;

@Repository
public class WorkDAOImplement implements WorkDAO {
    
    final String INSERT = "insert into works (id, name, user_id, description) values (?,?,?,?)";


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
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return rs > 0;
    }

    @Override
    public Work get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean update(Work work) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Work> getItems(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItems'");
    }

}
