package controller;

import entity.Coder;
import entity.Vacante;
import model.CoderModel;
import model.VacanteModel;

import javax.swing.*;

public class VacanteController {

    public static void getAll(){
        VacanteModel objModel = new VacanteModel();

        String listVacantes = " VACANCIES LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Vacancies
            Vacante objVacante = (Vacante) i;
            listVacantes+=objVacante.toString() +"\n";
        }
        JOptionPane.showMessageDialog(null, listVacantes);
    }

    public static String getAllString(){
        VacanteModel objModel = new VacanteModel();

        String listVacantes = " VACANCIES LIST \n";

        for (Object i: objModel.findAll()){
            //Convert to obj Vacancies
            Vacante objVacante = (Vacante) i;
            listVacantes+=objVacante.toString() +"\n";
        }
        return listVacantes;
    }

    public static void create(){
        VacanteModel objModel = new VacanteModel();

        String titulo = JOptionPane.showInputDialog("Insert vacancy title: ");
        String descripcion = JOptionPane.showInputDialog("Insert description: ");
        String duracion = JOptionPane.showInputDialog("Insert contract period: ");
        Boolean estado = Boolean.parseBoolean(JOptionPane.showInputDialog("Inset status - (Active) or (Inactive) "));
        String tecnologia = JOptionPane.showInputDialog("Insert technology: ");
        int id_empresa_fk = Integer.parseInt(JOptionPane.showInputDialog("\n Insert the Vacant ID: "));

        Vacante objVacante = new Vacante();
        objVacante.setTitulo(titulo);
        objVacante.setDescripcion(descripcion);
        objVacante.setDuracion(duracion);
        objVacante.setEstado(estado);
        objVacante.setTecnologia(tecnologia);
        objVacante.setEmpresa_id_fk(id_empresa_fk);

        //Insertion method and save obj that return Vacancies / Cast..
        objVacante = (Vacante) objModel.insert(objVacante);

        JOptionPane.showMessageDialog(null, objVacante.toString());

    }

    public static void update(){
        //Use model
        VacanteModel objModel = new VacanteModel();

        String listVacantes = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVacantes+ "\n Enter Vacancy ID to edit: "));

        //Obtain Vacancy ID
        Vacante objVacante = objModel.findById(idUpdate);

        //Valid existent of Vacancy
        if (objVacante == null){
            JOptionPane.showMessageDialog(null, "Vacancy not found...");
        }else {
            String titulo = JOptionPane.showInputDialog(null, "Enter the new name: ", objVacante.getTitulo());
            String descripcion = JOptionPane.showInputDialog(null, "Enter the new description: ");
            String duracion = JOptionPane.showInputDialog(null, "Enter the new contract period: ");
            Boolean estado = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "Enter the new status  - (Active) or (Inactive)", objVacante.getEstado()));
            int id_empresa_fk = Integer.parseInt(JOptionPane.showInputDialog("\n Insert the Vacant ID: "));

            objVacante.setTitulo(titulo);
            objVacante.setDescripcion(descripcion);
            objVacante.setDuracion(duracion);
            objVacante.setEstado(estado);
            objVacante.setEmpresa_id_fk(id_empresa_fk);

            objModel.update(objVacante);
        }
    }

    public static void delete(){
        VacanteModel objModel = new VacanteModel();

        String listVacantes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVacantes+ "\n Enter the Vacancy ID to delete: "));

        Vacante objVacante = objModel.findById(idDelete);

        int confirmo=1;
        if (objVacante == null){
            JOptionPane.showMessageDialog(null, "Vacancy not found...");
        }else {
            confirmo = JOptionPane.showConfirmDialog(null, "Are u sure want to delete the Vacancy? \n" + objVacante.toString());

            if (confirmo == 0) objModel.delete(objVacante);
        }
    }

    public static void getByTitle(){
        String  titulo = JOptionPane.showInputDialog("Insert title to search: ");

        VacanteModel objModel = new VacanteModel();

        String listString = " RESULT \n";
        for (Vacante i: objModel.findByTitulo(titulo)){
            listString+= i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listString);
    }

    public static void getByTechnology(){
        String tecnologia = JOptionPane.showInputDialog("Insert Technology to search: ");

        VacanteModel objModel = new VacanteModel();

        String listString = " RESULT \n";
        for (Vacante i: objModel.findByTecnology(tecnologia)){
            listString+= i.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listString);
    }
}
