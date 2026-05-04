package Conversor;

// Importação das bibliotecas do Swing (Interface Gráfica) e AWT (Layouts e Cores)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

/**
 * Classe TelaHistorico: Representa a janela que exibe os registros das conversões.
 * Herda de JFrame para criar uma janela independente.
 */
public class TelaHistorico extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // --- PALETA DE CORES PERSONALIZADA ---
    // Definidas em constantes para facilitar a manutenção visual do projeto
    private final Color COR_FUNDO = new Color(235, 227, 165); // Amarelo palha (Fundo)
    private final Color COR_CAMPOS = new Color(242, 245, 142); // Amarelo claro (Campos/Tabela)
    private final Color COR_BOTAO_PRINCIPAL = new Color(245, 204, 69); // Amarelo Ouro (Destaque)
    private final Color COR_BOTAO_SECUNDARIO = new Color(235, 182, 87); // Laranja pastel (Outros botões)

    // Componentes da Tabela
    private JTable table;              // O componente visual da tabela
    private DefaultTableModel model;   // O "cérebro" da tabela (onde os dados ficam guardados)

    public TelaHistorico() {
        // Configurações básicas da janela (Título, Tamanho e Centralização)
        setTitle("Histórico de Conversões");
        setBounds(100, 100, 1200, 900);
        setLocationRelativeTo(null);
        
        // ContentPane: O painel principal que segura todos os outros componentes
        JPanel contentPane = new JPanel(new BorderLayout(20, 20)); // BorderLayout organiza em Norte, Sul, Leste, Oeste e Centro
        contentPane.setBackground(COR_FUNDO);
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30)); // Margem interna de 30px
        setContentPane(contentPane);

        // --- DEFINIÇÃO DE FONTES ---
        Font fonteTitulo = new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 45);
        Font fonteTabela = new Font("Monospaced", Font.BOLD, 16);
        Font fonteBotoes = new Font("Tahoma", Font.BOLD, 18);

        // Rótulo do Título (Parte Superior - NORTH)
        JLabel lblTitulo = new JLabel("Histórico de Atividades");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(fonteTitulo);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // --- CONFIGURAÇÃO DA TABELA ---
        // Criamos o modelo com duas colunas específicas
        model = new DefaultTableModel(new Object[] { "Conversão Realizada", "Comentários do Usuário" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Bloqueia a edição da coluna 0 (Conversão), mas permite editar a 1 (Comentários)
                return column == 1; 
            }
        };

        table = new JTable(model);
        table.setFont(fonteTabela);
        table.setRowHeight(45); // Linhas mais altas para facilitar o clique e leitura
        table.setBackground(COR_CAMPOS);
        table.setSelectionBackground(COR_BOTAO_PRINCIPAL); // Cor de destaque ao clicar em uma linha
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16)); // Estilo do cabeçalho
        
        // Ajuste manual da largura das colunas (60% para descrição, 40% para comentário)
        table.getColumnModel().getColumn(0).setPreferredWidth(600);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);

        // ScrollPane: Necessário para que a tabela tenha barras de rolagem se houver muitos dados
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Singleton: Registra esta tela para ouvir mudanças no GerenciadorHistorico
        GerenciadorHistorico.getInstance().addChangeListener(this::atualizarTabela);

        // --- PAINEL DE BOTÕES (Parte Inferior - SOUTH) ---
        // GridLayout(1 linha, 5 colunas, 15px de espaço entre botões)
        JPanel painelBotoes = new JPanel(new GridLayout(1, 5, 15, 0)); 
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setPreferredSize(new Dimension(0, 80)); // Altura fixa de 80px para os botões

        JButton btnAdicionarComentario = new JButton("Add Comentário");
        JButton btnExcluir = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar Tudo");
        JButton btnSalvar = new JButton("Salvar .txt"); 
        JButton btnVoltar = new JButton("Voltar");

        // Loop para padronizar o estilo de todos os botões de uma vez
        for (JButton btn : new JButton[]{btnAdicionarComentario, btnExcluir, btnLimpar, btnSalvar, btnVoltar}) {
            btn.setFont(fonteBotoes);
            btn.setBackground(COR_BOTAO_SECUNDARIO);
            btn.setFocusPainted(false); // Remove o contorno de foco ao clicar
            painelBotoes.add(btn);
        }
        btnSalvar.setBackground(COR_BOTAO_PRINCIPAL); // Botão salvar ganha cor de destaque

        // --- LÓGICA DAS AÇÕES (LISTENERS) ---

        // Ação: Abre um balão (Input) para digitar comentário na linha selecionada
        btnAdicionarComentario.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String comentario = JOptionPane.showInputDialog(this, "Escreva um comentário:", 
                                     model.getValueAt(row, 1));
                if (comentario != null) {
                    model.setValueAt(comentario, row, 1);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha primeiro!");
            }
        });

        // Ação: Remove a linha selecionada do banco de dados (Singleton)
        btnExcluir.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                GerenciadorHistorico.getInstance().remover(row);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.");
            }
        });

        // Ação: Apaga todo o histórico após confirmação
        btnLimpar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Deseja apagar TUDO?", "Aviso", 0) == 0) {
                GerenciadorHistorico.getInstance().limpar();
            }
        });

        // Ação: Exporta os dados da tabela para um arquivo .txt físico
        btnSalvar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                // Garante que o arquivo tenha extensão .txt
                if (!arquivo.getName().endsWith(".txt")) arquivo = new File(arquivo.getAbsolutePath() + ".txt");

                try (PrintWriter writer = new PrintWriter(arquivo)) {
                    writer.println("--- HISTÓRICO DE CONVERSÕES ---");
                    for (int i = 0; i < model.getRowCount(); i++) {
                        writer.println("ITEM: " + model.getValueAt(i, 0));
                        writer.println("OBS: " + model.getValueAt(i, 1));
                        writer.println("-------------------------------");
                    }
                    JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar arquivo.");
                }
            }
        });

        // Ação: Fecha apenas a janela de histórico
        btnVoltar.addActionListener(e -> dispose());

        contentPane.add(painelBotoes, BorderLayout.SOUTH);
        
        // Carrega os dados iniciais ao abrir a tela
        atualizarTabela();
    }

    /**
     * Sincroniza os dados da JTable com a lista presente no GerenciadorHistorico.
     */
    private void atualizarTabela() {
        // SwingUtilities.invokeLater garante que a interface não trave durante a atualização
        SwingUtilities.invokeLater(() -> {
            model.setRowCount(0); // Limpa a tabela atual
            // Percorre os registros salvos no Singleton e adiciona no modelo
            for (String item : GerenciadorHistorico.getInstance().getRegistros()) {
                model.addRow(new Object[] { item, "" }); // Coluna 0 = Dado, Coluna 1 = Comentário Vazio
            }
        });
    }
}