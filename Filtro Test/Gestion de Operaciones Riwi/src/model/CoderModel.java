package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Coder objCoder = (Coder) obj;

        try {
            //3. Write sql
            String sql = "INSERT INTO coder(id, nombre, apellidos, documento, cohorte, cv, clan) VALUES (?,?,?,?,?,?,?);";

            //4. Prepare Statement + return_Generated_Keys, do statement sql return
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign value ???
            objPrepare.setInt(1, objCoder.getId_coder());
            objPrepare.setString(2, objCoder.getNombre());
            objPrepare.setString(3, objCoder.getApellidos());
            objPrepare.setString(4, objCoder.getDocumento());
            objPrepare.setInt(5, objCoder.getCohorte());
            objPrepare.setString(6, objCoder.getCv());
            objPrepare.setString(7, objCoder.getClan());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain result with IDs generated
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //8. While obtain register
            while (objRest.next()){
                //Can obtain
                objCoder.setId_coder(objRest.getInt(1));

                JOptionPane.showMessageDialog(null,"Coder insertion was successful!");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //9. Close connection
        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public List<Object> findAll() {
        //1. Create list to save returns
        List<Object> lisCoders = new ArrayList<>();

        //2. Open Connecition
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Query sql
            String sql = "SELECT * FROM coder;";

            //4. Use prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query and obtain result (Result set)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While there is a result, do
            while (objResult.next()){
                //6.1 Create coder
                Coder objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documentoo"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                //6.2 Add Coder to the list
                lisCoders.add(objCoder);

            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Closer connection
        ConfigDB.closeConnection();
        return lisCoders;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Coder
        Coder objCoder = (Coder) obj;

        //3. Variable to know the status of action
        boolean isUpdated = false;

        try {
            //4. Statements sql
            String sql = "UPDATE coder SET nombre =?, apellidos =?, documento =?, cohorte =?, cv =?, clan =? WHERE id;";

            //5. Create statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Assign value to parameters of query
            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setString(6, objCoder.getClan());
            objPrepare.setInt(7, objCoder.getId_coder());

            //7. Execute query
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
        Coder objCoder = (Coder) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create state variable
        boolean isDeleted=false;

        try {
            //4. Write Sql
            String sql = "DELETE FROM coder WHERE id =?;";

            //5. Create prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to the symbol
            objPrepare.setInt(1, objCoder.getId_coder());

            //7. Execute query (ExecuteUpdate) return number of registers affected
            int totalRowsAffected = objPrepare.executeUpdate();

            //If Rows affected > 0, its means that deleted
            if (totalRowsAffected > 0){
                isDeleted=false;
                JOptionPane.showMessageDialog(null, "The update was successful! ;)");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

            //8. Close Connection
            ConfigDB.closeConnection();
            return isDeleted;
        }
        return false;
    }

    public List<Coder> findByCohorte(int cohorte){
        //Create a list
        List<Coder> listCoders = new ArrayList<>();

        //Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            // Statement sql
            String sql = "SELECT * FROM coder WHERE cohorte =?;";

            // Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,cohorte);

            // Execute query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Coder objCoder = new Coder();

                //Give values
                objCoder.setId_coder(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                //Add list
                listCoders.add(objCoder);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog( null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listCoders;
    }

    public List<Coder> findByClan(String clan) {
        //Create a list
        List<Coder> listCoders = new ArrayList<>();

        //Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            // Statement sql
            String sql = "SELECT * FROM coder WHERE clan =?;";

            // Prepare Statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+clan+"%");

            // Execute query
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Coder objCoder = new Coder();

                //Give values
                objCoder.setId_coder(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                //Add list
                listCoders.add(objCoder);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog( null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listCoders;
    }

    public Coder findById(int id) {
        //1. Open
        Connection objConnection = ConfigDB.openConnection();

        //2. Create Coder to return
        Coder objCoder = null;

        try {
            //3. Sql
            String sql = "SELECT * FROM coder WHERE id =?;";

            //4. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. give value to query
            objPrepare.setInt(1, id);

            //6. Execute query
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objCoder.setId_coder(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCoder;
    }
}
