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

public class MultiplasFilas extends Escalonador{
    //public List clone;

    public MultiplasFilas(JList prontos, JTextArea processador) {
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
                if(atual.getFila() < menor.getFila())
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

    public void executaProcesso() {
        ordena(lista);
        Processo p = (Processo) lista.getFirst();
        
        atualiza();

        if(p.getFila() == 1)
        {
            try {
                Thread.sleep(getTempoFila1());
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiplasFilas.class.getName()).log(Level.SEVERE, null, ex);
            }

            p.setTempo(p.getTempo() - getTempoFila1());

            if(p.getTempo()<=0)
                lista.removeFirst();
            else
            {
                lista.removeFirst();
                p.setFila(2);
                p.setID(p.getID().replace("(1)", "(2)"));
                lista.add(p);
            }
            ordena(lista);
            atualiza();
            //reposicionaProcesso(p);

        }

        if(p.getFila() == 2)
        {
            try {
                Thread.sleep(getTempoFila2());
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiplasFilas.class.getName()).log(Level.SEVERE, null, ex);
            }

            p.setTempo(p.getTempo() - getTempoFila2());

            if(p.getTempo()<=0)
                lista.removeFirst();
            else
            {
                lista.removeFirst();
                p.setFila(3);
                p.setID(p.getID().replace("(2)", "(3)"));
                lista.add(p);
            }
            ordena(lista);
            atualiza();
        //reposicionaProcesso(p);

        }

        if(p.getFila() == 3)
        {
            try {
                Thread.sleep(p.getTempo());
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiplasFilas.class.getName()).log(Level.SEVERE, null, ex);
            }

            p.setTempo(0);
            lista.removeFirst();
        }
        ordena(lista);
        //atualiza();


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
