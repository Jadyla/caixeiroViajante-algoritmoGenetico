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
        //porcentagem da população que sofrerá mutação, cruzamento e que passará da seleção. A porcentagem vezes a quantidade 
        //de vértices vai indicar a quantidade de trocas na mutação
        
        int k = 10;
        double porcentagem = 0.5;
        int iteracoes = 5;
        
        //mutacoesPorIndividuo = mudancas dentro do individuo
        //qtdeMutacoes = numero de mutacoes na fase de mutacao
        //qtdeDeCruzamentos = numero de cruzamentos na fase de cruzamentos
        //passaDaSelecao = numero de melhores individuos que vai passar para a proxima iteracao (elitismo)
        int qtdeDeCruzamentos;
        int mutacoesPorIndividuo;
        int qtdeDeMutacoes;
        int passaDaSelecao;
        int qtdeGerados;
        
        qtdeGerados = k;
        qtdeDeCruzamentos = (int)(porcentagem * k);
        qtdeDeMutacoes = (int)(porcentagem * k);
        passaDaSelecao = (int)(porcentagem * k);
        
        Populacao aux = new Populacao();
        int qtdeVertices, peso, vertice, qtdeArestas;
        int matrizAdj[][] = null;
        String caminhos;
        
        //*****************LEITURA*********************
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./Teste_1.txt");
        
        qtdeVertices = Integer.parseInt(text.get(0));
        matrizAdj = new int[qtdeVertices][qtdeVertices];
        
        //a porcentagem determinada vezes a quantidade de vertices = quantidade de trocas na mutacao
        mutacoesPorIndividuo = (int)(porcentagem * qtdeVertices);
        
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
        
        System.out.println("--------------------MATRIZ--------------------");
        for (int i = 0; i < qtdeVertices; i++) {
            for (int j = 0; j < qtdeVertices; j++) {
                System.out.print(matrizAdj[i][j] + "    ");
            }
            System.out.println("  ");
        }
        System.out.println();
        
        aux.setK(k);
        aux.setQtdeVertices(qtdeVertices);
        aux.setMatriz(matrizAdj);
        
        long tempoInicial = System.currentTimeMillis();
        for (int i = 0; i < iteracoes; i++) {
            System.out.println("#################### GERAÇÃO " + i + " ####################");
            System.out.println();
            System.out.println();
            criaIndividuos(qtdeGerados, qtdeVertices, aux);
            aux.mostraPopulacao();

            cruzamento(aux, qtdeDeCruzamentos, k, qtdeVertices);
            aux.mostraPopulacao();
            
            k = k + qtdeDeCruzamentos;
            mutacao(aux, qtdeDeMutacoes, k, qtdeVertices, mutacoesPorIndividuo);
            aux.mostraPopulacao();

            k = k - qtdeDeCruzamentos;
            selecao (k, qtdeVertices, aux, passaDaSelecao, qtdeDeCruzamentos);
            aux.mostraPopulacao();
            qtdeGerados = k - passaDaSelecao;
        }
        long tempoFinal = System.currentTimeMillis();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " ms");
        System.out.println("Gerações: " + iteracoes);
        System.out.println("População: " + k + " indivíduos");
        System.out.println("Cruzamentos por geração: " + qtdeDeCruzamentos);
        System.out.println("Mutações por geração: " + qtdeDeMutacoes);
        System.out.println("Trocas por mutação: " + mutacoesPorIndividuo);
        exibeMelhorCaminho(aux, qtdeVertices);
    }
    
    //********************CRIA INDIVÍDUOS*******************
    //criação dos indvíduos de maneira aleatória
    public static void criaIndividuos(int k, int tam, Populacao aux){
        System.out.println("--------------------POPULAÇÃO--------------------");
        boolean visitados[];
        int caminho[];
        
        visitados = new boolean[tam];
        
        for (int i = 0; i < k; i++) {
            caminho = new int[tam];
            //caminho[0] = 0;
            //visitados[0] = true;
            for (int j = 0; j < tam; j++) {
                visitados[j] = false;
            }
            for (int j = 0; j < tam; j++) {
                int aleat = geraNumerosAleatorios(tam, visitados);
                caminho[j] = aleat;
                visitados[aleat] = true;
            }
            Populacao aux2 = new Populacao();
            aux2.setIndividuo(caminho);
            aux2.setEsforco(calculaEsforco(caminho, aux, tam));
            aux.adicionaIndividuo(aux2);
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
    
    //********************CRUZAMENTO*******************
    public static void cruzamento(Populacao aux, int qtdeDeCruzamentos, int k, int tam){ //qtdeDeCruzamentos = 0.2 * k (20%)
        System.out.println("--------------------CRUZAMENTO--------------------");
        Random gerador = new Random();
        int nPai, nMae;
        int filho[], pai[], mae[];
        int divisao = aux.getQtdeVertices() / 2;
        
        
        for (int i = 0; i < qtdeDeCruzamentos; i++) {
            filho = new int[tam];
            do{
                nPai = gerador.nextInt(k);
                nMae = gerador.nextInt(k);
            }while (nPai == nMae);
            pai = aux.getListaDeIndividuos().get(nPai).getIndividuo();
            mae = aux.getListaDeIndividuos().get(nMae).getIndividuo();
            for (int j = 0; j < divisao; j++) {
                filho[j] = pai[j]; //primeia metade igual ao pai
            }
            for (int j = divisao; j < tam; j++) {
                filho[j] = mae[j]; //segunda metade igual a mae
            }
            
            
            //para PRINTAR todo o processo de criação dos filhos: DESCOMENTAR linhas abaixo
            /*
            System.out.print("PAI (" + nPai + "): ");
            for (int j = 0; j < tam; j++) {
                System.out.print(pai[j]);
            }
            System.out.println();
            System.out.print("MAE (" + nMae + "): ");
            for (int j = 0; j < tam; j++) {
                System.out.print(mae[j]);
            }
            System.out.println();
            System.out.print("FILHO: ");
            for (int j = 0; j < tam; j++) {
                System.out.print(filho[j]);
            }
            System.out.println();
            System.out.println("*************");
            */
            
            
            Populacao aux2 = new Populacao();
            aux2.setIndividuo(filho);
            aux2.setEsforco(calculaEsforco(filho, aux, tam));
            aux.adicionaIndividuo(aux2);
        }
        
    }
    
    //********************MUTAÇÃO*******************
    //a mutação feita foi de troca, entre o meio +1 e o meio -1
    public static void mutacao(Populacao aux, int qtdeDeMutacoes, int k, int tam, int mutacoesPorIndividuo){
        System.out.println("---------------------MUTAÇÃO---------------------");
        Random gerador = new Random();
        int aleat, aux3, gene1, gene2;
        
        for (int i = 0; i < qtdeDeMutacoes; i++) {
            aleat = gerador.nextInt(k);
            for (int j = 0; j < mutacoesPorIndividuo; j++) {
                do{
                    gene1 = gerador.nextInt(tam);
                    gene2 = gerador.nextInt(tam);
                }while(gene1 == gene2);

                //para PRINTAR todo o processo de mutação: DESCOMENTAR os comentários abaixo
                /*System.out.print("Antes (" + aleat + "): ");
                for (int n = 0; n < tam; n++) {
                    System.out.print(aux.getListaDeIndividuos().get(aleat).getIndividuo()[n]);
                }
                System.out.println();*/
                aux3 = aux.getListaDeIndividuos().get(aleat).getIndividuo()[gene1];
                aux.getListaDeIndividuos().get(aleat).getIndividuo()[gene1] = aux.getListaDeIndividuos().get(aleat).getIndividuo()[gene2];
                aux.getListaDeIndividuos().get(aleat).getIndividuo()[gene2] = aux3;
                /*System.out.print("Depois (" + aleat + "): ");
                for (int n = 0; n < tam; n++) {
                    System.out.print(aux.getListaDeIndividuos().get(aleat).getIndividuo()[n]);
                }
                System.out.println();
                System.out.println("********");*/
            }
        }
    }
    
    //********************SELEÇÃO*******************
    public static void selecao(int k, int tam, Populacao aux, int passaDaSelecao, int qtdeDeCruzamentos){
        System.out.println("---------------------SELEÇÃO---------------------");
        int menorEsforco, posicao;
        
        //*****************ORDENAÇÃO*********************
        while (aux.getListaDeIndividuos().size() != 0){
            posicao = 0;
            menorEsforco = aux.getListaDeIndividuos().get(0).getEsforco();
            
            for (int i = 1; i < aux.getListaDeIndividuos().size(); i++) {
                if (aux.getListaDeIndividuos().get(i).getEsforco() == -1){
                    continue;
                }else{
                    if (aux.getListaDeIndividuos().get(i).getEsforco() <= menorEsforco){
                        menorEsforco = aux.getListaDeIndividuos().get(i).getEsforco();
                        posicao = i;
                    }
                }
            }
            
            Populacao aux2 = new Populacao();
            aux2.setIndividuo(aux.getListaDeIndividuos().get(posicao).getIndividuo());
            aux2.setEsforco(aux.getListaDeIndividuos().get(posicao).getEsforco());
            aux.adicionaIndividuoAux(aux2);
            aux.removeIndividuo(posicao);
        }
        
        aux.getListaDeIndividuos().addAll(aux.getListaDeIndividuosAux());
        aux.getListaDeIndividuosAux().clear();
        
        //*****************ELITISMO*********************
        for (int i = passaDaSelecao; i < k+qtdeDeCruzamentos; i++) {
            aux.removeIndividuo(passaDaSelecao);
        }
        
    }
    
    
    
    //********************MÉTODOS AUXILIARES*******************
    public static int calculaEsforco(int[] v, Populacao aux, int tam){
        int soma = 0;
        boolean aux3 = false;
        for (int j = 0; j < tam-1; j++) {
            if (aux.getMatriz()[v[j]][v[j+1]] == -1){
                soma = -1;
                break;
            }
            soma = soma + aux.getMatriz()[v[j]][v[j+1]];
        }
        for (int i = 0; i < tam; i++) {
            for (int j = i+1; j < tam; j++) {
                if (v[i] == v[j]){
                    soma = -1;
                    aux3 = true;
                    break;
                }
            }
            if(aux3){
                break;
            }
        }
        if (aux.getMatriz()[v[tam-1]][v[0]] == -1){
            soma = -1;
        }
        return soma;
    }
    
    public static void exibeMelhorCaminho(Populacao aux, int tam){
        System.out.print("Melhor caminho: ");
        for (int i = 0; i < tam; i++) {
            System.out.print(aux.getListaDeIndividuos().get(0).getIndividuo()[i] + " ");
        }
        System.out.println();
        System.out.println("Esforço: " + aux.getListaDeIndividuos().get(0).getEsforco());
    }
}
