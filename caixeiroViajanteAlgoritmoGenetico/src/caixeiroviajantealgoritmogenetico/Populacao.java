package caixeiroviajantealgoritmogenetico;

import java.util.ArrayList;

/**
 *
 * @author jadyl
 */
public class Populacao {
    private ArrayList<Populacao> listaDeIndividuos = new ArrayList();
    private ArrayList<Populacao> listaDeIndividuosAux = new ArrayList();
    private int individuo[];
    private int qtdeVertices;
    private int k;
    private int matriz[][];
    private int esforco;
    
    public Populacao(){
        this.individuo = individuo;
        this.esforco = esforco;
    }
    
    public void adicionaIndividuo(Populacao novoIndividuo){
        listaDeIndividuos.add(novoIndividuo);
    }
    
    public void adicionaIndividuoAux (Populacao novoIndividuo){
        listaDeIndividuosAux.add(novoIndividuo);
    }
    
    public void removeIndividuo(int i){
        listaDeIndividuos.remove(i);
    }
    
    public void removeIndividuoAux(int i){
        listaDeIndividuosAux.remove(i);
    }
    
    public void mostraPopulacao(){
        for (int i = 0; i < getListaDeIndividuos().size(); i++) {
            System.out.print("Individuo " + i + ": ");
            for (int j = 0; j < qtdeVertices; j++) {
                System.out.print(getListaDeIndividuos().get(i).individuo[j] + " ");
            }
            System.out.println();
            System.out.print("Esforço: " + getListaDeIndividuos().get(i).esforco);
            System.out.println();
        }
        System.out.println();
    }
    public void mostraPopulacaoAux(){
        System.out.println("AQUIIIIIIIIII");
        for (int i = 0; i < getListaDeIndividuosAux().size(); i++) {
            System.out.print("Individuo " + i + ": ");
            for (int j = 0; j < qtdeVertices; j++) {
                System.out.print(getListaDeIndividuosAux().get(i).individuo[j] + " ");
            }
            System.out.print("   Esforço: " + getListaDeIndividuosAux().get(i).esforco);
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
    public void setListaDeIndividuos(ArrayList<Populacao> lista){
        this.listaDeIndividuos = lista;
    }
    public void setQtdeVertices(int qtde){
        this.qtdeVertices = qtde;
    }
    public int getQtdeVertices(){
        return qtdeVertices;
    }
    public void setMatriz(int[][] matrizAdj){
        this.matriz = matrizAdj;
    }
    public int[][] getMatriz(){
        return matriz;
    }
    public ArrayList<Populacao> getListaDeIndividuosAux(){
        return listaDeIndividuosAux;
    }
    public void setEsforco(int esforco){
        this.esforco = esforco;
    }
    public int getEsforco(){
        return esforco;
    }
}
