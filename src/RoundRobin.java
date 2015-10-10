/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escalonadores;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;

public class RoundRobin extends Escalonador{
    //public List clone;
    public RoundRobin(JList prontos, JTextArea processador) {
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

    public void reposicionaProcesso(Processo p) {

        if(p.getTempo()==0)
        {
            lista.removeFirst();
            prontos.setListData(lista.toArray());

        }
        else
        {
            lista.removeFirst();
            lista.addLast(p);
            prontos.setListData(lista.toArray());

        }
    }

    public void executaProcesso() {   
        Processo p = (Processo) lista.getFirst();
        
            System.out.println("Executando: " + p);
            atualiza();

            if(p.getTempo()>=fatiaDeTempo)
            {
                try {
                    Thread.sleep(getFatiaDeTempo());
                } catch (InterruptedException ex) {
                    Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                try {
                    Thread.sleep(p.getTempo());
                } catch (InterruptedException ex) {
                    Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(p.getTempo()>=fatiaDeTempo)
            {
                p.setTempo(p.getTempo() - fatiaDeTempo);
                lista.removeFirst();
                lista.addFirst(p);
                System.out.println(p.getTempo());
            }
            else
            {
                lista.removeFirst();
                lista.addFirst(p);
                p.setTempo(0);
                System.out.println(p.getTempo());
            }

            reposicionaProcesso(p);
            System.out.println("reposiciona");

        
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
