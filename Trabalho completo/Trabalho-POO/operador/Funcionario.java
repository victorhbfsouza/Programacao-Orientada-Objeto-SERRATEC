package operador;
import conta.Agencia;

public abstract class Funcionario extends Pessoa {
    
	public Funcionario() {
	}
	
	public Funcionario(String nome, String cpf, int senha) {
		super(nome, cpf, senha);
	}

	public abstract Agencia getAgencia();

}