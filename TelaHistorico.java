package Conversor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaHistorico extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public TelaHistorico() {
        setTitle("Histórico de Conversões");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Conversões Realizadas"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Atualiza a tabela sempre que o Manager sofrer alteração
        HistoricoManager.getInstance().addChangeListener(this::atualizarTabela);

        JPanel painelBotoes = new JPanel();
        JButton btnExcluir = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");

        btnExcluir.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) HistoricoManager.getInstance().remover(row);
        });

        btnLimpar.addActionListener(e -> HistoricoManager.getInstance().limpar());

        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);
        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTabela();
    }

    private void atualizarTabela() {
        SwingUtilities.invokeLater(() -> {
            model.setRowCount(0);
            for (String item : HistoricoManager.getInstance().getRegistros()) {
                model.addRow(new Object[]{item});
            }
        });
    }
}