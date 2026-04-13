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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLeiOhm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtValorDigitado1;
    private JTextField txtValorDigitado2;

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
        
        JLabel lblValor1 = new JLabel("Digite o Valor:");
        lblValor1.setBounds(160, 125, 94, 14);
        contentPane.add(lblValor1);
        
        String[] unidades = {"Tensão (V)", "Corrente (A)", "Resistência (Ω)", "Potência (W)"};
        
        JComboBox<String> boxEscolhaUnidadeMedida1 = new JComboBox<>(unidades);
        boxEscolhaUnidadeMedida1.setBounds(368, 162, 176, 22);
        contentPane.add(boxEscolhaUnidadeMedida1);
        
        txtValorDigitado1 = new JTextField();
        txtValorDigitado1.setBounds(256, 122, 94, 20);
        contentPane.add(txtValorDigitado1);
        txtValorDigitado1.setColumns(10);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnVoltar.setBounds(178, 440, 89, 23);
        contentPane.add(btnVoltar);
        
        JLabel lblValor2 = new JLabel("Digite o 2° Valor:");
        lblValor2.setBounds(160, 226, 94, 14);
        contentPane.add(lblValor2);
        
        txtValorDigitado2 = new JTextField();
        txtValorDigitado2.setColumns(10);
        txtValorDigitado2.setBounds(267, 223, 94, 20);
        contentPane.add(txtValorDigitado2);
        
        JComboBox<String> boxEscolhaUnidadeMedida2 = new JComboBox<>(unidades);
        boxEscolhaUnidadeMedida2.setBounds(368, 269, 176, 22);
        contentPane.add(boxEscolhaUnidadeMedida2);
        
        JLabel lblEscolhaUnidadeMedida1 = new JLabel("Escolha a Unidade de Medida:");
        lblEscolhaUnidadeMedida1.setBounds(160, 166, 201, 14);
        contentPane.add(lblEscolhaUnidadeMedida1);
        
        JLabel lblEscolhaUnidadeMedida2 = new JLabel("Escolha a Unidade de Medida:");
        lblEscolhaUnidadeMedida2.setBounds(160, 273, 201, 14);
        contentPane.add(lblEscolhaUnidadeMedida2);
        
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCalcular.setBounds(289, 440, 139, 23);
        contentPane.add(btnCalcular);
        
        JTextArea txtAreaResultadoLeiOhm = new JTextArea();
        txtAreaResultadoLeiOhm.setBackground(new Color(123, 172, 170));
        txtAreaResultadoLeiOhm.setEditable(false);
        txtAreaResultadoLeiOhm.setBounds(160, 327, 396, 82);
        contentPane.add(txtAreaResultadoLeiOhm);
    }
}