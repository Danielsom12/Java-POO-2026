package Conversor;


import java.awt.Color;               // Apenas estético.
//Importando Bibliotecas Necessárias
import java.awt.EventQueue;          // Gerencia a fila de eventos do sistema
import java.awt.Font;                // Apenas estético.
import java.awt.Image;               // Classe base para manipular imagens, redimensionar o logo.
import java.awt.event.ActionEvent;    // Representa o evento que ocorreu (como o clique em si) e carrega informações sobre ele.
import java.awt.event.ActionListener; // Interface que "escuta" quando um botão é clicado para disparar uma ação.
import java.time.LocalDateTime;      // Captura a data e hora atual do sistema.
import java.time.format.DateTimeFormatter; // Define como a data e hora devem ser exibidas.

// Importa todos os componentes visuais modernos do Java .
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder; // Cria uma borda invisível ao redor dos componentes para dar espaçamento.


public class Tela extends JFrame {

    private static final long serialVersionUID = 1L;

    // Paleta de Cores Aplicada
    private final Color COR_FUNDO = new Color(235, 227, 165); // #EBE3A5
    private final Color COR_CAMPOS = new Color(242, 245, 142); // #F2F58E
    private final Color COR_BOTAO_PRINCIPAL = new Color(245, 204, 69); // #F5CC45
    private final Color COR_BOTAO_SECUNDARIO = new Color(235, 182, 87); // #EBB657
    // Definindo padrão de data
    private final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    //Definindo oselementos da nossa Tela
    private JPanel contentPane;
    private JTextField txtDigiteOValor; //onde o usuário digita o valor;
    private JComboBox<String> boxEscalaOrigem; //seleção de escala do número digitado por ele
    private JComboBox<String> boxUnidade; //só escolhe a unidade, ela vai aparecer no resultado.
    private JComboBox<String> boxEscalaDestino; //seleção da escala que ele quer chegar
    private JTextArea textAreaResultadoConversao; //onde vai aparecer o resultado

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Tela frame = new Tela();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    //Define o tamanho da Tela e Cor do Fundo
    public Tela() {
        setTitle("CONVERSOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Tamanho aumentado para tela grande
        setBounds(100, 100, 1200, 900);
        setLocationRelativeTo(null); // Centraliza a janela ao abrir

        contentPane = new JPanel();
        contentPane.setBackground(COR_FUNDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Fontes padronizadas para leitura fácil
        Font fonteGrandeBold = new Font("Tahoma", Font.BOLD, 22);
        Font fonteMedia = new Font("Tahoma", Font.PLAIN, 20);

        // Título
        JLabel lblConversor = new JLabel("CONVERSOR");
        lblConversor.setBounds(200, 30, 800, 60);
        lblConversor.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 45));
        lblConversor.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblConversor);

        // Escala Origem
        JLabel lblSelecionarEscala = new JLabel("Escolha a Escala Origem:");
        lblSelecionarEscala.setBounds(400, 120, 400, 30);
        lblSelecionarEscala.setFont(fonteGrandeBold);
        contentPane.add(lblSelecionarEscala);

        String[] escalas = { "Mega", "Kilo", "Mili", "Micro" };
        boxEscalaOrigem = new JComboBox<>(escalas);
        boxEscalaOrigem.setBounds(400, 155, 400, 45);
        boxEscalaOrigem.setBackground(COR_CAMPOS);
        boxEscalaOrigem.setFont(fonteMedia);
        contentPane.add(boxEscalaOrigem);

        // Valor de Entrada
        JLabel lblValorInserido = new JLabel("Digite o Valor Para Converter:");
        lblValorInserido.setBounds(400, 220, 400, 30);
        lblValorInserido.setFont(fonteGrandeBold);
        contentPane.add(lblValorInserido);

        txtDigiteOValor = new JTextField();
        txtDigiteOValor.setBounds(400, 255, 400, 45);
        txtDigiteOValor.setBackground(COR_CAMPOS);
        txtDigiteOValor.setFont(fonteMedia);
        contentPane.add(txtDigiteOValor);

        // Unidade
        JLabel lblSelecionarUnidade = new JLabel("Escolha a Unidade (A, Ω, V, W):");
        lblSelecionarUnidade.setBounds(400, 320, 400, 30);
        lblSelecionarUnidade.setFont(fonteGrandeBold);
        contentPane.add(lblSelecionarUnidade);

        String[] unidades = { "A", "Ω", "V", "W" };
        boxUnidade = new JComboBox<>(unidades);
        boxUnidade.setBounds(400, 355, 400, 45);
        boxUnidade.setBackground(COR_CAMPOS);
        boxUnidade.setFont(fonteMedia);
        contentPane.add(boxUnidade);

        // Escala Destino
        JLabel lblSelecionarDestino = new JLabel("Escala que deseja Obter:");
        lblSelecionarDestino.setBounds(400, 420, 400, 30);
        lblSelecionarDestino.setFont(fonteGrandeBold);
        contentPane.add(lblSelecionarDestino);

        boxEscalaDestino = new JComboBox<>(escalas);
        boxEscalaDestino.setBounds(400, 455, 400, 45);
        boxEscalaDestino.setBackground(COR_CAMPOS);
        boxEscalaDestino.setFont(fonteMedia);
        contentPane.add(boxEscalaDestino);

        // Botão Converter
        JButton btnConverter = new JButton("Converter");
        btnConverter.setBounds(300, 540, 280, 60);
        btnConverter.setBackground(COR_BOTAO_PRINCIPAL);
        btnConverter.setFont(new Font("Tahoma", Font.BOLD, 22));
        btnConverter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String texto = txtDigiteOValor.getText().trim(); // Remove espaços vazios Acidentais!
                    if (texto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Erro: O campo de valor está vazio."); //modal de erro do JFrame e requisito para ele aparecer: se o valor digitado for vazio
                        return;
                    }

                    texto = texto.replace(",", "."); // subistitui "," por "." 
                    double valorDigitado = Double.parseDouble(texto); //pega o valor digitado; o txtDIgite... retorna o string e o parse double transforma em double 

                    if (valorDigitado < 0) {
                        JOptionPane.showMessageDialog(null, "Erro: Insira apenas valores positivos."); //modal de erro do JFrame e requisito para ele aparecer: se o valor digitado for menor que 0
                        return;
                    }

                    String escOrigem = (String) boxEscalaOrigem.getSelectedItem(); //pega a escala de origem; o (String) é um casting, estou dizendo para o java que é um str
                    String escDestino = (String) boxEscalaDestino.getSelectedItem(); //pega a escala desejada
                    String escUnidade = (String) boxUnidade.getSelectedItem();

                    Conversor objOrigem = selecionarEscala(escOrigem, valorDigitado); //pega o que o usuário escolheu e cria o objeto correto, new Escala(valorDigitado)
                    Conversor objDestino = selecionarEscala(escDestino, 0); //cria o obj na escala final como zero já que o construtor exige um double e ele não precisa do valor, só do fator já que ele precisa saber a conta que o destino usa (tipo vezes 1000) e não o nome string

                    double resultado = objOrigem.converter(objDestino); //na classe conversor ele é chamado de destino 

                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.##########"); // zero o # só mostra número se precisar e o ponto é o separador. As # mostram que é até 10 casas, mas sem encher de zero
                    String resultadoFormatado = df.format(resultado);

                    textAreaResultadoConversao
                            .setText("Resultado: " + resultadoFormatado + " " + escDestino + " " + escUnidade);

                    // Registrando no Histórico
                    String dataFormatada = LocalDateTime.now().format(FORMATADOR_DATA);
                    
                    // Define como será salvo o registro 
                    String registro = String.format("[%s] %s %s = %s %s %s", 
                            dataFormatada, 
                            valorDigitado, 
                            escOrigem,
                            resultadoFormatado, 
                            escDestino, 
                            escUnidade);

                    GerenciadorHistorico.getInstance().adicionar(registro); // Salvar a conversão dentro  do registro

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Use apenas números."); //modal de erro do JFrame e requisito para ele aparecer: se o valor digitado uma String
                }
            }
        });
        contentPane.add(btnConverter);

        // Botão Lei de Ohm
        JButton btnMudarTela = new JButton("Lei de Ohm");
        btnMudarTela.setBounds(620, 540, 280, 60);
        btnMudarTela.setBackground(COR_BOTAO_SECUNDARIO);
        btnMudarTela.setFont(new Font("Tahoma", Font.BOLD, 22));
        btnMudarTela.addActionListener(e -> {
            TelaLeiOhm telaOhm = new TelaLeiOhm();
            telaOhm.setVisible(true);
        });
        contentPane.add(btnMudarTela);

        // Botão Histórico
        JButton btnHistorico = new JButton("Histórico");
        btnHistorico.setBounds(300, 620, 600, 50); // Posicionado abaixo dos outros botões
        btnHistorico.setBackground(COR_BOTAO_SECUNDARIO);
        btnHistorico.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnHistorico.addActionListener(e -> {
            TelaHistorico telaHist = new TelaHistorico();
            telaHist.setVisible(true);
        });
        contentPane.add(btnHistorico);

        // Resultado
        textAreaResultadoConversao = new JTextArea();
        textAreaResultadoConversao.setBounds(200, 710, 800, 100);
        textAreaResultadoConversao.setFont(new Font("Dialog", Font.BOLD, 35));
        textAreaResultadoConversao.setBackground(COR_FUNDO);
        textAreaResultadoConversao.setEditable(false);
        textAreaResultadoConversao.setMargin(new java.awt.Insets(20, 20, 20, 20));
        contentPane.add(textAreaResultadoConversao);

        // Logo
        JLabel lblNewLabel = new JLabel("");
        try {
            ImageIcon originalIcon = new ImageIcon("/home/dan/eclipse-workspace/DanielSammartano/src/Conversor");
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
            lblNewLabel.setIcon(new ImageIcon("/home/dan/eclipse-workspace/DanielSammartano/src/Conversor/logo_S&B_Convert.png"));
        } catch (Exception e) {
            System.out.println("Caminho da imagem não encontrado.");
        }
        lblNewLabel.setBounds(31, 64, 324, 186);
        contentPane.add(lblNewLabel);
    }

    // aqui é o ponto central do sistema, com base no que estiver selecionado na combobox o cálculo será feito a partir disso
    private Conversor selecionarEscala(String escOrigem, double valorDigitado) {
        if (escOrigem.equals("Mega"))
            return new Mega(valorDigitado); 
        if (escOrigem.equals("Kilo"))
            return new Kilo(valorDigitado);
        if (escOrigem.equals("Mili"))
            return new Mili(valorDigitado);
        if (escOrigem.equals("Micro")) //através desse construtor fica new Micro (0)
            return new Micro(valorDigitado);
        return new Conversor(valorDigitado);
    }
}