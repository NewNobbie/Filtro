package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Vacante objVacante = (Vacante) obj;

        try {
            //3. Sql
            String sql = "INSERT INTO vacante(id, titulo, descripcion, duracion, estado, empresa_id) VALUES (?,?,?,?,?,?);";

            //4. Prepare Statement + return_Generated_Keys, use statement sql  to return IDs generated for DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign symbols ???
            objPrepare.setInt(1, objVacante.getId_vacante());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getDescripcion());
            objPrepare.setString(4, objVacante.getDuracion());
            objPrepare.setBoolean(5, objVacante.getEstado());
            objPrepare.setInt(6, objVacante.getEmpresa_id_fk());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result generated for ids
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iterate while get registered for IDs
            while (objRest.next()){
                //Can obtain
                objVacante.setId_vacante(objRest.getInt(1));

                JOptionPane.showMessageDialog(null, "Job vacancy insertion was successful! ;)");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to save returns of DB
        List<Object> listVacantes = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Sql
            String sql = "SELECT * FROM vuelo;";

            //4. Use prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute and obtain result (Result set)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While there is result, do
            while (objResult.next()){

                //6.1 Create job vacant
                Vacante objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getBoolean("estado"));
                objVacante.setEmpresa_id_fk(objResult.getInt("empresa_id"));

                //6.2 Add Vacant to the list
                listVacantes.add(objVacante);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Close connection
        ConfigDB.closeConnection();
        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to vacant job
        Vacante objVacante = (Vacante) obj;

        //3. Variable to know the status of action
        boolean isUpdated=false;

        try {
            //4. Sql
            String sql = "UPDATE vacante SET titulo =?, descripcion =?, duracion =?, estado =?, empresa_id =? WHERE id =?;";

            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Assign value to parameters of query
            objPrepare.setString(1, objVacante.getTitulo());
            objPrepare.setString(2, objVacante.getDescripcion());
            objPrepare.setString(3, objVacante.getDuracion());
            objPrepare.setBoolean(4, objVacante.getEstado());
            objPrepare.setInt(5, objVacante.getEmpresa_id_fk());
            objPrepare.setInt(6, objVacante.getId_vacante());

            //7. Eecute query
            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "The update was successful! ;)");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert obj into entity
        Vacante objVacante = (Vacante) obj;

        //2. Open
        Connection objConnection = ConfigDB.openConnection();

        //3. Create status varible
        boolean isDeleted=false;

        try {
            //4. SQL
            String sql = "DELETE FROM vacante WHERE id;";

            //5. Create prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to the symbol
            objPrepare.setInt(1, objVacante.getId_vacante());

            //7. Execute query (ExecuteUpdate) return number of registers affected
            int totalRowsAffected = objPrepare.executeUpdate();

            //If RowsAffected > 0, it means that is deleted
            if (totalRowsAffected > 0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null, "The update was successful! ;)");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Close Connection
        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Vacante findById(int id) {
        //1. Open
        Connection objConnection = ConfigDB.openConnection();

        //2. Create Coder to return
        Vacante objVacante = null;

        try {
            //3. Sql
            String sql = "SELECT * FROM vacante WHERE id =?;";

            //4. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. give value to query
            objPrepare.setInt(1, id);

            //6. Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getBoolean("estado"));
                objVacante.setEmpresa_id_fk(objResult.getInt("empresa_id"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVacante;
    }

    public List<Vacante> findByTitulo(String titulo){
        //1. Create list
        List<Vacante> listVacantes = new ArrayList<>();

        //2. Open
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. SQL
            String sql = "SELECT * FROM vacante WHERE titulo LIKE ?;";

            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, "%"+titulo+"%");

            //5. Execute query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getBoolean("estado"));
                objVacante.setEmpresa_id_fk(objResult.getInt("empresa_id"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                listVacantes.add(objVacante);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacantes;
    }

    public List<Vacante> findByTecnology(String tecnologia){
        //1. Create list
        List<Vacante> listVacantes = new ArrayList<>();

        //2. Open
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. SQL
            String sql = "SELECT * FROM vacante WHERE tecnologia LIKE ?;";

            //4. Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, "%"+tecnologia+"%");

            //5. Execute query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();

                objVacante.setId_vacante(objResult.getInt("id"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getBoolean("estado"));
                objVacante.setEmpresa_id_fk(objResult.getInt("empresa_id"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                listVacantes.add(objVacante);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listVacantes;
    }
}
