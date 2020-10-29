/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador;

/**
 *
 * @author Jose
 */
public class Triplo {
    private Object valor1;
    private String operador;
    private Object valor2;

    public Triplo() {
    }

    public Triplo(Object valor1, String operador, Object valor2) {
        this.valor1 = valor1;
        this.operador = operador;
        this.valor2 = valor2;
    }

    public Object getValor1() {
        return valor1;
    }

    public void setValor1(Object valor1) {
        this.valor1 = valor1;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Object getValor2() {
        return valor2;
    }

    public void setValor2(Object valor2) {
        this.valor2 = valor2;
    }
    
    
}
