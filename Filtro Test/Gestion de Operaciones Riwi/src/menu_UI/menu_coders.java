package menu_UI;

import controller.CoderController;
import entity.Coder;

import javax.swing.*;
import java.awt.*;

public class menu_coders {

    public static void show_menuC(){

        String option="";
        do {

            option = JOptionPane.showInputDialog("""
                MENU CODERS
                1) List
                2) Insert
                3) Update
                4) Delete
                5) Search by Cohort
                6) Search by Clan
                7) Return to the Main Menu
                """);
            switch (option){
                case "1":
                    CoderController.getAll();
                    break;
                case "2":
                    CoderController.create();
                    break;
                case "3":
                    CoderController.update();
                    break;
                case "4":
                    CoderController.delete();
                    break;
                case "5":
                    CoderController.getByCohort();
                    break;
                case "6":
                    CoderController.getByClan();
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "Returning to Main Menu");
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option, try again...");

            }
        }while (!option.equals("7"));
    }

}
