package Conversor;

import java.util.ArrayList;
import java.util.List;

// Gerenciador de histórico usando Singleton para garantir que todas as telas usem a mesma lista
public class GerenciadorHistorico {
    // A própria instância única da classe (privada e estática)
    private static GerenciadorHistorico instance;
    
    // Lista que armazena as frases das conversões realizadas
    private List<String> registros = new ArrayList<>();
    
    // Lista de ações (tarefas) que devem ser executadas quando os dados mudarem
    private List<Runnable> listeners = new ArrayList<>();

    // Construtor privado: impede que outras classes criem um "new GerenciadorHistorico()"
    private GerenciadorHistorico() {
    }

    // Método global que entrega a única instância existente (cria se não houver, retorna se já existir)
    public static GerenciadorHistorico getInstance() {
        if (instance == null)
            instance = new GerenciadorHistorico();
        return instance;
    }

    // Permite que uma tela se "inscreva" para ser avisada sempre que houver mudanças no histórico
    public void addChangeListener(Runnable listener) {
        listeners.add(listener);
    }

    // Salva um novo texto no histórico e avisa as telas para se atualizarem
    public void adicionar(String registro) {
        registros.add(registro);
        notifyListeners();
    }

    // Deleta um item específico da lista com base na linha selecionada
    public void remover(int index) {
        if (index >= 0 && index < registros.size()) {
            registros.remove(index);
            notifyListeners();
        }
    }

    // Apaga todo o conteúdo da lista de uma vez
    public void limpar() {
        registros.clear();
        notifyListeners();
    }

    // Percorre a lista de telas inscritas e executa a atualização em cada uma delas
    private void notifyListeners() {
        for (Runnable listener : listeners)
            listener.run();
    }

    // Retorna a lista completa de registros para ser exibida na JTable ou JList
    public List<String> getRegistros() {
        return registros;
    }
}