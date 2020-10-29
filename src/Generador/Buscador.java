/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador;

import Sintactico.Nodo;
import java.util.Stack;

/**
 *
 * @author Jose
 */
public class Buscador {

    private Nodo raiz;
    private Stack<Object> pila;

    public Buscador() {
        this.pila = new Stack<>();
    }
    //producciones de asignacion de valores
    public void buscarProduccion(Nodo raiz, String nodoBuscado) {
        this.raiz = rastrearNodo(raiz, nodoBuscado);
        this.imprimirNodo(this.raiz);
        this.cargarPilaConHojas(this.raiz);
        this.imprimirPila();
    }

    
    
    public void imprimirPila(){
        System.out.println("\n");
        System.out.println("\n<--");
        for (int i = this.pila.size()-1; i >= 0; i--) {
            Nodo aux = (Nodo) this.pila.get(i);
            System.out.println(i+" : "+aux.getValor());
        }
        System.out.println("-->\n");
    }
    private void imprimirNodo(Nodo nodo) {
        System.out.println("\n<--");
        System.out.println("Nombre: " + nodo.getNombreRaiz());
        System.out.println("Valor: " + nodo.getValor());
        System.out.println("-->\n");

    }

    public Nodo rastrearNodo(Nodo raiz, String nodoBuscado) {
        //la raiz es lo que busco?
        Nodo aux = new Nodo("", "", 0);
        if (raiz.getNombreRaiz().equals(nodoBuscado)) {
            return raiz;
        } else {
            //tiene hojas?
            if (!raiz.getHojas().isEmpty()) {
                
                for (Nodo hoja : raiz.getHojas()) {
                    aux = rastrearNodo(hoja, nodoBuscado);
                    if (aux.getNombreRaiz().equals(nodoBuscado)) {
                        break;
                    }
                }
                return aux;
            }else{
                return aux;
            }
        }
    }

    public void cargarPilaConHojas(Nodo nraiz) {
        //es raiz?
        if (!nraiz.getHojas().isEmpty()) {
            for (Nodo hoja : nraiz.getHojas()) {
                cargarPilaConHojas(hoja);
            }
        } else {
            this.pila.push(nraiz);
        }
        
    }
    
    public void cargarPilaConHojaEspecifica(Nodo nraiz, String valorhoja){
        if (!nraiz.getHojas().isEmpty()) {
            for (Nodo hoja : nraiz.getHojas()) {
                cargarPilaConHojaEspecifica(hoja,valorhoja);
            }
        } else {
            if(nraiz.getNombreRaiz().equals(valorhoja)){
                this.pila.push(nraiz);
            }            
        }
    }

    public Stack<Object> getPila() {
        return pila;
    }
    
    public String getPilaString(){
        String exp = "";
        for (int i = 0; i < this.pila.size(); i++) {
            exp += ((Nodo)this.pila.get(i)).getValor();
        }
        return exp;
    }

    
}
