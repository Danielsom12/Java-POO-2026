package Conversor;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane; //importei isso aqui para poder criar aqueles modais de erro
import java.text.DecimalFormat;//importei isso aqui para os números aparecerem em formato decimal.

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;

	// Paleta de Cores Aplicada
	private final Color COR_FUNDO = new Color(235, 227, 165); // #EBE3A5
	private final Color COR_CAMPOS = new Color(242, 245, 142); // #F2F58E
	private final Color COR_BOTAO_PRINCIPAL = new Color(245, 204, 69); // #F5CC45
	private final Color COR_BOTAO_SECUNDARIO = new Color(235, 182, 87); // #EBB657

	private JPanel contentPane;
	private JTextField txtDigiteOValor; //onde o usuário digita o valor;
	private JComboBox<String> boxEscalaOrigem;//seleção de escala do número digitado por ele
	private JComboBox<String> boxUnidade;//só escolhe a unidade, ela vai aparecer no resultado.
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
		setBounds(100, 100, 836, 669);

		contentPane = new JPanel();
		contentPane.setBackground(COR_FUNDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Título
		JLabel lblConversor = new JLabel("CONVERSOR");
		lblConversor.setBounds(180, 23, 334, 39);
		lblConversor.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 28));
		lblConversor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblConversor);

		// Valor de Entrada
		JLabel lblValorInserido = new JLabel("Digite o Valor Para Converter:");
		lblValorInserido.setBounds(264, 137, 179, 22);
		contentPane.add(lblValorInserido);

		txtDigiteOValor = new JTextField();
		txtDigiteOValor.setBounds(264, 103, 179, 22);
		txtDigiteOValor.setBackground(COR_CAMPOS);
		contentPane.add(txtDigiteOValor);

		// Escala Origem
		JLabel lblSelecionarEscala = new JLabel("Escolha a Escala Origem:");
		lblSelecionarEscala.setBounds(264, 69, 179, 22);
		contentPane.add(lblSelecionarEscala);

		String[] escalas = { "Mega", "Kilo", "Mili", "Micro" };
		boxEscalaOrigem = new JComboBox<>(escalas);
		boxEscalaOrigem.setBounds(264, 171, 179, 24);
		boxEscalaOrigem.setBackground(COR_CAMPOS);
		contentPane.add(boxEscalaOrigem);

		// Unidade
		JLabel lblSelecionarUnidade = new JLabel("Escolha a Unidade (A, Ω, V, W):");
		lblSelecionarUnidade.setBounds(264, 207, 179, 22);
		contentPane.add(lblSelecionarUnidade);

		String[] unidades = { "A", "Ω", "V", "W" };
		boxUnidade = new JComboBox<>(unidades);
		boxUnidade.setBounds(264, 241, 179, 24);
		boxUnidade.setBackground(COR_CAMPOS);
		contentPane.add(boxUnidade);

		// Escala Destino
		JLabel lblSelecionarDestino = new JLabel("Escala que deseja Obter:");
		lblSelecionarDestino.setBounds(265, 277, 178, 22);
		contentPane.add(lblSelecionarDestino);

		boxEscalaDestino = new JComboBox<>(escalas);
		boxEscalaDestino.setBounds(264, 311, 179, 22);
		boxEscalaDestino.setBackground(COR_CAMPOS);
		contentPane.add(boxEscalaDestino);

		// Resultado
		textAreaResultadoConversao = new JTextArea();
		textAreaResultadoConversao.setBounds(160, 454, 354, 61);
		textAreaResultadoConversao.setFont(new Font("Dialog", Font.BOLD, 20));
		textAreaResultadoConversao.setBackground(COR_CAMPOS);
		textAreaResultadoConversao.setEditable(false);
		contentPane.add(textAreaResultadoConversao);

		// Botão Converter
		JButton btnConverter = new JButton("Converter");
		btnConverter.setBounds(160, 383, 171, 24);
		btnConverter.setBackground(COR_BOTAO_PRINCIPAL);
		btnConverter.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String texto = txtDigiteOValor.getText().trim();
					if (texto.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Erro: O campo de valor está vazio.");//modal de erro do JFrame e requisito para ele aparecer: se o valor digitado for vazio
						return;
					}

					texto = texto.replace(",", ".");
					double valorDigitado = Double.parseDouble(texto);;//pega o valor digitado; o txtDIgite... retorna o string e o parse double transforma em double 


					if (valorDigitado < 0) {
						JOptionPane.showMessageDialog(null, "Erro: Insira apenas valores positivos.");//modal de erro do JFrame e requisito para ele aparecer: se o valor digitado for menor que 0
						return;
					}

					String escOrigem = (String) boxEscalaOrigem.getSelectedItem();//pega a escala de origem; o (String) é um casting, estou dizendo para o java que é um str
					String escDestino = (String) boxEscalaDestino.getSelectedItem();//pega a escala desejada
					String escUnidade = (String) boxUnidade.getSelectedItem();

					 Conversor objOrigem = selecionarEscala(escOrigem, valorDigitado); //pega o que o usuário escolheu e cria o objeto correto, new Escala(valorDigitado)
	                    Conversor objDestino = selecionarEscala(escDestino, 0);//cria o obj na escala final como zero já que o construtor exige um double e ele não precisa do valor, só do fator já que ele precisa saber a conta que o destino usa (tipo vezes 1000) e não o nome string

					double resultado = objOrigem.converter(objDestino);//na classe conversor ele é chamado de destino 

					java.text.DecimalFormat df = new java.text.DecimalFormat("0.##########"); // zero o # só mostra número se precisar e o ponto é o separador. As # mostram que é até 10 casas, mas sem encher de zero
					String resultadoFormatado = df.format(resultado);

					textAreaResultadoConversao
							.setText("Resultado: " + resultadoFormatado + " " + escDestino + " " + escUnidade);

					// Registrando no Histórico
					String hora = java.time.LocalDateTime.now()
							.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
					String registro = String.format("[%s] %s %s = %s %s %s", hora, valorDigitado, escOrigem,
							resultadoFormatado, escDestino, escUnidade);
					GerenciadorHistorico.getInstance().adicionar(registro);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Use apenas números.");//modal de erro do JFrame e requisito para ele aparecer: se o valor digitado uma String
				}
			}
		});
		contentPane.add(btnConverter);

		// Botão Lei de Ohm
		JButton btnMudarTela = new JButton("Lei de Ohm");
		btnMudarTela.setBounds(343, 383, 171, 24);
		btnMudarTela.setBackground(COR_BOTAO_SECUNDARIO);
		btnMudarTela.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMudarTela.addActionListener(e -> {
			TelaLeiOhm telaOhm = new TelaLeiOhm();
			telaOhm.setVisible(true);
		});
		contentPane.add(btnMudarTela);

		// Botão Histórico
		JButton btnHistorico = new JButton("Histórico");
		btnHistorico.setBounds(160, 420, 354, 24); // Posicionado abaixo dos outros botões
		btnHistorico.setBackground(COR_BOTAO_SECUNDARIO);
		btnHistorico.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHistorico.addActionListener(e -> {
			TelaHistorico telaHist = new TelaHistorico();
			telaHist.setVisible(true);
		});
		contentPane.add(btnHistorico);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/dan/Documentos/Gemini_Generated_Image_xq8jgxq8j.png"));
		lblNewLabel.setBounds(12, 23, 317, 157);
		contentPane.add(lblNewLabel);
	}

//aqui é o ponto central do sistema, com base no que estiver selecionado na combobox o cálculo será feito a partir disso
private Conversor selecionarEscala(String escOrigem, double valorDigitado) {
    if (escOrigem.equals("Mega")) return new Mega(valorDigitado); 
    if (escOrigem.equals("Kilo")) return new Kilo(valorDigitado);
    if (escOrigem.equals("Mili")) return new Mili(valorDigitado);
    if (escOrigem.equals("Micro")) return new Micro(valorDigitado); //através desse construtor fica new Micro (0)
    return new Conversor(valorDigitado); 
}
}