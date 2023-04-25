package operador;

import conta.Agencia;

public class Presidente extends Diretor {
	CargosEnum tipo = CargosEnum.PRESIDENTE;

	public Presidente() {
	}

	public Presidente(String nome, String cpf, int senha) {
		super(nome, cpf, senha);
	}
	public CargosEnum getTipo() {
		return tipo;
	}
}