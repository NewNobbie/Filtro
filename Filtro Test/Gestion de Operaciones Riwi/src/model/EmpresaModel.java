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

public class EmpresaModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert obj
        Empresa objEmpresa = (Empresa) obj;

        try {
            //3. sql
            String sql = "INSERT INTO empresa(id, nombre, sector, ubicacion, contacto) VALUES (?,?,?,?);";

            //4. Prepare statement + return_Generated_Keys, do sentencia sql return
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Assign values to ???
            objPrepare.setInt(1, objEmpresa.getId_empresa());
            objPrepare.setString(2, objEmpresa.getNombre());
            objPrepare.setString(3, objEmpresa.getSector());
            objPrepare.setString(4, objEmpresa.getUbicacion());
            objPrepare.setString(5, objEmpresa.getContacto());

            //6. Execute query
            objPrepare.execute();

            //7. Obtain Result with Ids generated
            ResultSet objResult = objPrepare.getResultSet();

            //8. While obtain registers
            while (objResult.next()){
                //Can obtain
                objEmpresa.setId_empresa(1);

                JOptionPane.showMessageDialog(null, "The company insertion was successful! ;)");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objEmpresa;
    }

    @Override
    public List<Object> findAll() {
        //1. Creat4e list to save returns
        List<Object> listEmpresas = new ArrayList<>();

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //Query sql
            String sql = "SELECT FROM empresa;";

            //4. Use prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Execute query and obtain result (Result set)
            ResultSet objResult = objPrepare.executeQuery();

            //6. While there is a result, do
            while (objResult.next()){

                //6.1 Create Company
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId_empresa(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                //6.2 Add Company to the list
                listEmpresas.add(objEmpresa);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        //7. Close connection
        ConfigDB.closeConnection();
        return listEmpresas;
    }

    @Override
    public boolean update(Object obj) {
        //1. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //2. Convert to Company
        Empresa objEmpresa = (Empresa) obj;

        //3. Variable to know the status of action
        boolean isUpdated=false;

        try {
            //4. Sql
            String sql = "UPDATE empresa SET nombre =?, sector =?, ubicacion =?, contacto =? WHERE id;";

            //5. Create statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Assign value to parameters of query
            objPrepare.setString(1, objEmpresa.getNombre());
            objPrepare.setString(2, objEmpresa.getSector());
            objPrepare.setString(3, objEmpresa.getUbicacion());
            objPrepare.setString(4, objEmpresa.getContacto());
            objPrepare.setInt(5, objEmpresa.getId_empresa());

            //7. Execute query
            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0){
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
        Empresa objEmpresa = (Empresa) obj;

        //2. Open Connection
        Connection objConnection = ConfigDB.openConnection();

        //3. Create status vaiable
        boolean isDeleted=false;

        try {
            //4. Write SQL
            String sql = "DELETE FROM empresa WHERE id =?;";

            //5. Create Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Give value to the symbol
            objPrepare.setInt(1, objEmpresa.getId_empresa());

            //7. Execute Query (executeUpdate) return number of registers
            int totalAffectedRows = objPrepare.executeUpdate();

            //If RowsAffected > 0, it means that is deleted
            if (totalAffectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successfull ;)");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog( null, e.getMessage());


            //8. Close
            ConfigDB.closeConnection();
            return isDeleted;
        }
        return false;
    }

}
