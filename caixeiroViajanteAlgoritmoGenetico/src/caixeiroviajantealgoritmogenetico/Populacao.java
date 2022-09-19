package caixeiroviajantealgoritmogenetico;

import java.util.ArrayList;

/**
 *
 * @author jadyl
 */
public class Populacao {
    private ArrayList<Populacao> listaDeIndividuos = new ArrayList();
    private int individuo[];
    private int qtdeVertices;
    private int k;
    
    public Populacao(){
        this.individuo = individuo;
    }
    
    public void adicionaIndividuo(Populacao novoIndividuo){
        listaDeIndividuos.add(novoIndividuo);
    }
    
    public void mostraPopulacao(){
        for (int i = 0; i < getListaDeIndividuos().size(); i++) {
            System.out.print("Individuo " + i + ": ");
            for (int j = 0; j < qtdeVertices; j++) {
                System.out.print(getListaDeIndividuos().get(i).individuo[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    
    //*******************MÉTODOS GETs E SETs*******************
    public void setIndividuo(int[] individuo){
        this.individuo = individuo;
    }
    public int[] getIndividuo(){
        return individuo;
    }
    public void setK(int qtde){
        this.k = qtde;
    }
    public ArrayList<Populacao> getListaDeIndividuos(){
        return listaDeIndividuos;
    }
    public void setQtdeVertices(int qtde){
        this.qtdeVertices = qtde;
    }
    public int getQtdeVertices(){
        return qtdeVertices;
    }
}
