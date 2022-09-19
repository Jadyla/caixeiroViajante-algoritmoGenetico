package caixeiroviajantealgoritmogenetico;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author jadyl
 */
public class CaixeiroViajanteAlgoritmoGenetico {
    
    public static void main(String[] args) {
        //******************DEFINIR ALEATÓRIOS*********************
        //k = quantidade de indivíduos
        //iteracoes = numero de geracoes
        int k = 10;
        int iteracoes;
        
        Populacao aux = new Populacao();
        int qtdeVertices, peso, vertice, qtdeArestas;
        int matrizAdj[][] = null;
        String caminhos;
        
        //*****************LEITURA*********************
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./Teste_1.txt");
        
        qtdeVertices = Integer.parseInt(text.get(0));
        matrizAdj = new int[qtdeVertices][qtdeVertices];
        
        
        
        ///*****************CRIANDO MATRIZ DE ADJACÊNCIA*********************
        for (int i = 0; i < qtdeVertices; i++) {
            caminhos = text.get(i+1);
            qtdeArestas = (caminhos.split(";")).length;
            String[] semVertice = caminhos.split(" ");
            
            //esse 'for' vai analisar cada aresta a partir no vértice 'i'
            for (int j = 1; j <= qtdeArestas; j++) {
                String[] semHifen = semVertice[j].split("-");
                String[] semPontoEVirgula = semHifen[1].split(";");
                //System.out.println(semPontoEVirgula[0]);

                vertice = Integer.parseInt(semHifen[0]);
                peso = Integer.parseInt(semPontoEVirgula[0]);

                matrizAdj[i][vertice] = peso;
            }
            for (int j = 0; j < qtdeVertices; j++) {
                if (matrizAdj[i][j] == 0){
                    matrizAdj[i][j] = -1;
                }
            }
            
            matrizAdj[i][i] = -1;
        }
        
        System.out.println("****MATRIZ****");
        for (int i = 0; i < qtdeVertices; i++) {
            for (int j = 0; j < qtdeVertices; j++) {
                System.out.print(matrizAdj[i][j] + "    ");
            }
            System.out.println("  ");
        }
        System.out.println("*************");
        System.out.println(" ");
        
        aux.setK(k);
        aux.setQtdeVertices(qtdeVertices);
        criaIndividuos(k, qtdeVertices, aux);
        aux.mostraPopulacao();
    }
    
    //********************CRIA INDIVÍDUOS*******************
    //criação dos indvíduos de maneira aleatória
    public static void criaIndividuos(int k, int tam, Populacao aux){
        boolean visitados[];
        int caminho[];
        
        visitados = new boolean[tam];
        
        for (int i = 0; i < k; i++) {
            caminho = new int[tam];
            caminho[0] = 0;
            visitados[0] = true;
            for (int j = 1; j < tam; j++) {
                visitados[j] = false;
            }
            for (int j = 1; j < tam; j++) {
                int aleat = geraNumerosAleatorios(tam, visitados);
                caminho[j] = aleat;
                visitados[aleat] = true;
            }
            Populacao aux2 = new Populacao();
            aux2.setIndividuo(caminho);
            aux.adicionaIndividuo(aux2, i);
        }
    }
    
    
    //********************GERA ALEATÓRIOS*******************
    //nesta função já é verificado se foi visitado ou não para gerar o aleatorio
    public static int geraNumerosAleatorios(int tam, boolean[] visitados){
        Random gerador = new Random();
        int aleat;
        
        do{
            aleat = gerador.nextInt(tam);
        }while(visitados[aleat] == true);
        
        return aleat;
    }
}
