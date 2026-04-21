package Conversor;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLeiOhm extends JFrame {

    private static final long serialVersionUID = 1L;
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
        setBounds(100, 100, 670, 551);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(123, 172, 171));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblCalcularLeiOhm = new JLabel("Cálculo da Lei de Ohm");
        lblCalcularLeiOhm.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 28));
        lblCalcularLeiOhm.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalcularLeiOhm.setBounds(160, 20, 334, 46);
        contentPane.add(lblCalcularLeiOhm);
        
        JLabel lblValor1 = new JLabel("Digite o 1° Valor:");
        lblValor1.setBounds(160, 122, 120, 20);
        contentPane.add(lblValor1);
        
        txtValorDigitado1 = new JTextField();
        txtValorDigitado1.setBounds(323, 122, 94, 20);
        contentPane.add(txtValorDigitado1);
        
        JLabel lblEscolhaUnidadeMedida1 = new JLabel("Escolha a Unidade de Medida:");
        lblEscolhaUnidadeMedida1.setBounds(160, 166, 200, 14);
        contentPane.add(lblEscolhaUnidadeMedida1);

        String[] unidades = {"Tensão (V)", "Corrente (A)", "Resistência (Ω)", "Potência (W)"};
        boxEscolhaUnidadeMedida1 = new JComboBox<>(unidades);
        boxEscolhaUnidadeMedida1.setBounds(323, 162, 176, 22);
        contentPane.add(boxEscolhaUnidadeMedida1);
        
        JLabel lblValor2 = new JLabel("Digite o 2° Valor:");
        lblValor2.setBounds(160, 208, 120, 20);
        contentPane.add(lblValor2);

        txtValorDigitado2 = new JTextField();
        txtValorDigitado2.setBounds(323, 208, 94, 20);
        contentPane.add(txtValorDigitado2);

        JLabel lblEscolhaUnidadeMedida2 = new JLabel("Escolha a Unidade de Medida:");
        lblEscolhaUnidadeMedida2.setBounds(160, 252, 200, 14);
        contentPane.add(lblEscolhaUnidadeMedida2);
        
        boxEscolhaUnidadeMedida2 = new JComboBox<>(unidades);
        boxEscolhaUnidadeMedida2.setBounds(323, 248, 176, 22);
        contentPane.add(boxEscolhaUnidadeMedida2);
        
        txtAreaResultadoLeiOhm = new JTextArea();
        txtAreaResultadoLeiOhm.setBackground(new Color(245, 245, 220));
        txtAreaResultadoLeiOhm.setEditable(false);
        txtAreaResultadoLeiOhm.setFont(new Font("Dialog", Font.BOLD, 14));
        txtAreaResultadoLeiOhm.setBounds(160, 327, 396, 82);
        contentPane.add(txtAreaResultadoLeiOhm);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCalcular.setBounds(289, 440, 139, 23);
        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularLeiOhm();
            }
        });
        contentPane.add(btnCalcular);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVoltar.setBounds(178, 440, 89, 23);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnVoltar);
    }

    private void calcularLeiOhm() {
        try {
            double v1 = Double.parseDouble(txtValorDigitado1.getText());
            double v2 = Double.parseDouble(txtValorDigitado2.getText());

            if (v1 < 0 || v2 < 0) {
                JOptionPane.showMessageDialog(this, "Erro: Insira apenas valores positivos.");
                return;
            }

            String u1 = (String) boxEscolhaUnidadeMedida1.getSelectedItem();
            String u2 = (String) boxEscolhaUnidadeMedida2.getSelectedItem();

            if (u1.equals(u2)) {
                JOptionPane.showMessageDialog(this, "Erro: Selecione grandezas diferentes.");
                return;
            }

            double tensao = 0, corrente = 0, resistencia = 0, potencia = 0;
            boolean tSet = false, cSet = false, rSet = false, pSet = false;

            // Atribuir valores conhecidos
            if (u1.contains("V")) { tensao = v1; tSet = true; }
            else if (u1.contains("A")) { corrente = v1; cSet = true; }
            else if (u1.contains("Ω")) { resistencia = v1; rSet = true; }
            else if (u1.contains("W")) { potencia = v1; pSet = true; }

            if (u2.contains("V")) { tensao = v2; tSet = true; }
            else if (u2.contains("A")) { corrente = v2; cSet = true; }
            else if (u2.contains("Ω")) { resistencia = v2; rSet = true; }
            else if (u2.contains("W")) { potencia = v2; pSet = true; }

            // Lógica de cálculo (Lei de Ohm: V = R * I, P = V * I)
            if (tSet && cSet) {
                resistencia = tensao / corrente;
                potencia = tensao * corrente;
            } else if (tSet && rSet) {
                corrente = tensao / resistencia;
                potencia = (tensao * tensao) / resistencia;
            } else if (cSet && rSet) {
                tensao = corrente * resistencia;
                potencia = (corrente * corrente) * resistencia;
            } else if (pSet && tSet) {
                corrente = potencia / tensao;
                resistencia = (tensao * tensao) / potencia;
            } else if (pSet && cSet) {
                tensao = potencia / corrente;
                resistencia = potencia / (corrente * corrente);
            } else if (pSet && rSet) {
                tensao = Math.sqrt(potencia * resistencia);
                corrente = Math.sqrt(potencia / resistencia);
            }

            txtAreaResultadoLeiOhm.setText(String.format(
                "Resultados:\nTensão: %.4f V\nCorrente: %.4f A\nResistência: %.4f Ω\nPotência: %.4f W",
                tensao, corrente, resistencia, potencia
            ));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Entrada inválida. Use apenas números.");
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Divisão por zero.");
        }
    }
}