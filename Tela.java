package Conversor;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane; //importei isso aqui para poder criar aqueles modais de erro

public class Tela extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDigiteOValor; //onde o usuário digita o valor;
    private JComboBox<String> boxEscalaOrigem;//seleção de escala do número digitado por ele
    private JComboBox<String> boxUnidade;//só escolhe a unidade, não estou conseguindo colocar a unidade escolhida no resultado.
    private JComboBox<String> boxEscalaDestino;//seleção da escala que ele quer chegar
    private JTextArea textAreaResultadoConversao;//onde vai aparecer o resultado

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

    public Tela() {
        setTitle("CONVERSOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 454);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 220));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblConversor = new JLabel("CONVERSOR");
        lblConversor.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 28));
        lblConversor.setHorizontalAlignment(SwingConstants.CENTER);
        lblConversor.setBounds(180, 23, 334, 39);
        contentPane.add(lblConversor);

        // Valor de Entrada
        JLabel lblValorInserido = new JLabel("Digite o Valor Para Converter:");
        lblValorInserido.setBounds(264, 137, 179, 22);
        contentPane.add(lblValorInserido);

        txtDigiteOValor = new JTextField();
        txtDigiteOValor.setBounds(264, 103, 179, 22);
        contentPane.add(txtDigiteOValor);

        // Escala Origem
        JLabel lblSelecionarEscala = new JLabel("Escolha a Escala Origem:");
        lblSelecionarEscala.setBounds(264, 69, 179, 22);
        contentPane.add(lblSelecionarEscala);

        String[] escalas = {"Mega", "Kilo", "Mili", "Micro"};
        boxEscalaOrigem = new JComboBox<>(escalas);
        boxEscalaOrigem.setBackground(new Color(255, 255, 255));
        boxEscalaOrigem.setBounds(264, 171, 179, 24);
        contentPane.add(boxEscalaOrigem);

        // Unidade
        JLabel lblSelecionarUnidade = new JLabel("Escolha a Unidade (A, Ω, V, W):");
        lblSelecionarUnidade.setBounds(264, 207, 179, 22);
        contentPane.add(lblSelecionarUnidade);

        String[] unidades = {"A", "Ω", "V", "W"};
        boxUnidade = new JComboBox<>(unidades);
        boxUnidade.setBackground(new Color(255, 255, 255));
        boxUnidade.setBounds(264, 241, 179, 24);
        contentPane.add(boxUnidade);

        // Escala Destino
        JLabel lblSelecionarDestino = new JLabel("Escala que deseja Obter:");
        lblSelecionarDestino.setBounds(265, 277, 178, 22);
        contentPane.add(lblSelecionarDestino);

        boxEscalaDestino = new JComboBox<>(escalas);
        boxEscalaDestino.setBackground(new Color(255, 255, 255));
        boxEscalaDestino.setBounds(264, 311, 179, 22);
        contentPane.add(boxEscalaDestino);

        // Resultado
        textAreaResultadoConversao = new JTextArea();
        textAreaResultadoConversao.setFont(new Font("Dialog", Font.BOLD, 20));
        textAreaResultadoConversao.setBackground(new Color(245, 245, 220));
        textAreaResultadoConversao.setEditable(false);
        textAreaResultadoConversao.setBounds(158, 381, 354, 61);
        contentPane.add(textAreaResultadoConversao);

        // Botão Converter
        JButton btnConverter = new JButton("Converter");
        btnConverter.setBackground(new Color(255, 255, 255));
        btnConverter.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnConverter.setBounds(158, 345, 171, 24);
        

        btnConverter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valorDigitado = Double.parseDouble(txtDigiteOValor.getText());//pega o valor digitado; o txtDIgite... retorna o string e o parse double transforma em double 

                    if (valorDigitado < 0) {
                        JOptionPane.showMessageDialog(null, "Erro: Insira apenas valores positivos.");//modal de erro do JFrame e requisito para ele aparecer: se o valor digitado for menor que 0
                        return;
                    }

                    String escOrigem = (String) boxEscalaOrigem.getSelectedItem();//pega a escala de origem; o (String) é um casting, estou dizendo para o java que é um str
                    String escDestino = (String) boxEscalaDestino.getSelectedItem();//pega a escala desejada

                    Conversor objOrigem = selecionarEscala(escOrigem, valorDigitado); //pega o que o usuário escolheu e cria o objeto correto, new Escala(valorDigitado)
                    Conversor objDestino = selecionarEscala(escDestino, 0);//cria o obj na escala final como zero já que ele não precisa do valor, só do fator já que ele precisa saber a conta que o destilo usa (tipo vezes 1000) e n~ao o nome string

                    double resultado = objOrigem.converter(objDestino);

                    String resultadoFormatado = String.format("%.f", resultado); //se deixar 4 casas decimais vai "dar" zero então aumentei para 10, tem que mudar no doc
                    textAreaResultadoConversao.setText("Resultado: " + resultadoFormatado);
                    
                    System.out.println("Origem fator: " + objOrigem.exibir());
                    System.out.println("Destino fator: " + objDestino.exibir());
                    System.out.println("Valor: " + objOrigem.getValorInserido());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Use apenas números."); //como usamos o parsedouble ali em cima, o JAVA não consegue converter se salva letra ou outro caracere, aí essa biblioteca JOPtionPane tem esse método de tratamento de erro
                }//o ex é o erro capturado
            }
        });
        
        contentPane.add(btnConverter);

        // Botão Mudar Tela
        JButton btnMudarTela = new JButton("Lei de Ohm");
        btnMudarTela.setBackground(new Color(255, 255, 255));
        btnMudarTela.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnMudarTela.setBounds(341, 345, 171, 23);
        btnMudarTela.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaLeiOhm telaOhm = new TelaLeiOhm();
                telaOhm.setVisible(true);
            }
        });
        contentPane.add(btnMudarTela);
    }
    private Conversor selecionarEscala(String nome, double valor) { //aqui é o ponto central do sistema, com base no que estiver selecionado na combobox o cálculo será feito a partir disso
        if (nome.equals("Mega")) return new Mega(valor);
        if (nome.equals("Kilo")) return new Kilo(valor);
        if (nome.equals("Mili")) return new Mili(valor);
        if (nome.equals("Micro")) return new Micro(valor);
        return new Conversor(valor); 
    }
}