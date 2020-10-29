/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantico;

import java_cup.runtime.Symbol;

/**
 *
 * @author Jose
 */
public class FilaTablaSimbolos {

    private String produccionRaiz;
    private String Token;
    private String valorToken;
    private Symbol symbol;

    public FilaTablaSimbolos() {
    }

    public FilaTablaSimbolos(String produccionRaiz, String Token, String valorToken, Symbol symbol) {
        this.produccionRaiz = produccionRaiz;
        this.Token = Token;
        this.valorToken = valorToken;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean filasIguales(FilaTablaSimbolos fila) {
        return this.getProduccionRaiz().equals(fila.getProduccionRaiz()) && this.getToken().equals(fila.getToken()) && this.getValorToken().equals(fila.getValorToken());
    }
    

    public String getProduccionRaiz() {
        return produccionRaiz;
    }

    public void setProduccionRaiz(String produccionRaiz) {
        this.produccionRaiz = produccionRaiz;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getValorToken() {
        return valorToken;
    }

    public void setValorToken(String valorToken) {
        this.valorToken = valorToken;
    }

}
