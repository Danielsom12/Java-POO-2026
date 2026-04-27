package Conversor;

import java.util.ArrayList;
import java.util.List;

// Gerenciador de histórico usando Singleton para manter a mesma instância
public class GerenciadorHistorico {
	private static GerenciadorHistorico instance;
	private List<String> registros = new ArrayList<>();
	private List<Runnable> listeners = new ArrayList<>();

	private GerenciadorHistorico() {
	}

	public static GerenciadorHistorico getInstance() {
		if (instance == null)
			instance = new GerenciadorHistorico();
		return instance;
	}

	// Adiciona uma ação de "ouvinte" para atualizar a tela quando o histórico mudar
	public void addChangeListener(Runnable listener) {
		listeners.add(listener);
	}

	// Adiciona um registro ao histórico
	public void adicionar(String registro) {
		registros.add(registro);
		notifyListeners();
	}

	// Remove um registro específico
	public void remover(int index) {
		if (index >= 0 && index < registros.size()) {
			registros.remove(index);
			notifyListeners();
		}
	}

	// Limpa todos os registros
	public void limpar() {
		registros.clear();
		notifyListeners();
	}

	// Notifica todos os ouvintes que o histórico mudou
	private void notifyListeners() {
		for (Runnable listener : listeners)
			listener.run();
	}

	public List<String> getRegistros() {
		return registros;
	}
}