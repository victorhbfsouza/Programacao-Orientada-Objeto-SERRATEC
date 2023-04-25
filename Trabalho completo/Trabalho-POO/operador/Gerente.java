package operador;

import conta.Agencia;

public class Gerente extends Funcionario {
	CargosEnum tipo = CargosEnum.GERENTE;
	// Cada gerente está vinculado à uma agência específica, porém, diretores e o
	// presidente não estão, estes estão vinculados ao próprio banco
	// podendo acessar métodos de qualquer agência
	Agencia agencia = new Agencia();

	public Gerente() {
	}

	public Gerente(String nome, String cpf, int senha, Agencia agencia) {
		super(nome, cpf, senha);
		this.agencia = agencia;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public CargosEnum getTipo() {
		return tipo;
	}

}