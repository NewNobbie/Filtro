package controller;

import database.ConfigDB;
import entity.Contratacion;
import entity.Empresa;
import model.ContratacionModel;
import model.EmpresaModel;

import javax.swing.*;
import java.time.LocalDate;

public class ContratacionController {

    public static void getAll(){
        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = " HIRING LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Company
            Contratacion objContratacion = (Contratacion) i;
            listContrataciones+=objContratacion.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listContrataciones);
    }

    public static String getAllString(){
        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = " HIRING LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Company
            Contratacion objContratacion = (Contratacion) i;
            listContrataciones+=objContratacion.toString()+"\n";
        }
        return listContrataciones;
    }

    public static void create(){
        ContratacionModel objModel = new ContratacionModel();

        LocalDate fecha_aplicacion = LocalDate.parse(JOptionPane.showInputDialog("Insert the date application: "));
        Boolean estado = Boolean.parseBoolean(JOptionPane.showInputDialog("Insert the status - (Active) or (Inactive)"));
        float salario = Float.parseFloat(JOptionPane.showInputDialog("Insert salary: "));
        int vancante_id_fk = Integer.parseInt(JOptionPane.showInputDialog("Insert Vacancy ID:"));
        int coder_id_fk = Integer.parseInt(JOptionPane.showInputDialog("Insert Coder ID: "));

        Contratacion objContratacion = new Contratacion();
        objContratacion.setFecha_aplicacion(fecha_aplicacion);
        objContratacion.setEstado_contratacion(estado);
        objContratacion.setSalario(salario);
        objContratacion.setVacante_id_fk(vancante_id_fk);
        objContratacion.setCoder_id_fk(coder_id_fk);

        //Call method to save
        objContratacion = (Contratacion) objModel.insert(objContratacion);

        JOptionPane.showMessageDialog(null, objContratacion.toString());
    }

    public static void delete(){
        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listContrataciones+"\n Enter ID of the Hiring to delete: "));

        Contratacion objContratacion = objModel.findById(idDelete);

        int confirm=1;
        if (objContratacion == null){
            JOptionPane.showMessageDialog(null, "Hiring not found!");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are u sure want to delete Hiring? \n"+objContratacion.toString());

            if (confirm == 0) objModel.delete(objContratacion);
        }
    }

    public static void update(){
        //Use Model
        ContratacionModel objModel = new ContratacionModel();

        String listContrataciones = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listContrataciones+"\n Enter the Hiring ID to edit: "));

        //Obtain Hiring for ID added
        Contratacion objContratacion = objModel.findById(idUpdate);

        //Valid existent of Hiring
        if (objContratacion == null){
            JOptionPane.showMessageDialog(null, "Hiring not found!");
        }else {
            LocalDate fecha_aplicacion = LocalDate.parse(JOptionPane.showInputDialog("Insert the date application: "));
            Boolean estado = Boolean.parseBoolean(JOptionPane.showInputDialog("Insert the status - (Active) or (Inactive)"));
            float salario = Float.parseFloat(JOptionPane.showInputDialog("Insert salary: "));
            int vancante_id_fk = Integer.parseInt(JOptionPane.showInputDialog("Insert Vacancy ID:"));
            int coder_id_fk = Integer.parseInt(JOptionPane.showInputDialog("Insert Coder ID: "));

            objContratacion.setFecha_aplicacion(fecha_aplicacion);
            objContratacion.setEstado_contratacion(estado);
            objContratacion.setSalario(salario);
            objContratacion.setVacante_id_fk(vancante_id_fk);
            objContratacion.setCoder_id_fk(coder_id_fk);

            objModel.update(objContratacion);
        }

    }
}
