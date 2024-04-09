package menu_UI;

import controller.CoderController;
import controller.VacanteController;
import entity.Vacante;

import javax.swing.*;

public class menu_vacante{

    public static void show_menuV(){

        String option="";

        do {

            option = JOptionPane.showInputDialog("""
                MENU VACANCIES
                1) List
                2) Insert
                3) Update
                4) Delete
                5) Search by Title
                6) Search by Technology
                7) Return to the Main Menu
                """);
            switch (option){
                case "1":
                    VacanteController.getAll();
                    break;
                case "2":
                    VacanteController.create();
                    break;
                case "3":
                    VacanteController.update();
                    break;
                case "4":
                    VacanteController.delete();
                    break;
                case "5":
                    VacanteController.getByTitle();
                    break;
                case "6":
                    VacanteController.getByTechnology();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "Returning to the main menu...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option, try again...");
            }
        }while (!option.equals("7"));

    }
}
