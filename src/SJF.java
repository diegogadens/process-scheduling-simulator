/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package escalonadores;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;

public class SJF extends Escalonador {

    //public List clone;
    public SJF(JList prontos, JTextArea processador) {
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
        ordena(lista);
        Processo p = (Processo) lista.getFirst();
        try {
            System.out.println("SJF Executando: " + p);
            atualiza();
            Thread.sleep(p.getTempo());
            removeProcesso();
        } catch (InterruptedException ex) {
            Logger.getLogger(SJF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ordena(LinkedList aOrdenar)
    {
        LinkedList aux = new LinkedList<Processo>();
        int limite = aOrdenar.size();

        for(int i=0; i<limite; i++)
        {

            Processo menor = (Processo) aOrdenar.get(0);
            int índiceARemover = 0;

            for(int j=0; j<aOrdenar.size(); j++)
            {
                Processo atual = (Processo) aOrdenar.get(j);
                if(atual.getTempo() < menor.getTempo())
                {
                    menor = atual;
                    índiceARemover = j;
                }

            }
            aOrdenar.remove(índiceARemover);
            aux.addLast(menor);
        }

        lista = aux;
        prontos.setListData(lista.toArray());
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
