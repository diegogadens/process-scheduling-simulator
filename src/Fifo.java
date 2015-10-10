/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonadores;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Luiz
 */
public class Fifo extends Escalonador {

    //public List clone;
    public Fifo(JList prontos, JTextArea processador) {
        super(prontos, processador);

    }

    @Override
    public void run() {
        while (executar) {
            if(!lista.isEmpty())
                executaProcesso();
            else
                processador.setText("");
        }
        //executar = false;
        processador.setText("");
    }

    public void removeProcesso() {
        lista.removeFirst();
    }

    public void executaProcesso() {
        Processo p = (Processo) lista.getFirst();
        try {
            System.out.println("Executando: " + p);
            atualiza();
            Thread.sleep(p.getTempo());
            removeProcesso();
        } catch (InterruptedException ex) {
            Logger.getLogger(Fifo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualiza() {
        //Atualiza Jlist e JText Area.
        //System.out.println("entrou na thread do atualizador");
        if (lista.size() > 1) {
        //    System.out.println("OI: ");

            clone = lista.subList(1, lista.size() );

            prontos.setListData(clone.toArray());

            processador.setText(lista.getFirst().toString());
        } else {
           // System.out.println("OI2: ");

            processador.setText(lista.getFirst().toString());

            prontos.setListData(new String[0]);
        }

    }
}
