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

public class Prioridades extends Escalonador {

    //public List clone;
    public Prioridades(JList prontos, JTextArea processador) {
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
            atualiza();
            removeProcesso(); 

            while(p.getTempo()>0)
            {
                System.out.println("Prioridades Executando: " + p);
                if(!entrouProcessoMaiorPrioridade(p.getPrioridade()))
                {
                    if(p.getTempo()>100)
                    {
                        System.out.println("IF");
                        try { Thread.sleep(100);} catch (InterruptedException ex) {
                            Logger.getLogger(Prioridades.class.getName()).log(Level.SEVERE, null, ex);}
                        p.setTempo(p.getTempo()-100);
                        System.out.println(p.getTempo());
                        ordena(lista);
                    }
                    else
                    {
                        System.out.println("Else");
                        try {Thread.sleep(p.getTempo());} catch (InterruptedException ex) {
                            Logger.getLogger(Prioridades.class.getName()).log(Level.SEVERE, null, ex);                }
                        p.setTempo(0);
                        System.out.println(p.getTempo());
                        ordena(lista);
                    }
                }
                else
                {
                    lista.addLast(p);
                    ordena(lista);
                    p = (Processo) lista.getFirst();
                    atualiza();
                    removeProcesso();
                }
            }

    }


    public boolean entrouProcessoMaiorPrioridade(int prioridadeAtual)
    {
        if(lista.isEmpty())
            return false;
        else
        {
            Processo aux = (Processo) lista.getFirst();
            if(aux.getPrioridade() < prioridadeAtual)
            {
                return true;
            }
            else
                return false;
        }
    }



    public void ordena(LinkedList aOrdenar)
    {
        LinkedList aux = new LinkedList<Processo>();
        int limite = aOrdenar.size();

        for(int i=0; i<limite; i++)
        {

            Processo maiorPrioridade = (Processo) aOrdenar.get(0);
            int índiceARemover = 0;

            for(int j=0; j<aOrdenar.size(); j++)
            {
                Processo atual = (Processo) aOrdenar.get(j);
                if(atual.getPrioridade() < maiorPrioridade.getPrioridade())
                {
                    maiorPrioridade = atual;
                    índiceARemover = j;
                }

            }
            aOrdenar.remove(índiceARemover);
            aux.addLast(maiorPrioridade);
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
