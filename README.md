<img width="816" height="634" alt="image" src="https://github.com/user-attachments/assets/957e6483-6eca-44d5-a58b-631d5445e016" />


<img width="1322" height="590" alt="image" src="https://github.com/user-attachments/assets/6a3e958c-7eb6-4eb7-8d71-82d4a6d1b4f7" />


https://app.diagrams.net/#G1hROKAkgqtNUdFsNCblaknKJjJZTwFMMj#{%22pageId%22%3A%22ZfpAr39wWB8uVWxP9SLy%22}

https://docs.google.com/document/d/1A2wMIPhPiMmkt90xg02JmvIB3o3XdmUMnpz_CZDe1JU/edit?tab=t.0


Contexto do projeto: 

A proposta desse projeto é a criação de uma calculadora capaz de converter de escalas comumente usadas na disciplina de Eletrônica Analógica e Digital, ao inserir o valor na escala atual e posteriormente a escala desejada. Bem como realizar o cálculo de Lei de Ohm ao inserir dois valores e suas unidades de medida.


Explicaçẽo das classes principais:

A classe Converter funciona como a classe mãe (superclasse) que centraliza a lógica global do sistema, armazenando o valor numérico base inserido pelo usuário e as regras para seleção de grandezas e escalas. Ela é responsável por gerenciar os dados brutos e preparar o terreno para a normalização das unidades, mas não realiza os cálculos matemáticos finais da Lei de Ohm nem aplica sozinha os multiplicadores específicos de cada prefixo métrico.

As classes Mega, Kilo, Milli (citada como MIII no diagrama) e Micro são as classes filhas que herdam as propriedades da classe principal (elas possuem relação de poliformismo com a classe principal) para tratar cada escala de forma personalizada. Elas fazem a aplicação do fator multiplicador correto (de Micro a Mega) e exibem o valor formatado para o usuário, porém não possuem autonomia para alterar as regras gerais de grandeza sem o suporte da classe mãe.

A classe Calcular Ohm atua como o motor de processamento físico do software, trabalhando em conjunto com o conversor para receber os números já ajustados e aplicar as fórmulas de Tensão, Corrente e Resistência. Ela faz a execução dos cálculos complexos com precisão de quatro casas decimais, mas não faz a gestão direta da interface de entrada de dados nem a transformação inicial dos prefixos métricos, dependendo inteiramente dos valores previamente tratados pelas outras classes.

A classe GerenciadorHistorico funciona como a memória central e o mensageiro inteligente do programa, garantindo que todas as conversões sejam anotadas em um único lugar seguro e compartilhado. Através de um sistema de "avisos", ela comunica automaticamente qualquer alteração na lista de dados para que as telas do sistema se mantenham sempre atualizadas. Ela é a guardiã da integridade das informações, sendo responsável por guardar, remover e organizar os registros em silêncio, mas sem se envolver diretamente com a aparência visual da tabela ou com as regras matemáticas de conversão.


