# Project of the Curricular Unit: Multiparadigm Programming Project

## About:
Implementation of an Image Editor with the images being stored as QuadTrees, and modified (rotation, filtering, etc.) using functional programming concepts. It also has a GUI for visualizing the images and applying effects to them.

## Specifications
- Project done in a group of 2 people
- Technologies used:
  - Scala
  - JavaFX
  - Git
  - Java
  

## Guia de Utilização de "PPM Gallery"

-A opção "Grid" permite ao utilizador selecionar imagens especificas para serem apresentadas (que estejam na diretoria src/Images),
podendo trabalhar com um workspace de até 9 imagens ao mesmo tempo.
O utilizador pode substituir as imagens por outras a qualquer altura. Caso deseje editar uma imagem o utilizador 
pode carregar no botão edit, que irá redirecionar o mesmo para uma window em que poderá adicionar as imagens a alterar 
(File-New).
Esta opção será util para visualizar várias imagens editadas que foram guardadas ao mesmo tempo, comparando resultados.
A "Grid" guarda o estado, as imagens que um utilizador pesquisar permaneceram mesmo que a aplicação seja reiniciada e até
o utilizador apagar uma dada imagem.

-A opção "Slideshow" permite visualizar quaisquer imagens adicionadas a partir de (File-New) e, pode ainda fazer edição as 
mesmas. É possivel remover imagens e salvar imagens no estado atual.

-A opção "Exit" permite ao utilizador sair da aplicação.

Observações de back-end

-Função applyNoise() é impura e devolve noise cinzento
-Função applyNoisePure() é pura e devolve Noise a cores

Observações do tree-testing

-Devido às limitações do site, apenas utilizámos duas tarefas
