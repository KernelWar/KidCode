/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posfija;

import java.util.ArrayList;

/**
 *
 * @author Linux
 */
public class Posfija {

    public String convertirExp(String exp) {
        return evaluar(posfija(exp));
    }
    public void infija_posfija(String exp){
        ArrayList<Object> pos = posfija(exp);
        for (int i = 0; i < pos.size(); i++) {
            System.out.print(pos.get(i).toString());
        }
        System.out.println("");
    }

    public ArrayList posfija(String exp) {
        Pila pila = new Pila();
        ArrayList<Object> resultado = new ArrayList<>();
        String valorSecuencia = "";
        String valorNumero = "";

        for (int i = 0; i < exp.length(); i++) {
            valorSecuencia = exp.charAt(i) + "";
            valorNumero = valorSecuencia;
            boolean seguir = true;
            while (seguir && tipoOperador(exp.charAt(i) + "").equals("numero")) {
                if (i != exp.length() - 1) {
                    if (tipoOperador(exp.charAt(i + 1) + "").equals("numero")) {
                        valorNumero += exp.charAt(i + 1);
                        i++;
                    } else {
                        valorSecuencia = valorNumero;
                        seguir = false;
                    }
                } else {
                    valorSecuencia = valorNumero;
                    seguir = false;
                }
            }
            switch (tipoOperador(valorSecuencia)) {
                case "(":
                    pila.push(valorSecuencia);
                    break;
                case ")":
                    Nodo res = pila.getCabeza();
                    while (res != null && !res.getValor().equals("(")) {
                        resultado.add(res.getValor());
                        res = res.getSiguiente();
                        pila.pop();
                    }
                    pila.pop();
                    break;
                case "operador":
                    Nodo nuevo = pila.getCabeza();
                    while (pila.getCabeza() != null && prioridad(pila.getCabeza().getValor().toString()) >= prioridad(valorSecuencia)) {
                        resultado.add(pila.getCabeza().getValor());
                        nuevo = pila.getCabeza().getSiguiente();
                        pila.pop();
                    }
                    pila.push(valorSecuencia);
                    break;
                case "numero":
                case "variable":
                    resultado.add(valorSecuencia);
                    break;
            }

        }
        Nodo nuevo = pila.getCabeza();
        while (nuevo != null) {
            resultado.add(nuevo.getValor());
            nuevo = nuevo.getSiguiente();
        }
		
        return resultado;

    }

    private String evaluar(ArrayList notacion) {
        Pila pila = new Pila();
		
        for (int i = 0; i < notacion.size(); i++) {
            if (tipoOperador(notacion.get(i).toString()).equals("operador")) {
                float a = 0, b = 0;
                b = Float.parseFloat(String.valueOf(pila.getCabeza().getValor()));
                pila.pop();
				
                a = Float.parseFloat(String.valueOf(pila.getCabeza().getValor()));
                pila.pop();
				System.out.println("-----------------------");
				System.out.println("Valor a = "+a);
				System.out.println("Valor b = "+b);
				System.out.println("Operador = "+notacion.get(i));
				System.out.println("Resultado = "+(realizarOperaacion(a, b, notacion.get(i).toString())));
				System.out.println("-----------------------");
                pila.push(realizarOperaacion(a, b, notacion.get(i).toString()));

            } else {
                pila.push(notacion.get(i));

            }
        }
        return String.valueOf(pila.getCabeza().getValor());
    }

    private float realizarOperaacion(float a, float b, String operador) {
        switch (operador) {
            case "^":
                return (float) Math.pow(a, b);
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "+":
                return a + b;
            case "-":
                return a - b;
        }
        return 0;
    }

    private String tipoOperador(String oper) {
        switch (oper) {
            case "^":
                return "operador";
            case "*":
                return "operador";
            case "/":
                return "operador";
            case "+":
                return "operador";
            case "-":
                return "operador";
            case "(":
                return "(";
            case ")":
                return ")";
        }
        for (int i = 97; i < 122; i++) {
            char b = (char) (i);
            if (oper.equals(b)) {
                return "variable";
            }
        }
        return "numero";
    }

    private int prioridad(String operador) {

        switch (operador) {
            case "^":
                return 5;

            case "*":
            case "/":
                return 3;

            case "+":
            case "-":
                return 2;

            case "(":
                return 1;

        }
        return 0;
    }

}
