# Caixeiro Viajante (algoritmo genético)

### 💻 Tecnologias
<div style="display: flex;">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
</div>

### 🤙 E ae?
Este código foi desenvolvido com o objetivo de resolver o problema <b>Caixeiro Viajante</b>. Anteriormente, em outro repositório, que pode ser encontrado 
<a href="https://github.com/Jadyla/caixeiroViajante">clicando aqui</a>, foi construída uma solução utilizando um algoritmo ótimo. Aqui uma nova solução foi desenvolvida, 
desta vez utilizando um <b>algoritmo genético</b>, com o objetivo de possibilitar a resolução do problema com um número maior de vértices.

### 🤔 O Problema
Suponha que um caixeiro viajante tenha de visitar <i>n</i> cidades diferentes, iniciando e encerrando sua viagem na primeira cidade. Suponha, também, que não importa a ordem 
com que as cidades são visitadas e que de cada uma delas pode-se ir diretamente a qualquer outra. O problema do caixeiro viajante consiste em <b>descobrir a rota que 
torna mínima a viagem total</b>. <a href="http://www.mat.ufrgs.br/~portosil/caixeiro.html#:~:text=O%20problema%20do%20caixeiro%20viajante%20consiste%20em%20
descobrir%20a%20rota,e%20ent%C3%A3o%20volte%20a%20A." target="_blank">Clique aqui</a> para saber mais. <br>

### 🧬 Algoritmo Genético
Algoritmos Genéticos são algoritmos de otimização global, baseados nos mecanismos de seleção natural e da genética. Eles empregam uma estratégia de busca paralela e 
estruturada, mas aleatória, que é voltada em direção ao reforço da busca de pontos de "alta aptidão", ou seja, pontos nos quais a função a ser minimizada (ou maximizada) 
tem valores relativamente baixos (ou altos). Apesar de aleatórios, eles não são caminhadas aleatórias não direcionadas, pois exploram informações históricas para
encontrar novos pontos de busca onde são esperados melhores desempenhos. Isto é feito através de processos iterativos, onde cada iteração é chamada de geração. <br>
A cada iteração os seguintes etapas são realizadas:
<ul>
  <li>Geração da população: uma nova população de indivíduos é criada aleatoriamente, somada com os indivíduos vindos da selação da geração anterior;</li>
  <li>Cruzamento: dois indivíduos são selecionados aleatoriamente, gerando um novo indivíduo (filho) advindo da concatenação de parte dos dois aleatórios. Esse novo 
  indivíduo será acrescentado na população;</li>
  <li>Mutação: mudança nas posições do indivíduo sorteado aleatoriamente. Esse processo acontecerá com <i>n</i> indivíduos;</li>
  <li>Seleção: seleção de indivíduos para seguirem para a próxima população. Nesta etapa pode-se utilizar do elitismo para selecionar os melhores indivíduos para
  seguirem.</li>
</ul>
Para saber mais sobre o <b>algoritmo genético</b>, <a href="https://sites.icmc.usp.br/andre/research/genetic/#hist">clique aqui</a>.

### ⌨️ Código
Para resolução do problema, foi implementado um algoritmo genético, que seguiu parâmetros definidos dentro do código, como a quantidade de indivíduos gerados, 
porcentagem de indivíduos para cruzamento, mutação, etc. Segue abaixo algumas informações e descrições das etapas desta implementação:
<ul>
  <li>Geração da população: a cada iteração novos indivíduos foram criados de forma que sempre a mesma quantidade <i>k</i> de indivíduos permanecesse. Por isso, 
  a população gerada é de <i>k - passaDaSelecao</i>, que é a quantidade de indivíduos selecionados para prosseguirem para a próxima geração;</li>
  <li>Cruzamento: foram selecionados dois indivíduos aleatórios que foram divididos ao meio, gerando um novo indivíduo que possuirá a primeira metade do primeiro
  sorteado e a segunda metade do segundo sorteado;</li>
  <li>Mutação: a mutação escolhida foi de escolher <i>n</i> indivíduos aleatórios para fazer a mutação <i>n</i> vezes, trocando as posicoes selecionadas;</li> 
  <li>Seleção: no código desenvolvido foi aplicado o elitismo, que passou para a próxima geração os melhores dentre os indivíduos válidos.</li>
</ul>
Como saída tem-se que, após todas as iterações determinadas, o melhor dentre todos indivíduos indicará o melhor caminho possível com o menor esforço, dentre todos os 
indivíduos que foram gerados.<br>
De maneira geral, o resultado pode não ser o melhor dentre todas as possibilidades possíveis, mas é o melhor que pôde ser calculado.
