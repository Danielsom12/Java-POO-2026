package Conversor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

/**
 * Classe responsável por exibir a janela de histórico de conversões.
 * Permite visualizar, remover, limpar e salvar os registros com comentários.
 */
public class TelaHistorico extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // --- DEFINIÇÃO DA PALETA DE CORES ---
    private final Color COR_FUNDO = new Color(235, 227, 165); // Bege/Amarelo claro
    private final Color COR_CAMPOS = new Color(242, 245, 142); // Amarelo pastel
    private final Color COR_BOTAO_PRINCIPAL = new Color(245, 204, 69); // Amarelo vibrante
    private final Color COR_BOTAO_SECUNDARIO = new Color(235, 182, 87); // Laranja suave

    // Componentes principais para manipulação de dados na tela
    private JTable table;
    private DefaultTableModel model;

    public TelaHistorico() {
        // Configurações básicas da Janela
        setTitle("Histórico de Conversões");
        setBounds(100, 100, 1200, 900); // Tamanho grande para facilitar a leitura
        setLocationRelativeTo(null); // Centraliza a tela ao abrir
        
        // Painel Principal com Layout de bordas e espaçamentos (padding)
        JPanel contentPane = new JPanel(new BorderLayout(20, 20));
        contentPane.setBackground(COR_FUNDO);
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        setContentPane(contentPane);

        // --- CONFIGURAÇÃO DAS FONTES ---
        Font fonteTitulo = new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 45);
        Font fonteTabela = new Font("Monospaced", Font.BOLD, 16);
        Font fonteBotoes = new Font("Tahoma", Font.BOLD, 18);

        // Título superior
        JLabel lblTitulo = new JLabel("Histórico de Atividades");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(fonteTitulo);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // --- CONFIGURAÇÃO DA TABELA (COLUNAS E EDIÇÃO) ---
        // Criamos o modelo de dados com duas colunas
        model = new DefaultTableModel(new Object[] { "Conversão Realizada", "Comentários do Usuário" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Apenas a coluna 1 (Comentários) pode ser editada clicando diretamente na tabela
                return column == 1; 
            }
        };

        table = new JTable(model);
        table.setFont(fonteTabela);
        table.setRowHeight(45); // Altura da linha para melhor visualização
        table.setBackground(COR_CAMPOS);
        table.setSelectionBackground(COR_BOTAO_PRINCIPAL); // Cor ao selecionar linha
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16)); // Estilo do cabeçalho
        
        // Define proporções: Coluna 0 é maior que a coluna de Comentários
        table.getColumnModel().getColumn(0).setPreferredWidth(600);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);

        // ScrollPane: Adiciona barra de rolagem caso a lista fique muito grande
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // LISTENER: Faz a tela "ouvir" mudanças no Singleton e atualizar automaticamente
        GerenciadorHistorico.getInstance().addChangeListener(this::atualizarTabela);

        // --- PAINEL DE BOTÕES (RODAPÉ) ---
        // Organiza 5 botões em uma linha com 15px de espaço entre eles
        JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 15, 0)); 
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setPreferredSize(new Dimension(0, 80));

        JButton btnAdicionarComentario = new JButton("Add Comentário");
        JButton btnExcluir = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar Tudo");
        JButton btnSalvar = new JButton("Salvar .txt"); 
        JButton btnVoltar = new JButton("Voltar");

        // Loop para aplicar estilo padrão em todos os botões rapidamente
        for (JButton btn : new JButton[]{btnAdicionarComentario, btnExcluir, btnLimpar, btnSalvar, btnVoltar}) {
            btn.setFont(fonteBotoes);
            btn.setBackground(COR_BOTAO_SECUNDARIO);
            btn.setFocusPainted(false); // Remove aquele quadrado de foco feio
            painelBotoes.add(btn);
        }
        btnSalvar.setBackground(COR_BOTAO_PRINCIPAL); // Destaca o botão de salvar

        // --- LÓGICA DE AÇÕES DOS BOTÕES ---

        // AÇÃO: Adicionar Comentário via Janela de Digitação
        btnAdicionarComentario.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // Abre um popup para o usuário digitar
                String comentario = JOptionPane.showInputDialog(this, "Escreva um comentário para esta conversão:", 
                                     model.getValueAt(row, 1));
                if (comentario != null) {
                    model.setValueAt(comentario, row, 1); // Atualiza visualmente a tabela
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para comentar.");
            }
        });

        // AÇÃO: Excluir linha selecionada
        btnExcluir.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                GerenciadorHistorico.getInstance().remover(row); // Remove no Singleton
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.");
            }
        });

        // AÇÃO: Limpar todo o histórico
        btnLimpar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Limpar histórico?", "Aviso", 0) == 0) {
                GerenciadorHistorico.getInstance().limpar();
            }
        });

        // AÇÃO: Exportar para arquivo de texto (.txt)
        btnSalvar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                // Força a extensão .txt se o usuário esquecer
                if (!arquivo.getName().endsWith(".txt")) arquivo = new File(arquivo.getAbsolutePath() + ".txt");

                try (PrintWriter writer = new PrintWriter(arquivo)) {
                    writer.println("--- RELATÓRIO DE CONVERSÕES ---");
                    // Percorre todas as linhas da tabela para salvar
                    for (int i = 0; i < model.getRowCount(); i++) {
                        writer.println("Conversão: " + model.getValueAt(i, 0));
                        writer.println("Comentário: " + model.getValueAt(i, 1));
                        writer.println("-------------------------------");
                    }
                    JOptionPane.showMessageDialog(this, "Exportado com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
                }
            }
        });

        // AÇÃO: Fecha a janela atual
        btnVoltar.addActionListener(e -> dispose());

        // Adiciona os botões na parte de baixo e inicializa os dados
        contentPane.add(painelBotoes, BorderLayout.SOUTH);
        atualizarTabela();
    }

    /**
     * Método que sincroniza a JTable com os dados que estão no Singleton GerenciadorHistorico.
     */
    private void atualizarTabela() {
        // Garante que a atualização da interface ocorra na Thread correta (EDT)
        SwingUtilities.invokeLater(() -> {
            model.setRowCount(0); // Limpa a tabela para reconstruí-la
            
            // Pega a lista de strings do Singleton e coloca na primeira coluna
            // A segunda coluna (Comentários) inicia vazia para o usuário preencher
            for (String item : GerenciadorHistorico.getInstance().getRegistros()) {
                model.addRow(new Object[] { item, "" }); 
            }
        });
    }
}