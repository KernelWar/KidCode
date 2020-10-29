/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import Sintactico.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class AnalizadorSemantico {

    public ArrayList<FilaTablaSimbolos> tablaSimbolos;
    public ArrayList<String> listaErrores;

    public AnalizadorSemantico() {
        this.tablaSimbolos = new ArrayList<>();
        this.listaErrores = new ArrayList<>();
    }

    public void rastrearProduccion(String produccion, Nodo arbol, String produccionMostrar) {
        produccionBuscada(produccion, arbol, produccionMostrar);
//        System.out.println("Raiz\tToken\tValor\tLinea");
//        for (FilaTablaSimbolos fila : this.tablaSimbolos) {
//            int linea = fila.getSymbol().right;
//            linea++;
//            System.out.println(fila.getProduccionRaiz() + "\t" + fila.getToken() + "\t" + fila.getValorToken() + "\t" + linea);
//        }
    }

    private Nodo produccionBuscada(String produccion, Nodo arbol, String produccionMostrar) {
        if (arbol.getNombreRaiz().equals(produccion)) {
            //Para encontrar el valor final
////            System.out.println("AQUI : "+produccion);
            produccionBasica(produccion, arbol, produccionMostrar);
            return arbol;
        } else {
            for (Nodo hoja : arbol.getHojas()) {
                produccionBuscada(produccion, hoja, produccionMostrar);
            }
        }
        return null;
    }

    private Nodo produccionBasica(String produccion, Nodo arbol, String produccionMostrar) {
        if (arbol.getNombreRaiz().equals(produccionMostrar)) {
            //this.tablaSimbolos.add(new FilaTablaSimbolos(produccion, arbol.getNombreRaiz(), arbol.getValor()));
//            System.out.println("AQUI BASICA "+produccionMostrar+" valor "+arbol.getValor()+" prod "+produccion);
            if (arbol.getSymbol() != null) {
//                System.out.println("------------------------------");
//                System.out.println("valor "+arbol.getValor());
//                System.out.println("valor "+arbol.getSymbol().value);
//                System.out.println("sym "+arbol.getSymbol().sym);
//                System.out.println("Linea "+((arbol.getSymbol().right)+1));
//                System.out.println("Left "+arbol.getSymbol().left);
//                System.out.println("------------------------------");
            }
            this.agregarFila(new FilaTablaSimbolos(produccion, arbol.getNombreRaiz(), arbol.getValor(), arbol.getSymbol()));
            return arbol;
        } else {
            for (Nodo hoja : arbol.getHojas()) {
                produccionBasica(produccion, hoja, produccionMostrar);
            }
        }
        return null;
    }
    
    private void verTabla(){
        System.out.println("===============================================");
        System.out.println("Raiz\tToken\tValor\tLinea");
        for (FilaTablaSimbolos fila : this.tablaSimbolos) {
            int linea = fila.getSymbol().right;
            linea++;
            System.out.println(fila.getProduccionRaiz() + "\t" + fila.getToken() + "\t" + fila.getValorToken() + "\t" + linea);
        }
        System.out.println("===============================================");
    }
            
    //Aqui se definen las reglas del analizador semantico
    private void agregarFila(FilaTablaSimbolos fila) {
        
        
        
        int linea = ((fila.getSymbol().right) + 1);
        String valor = fila.getValorToken();
        switch (fila.getProduccionRaiz()) {            
            case "DECLARACION":
            case "DECL_ASIG_CICLO":
                //ERROR -> VARIBLE YA DECLARADA
                //LA VARIABE YA ESTA DECLARADA ?
                //true : En el codigo se intenta declarar varias veces
                //false: se agrega la declaracion a la tabla de simbolos
                if (buscarFila(fila)) {
//                    System.out.println("Error semantico variable ya declarada.");
//                    System.out.println("Linea: "+((fila.getSymbol().right)+1));
//                    System.out.println("Token: "+fila.getToken());
//                    System.out.println("Valor: "+fila.getValorToken());
                    System.out.println("==============1===============");
                    System.out.println("#"+fila.getProduccionRaiz()+"#");
                    System.out.println("#"+fila.getValorToken()+"#");
                    System.out.println("#"+fila.getToken()+"#");
                    System.out.println("==============================");
                    listaErrores.add("Error semantico variable ya declarada.\n\tLinea: " + ((fila.getSymbol().right) + 1) + "\n\tValor token: " + fila.getValorToken());
                } else {
                    if(fila.getProduccionRaiz().equals("DECL_ASIG_CICLO")){                        
                        fila.setProduccionRaiz("DECLARACION");
                        this.tablaSimbolos.add(fila);                        
                        FilaTablaSimbolos aux = new FilaTablaSimbolos("ASIGNACION",fila.getToken(),fila.getValorToken(),fila.getSymbol());
                        this.tablaSimbolos.add(aux);                        
                    }else{
                        this.tablaSimbolos.add(fila);
                    }
                    
                }
                break;            
            case "VARIABLE_DA":
            case "ASIGNACION":
            case "LEER_DATO":
            case "TIPO_VALOR":
            case "CONDICION_ALTA":
            case "CONDICION_MEDIA":
            case "CONDICION_BAJA":
            case "ASIGNACION_INC_DEC":
            case "ASIGNACION_CICLO":
                //ERROR -> VARIBLE NO DECLARADA
                //LA VARIABE YA ESTA DECLARADA ?
                //false: se quiere hacer uso de la variabel pero aun no ah sido declarada
                //true: se agrega la asigancion u otra operacion a la tabla de simbolos
                if (buscarDeclaracion(fila.getValorToken()) == false) {
//                    System.out.println("Token: "+fila.getToken());
//                    System.out.println("Valor: "+fila.getValorToken());
//                    System.out.println("Error semantico variable no declarada.");
//                    System.out.println("Linea: "+((fila.getSymbol().right)+1));
                    System.out.println("==============2===============");
                    System.out.println("#"+fila.getProduccionRaiz()+"#");
                    System.out.println("#"+fila.getValorToken()+"#");
                    System.out.println("#"+fila.getToken()+"#");
                    System.out.println("==============================");
                    String er = "Error semantico variable no declarada.\n\tLinea: " + linea + "\n\tValor token: " + valor;
                    listaErrores.add(er);
                } else {

                    switch (fila.getProduccionRaiz()) {
                        //ERROR VARIABLE NO INICIALIZADA 
                        case "ASIGNACION_CICLO":
                            fila.setProduccionRaiz("ASIGNACION");
                            this.tablaSimbolos.add(fila);
                            break;
                        case "CONDICION_ALTA":
                        case "CONDICION_MEDIA":
                        case "CONDICION_BAJA":
                        case "ASIGNACION_INC_DEC":
                            if (buscarAsignacion(fila.getValorToken()) == false) {
                                System.out.println("==============3===============");
                                System.out.println("#"+fila.getProduccionRaiz()+"#");
                                System.out.println("#"+fila.getValorToken()+"#");
                                System.out.println("#"+fila.getToken()+"#");
                                System.out.println("==============================");
                                String er = "Error semantico variable no inicializada.\n\tLinea: " + linea + "\n\tValor token: " + valor;
                                listaErrores.add(er);
                            }else{
                               this.tablaSimbolos.add(fila); 
                            }
                            break;
                        default:
                            this.tablaSimbolos.add(fila);
                    }
                }
                break;
                
            /*
                if(buscarDeclaracion(fila.getValorToken()) == false){
                    System.out.println("Token: "+fila.getToken());
                    System.out.println("Valor: "+fila.getValorToken());
                    System.out.println("Error semantico variable no declarada.");
                    System.out.println("Linea: "+((fila.getSymbol().right)+1));
                    
                    int linea = ((fila.getSymbol().right)+1);
                    String valor = fila.getValorToken();
                    String er = "Error semantico variable no declarada.\n\tLinea: "+linea+"\n\tValor token: "+valor;                    
                    listaErrores.add(er);
                }else{
                     this.tablaSimbolos.add(fila);
                }
                break;
             */
        }
    }

    private boolean buscarAsignacion(String variable) {
        //retorna true si la variable ya fue inicializada
        for (FilaTablaSimbolos tabla : this.tablaSimbolos) {
            if (tabla.getProduccionRaiz().equals("ASIGNACION") && tabla.getValorToken().equals(variable)) {
                return true;
            }
        }
        return false;
    }

    private boolean buscarDeclaracion(String nombreVar) {
        //retorna true si la variable fue declarada
        for (FilaTablaSimbolos tabla : this.tablaSimbolos) {
            if (tabla.getProduccionRaiz().equals("DECLARACION") && tabla.getValorToken().equals(nombreVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean buscarFila(FilaTablaSimbolos fila) {
        //retorna true si la variable ya fue declarada
        for (FilaTablaSimbolos filaTabla : tablaSimbolos) {
            return filaTabla.filasIguales(fila);
        }
        return false;
    }
}
