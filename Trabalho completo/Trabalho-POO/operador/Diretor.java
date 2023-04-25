package operador;

import conta.Agencia;

public class Diretor extends Gerente {
	CargosEnum tipo = CargosEnum.DIRETOR;
	
    
    public Diretor() {
	}
	
    public Diretor(String nome, String cpf, int senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
    public CargosEnum getTipo() {
		return tipo;
	}
}