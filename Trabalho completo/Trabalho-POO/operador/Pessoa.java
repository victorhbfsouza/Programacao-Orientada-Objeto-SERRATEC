package operador;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected int senha;
	protected CargosEnum tipo;

	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String cpf, int senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getSenha() {
		return this.senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public CargosEnum getTipo() {
		return tipo;
	}

	public void setTipo(CargosEnum tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + "]";
	}
	

}