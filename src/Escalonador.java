/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonadores;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Luiz
 */
public class Escalonador extends Thread {

    JList prontos;
    List clone;
    JTextArea processador;
    LinkedList lista = new LinkedList<Processo>();
    //Atualizador at = new Atualizador();
    boolean executar = false;
    int fatiaDeTempo = 100;

    private int tempoFila1, tempoFila2;

    public int getFatiaDeTempo() {
        return fatiaDeTempo;
    }

    public void setFatiaDeTempo(int fatiaDeTempo) {
        this.fatiaDeTempo = fatiaDeTempo;
    }

    public Escalonador(JList prontos, JTextArea processador) {
        this.prontos = prontos;
        this.processador = processador;
    }

    public void novoProcesso(Processo p) {
        if(!executar){
            lista.addLast(p);
            prontos.setListData(lista.toArray());
        }
        else{
            lista.addLast(p);
            prontos.setListData(lista.subList(1, lista.size()).toArray());
        }
    }

    public void setExecutar(boolean executar) {
        this.executar = executar;
    }

    
    public int getTempoFila1() {
        return tempoFila1;
    }

    public void setTempoFila1(int tempoFila1) {
        this.tempoFila1 = tempoFila1;
    }

    public int getTempoFila2() {
        return tempoFila2;
    }

    public void setTempoFila2(int tempoFila2) {
        this.tempoFila2 = tempoFila2;
    }
    
    
}
