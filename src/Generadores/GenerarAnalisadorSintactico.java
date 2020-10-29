/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generadores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.parser;

/**
 *
 * @author Jose
 */
public class GenerarAnalisadorSintactico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("\n*** Generando ***\n");
        String archLexico = "";
        String archSintactico = "";

        archLexico = "C:\\Users\\Jose\\Documents\\NetBeansProjects\\KidCode\\src\\Lexico\\kidcode.flex";;
        archSintactico = "C:\\Users\\Jose\\Documents\\NetBeansProjects\\KidCode\\src\\Sintactico\\Gramatica.cup";;
        String[] alexico = {archLexico};
        String[] asintactico = {"-parser", "AnalizadorSintactico", archSintactico};
        jflex.Main.main(alexico);
        try {
            java_cup.Main.main(asintactico);
        } catch (NullPointerException ex) {
            Logger.getLogger(GenerarAnalisadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerarAnalisadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GenerarAnalisadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean mvAL = moverArch("AnalizadorLexico.java");
        boolean mvAS = moverArch("AnalizadorSintactico.java");
        boolean mvSym = moverArch("sym.java");
        if (mvAL && mvAS && mvSym) {
            System.exit(0);
        }
        System.out.println("Generado!");

    }

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;

        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "Sintactico" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }

        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }

    
}
