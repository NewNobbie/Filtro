package menu_UI;

import controller.CoderController;
import controller.ContratacionController;
import controller.VacanteController;
import entity.Contratacion;
import model.ContratacionModel;

import javax.print.attribute.standard.JobSheets;
import javax.swing.*;

public class menu_contratacion {

    public static void show_menuC(){

        String option="";
        do {
            option = JOptionPane.showInputDialog("""
                MENU HIRING
                1) List
                2) Insert
                3) Update
                4) Delete
                5) Search by ID
                6) Return to the Main Menu
                """);
            switch (option){
                case "1":
                    ContratacionController.getAll();
                    break;
                case "2":
                    ContratacionController.create();
                    break;
                case "3":
                    ContratacionController.update();
                    break;
                case "4":
                    ContratacionController.delete();
                    break;
                case "5":
                    ContratacionModel objModel = new ContratacionModel();

                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert Hirng ID to search: "));

                    Contratacion contratacion = (Contratacion) objModel.findById(id);
                    JOptionPane.showMessageDialog(null, contratacion.toString());
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Returning to the main menu...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option, try again...");
            }
        }while (!option.equals("6"));

    }
}
