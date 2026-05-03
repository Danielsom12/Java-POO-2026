package Conversor;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.border.EmptyBorder;

// BIBLIOTECAS Exclusivas da TelaLeiOhm 
import javax.swing.JFrame;            // Cria a janela base (moldura) onde o programa é construído.
import javax.swing.JPanel;            // Funciona como um contêiner para agrupar e organizar os componentes.
import javax.swing.JLabel;            // Exibe textos fixos ou rótulos de instrução na interface.
import javax.swing.SwingConstants;    // Fornece opções de alinhamento.
import javax.swing.JComboBox;         // Cria a caixa de seleção para escolher as grandezas (V, A, Ω, W).
import javax.swing.JTextField;        // Campo de entrada que permite ao usuário digitar os valores.
import javax.swing.JButton;           // Cria os botões que disparam os cálculos ou fecham a tela.
import javax.swing.JTextArea;         // Área de texto que suporta várias linhas para exibir o resultado detalhado.
import javax.swing.JOptionPane;       // Abre caixas de diálogo para avisos, erros ou mensagens rápidas.
import javax.swing.ImageIcon;         // Permite carregar e exibir ícones ou fotos (como o logo).

public class TelaLeiOhm extends JFrame {

    private static final long serialVersionUID = 1L;

    // Paleta de Cores (Sincronizada com a classe Tela)
    private final Color COR_FUNDO = new Color(235, 227, 165); // #EBE3A5
    private final Color COR_CAMPOS = new Color(242, 245, 142); // #F2F58E
    private final Color COR_BOTAO_PRINCIPAL = new Color(245, 204, 69); // #F5CC45
    private final Color COR_BOTAO_SECUNDARIO = new Color(235, 182, 87); // #EBB657

    private JPanel contentPane;
    private JTextField txtValorDigitado1;
    private JTextField txtValorDigitado2;
    private JComboBox<String> boxEscolhaUnidadeMedida1;
    private JComboBox<String> boxEscolhaUnidadeMedida2;
    private JTextArea txtAreaResultadoLeiOhm;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaLeiOhm frame = new TelaLeiOhm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaLeiOhm() {
        setTitle("CÁLCULO LEI DE OHM");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //    AUMENTO DA TELA
        setBounds(100, 100, 1200, 900);
        setLocationRelativeTo(null); // Centraliza ao abrir

        contentPane = new JPanel();
        contentPane.setBackground(COR_FUNDO); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Fontes padronizadas para leitura fácil
        Font fonteGrandeBold = new Font("Tahoma", Font.BOLD, 22);
        Font fonteMedia = new Font("Tahoma", Font.PLAIN, 20);

        JLabel lblCalcularLeiOhm = new JLabel("Cálculo da Lei de Ohm");
        lblCalcularLeiOhm.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 45));
        lblCalcularLeiOhm.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalcularLeiOhm.setBounds(200, 30, 800, 70);
        contentPane.add(lblCalcularLeiOhm);

        //  ENTRADA 1 
        JLabel lblValor1 = new JLabel("Digite o 1° Valor:");
        lblValor1.setFont(fonteGrandeBold);
        lblValor1.setBounds(300, 150, 300, 30);
        contentPane.add(lblValor1);

        txtValorDigitado1 = new JTextField();
        txtValorDigitado1.setFont(fonteMedia);
        txtValorDigitado1.setBounds(300, 185, 280, 50);
        txtValorDigitado1.setBackground(COR_CAMPOS); 
        contentPane.add(txtValorDigitado1);

        JLabel lblEscolhaUnidadeMedida1 = new JLabel("Unidade do 1° Valor:");
        lblEscolhaUnidadeMedida1.setFont(fonteGrandeBold);
        lblEscolhaUnidadeMedida1.setBounds(620, 150, 300, 30);
        contentPane.add(lblEscolhaUnidadeMedida1);

        String[] unidades = { "Tensão (V)", "Corrente (A)", "Resistência (Ω)", "Potência (W)" };
        boxEscolhaUnidadeMedida1 = new JComboBox<>(unidades);
        boxEscolhaUnidadeMedida1.setFont(fonteMedia);
        boxEscolhaUnidadeMedida1.setBounds(620, 185, 280, 50);
        boxEscolhaUnidadeMedida1.setBackground(COR_CAMPOS); 
        contentPane.add(boxEscolhaUnidadeMedida1);

        //  ENTRADA 2 
        JLabel lblValor2 = new JLabel("Digite o 2° Valor:");
        lblValor2.setFont(fonteGrandeBold);
        lblValor2.setBounds(300, 260, 300, 30);
        contentPane.add(lblValor2);

        txtValorDigitado2 = new JTextField();
        txtValorDigitado2.setFont(fonteMedia);
        txtValorDigitado2.setBounds(300, 295, 280, 50);
        txtValorDigitado2.setBackground(COR_CAMPOS); 
        contentPane.add(txtValorDigitado2);

        JLabel lblEscolhaUnidadeMedida2 = new JLabel("Unidade do 2° Valor:");
        lblEscolhaUnidadeMedida2.setFont(fonteGrandeBold);
        lblEscolhaUnidadeMedida2.setBounds(620, 260, 300, 30);
        contentPane.add(lblEscolhaUnidadeMedida2);

        boxEscolhaUnidadeMedida2 = new JComboBox<>(unidades);
        boxEscolhaUnidadeMedida2.setFont(fonteMedia);
        boxEscolhaUnidadeMedida2.setBounds(620, 295, 280, 50);
        boxEscolhaUnidadeMedida2.setBackground(COR_CAMPOS); 
        contentPane.add(boxEscolhaUnidadeMedida2);

        //  ÁREA DE RESULTADO 
        txtAreaResultadoLeiOhm = new JTextArea();
        txtAreaResultadoLeiOhm.setBackground(COR_FUNDO); 
        txtAreaResultadoLeiOhm.setEditable(false);
        txtAreaResultadoLeiOhm.setFont(new Font("Dialog", Font.BOLD, 28));
        txtAreaResultadoLeiOhm.setBounds(300, 399, 600, 180);
        txtAreaResultadoLeiOhm.setMargin(new java.awt.Insets(20, 20, 20, 20));
        contentPane.add(txtAreaResultadoLeiOhm);

        //  BOTÕES 
        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 22));
        btnCalcular.setBounds(300, 620, 280, 70);
        btnCalcular.setBackground(COR_BOTAO_PRINCIPAL); 
        btnCalcular.addActionListener(e -> calcularLeiOhm());
        contentPane.add(btnCalcular);

        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 22));
        btnVoltar.setBounds(620, 620, 280, 70);
        btnVoltar.setBackground(COR_BOTAO_SECUNDARIO); 
        btnVoltar.addActionListener(e -> dispose());
        contentPane.add(btnVoltar);

        // Logo 
        JLabel lblLogo = new JLabel("");
        try {
            ImageIcon originalIcon = new ImageIcon("/home/dan/eclipse-workspace/DanielSammartano/src/Conversor/logo_S&B_Convert.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(250, 120, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.out.println("Logo não encontrada.");
        }
        lblLogo.setBounds(30, 20, 221, 210);
        contentPane.add(lblLogo);
        try {
            ImageIcon ohmIcon = new ImageIcon("/home/dan/Área de trabalho/Gemini_Generated_Image_xq8jgxq8j.png");
            Image scaledOhm = ohmIcon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
        } catch (Exception e) { /* Silencioso se não achar */ }
    }

    private void calcularLeiOhm() {
        try {
            // Converte vírgulas em pontos e transforma o texto digitado em números (double)
            String txt1 = txtValorDigitado1.getText().replace(",", ".");
            String txt2 = txtValorDigitado2.getText().replace(",", ".");
            double v1 = Double.parseDouble(txt1);
            double v2 = Double.parseDouble(txt2);

            // Validação: impede o uso de números negativos
            if (v1 < 0 || v2 < 0) {
                JOptionPane.showMessageDialog(this, "Erro: Insira apenas valores positivos.");
                return;
            }

            // Obtém as unidades selecionadas (V, A, Ω ou W)
            String u1 = (String) boxEscolhaUnidadeMedida1.getSelectedItem();
            String u2 = (String) boxEscolhaUnidadeMedida2.getSelectedItem();

            // Validação: impede que o usuário tente calcular usando duas grandezas iguais (ex: V e V)
            if (u1.equals(u2)) {
                JOptionPane.showMessageDialog(this, "Erro: Selecione grandezas diferentes.");
                return;
            }

            double tensao = 0, corrente = 0, resistencia = 0, potencia = 0;
            boolean tSet = false, cSet = false, rSet = false, pSet = false;

            // Mapeamento: identifica qual valor (v1 ou v2) pertence a qual grandeza (V, A, Ω, W)
            if (u1.contains("V")) { tensao = v1; tSet = true; } 
            else if (u1.contains("A")) { corrente = v1; cSet = true; } 
            else if (u1.contains("Ω")) { resistencia = v1; rSet = true; } 
            else if (u1.contains("W")) { potencia = v1; pSet = true; }

            if (u2.contains("V")) { tensao = v2; tSet = true; } 
            else if (u2.contains("A")) { corrente = v2; cSet = true; } 
            else if (u2.contains("Ω")) { resistencia = v2; rSet = true; } 
            else if (u2.contains("W")) { potencia = v2; pSet = true; }

            // Bloco de Fórmulas: dependendo do par de valores conhecidos, calcula as outras duas grandezas
            if (tSet && cSet) { // Se tenho Tensão e Corrente
                resistencia = tensao / corrente;
                potencia = tensao * corrente;
            } else if (tSet && rSet) { // Se tenho Tensão e Resistência
                corrente = tensao / resistencia;
                potencia = (tensao * tensao) / resistencia;
            } else if (cSet && rSet) { // Se tenho Corrente e Resistência
                tensao = corrente * resistencia;
                potencia = (corrente * corrente) * resistencia;
            } else if (pSet && tSet) { // Se tenho Potência e Tensão
                corrente = potencia / tensao;
                resistencia = (tensao * tensao) / potencia;
            } else if (pSet && cSet) { // Se tenho Potência e Corrente
                tensao = potencia / corrente;
                resistencia = potencia / (corrente * corrente);
            } else if (pSet && rSet) { // Se tenho Potência e Resistência
                tensao = Math.sqrt(potencia * resistencia);
                corrente = Math.sqrt(potencia / resistencia);
            }

         // Exibe os resultados formatados com 4 casas decimais no JTextArea
            txtAreaResultadoLeiOhm.setText(String.format(
                    "RESULTADOS:\nTensão: %.4f V\nCorrente: %.4f A\nResistência: %.4f Ω\nPotência: %.4f W", 
                    tensao, corrente, resistencia, potencia));

        } catch (NumberFormatException ex) {
            // Captura erros caso o usuário digite letras ou deixe campos vazios
            JOptionPane.showMessageDialog(this, "Erro: Entrada inválida. Use apenas números.");
        } catch (ArithmeticException ex) {
            // Captura erros matemáticos, como tentar dividir um número por zero
            JOptionPane.showMessageDialog(this, "Erro: Divisão por zero.");
        }}
    }