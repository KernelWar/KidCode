/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] Terminos = new String[]{"OPER_ASIG_InDe","VALOR_ENT"};
         String[] terminalNames = new String[]{
            "EOF",
            "error",
            "PAL_INICIO_PROG",
            "PAL_FIN_PROG",
            "PAL_VARIABLE",
            "PAL_GRUPO",
            "LEER",
            "IMPRIMIR",
            "CICLO_REPETIR",
            "REPETIR_HASTA",
            "REPETIR_FIN",
            "CICLO_MIENTRAS",
            "INICIO_CICLO",
            "FIN_MIENTRAS",
            "CONDICION_SI",
            "CONDICION_INICIO",
            "CONDICION_SINO",
            "CONDICION_FIN",
            "OPER_REL",
            "OPER_ART_SUMA",
            "OPER_ART_RESTA",
            "OPER_ART_MUL",
            "OPER_ART_DIV",
            "OPER_ASIG_IGUAL",
            "OPER_ASIG_InDe",
            "OPER_AGRUP_PI",
            "OPER_AGRUP_PF",
            "OPER_AGRUP_LLI",
            "OPER_AGRUP_LLF",
            "OPER_AGRUP_CI",
            "OPER_AGRUP_CF",
            "OPER_AGRUP_COMA",
            "VALOR_ENT",
            "VALOR_FLO",
            "VALOR_STRING",
            "VALOR_LOG",
            "NOMBRE_VAR",
            "PUNTOYCOMA",
            "ERROR"
        };
        String[] noTerminalNames = new String[]{
            "INIT", "PROGRAMA", "CODIGO",
            //Declarar y/o Asignar
            "ESTRUCTURAS", "DECLARACION", "LISTA_VARIABLES", "ASIGNACION", "EXP_ASIGNACION",
            "DECLARACION_GRUPO", "ASIGNACION_GRUPO", "LISTA_VALORES", "VALOR_GRUPO",
            "CONDICIONALES", "LISTA_CONDICIONES", "CONDICION_ALTA", "CONDICION_MEDIA", "CONDICION_BAJA",
            //Ciclos
            "C_MIENTRAS", "C_REPETIR",
            //Generales
            "TIPO_VALOR", "OPERADOR_ARITMETICO", "VARIABLE_DA", "R_INCDEC", "LEER_DATO", "IMPRIMIR_DATO", "LISTA_IMPRECIONES", "VALOR_IMPRIMIR"
        };
        
        int cn = 0;
        int cr = 0;
        String expFinal = "";
        boolean nodo = false;
        String expNodos = "";
        String expRaizes = "";
        
        ArrayList<String> nodos = new ArrayList<>();
        ArrayList<String> raices = new ArrayList<>();
        for (int i = 0; i < Terminos.length; i++) {
            for (int j = 0; j < terminalNames.length; j++) {
                if(Terminos[i].equals(terminalNames[j])){
                    cn++;
                    expFinal = expFinal+terminalNames[j]+":n"+cn+" ";
                    expNodos = "Nodo nd"+cn+" = new Nodo(\""+terminalNames[j]+"\",n"+cn+".toString(),parser.guia);";
                    nodos.add(expNodos);
                    nodo = true;
                    break;
                }
            }
            if(nodo == false){
                for (int j = 0; j < noTerminalNames.length; j++) {
                    if(Terminos[i].equals(noTerminalNames[j])){
                        cr++;
                        expFinal = expFinal+noTerminalNames[j]+":raiz"+cr+" ";                        
                        expRaizes = "raiz.addRaiz((Nodo)raiz"+cr+");";
                        raices.add(expRaizes);
                        break; 
                    }
                }
            }
            nodo = false;
        }
        System.out.println("");
        System.out.println("Expresion final: \n"+expFinal);
        System.out.println("");
        for (int i = 0; i < nodos.size(); i++) {
            System.out.println(nodos.get(i));
            System.out.println("getGuia();");
        }
        
        System.out.println("");
        
        
        cn = 1;
        cr = 0;
        for (int i = 0; i < Terminos.length; i++) {
            for (int j = 0; j < terminalNames.length; j++) {
                if(Terminos[i].equals(terminalNames[j])){
                    System.out.println("raiz.addRaiz(nd"+cn+");");
                    cn++;
                    nodo = true;
                    break;
                }
            }
            if(nodo == false){
                for (int j = 0; j < noTerminalNames.length; j++) {
                    if(Terminos[i].equals(noTerminalNames[j])){
                        System.out.println(raices.get(cr));
                        cr++;
                        break; 
                    }
                }
            }
            nodo = false;
            
        }
        System.out.println("RESULT = raiz;");
    }
    
}
