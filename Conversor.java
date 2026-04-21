package Conversor;

public class Conversor {
    
    private double valorInserido; //guarda o valor que o usuario digirou.;
    
    public Conversor (double valorInserido) { //contrutor; inicializa o projeto com o valor digitado.
    	this.valorInserido = valorInserido;
    }
    
    public double getValorInserido() { //uso do get seguindo a norma de criar o nome com get ao início; encapsulamento
    	return valorInserido;
    }
    
    public void setValorInserido(double valorInserido) { //só o set pode mudar o que for digitado; encapsulamento
    	this.valorInserido = valorInserido;
    }
    
	public void selecionarGrandeza() { }
    public void selecionarEscala() { }
    
    public double converter(Conversor destino) { //converte para a unidade base e converte para a unidade de destino (com base nas combobox do jframe)
    	double valorBase = this.getValorInserido() * this.exibir(); 
        return valorBase / destino.exibir();
    }
	
	public double exibir() { //método que será usado no polimorfismo; é o fator da escala.
		return 1.0;
	}
}
    
 