package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.awt.*;

public class CoderController {

    public static void getAll(){
        CoderModel objModel = new CoderModel();

        String listCoders = " CODERS LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Coder
            Coder objCoder = (Coder) i;
            listCoders+=objCoder.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listCoders);
    }

    public static String getAllString(){
        CoderModel objModel = new CoderModel();

        String listCoders = " CODERS LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Coder
            Coder objCoder = (Coder) i;
            listCoders+=objCoder.toString()+"\n";
        }
        return listCoders;
    }

    public static void create(){
        CoderModel objModel = new CoderModel();

        String nombre = JOptionPane.showInputDialog("Insert name");
        String apellidos = JOptionPane.showInputDialog("Insert surname");
        String documento = JOptionPane.showInputDialog("Insert document");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Insert cohort"));
        String cv = JOptionPane.showInputDialog("Insert cv");
        String clan = JOptionPane.showInputDialog("Insert clan");

        Coder objCoder = new Coder();
        objCoder.setNombre(nombre);
        objCoder.setApellidos(apellidos);
        objCoder.setDocumento(documento);
        objCoder.setCohorte(cohorte);
        objCoder.setCv(cv);
        objCoder.setClan(clan);

        //Call Method insert and save
        objCoder = (Coder) objModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());

    }

    public static void delete(){
        CoderModel objModel = new CoderModel();

        String listCoders = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoders+"\n Enter the Coder ID to delete: "));

        Coder objCoder = objModel.findById(idDelete);

        int confirm=1;
        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found!");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the Coder? \n"+ objCoder.toString());

            if (confirm == 0) objModel.delete(objCoder);
        }
    }

    public static void update(){
        //Use model
        CoderModel objModel = new CoderModel();

        String listCoders = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders+"\n Enter the Coder ID to delete: "));

        //Obtain oder for ID added
        Coder objCoder = (Coder) objModel.findById(idUpdate);

        //Valid existent of Coder
        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder not found!");
        }else {
            String nombre = JOptionPane.showInputDialog("Insert name");
            String apellidos = JOptionPane.showInputDialog("Insert surname");
            String documento = JOptionPane.showInputDialog("Insert document");
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Insert cohort"));
            String cv = JOptionPane.showInputDialog("Insert cv");
            String clan = JOptionPane.showInputDialog("Insert clan");

            objCoder.setNombre(nombre);
            objCoder.setApellidos(apellidos);
            objCoder.setDocumento(documento);
            objCoder.setCohorte(cohorte);
            objCoder.setCv(cv);
            objCoder.setClan(clan);

            objModel.update(objCoder);
        }
    }

    public static void getByCohort(){
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Insert cohort to search: "));

        CoderModel objModel = new CoderModel();

        String listString = " RESULT \n";
        for (Coder i: objModel.findByCohorte(cohorte)){
            listString+= i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listString);
    }

    public static void getByClan(){
        String clan = JOptionPane.showInputDialog("Insert Clan to search: ");

        CoderModel objModel = new CoderModel();

        String listString = " RESULT \n";
        for (Coder i: objModel.findByClan(clan)){
            listString+= i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listString);
    }
}
