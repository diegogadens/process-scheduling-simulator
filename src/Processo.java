/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escalonadores;

/**
 *
 * @author Luiz
 */
public class Processo {

    private int prioridade;
    private int fila;

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    private String id;
    private int tempo;
    /*
     * Constructor
     */
    public Processo(String id, int prioridade, int fila, int tempo){
        this.id = id;
        this.prioridade = prioridade;
        this.fila = fila;
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return ("Processo " + id);
    }

    public String getID()
    {
        return id;
    }

    public void setID(String novaID)
    {
        this.id = novaID;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
