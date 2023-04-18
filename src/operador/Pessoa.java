package operador;

public abstract class Pessoa {
	String nome;
	int cpf;
	int senha;
	String cargo;
	int digito;
	
	public String getNome() {
	return nome;
	}
	
	public Pessoa() {
	}
	
	public Pessoa(String nome, int cpf, int senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public abstract String getTipo();
}
