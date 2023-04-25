package operador;

import conta.Agencia;

public class Gerente extends Funcionario {
	CargosEnum tipo = CargosEnum.GERENTE;
	// Cada gerente est� vinculado � uma ag�ncia espec�fica, por�m, diretores e o
	// presidente n�o est�o, estes est�o vinculados ao pr�prio banco
	// podendo acessar m�todos de qualquer ag�ncia
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