package conta;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
	public int idAgencia;
	// Cada agência pode ter várias contas, por isso é registrado uma lista de
	// contas em cada uma delas
	List<Conta> listaContas = new ArrayList<Conta>();

	public Agencia() {

	}

	public Agencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public void adicionarConta(Conta conta) {
		listaContas.add(conta);
	}

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public List<Conta> getListaContas() {
		return listaContas;
	}

}