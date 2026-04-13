package Conversor;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class Tela extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDigiteOValor;
    private JComboBox<String> boxEscalaOrigem;
    private JComboBox<String> boxUnidade;
    private JComboBox<String> boxEscalaDestino;
    private JTextArea textAreaResultadoConversao;

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
        contentPane.setBackground(new Color(123, 172, 171));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblConversor = new JLabel("CONVERSOR");
        lblConversor.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 28));
        lblConversor.setHorizontalAlignment(SwingConstants.CENTER);
        lblConversor.setBounds(178, 40, 334, 46);
        contentPane.add(lblConversor);

        JLabel lblValorInserido = new JLabel("Digite o Valor Para Converter:");
        lblValorInserido.setBounds(12, 86, 215, 55);
        contentPane.add(lblValorInserido);

        txtDigiteOValor = new JTextField();
        txtDigiteOValor.setBounds(211, 103, 178, 20);
        contentPane.add(txtDigiteOValor);

        JLabel lblSelecionarEscala = new JLabel("Escolha a Escala Origem:");
        lblSelecionarEscala.setBounds(22, 153, 178, 14);
        contentPane.add(lblSelecionarEscala);

        String[] escalas = {"Mega", "Kilo", "Padrão", "Mili", "Micro"};
        boxEscalaOrigem = new JComboBox<>(escalas);
        boxEscalaOrigem.setBounds(211, 149, 100, 22);
        contentPane.add(boxEscalaOrigem);

        JLabel lblSelecionarUnidade = new JLabel("Escolha a Unidade (A, Ω, V, W):");
        lblSelecionarUnidade.setBounds(12, 193, 260, 14);
        contentPane.add(lblSelecionarUnidade);

        String[] unidades = {"A", "Ω", "V", "W"};
        boxUnidade = new JComboBox<>(unidades);
        boxUnidade.setBounds(211, 189, 100, 22);
        contentPane.add(boxUnidade);

        JLabel lblSelecionarDestino = new JLabel("Escala que deseja Obter:");
        lblSelecionarDestino.setBounds(22, 233, 178, 14);
        contentPane.add(lblSelecionarDestino);

        boxEscalaDestino = new JComboBox<>(escalas);
        boxEscalaDestino.setBounds(211, 229, 100, 22);
        contentPane.add(boxEscalaDestino);

       
        textAreaResultadoConversao = new JTextArea();
        textAreaResultadoConversao.setBackground(new Color(123, 172, 170));
        textAreaResultadoConversao.setEditable(false);
        textAreaResultadoConversao.setBounds(410, 149, 240, 144);
        contentPane.add(textAreaResultadoConversao);

        JButton btnConverter = new JButton("Converter");
        btnConverter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        btnConverter.setBounds(35, 295, 98, 24);
        contentPane.add(btnConverter);

        JButton btnMudarTela = new JButton("LEI DE OHM");
        btnMudarTela.setFont(new Font("Dubai", Font.BOLD, 15));
        btnMudarTela.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                TelaLeiOhm telaOhm = new TelaLeiOhm();
                telaOhm.setVisible(true);
            }
        });
        btnMudarTela.setBounds(158, 296, 171, 23);
        contentPane.add(btnMudarTela);}}
    

  
    

 
    
