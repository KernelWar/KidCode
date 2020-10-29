/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Scanner;


/**
 *
 * @author Jose
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
       
        System.out.println("Expresion: ");
        String expresion = leer.nextLine();
        
        expresion = expresion.replace(" ", ",");
        System.out.println("expresion remplace: "+expresion);
        
        
        String expNueva = "\"";
        String aux = "";
        System.out.println("tam = "+expresion.length());
        for (int i = 0; i < expresion.length(); i++) {
            aux = expresion.charAt(i)+"";
            if(aux.equals(",")){
                expNueva = expNueva+"\",\"";
            }else{
                expNueva = expNueva+aux;
            }
            
        }
        expNueva = expNueva+"\"";
        System.out.println("String[] Terminos = new String[]{"+expNueva+"};");
        
       
        
         
    }

}
