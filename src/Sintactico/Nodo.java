package Sintactico;

import java.util.ArrayList;
import java_cup.runtime.Symbol;

public class Nodo {
   
    private String nombreRaiz;
    private ArrayList<Nodo> hojas = new ArrayList<>();
    private String valor;
    private int numeroGuia;
    private Symbol symbol;
    

    public Nodo(String nombreRaiz, String valor, int numeroGuia, Symbol symbol) {
        this.nombreRaiz = nombreRaiz;
        this.valor = valor;
        this.numeroGuia = numeroGuia;
        this.symbol = symbol;
    
    }
    
   
    
     
    
    //para el analizador sintactico
    public Nodo(String nombreRaiz, String valor, int numeroGuia) {
        this.nombreRaiz = nombreRaiz;
        this.valor = valor;
        this.numeroGuia = numeroGuia;
    
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
        
    }
    
    
    public void addRaiz(Nodo nuevo){
        this.hojas.add(nuevo);
    }

    public ArrayList<Nodo> getHojas() {
        return hojas;
    }
    
    public void imprimirHojas(){
        for (Nodo hoja : this.hojas) {            
            System.out.println(this.hojas.indexOf(hoja)+"> "+hoja.getNombreRaiz());
        }
    }
    
    public void setHojas(ArrayList<Nodo> hojas) {
        this.hojas = hojas;
     
    }
    
    public String getNombreRaiz() {
        return nombreRaiz;
    }

    public void setNombreRaiz(String nombreRaiz) {
        this.nombreRaiz = nombreRaiz;
    }

    public String getValor() {
//        if(this.valor.length() <= 15){
//            return valor;
//        }else{
//            return this.valor.substring(0, 15)+"...";
//        }
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
        
    }

    public int getNumeroGuia() {
        return numeroGuia;        
    }

    public void setNumeroGuia(int numeroGuia) {
        this.numeroGuia = numeroGuia;
        
    }


}
