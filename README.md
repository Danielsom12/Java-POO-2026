<img width="816" height="634" alt="image" src="https://github.com/user-attachments/assets/957e6483-6eca-44d5-a58b-631d5445e016" />


<img width="1322" height="590" alt="image" src="https://github.com/user-attachments/assets/6a3e958c-7eb6-4eb7-8d71-82d4a6d1b4f7" />


https://app.diagrams.net/#G1hROKAkgqtNUdFsNCblaknKJjJZTwFMMj#{%22pageId%22%3A%22ZfpAr39wWB8uVWxP9SLy%22}

https://docs.google.com/document/d/1A2wMIPhPiMmkt90xg02JmvIB3o3XdmUMnpz_CZDe1JU/edit?tab=t.0


---

# ⚡ Eletron Converter & Ohm Calculator

Um software em Java desenvolvido para facilitar a rotina de estudantes e profissionais de **Eletrônica Analógica e Digital**. O sistema permite a conversão rápida entre prefixos métricos (escalas) e o cálculo preciso das grandezas da **Lei de Ohm**.

---

## 🚀 Funcionalidades Principal

* **Conversor de Escalas:** Transformação entre unidades como Mega, Kilo, Mili e Micro.
* **Calculadora de Lei de Ohm:** Cálculo automático de Tensão (V), Corrente (A), Resistência (Ω) e Potência (W) a partir de dois valores conhecidos.
* **Gerenciamento de Histórico:** Registro em tempo real de todas as atividades, com opção de remover itens, limpar a lista e exportar para arquivo `.txt`.
* **Interface Adaptativa:** Design otimizado para telas grandes (1200x900) com alta legibilidade.

---

## 🏗️ Arquitetura do Sistema

O projeto foi construído utilizando conceitos sólidos de **Programação Orientada a Objetos (POO)** e Padrões de Projeto (Design Patterns).

### Diagrama de Classes (Lógica)


### Explicação das Classes

#### 1. Classe `Conversor` (Superclasse)
Funciona como a classe mãe que centraliza a lógica global do sistema. Ela armazena o valor numérico base e gerencia as regras gerais de grandezas e escalas, preparando os dados para a normalização.

#### 2. Classes de Escala (`Mega`, `Kilo`, `Mili`, `Micro`)
São as classes filhas que herdam de `Conversor`. Utilizam **Polimorfismo** para aplicar o fator multiplicador específico de cada prefixo métrico:
* **Mega:** $10^6$
* **Kilo:** $10^3$
* **Mili:** $10^{-3}$
* **Micro:** $10^{-6}$

#### 3. Classe `TelaLeiOhm` (Cálculo Ohm)
Atua como o motor de processamento físico. Recebe os valores tratados e aplica as fórmulas da Lei de Ohm com precisão de quatro casas decimais.

#### 4. Classe `GerenciadorHistorico` (Singleton)
Funciona como a memória central do programa. Utiliza o padrão **Singleton** para garantir que todas as telas compartilhem a mesma instância de dados e um sistema de **Listeners** para atualizar a interface automaticamente sempre que um registro é adicionado ou removido.

---

## 🎨 Identidade Visual

O projeto utiliza uma paleta de cores "quente" e suave para reduzir a fadiga visual:

| Elemento | Cor | Hexadecimal |
| :--- | :--- | :--- |
| **Fundo** | Creme | `#EBE3A5` |
| **Campos** | Amarelo Pastel | `#F2F58E` |
| **Botão Principal** | Ouro | `#F5CC45` |
| **Botão Secundário** | Laranja Outono | `#EBB657` |

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Biblioteca Gráfica:** Swing / AWT
* **Gerenciamento de Tempo:** `java.time.LocalDateTime`
* **Formatação:** `java.text.DecimalFormat`

---

## ⚙️ Como Executar

1.  Certifique-se de ter o **JDK 17** ou superior instalado.
2.  Clone o repositório ou baixe os arquivos fonte.
3.  Importe o projeto em sua IDE de preferência (Eclipse, IntelliJ ou VS Code).
4.  Execute a classe `Tela.java` (localizada no pacote `Conversor`) para iniciar a aplicação.

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais e acadêmicos na disciplina de Eletrônica e Programação.

---
*Desenvolvido com foco em precisão e usabilidade.*


