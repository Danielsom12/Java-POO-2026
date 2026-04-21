<img width="816" height="634" alt="image" src="https://github.com/user-attachments/assets/957e6483-6eca-44d5-a58b-631d5445e016" />


<img width="1322" height="590" alt="image" src="https://github.com/user-attachments/assets/6a3e958c-7eb6-4eb7-8d71-82d4a6d1b4f7" />


https://app.diagrams.net/#G1hROKAkgqtNUdFsNCblaknKJjJZTwFMMj#{%22pageId%22%3A%22ZfpAr39wWB8uVWxP9SLy%22}

https://docs.google.com/document/d/1A2wMIPhPiMmkt90xg02JmvIB3o3XdmUMnpz_CZDe1JU/edit?tab=t.0

MODIFICAÇÔES:

1. Limpeza de Código Duplicado (Linhas 50 a 115)
O arquivo original tinha várias declarações de botões e campos de texto repetidas, o que causava erros de compilação (o Java não sabia qual "botão" usar).

    O que foi feito: Removi as duplicatas e organizei o layout para que cada componente (campo de texto, caixa de seleção e botão) fosse único e estivesse no lugar certo da tela.

2. Proteção de Dados com Try-Catch (Linhas 118 a 133 e 175 a 179)
Esta foi a implementação do requisito de segurança solicitado:
Java

118: try {
119:     double v1 = Double.parseDouble(txtValorDigitado1.getText());
120:     double v2 = Double.parseDouble(txtValorDigitado2.getText());
...
175: } catch (NumberFormatException ex) {
176:     JOptionPane.showMessageDialog(this, "Erro: Entrada inválida. Use apenas números.");
177: }

    O que foi feito: O bloco try tenta transformar o texto digitado em números. Se o usuário digitar uma letra, o Java "pula" para o catch na linha 175, que exibe um alerta amigável em vez de deixar o programa travar.

3. Lógica da Lei de Ohm (Linhas 135 a 168)
Esta parte não existia no código original e foi criada do zero para fazer o sistema funcionar:
Java

149: // Lógica de cálculo (Lei de Ohm: V = R * I, P = V * I)
150: if (tSet && cSet) { // Se o usuário deu Tensão e Corrente
151:     resistencia = tensao / corrente;
152:     potencia = tensao * corrente;
153: } else if (tSet && rSet) { // Se deu Tensão e Resistência
154:     corrente = tensao / resistencia;
...

    O que foi feito: Criei uma estrutura que identifica quais grandezas o usuário escolheu (Tensão, Corrente, Resistência ou Potência) e aplica a fórmula correta para descobrir os outros dois valores que faltam.

4. Formatação do Resultado (Linhas 170 a 173)
Java

170: txtAreaResultadoLeiOhm.setText(String.format(
171:     "Resultados:\nTensão: %.4f V\nCorrente: %.4f A\nResistência: %.4f Ω\nPotência: %.4f W",
172:     tensao, corrente, resistencia, potencia
173: ));

    O que foi feito: Configurei para que o resultado apareça de forma organizada em várias linhas e com 4 casas decimais, exatamente como pedido na sua documentação acadêmica.
