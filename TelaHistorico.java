package Conversor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

public class TelaHistorico extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Paleta de Cores (Sincronizada com o projeto)
    private final Color COR_FUNDO = new Color(235, 227, 165); // #EBE3A5
    private final Color COR_CAMPOS = new Color(242, 245, 142); // #F2F58E
    private final Color COR_BOTAO_PRINCIPAL = new Color(245, 204, 69); // #F5CC45
    private final Color COR_BOTAO_SECUNDARIO = new Color(235, 182, 87); // #EBB657

    private JTable table;
    private DefaultTableModel model;

    public TelaHistorico() {
        setTitle("Histórico de Conversões");
        
        // --- PADRONIZAÇÃO DE TAMANHO GRANDE ---
        setBounds(100, 100, 1200, 900);
        setLocationRelativeTo(null);
        
        // Layout principal com preenchimento nas bordas
        JPanel contentPane = new JPanel(new BorderLayout(20, 20));
        contentPane.setBackground(COR_FUNDO);
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        setContentPane(contentPane);

        // Fontes padronizadas
        Font fonteTitulo = new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 45);
        Font fonteTabela = new Font("Monospaced", Font.BOLD, 18);
        Font fonteBotoes = new Font("Tahoma", Font.BOLD, 20);

        // Cabeçalho da tela
        JLabel lblTitulo = new JLabel("Histórico de Atividades");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(fonteTitulo);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Configuração da Tabela para visualização profissional
        model = new DefaultTableModel(new Object[] { "Conversões Realizadas" }, 0);
        table = new JTable(model);
        table.setFont(fonteTabela);
        table.setRowHeight(40); // Linhas mais altas para facilitar leitura
        table.setBackground(COR_CAMPOS);
        table.setSelectionBackground(COR_BOTAO_PRINCIPAL);
        
        // ScrollPane para permitir rolar o histórico longo
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Atualiza a tabela sempre que o Manager sofrer alteração (Singleton)
        GerenciadorHistorico.getInstance().addChangeListener(this::atualizarTabela);

        // Painel inferior para organizar os botões grandes
        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 20, 0)); // 1 linha, 4 colunas, espaço de 20px
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setPreferredSize(new Dimension(0, 80));

        JButton btnExcluir = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar Tudo");
        JButton btnSalvar = new JButton("Salvar .txt"); 
        JButton btnVoltar = new JButton("Voltar");

        // Aplicando estilo aos botões
        for (JButton btn : new JButton[]{btnExcluir, btnLimpar, btnSalvar, btnVoltar}) {
            btn.setFont(fonteBotoes);
            btn.setBackground(COR_BOTAO_SECUNDARIO);
        }
        btnSalvar.setBackground(COR_BOTAO_PRINCIPAL); // Destaque para o botão de salvar

        // --- AÇÕES DOS BOTÕES ---

        // Ação para excluir item selecionado
        btnExcluir.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                GerenciadorHistorico.getInstance().remover(row);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.");
            }
        });

        // Ação para limpar tudo
        btnLimpar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja limpar todo o histórico?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                GerenciadorHistorico.getInstance().limpar();
            }
        });

        // Ação para Salvar o arquivo TXT (Lógica mantida e comentada)
        btnSalvar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Histórico como Texto");
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
                    JOptionPane.showMessageDialog(this, "Histórico exportado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo: " + ex.getMessage());
                }
            }
        });

        // Ação para fechar a janela
        btnVoltar.addActionListener(e -> dispose());

        // Adiciona os botões ao painel inferior
        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSalvar); 
        
        contentPane.add(painelBotoes, BorderLayout.SOUTH);

        // Inicializa os dados na abertura
        atualizarTabela();
    }

    /**
     * Atualiza os dados da JTable consultando o Singleton do histórico.
     * Utiliza invokeLater para garantir thread-safety na interface Swing.
     */
    private void atualizarTabela() {
        SwingUtilities.invokeLater(() -> {
            model.setRowCount(0); // Limpa a tabela atual
            for (String item : GerenciadorHistorico.getInstance().getRegistros()) {
                model.addRow(new Object[] { item }); // Adiciona as linhas do Singleton
            }
        });
    }
}