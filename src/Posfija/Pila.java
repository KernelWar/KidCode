/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posfija;

/**
 *
 * @author Linux
 */
public class Pila {

    Nodo cabeza;
    private int nelementos;

    public Pila() {
        this.cabeza = null;
        this.nelementos = 0;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public int getNelementos() {
        return nelementos;
    }

   

    public void push(Object e) {
        Nodo nuevo = new Nodo(e);
        if (this.getNelementos() == 0) {
            this.cabeza = nuevo;
            this.nelementos = 1;
        } else {
            nuevo.setSiguiente(this.cabeza);
            this.cabeza = nuevo;
            this.nelementos += 1;
        }
    }

    public void pop() {
        if (this.nelementos != 0) {
            Nodo n = null;
            n = this.cabeza.getSiguiente();
            this.cabeza = null;
            this.cabeza = n;
			this.nelementos--;
        } else {
            System.out.println("No hay elementos");
        }
    }

    public void verPila() {
        if (this.cabeza != null) {
            Nodo nuevo = this.cabeza;
            while(nuevo != null){                
                System.out.println(nuevo.getValor());
                nuevo = nuevo.getSiguiente();
            }
        }else{
            System.out.println("No hay datos");
        }
    }

}
