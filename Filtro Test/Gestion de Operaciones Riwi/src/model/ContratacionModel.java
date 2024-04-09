package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to obj
        Contratacion objContratacion = (Contratacion) obj;

        try {
            //3. SQL
            String sql = "INSERT INTO contratacion(fecha_aplicacion, estado, salario, vacante_id, coder_id) VALUES (?,?,?,?,?);";

            //4. Statement + Retur_Genereted_Keys, to do sentence SQL return ids genereted for DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign value to symbols ???
            objPrepare.setString(1, objContratacion.getFecha_aplicacion().toString()); // Heavy parse
            objPrepare.setBoolean(2, objContratacion.getEstado_contratacion()); //Aparece isEstado en get
            objPrepare.setFloat(3, objContratacion.getSalario());
            objPrepare.setInt(4, objContratacion.getVacante_id_fk());
            objPrepare.setInt(5, objContratacion.getCoder_id_fk());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result of Ids (GeKeys)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. Iteration while there is a record
            while (objRest.next()){
                //Can obtain value also with the symbol
                objContratacion.setId_contratacion(objRest.getInt(1));

                JOptionPane.showMessageDialog(null, "Hiring insertion was successful! ;)");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list
        List<Object> listContrataciones = new ArrayList<>();

        //2. Open
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Write query into sql
            String sql = "SELECT * FROM contratacion;";

            //4. Use PrepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query and obtain next resul (ResultSet)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While as there is a result do the next
            while (objResult.next()){

                //6.1 Create a Hiring
                Contratacion objContratacion = new Contratacion();

                objContratacion.setId_contratacion(objResult.getInt("id"));
                //Warning / Heavy parse
                objContratacion.setFecha_aplicacion(LocalDate.parse(objResult.getString("fecha_aplicacion")));
                objContratacion.setEstado_contratacion(objResult.getBoolean("estado"));
                objContratacion.setSalario(objResult.getFloat("salario"));
                objContratacion.setVacante_id_fk(objResult.getInt("vacante_id"));
                objContratacion.setCoder_id_fk(objResult.getInt("coder_id"));

                //6.2 Add Hiring into the list
                listContrataciones.add(objContratacion);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Close
        ConfigDB.closeConnection();
        return listContrataciones;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Hiring
        Contratacion objContratacion = (Contratacion) obj;

        //3. Variable for know status of connection
        boolean isUpdated=false;

        try {
            //4. SQL
            String sql = "UPDATE contratacion SET fecha_aplicacion =?, estado =?, salario =?, vacante_id =?, coder_id =? WHERE id;";

            //5. Create Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Assign value to parameters of query
            objPrepare.setString(1, objContratacion.getFecha_aplicacion().toString());
            objPrepare.setBoolean(2, objContratacion.getEstado_contratacion());
            objPrepare.setFloat(3, objContratacion.getSalario());
            objPrepare.setInt(4, objContratacion.getVacante_id_fk());
            objPrepare.setInt(5, objContratacion.getCoder_id_fk());
            objPrepare.setInt(6, objContratacion.getId_contratacion());

            //7. Execute query
            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null, "The update was successful! >;)");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error update Cita" + e.getMessage());
        }finally {
            ConfigDB.closeConnection();

        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convert to entity
        Contratacion objContratacion = (Contratacion) obj;

        //2. Open
        Connection objConnection = ConfigDB.openConnection();

        //3. Create variable status
        boolean idDeleted=false;

        try {
            //4. SQL
            String sql = "DELETE FROM contratacion WHERE id =?;";

            //5. Create Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to symbol
            objPrepare.setInt(1, objContratacion.getId_contratacion());

            //7. Execute query (executeUpdate) return number of registers affected
            int totalRowsAffected = objPrepare.executeUpdate();

            // If rows > 0, is ready deleted
            if (totalRowsAffected > 0){
                idDeleted=true;

                JOptionPane.showMessageDialog(null, "Hiring successful removed! B=)");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //8. Close
        ConfigDB.closeConnection();
        return idDeleted;
    }

    public Contratacion findById(int id){
        //1. Open
        Connection objConnection = ConfigDB.openConnection();

        //2. Create Hiring to return
        Contratacion objContratacion = null;

        try {
            //3. Statement sql
            String sql = "SELECT FROM contratacion WHERE id =?;";

            //4. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Give values to parameter of query
            objPrepare.setInt(1, id);

            //6. Execute query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objContratacion = new Contratacion();

                objContratacion.setId_contratacion(objResult.getInt("id"));
                //Warning / Heavy parse
                objContratacion.setFecha_aplicacion(LocalDate.parse(objResult.getString("fecha_aplicacion")));
                objContratacion.setEstado_contratacion(objResult.getBoolean("estado"));
                objContratacion.setSalario(objResult.getFloat("salario"));
                objContratacion.setVacante_id_fk(objResult.getInt("vacante_id"));
                objContratacion.setCoder_id_fk(objResult.getInt("coder_id"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objContratacion;
    }
}
