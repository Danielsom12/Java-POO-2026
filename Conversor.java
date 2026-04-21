package Conversor;

public class Conversor {
    
	// Atributo "valorInserido" -> o valor que o usuario digirou.;
    private double valorInserido; 
    
    //Define o Construtor; inicializa o projeto com o valor digitado.
    public Conversor (double valorInserido) { 
    	this.valorInserido = valorInserido;
    }
    
  //Usamos do get seguindo a norma de criar o nome com get ao início; encapsulamento
    public double getValorInserido() { 
    	return valorInserido;
    }
    
  //Define o set que pode mudar o que for digitado; encapsulamento
    public void setValorInserido(double valorInserido) { 
    	this.valorInserido = valorInserido;
    }
    
	//public void selecionarGrandeza() { } isso aqui foi feito em Tela.java
    //public void selecionarEscala() { } isso aqui foi feito em Tela.java
    
  //Converte para a unidade base e converte para a unidade de destino (com base nas combobox do jframe)
    public double converter(Conversor destino) { 
    	double valorBase = this.getValorInserido() * this.exibir(); 
        return valorBase / destino.exibir();
    }
	
  //Método que será usado no polimorfismo; é o fator da escala.
	public double exibir() { 
		return 1.0;
	}
}
    
 