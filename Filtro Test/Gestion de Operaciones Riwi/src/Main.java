import database.ConfigDB;
import menu_UI.menu_coders;
import menu_UI.menu_contratacion;
import menu_UI.menu_vacante;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option="";

        do {
            option = JOptionPane.showInputDialog("""
                    [ Welcome to the Operation Manager at Riwi ]
                    -> Here u can manage the hiring and vacancies of the coders
                    
                    { DataBases }
                    1) Coders
                    2) Vacancy
                    3) Hiring
                    4) Companies
                    5) ...
                    """);
            switch (option){
                case "1":
                    menu_coders.show_menuC();
                    break;
                case "2":
                    menu_vacante.show_menuV();
                    break;
                case "3":
                    menu_contratacion.show_menuC();
                    break;
                case "4":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option, try again...");
            }
        }while (!option.equals("5"));


    }
}