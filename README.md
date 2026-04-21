Encapsulamento e a Classe Base
A classe principal, chamada Conversor, atua como o molde fundamental para todas as escalas do sistema. Nela, aplicou-se o conceito de Encapsulamento. O valor numérico digitado pelo usuário é guardado em uma variável privada, o que significa que nenhuma outra parte do sistema pode alterar esse número acidentalmente. O acesso e a modificação desse valor só ocorrem de forma controlada através dos métodos "get" e "set". Além disso, essa classe representa a "unidade base" do sistema (como o Volt ou Ampère puro, sem prefixos), possuindo um método fundamental chamado "exibir", que retorna o fator de escala 1.0.

Herança e Polimorfismo
Para lidar com as diferentes escalas (Kilo, Mega, Mili, Micro) sem criar um código engessado e cheio de estruturas condicionais (if e else), o sistema utiliza Herança e Polimorfismo. Foram criadas classes específicas para cada escala, e todas elas herdam as características da classe Conversor. O grande diferencial é que cada uma dessas subclasses reescreve o método "exibir" para retornar o seu próprio fator multiplicador. Por exemplo, a classe Kilo retorna 1000.0, enquanto a classe Micro retorna 0.000001. Dessa forma, cada objeto passa a ter autonomia e "sabe" o seu próprio valor de conversão.

A Lógica Matemática em Dois Passos
A conversão matemática ocorre de forma inteligente através de um processo de duas etapas, localizado no método "converter". Em vez de programar uma fórmula direta para cada combinação possível (de Kilo para Mili, de Mega para Micro, etc.), o sistema utiliza a "unidade base" como uma ponte intermediária:

Primeiro passo: O sistema multiplica o valor inserido pelo usuário pelo fator da escala de origem. Isso converte qualquer grandeza para a unidade base (por exemplo, transformar 5 Kilos em 5000 unidades puras).

Segundo passo: O sistema pega esse valor base recém-calculado e o divide pelo fator da escala de destino.

Essa estratégia garante que o sistema consiga converter de qualquer escala para qualquer outra utilizando apenas uma única equação matemática universal.


Segurança e Tratamento de Exceções
Como o sistema depende da entrada de dados do usuário através de um campo de texto, existe o risco de o usuário digitar letras ou caracteres especiais em vez de números. Para evitar que o sistema sofra um colapso e feche abruptamente, foi implementada uma estrutura de proteção chamada de try/catch. O bloco "try" tenta converter o texto digitado em um número decimal. Se a conversão falhar devido a uma entrada inválida, o sistema lança uma exceção de formatação. O bloco "catch" captura essa exceção imediatamente antes que ela quebre o programa, interrompe o cálculo e exibe uma janela de alerta amigável informando ao usuário que apenas números são aceitos.
