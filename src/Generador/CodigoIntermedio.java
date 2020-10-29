/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generador;

import Sintactico.Nodo;
import java.util.ArrayList;
import java.util.Stack;
import Posfija.Posfija;

/**
 *
 * @author Jose
 */
public class CodigoIntermedio {

    private ArrayList<String> codigo;
    private int gvtemporal;
    private Nodo condicion_alta;
    private String nodoAnterior;
    private int getemporal;
    private Stack<Integer> etiquetas;
    private Stack<String> tempIncCiclo;
    private Stack<String> tempVarCiclo;

    public CodigoIntermedio() {
        this.codigo = new ArrayList<>();
        this.gvtemporal = -1;
        this.getemporal = -1;
        this.condicion_alta = new Nodo("", "", 0);
        this.nodoAnterior = "";
        this.etiquetas = new Stack<>();
        this.tempIncCiclo = new Stack<>();
        this.tempVarCiclo = new Stack<>();
    }

    public void recorrerArbolSintactico(Nodo arbol) {
        Buscador buscador = new Buscador();
        switch (arbol.getNombreRaiz()) {
            
            case "VARIABLE_DA":
                buscador.cargarPilaConHojas(arbol);
                if(buscador.getPila().size() == 4){
                    buscador.getPila().remove(0);
                }
                this.tempVarCiclo.push(((Nodo)buscador.getPila().get(0)).getValor());
                String iterador = buscador.getPilaString();
                this.codigo.add(iterador+";");
                break;
            case "R_INCDEC":
                buscador.cargarPilaConHojas(arbol);
                String inc_dec = ((Nodo)buscador.getPila().get(0)).getValor();
                buscador.getPila().remove(0);
                if(inc_dec.equals("inc")){
                    inc_dec = "+";
                }else{
                    inc_dec = "-";
                }
                this.gvtemporal++;
                String var = this.tempVarCiclo.pop();
                String codigoTemp = "t"+this.gvtemporal+"="+var+inc_dec+buscador.getPilaString()+";";
                String remVar = var+"="+"t"+this.gvtemporal+";";
                this.tempIncCiclo.push(codigoTemp+"\n"+remVar);
                
                break;            
            case "INICIO_CICLO":                
                this.getemporal++;
                String etiqueta_0 = "E" + this.getemporal + ":";                
                this.etiquetas.push(this.getemporal);
                this.getemporal++;                
                String condicion = "if false " + this.obtenerVariableAnterior() + " goto E" + this.getemporal + ";";
                this.etiquetas.push(this.getemporal);
                this.codigo.add(etiqueta_0);
                this.codigo.add(condicion);                
                break;
            case "FIN_MIENTRAS":
            case "REPETIR_FIN":
                String etiqueta_fin = "E" + this.etiquetas.pop() + ":";
                String salto_fin_ciclo = "goto E" + this.etiquetas.pop() + ";";
                if(arbol.getNombreRaiz().equals("REPETIR_FIN")){
                    this.codigo.add(this.tempIncCiclo.pop());
                }
                this.codigo.add(salto_fin_ciclo);
                this.codigo.add(etiqueta_fin);

                break;
            case "ASIGNACION":
                Nodo listaVar = buscador.rastrearNodo(arbol, "LISTA_VARIABLES");
                //se localizan las variables y se guardan en una pila
                buscador.cargarPilaConHojaEspecifica(listaVar, "NOMBRE_VAR");
                //se obtine la pila con los nombres de variables
                Stack<Object> variables = buscador.getPila();

                //se resetea el buscador
                buscador = new Buscador();
                Nodo exp_asig_basica = buscador.rastrearNodo(arbol, "EXP_ASIG_BASICA");
                if (exp_asig_basica.getNombreRaiz().equals("")) {
                    //SE TRATA DE UNA ASIGNACION SIMPLE EJEMPLO: g = 0;
                    Nodo tipo_valor = buscador.rastrearNodo(arbol, "TIPO_VALOR");
                    //carga la hoja o valor
                    buscador.cargarPilaConHojas(tipo_valor);
                } else {
                    //carga las hojas u operandos a la pila del buscador
                    buscador.cargarPilaConHojas(exp_asig_basica);
                }

                //ASIGNACION
                String codigo = "";

                //Se convierte la exp a estring
                //buscador.imprimirPila();
                String exp = buscador.getPilaString();
                Posfija pos = new Posfija();
                //Se obtiene la exp en notacion posfija
                ArrayList<Object> expCov = pos.posfija(exp);
                if (expCov.size() == 1) {
                    //codigo += ((Nodo)variables.pop()).getValor()+"="+expCov.get(0).toString()+";";
                    this.codigo.add(((Nodo) variables.pop()).getValor() + "=" + expCov.get(0).toString() + ";");
                } else {
                    Stack<Object> auxpila = new Stack<>();
                    for (int i = 0; i < expCov.size(); i++) {
                        if (esOperador(expCov.get(i).toString())) {
                            //operando operador operando
                            this.gvtemporal++;
                            String val2 = auxpila.pop().toString();
                            String val1 = auxpila.pop().toString();
                            //codigo += "t" + this.gvtemporal + " = " + val1 + expCov.get(i).toString() + val2 + ";\n";
                            this.codigo.add("t" + this.gvtemporal + " = " + val1 + expCov.get(i).toString() + val2 + ";");
                            auxpila.push("t" + this.gvtemporal);

                        } else {
                            auxpila.push(expCov.get(i).toString());
                        }
                    }
                    for (int i = 0; i < variables.size(); i++) {
                        //codigo += ((Nodo) variables.get(i)).getValor() + " = t" + this.gvtemporal + ";\n";
                        this.codigo.add(((Nodo) variables.get(i)).getValor() + " = t" + this.gvtemporal + ";");
                    }
                }

                break;
            case "CONDICION_ALTA":
                this.condicion_alta = arbol;
                System.out.println("Se asigno");
                break;

            case "CONDICION_BAJA":
                Nodo condicion_baja = arbol;
                buscador.cargarPilaConHojas(condicion_baja);
                Stack<Object> parametros = buscador.getPila();
                this.gvtemporal++;
                String operando2 = ((Nodo) parametros.pop()).getValor();
                String operador = simOperRel(((Nodo) parametros.pop()).getValor());
                String operando1 = ((Nodo) parametros.pop()).getValor();
                this.codigo.add("t" + this.gvtemporal + "=" + operando1 + operador + operando2 + ";");
                

//                if(this.condicion_alta.getNombreRaiz().equals("")){
//                    int aux = this.gvtemporal;
//                    this.gvtemporal++;
//                    this.codigo.add("t"+this.gvtemporal+"=t"+(aux-1)+(this.condicion_alta.getHojas().get(1).getValor())+"t"+(aux));
//                    this.condicion_alta = null;
//                }
                break;
            case "CONDICION_INICIO":
                this.getemporal++;
                String if_entonces = "if false " + this.obtenerVariableAnterior() + " goto E" + this.getemporal + ";";
                this.codigo.add(if_entonces);
                this.etiquetas.push(this.getemporal);
                break;
            case "CONDICION_SINO":
                String inicio = "E" + this.etiquetas.pop() + ":";
                this.getemporal++;
                this.etiquetas.push(this.getemporal);
                String salto = "goto E" + this.getemporal + ";";
                this.codigo.add(salto);
                this.codigo.add(inicio);
                break;
            case "CONDICION_FIN":
                String fin_si = "E" + this.etiquetas.pop() + ":";
                this.codigo.add(fin_si);
                break;
            case "LISTA_IMPRECIONES":
                buscador.cargarPilaConHojas(arbol);
                for (int i = 0; i < buscador.getPila().size(); i++) {
                    Nodo objTexto = (Nodo) buscador.getPila().get(i);
                    if (!objTexto.getNombreRaiz().equals("OPER_AGRUP_COMA")) {
                        this.codigo.add("write " + objTexto.getValor() + ";");
                    }

                }
                buscador.getPila().clear();
                return;
            case "ASIGNACION_INC_DEC":
                buscador.cargarPilaConHojas(arbol);
                buscador.getPila().pop();
                String variable = ((Nodo) buscador.getPila().pop()).getValor();
                String oper = ((Nodo) buscador.getPila().pop()).getValor();
                if (oper.equals("inc")) {
                    oper = "+";
                } else {
                    oper = "-";
                }
                this.gvtemporal++;
                this.codigo.add("t" + this.gvtemporal + "=" + variable + oper + "1;");
                this.codigo.add(variable + "=t" + this.gvtemporal + ";");
                break;
        }

        //es raiz?        
        if (!arbol.getHojas().isEmpty()) {
            this.nodoAnterior = arbol.getNombreRaiz();
            if (arbol.getNombreRaiz().equals("CONDICION_ALTA")) {
                System.out.println("-------------------------------------------------");
            }
            for (Nodo hoja : arbol.getHojas()) {
                recorrerArbolSintactico(hoja);
            }
        }

    }

    private String obtenerVariableAnterior() {
        String linea = this.codigo.get(this.codigo.size() - 1);        
        String aux = "";
        for (int i = 0; i < linea.length(); i++) {
            if (linea.charAt(i) == '=') {
                return aux;
            } else {
                aux += linea.charAt(i);
            }
        }
        return null;
    }

    private String simOperRel(String operador) {
        switch (operador) {
            case "mayor":
                return ">";
            case "menor":
                return "<";
            case "igual":
                return "==";
            case "diferente":
                return "!=";
            case "mayorigual":
                return ">=";
            case "menorigual":
                return "==";
            case "no":
                return "!";
            case "Y":
                return "&&";
            case "O":
                return "||";
        }
        return null;
    }

    private boolean esOperador(String valor) {
        if (valor.equals("+") || valor.equals("-")
                || valor.equals("*") || valor.equals("/")
                || valor.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean esCaracterValido(String valor) {
        if (valor.equals("(") || valor.equals(")") || valor.equals("+")
                || valor.equals("-") || valor.equals("*") || valor.equals("/")
                || valor.equals("^")) {
            return false;
        }
        return true;
    }

    public void imprimirCodigo() {
        System.out.println("<--");
        for (String linea : this.codigo) {
            System.out.println(linea.toString());
        }
        System.out.println("-->");
    }

    public String getCodigo() {
        String aux = "";
        for (String linea : this.codigo) {
            aux += linea.toString() + "\n";
        }
        return aux;
    }
}
