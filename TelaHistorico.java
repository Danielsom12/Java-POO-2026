package Conversor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

public class TelaHistorico extends JFrame {
	private JTable table;
	private DefaultTableModel model;

	public TelaHistorico() {
		setTitle("Histórico de Conversões");
		setSize(500, 350); // Aumentei um pouco o tamanho para caber o novo botão
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		model = new DefaultTableModel(new Object[] { "Conversões Realizadas" }, 0);
		table = new JTable(model);
		add(new JScrollPane(table), BorderLayout.CENTER);

		// Atualiza a tabela sempre que o Manager sofrer alteração
		GerenciadorHistorico.getInstance().addChangeListener(this::atualizarTabela);

		JPanel painelBotoes = new JPanel();
		JButton btnExcluir = new JButton("Remover");
		JButton btnLimpar = new JButton("Limpar");
		JButton btnSalvar = new JButton("Salvar .txt"); // Novo botão criado

		// Ação para excluir item selecionado
		btnExcluir.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row != -1)
				GerenciadorHistorico.getInstance().remover(row);
		});

		// Ação para limpar tudo
		btnLimpar.addActionListener(e -> GerenciadorHistorico.getInstance().limpar());

		// Ação para Salvar o arquivo TXT
		btnSalvar.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int resultado = fileChooser.showSaveDialog(this);

			if (resultado == JFileChooser.APPROVE_OPTION) {
				File arquivo = fileChooser.getSelectedFile();
				// Garante que o arquivo termine com .txt
				if (!arquivo.getName().toLowerCase().endsWith(".txt")) {
					arquivo = new File(arquivo.getAbsolutePath() + ".txt");
				}

				try (PrintWriter writer = new PrintWriter(arquivo)) {
					for (String registro : GerenciadorHistorico.getInstance().getRegistros()) {
						writer.println(registro);
					}
					JOptionPane.showMessageDialog(this, "Histórico salvo com sucesso!");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + ex.getMessage());
				}
			}
		});

		// Adiciona os botões ao painel
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnLimpar);
		painelBotoes.add(btnSalvar); // Adicionado o novo botão ao layout
		add(painelBotoes, BorderLayout.SOUTH);

		atualizarTabela();
	}

	private void atualizarTabela() {
		SwingUtilities.invokeLater(() -> {
			model.setRowCount(0);
			for (String item : GerenciadorHistorico.getInstance().getRegistros()) {
				model.addRow(new Object[] { item });
			}
		});
	}
}