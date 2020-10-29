package Lexico;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Pintar {

    public JTextPane componente = new JTextPane();
    //public StyleContext sc = new StyleContext();
    //public DefaultStyledDocument doc;
    public StyledDocument doc;
    private String texto;

    public Pintar() {
    }

    public Pintar(JTextPane componente) {
        this.componente = componente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void insertarCodigo(String texto) {
        this.texto = texto;
        doc = new DefaultStyledDocument();
        componente.setDocument(doc);
        try {
            doc.insertString(0, texto, null);
        } catch (Exception ex) {
            System.out.println("ERROR: no se pudo establecer estilo de documento");
        }

    }
    
     public void insertarCodigoPane(JTextPane componente) {                
        this.componente = componente;        
        this.doc = this.componente.getStyledDocument();       
    }

    public void setComponente(JTextPane componente) {
        this.componente = componente;
    }

    public void pintaAzulBold(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(25, 15, 230));
        StyleConstants.setBold(attributes, true);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }
    public void pintaRojoFuerte(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(255, 0, 0));
        StyleConstants.setBold(attributes, true);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }
    
    public void pintaAzulFuerte(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(1, 87, 155));
        StyleConstants.setBold(attributes, true);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }

    public void pintaAzul(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(25, 15, 230));
        StyleConstants.setBold(attributes, false);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }

    public void pintaRojo(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(245, 0, 87));
        StyleConstants.setBold(attributes, false);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }


    public void pintaRojoError(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setBackground(attributes, new Color(245, 0, 87));
        StyleConstants.setBold(attributes, false);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }

    public void pintaRojoBajo(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(255, 23, 68));
        StyleConstants.setBold(attributes, false);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }

    public void pintaVerde(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(0, 200, 83));
        StyleConstants.setBold(attributes, true);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }

    public void pintaMorado(int posini, int posfin) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, new Color(74, 20, 140));
        StyleConstants.setBold(attributes, true);
        doc.setCharacterAttributes(posini, posfin, attributes, true);

    }
}
