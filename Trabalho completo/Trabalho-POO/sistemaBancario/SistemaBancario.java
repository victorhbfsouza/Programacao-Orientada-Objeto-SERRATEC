package sistemaBancario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import conta.Agencia;
import conta.ContaCorrente;
import conta.ContaPoupanca;
import operador.Cliente;
import operador.Diretor;
import operador.Funcionario;
import operador.Gerente;
import operador.Presidente;

public class SistemaBancario {

	static List<Agencia> listaAgencias = new ArrayList<Agencia>();
	static List<Cliente> listaClientes = new ArrayList<>();
	static List<Gerente> listaGerentes = new ArrayList<>();
	static List<Diretor> listaDiretores = new ArrayList<>();
	static List<Presidente> listaPresidentes = new ArrayList<>();
	static List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	static Scanner read = new Scanner(System.in).useLocale(Locale.ROOT);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		inputDados();
		Collections.sort(listaClientes);
		menu();

	}

//	O menu separa os diferentes tipos de logins (Cliente e Funcionário)
	public static void menu() throws IOException {

		String escolha = "0";

		System.out.println("------------------------------------------------------------------");
		System.out.println("-------------------------   Six Bank  -----------------------------");
		System.out.println("------------------------------------------------------------------\n");
		while (!escolha.equals("3")) {
			System.out.println("1 - Login Cliente\n");
			System.out.println("2 - Login Funcionario\n");
			System.out.println("3 - Encerrar sistema\n");
			System.out.print("Opcao: ");
			escolha = read.next();

			switch (escolha) {
			case "1":
				menuCliente();
				break;
			case "2":
				menuFuncionario();
				System.out.println("Funcionario");
				break;
			case "3":
				System.out.println("Sistema encerrado!");
				break;
			default:
				System.out.println("Opcao invalida! \n ");
				break;

			}
		}

	}

//	O menu do cliente tem papel de login, com as opções referentes aos clientes
	public static void menuCliente() throws IOException {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String agoraFormatado = agora.format(formatter);

		LocalDateTime agora1 = LocalDateTime.now();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
		formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
		String agoraFormatado1 = agora1.format(formatter1);
		System.out.println("-------------------------   Menu do cliente:  -----------------------------\n");
		int agencia;
		int conta = 0;
		int senha;
		int posicao1 = -5;
		int posicao2 = -5;
		int posicao3 = -5;
		int posicao4 = -5;
		double valor = 0;
		String escolha = "0";
		int tempo = 0;
		double deposito = 0;

		try {
			// O cliente digita a agência
			System.out.print("Agencia: ");
			agencia = read.nextInt();

			// Checagem para ver se a agência existe
			for (int i = 0; i < listaAgencias.size(); i++) {
				if (listaAgencias.get(i).idAgencia == agencia) {
					posicao1 = i;
					break;
				}
			}

			if (posicao1 == -5) {
				System.out.print("Agencia nao encontrada!\n");
			} else {
				// O cliente digita a conta
				System.out.print("Conta: ");
				conta = read.nextInt();
				// Checagem para ver se a conta está vinculada a agência
				for (int i = 0; i < listaAgencias.get(posicao1).getListaContas().size(); i++) {
					if (listaAgencias.get(posicao1).getListaContas().get(i).getNumero() == conta) {
						// O cliente digita a senha
						System.out.print("Senha: ");
						senha = read.nextInt();
						// Checagem para ver se a senha do cliente está correta
						if (listaAgencias.get(posicao1).getListaContas().get(i).getCliente().getSenha() == senha) {
							posicao2 = i;
						} else {

						}
						break;
					}
				}
				if (posicao2 == -5) {
					System.out.println("Conta ou senha invalida!");
				}
			}

			if (posicao1 != -5 && posicao2 != -5) {
				System.out.println("Bem-vindo(a) "
						+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getCliente().getNome());
				while (!escolha.equals("6")) {
					System.out.println("Operacao desejada \n");
					System.out.println("0 - Saldo\n");
					System.out.println("1 - Saque\n");
					System.out.println("2 - Deposito\n");
					System.out.println("3 - Tranferencia\n");
					System.out.println("4 - Extrato\n");
					System.out.println("5 - Simulacao poupanca\n");
					System.out.println("6 - Encerrar\n");
					// Escolha da operação desejada pelo cliente
					System.out.print("Opcao: ");
					escolha = read.next();

					switch (escolha) {
					// A primeira opção só imprime o saldo do cliente no console
					case "0":
						System.out.println("Seu saldo e de: R$"
								+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo());
						break;
					// A segunda opção é o saque, o cliente digita o valor desejado (A própria
					// operação do saque faz a checagem de positividade
					// e se o saldo da conta do cliente é suficiente para o saque)
					case "1":
						if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
								.equals("CORRENTE")) {
							System.out.println("O saque feito por contas corrente tem uma taxa de R$0.10.");
						}
						System.out.println("Qual o valor desejado para o saque: ");
						valor = read.nextDouble();
						listaAgencias.get(posicao1).getListaContas().get(posicao2).Saque(valor);

						// Essa parte escreve um arquivo com todas as operações de saque realizadas
						// naquele dia pelo cliente no extrato do cliente
						// no extrato do cliente (acessado por ele) e no relatório de operações
						// (acessado pelos funcionários)
						if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
								.equals("CORRENTE")) {
							escrever(
									"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
											+ "-" + agoraFormatado + "-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getCliente()
													.getNome()
											+ ".txt",
									agoraFormatado1 + ": Saque: R$" + valor + ". Saldo R$" + Math
											.round(listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo()
													* 100.0)
											/ 100.0 + " (taxa de saque R$0.10)");
						} else {
							escrever(
									"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
											+ "-" + agoraFormatado + "-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getCliente()
													.getNome()
											+ ".txt",
									agoraFormatado1 + ": Saque: R$" + valor + ". Saldo R$" + Math
											.round(listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo()
													* 100.0)
											/ 100.0);
						}
						escrever(
								"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\operacoes"
										+ "conta"
										+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
										+ ".txt",
								agoraFormatado + "_" + agoraFormatado1 + ": Saque R$" + valor);
						break;
					case "2":
						// A terceira opção é a de depósito, o próprio método depósito checa a
						// positividade do valor depositado
						if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
								.equals("CORRENTE")) {
							System.out.println("O deposito feito em contas corrente tem uma taxa de R$0,10.");
						}
						System.out.println("Qual o valor desejado para o deposito: ");
						valor = read.nextDouble();
						listaAgencias.get(posicao1).getListaContas().get(posicao2).Deposito(valor);

						// Essa parte escreve um arquivo com todas as operações de depósito realizadas
						// naquele dia pelo cliente no extrato do cliente
						// no extrato do cliente (acessado por ele) e no relatório de operações
						// (acessado pelos funcionários)
						if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
								.equals("CORRENTE")) {
							escrever(
									"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
											+ "-" + agoraFormatado + "-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getCliente()
													.getNome()
											+ ".txt",
									agoraFormatado1 + ": Deposito: R$" + valor + ". Saldo R$" + Math
											.round(listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo()
													* 100.0)
											/ 100.0 + " (taxa de deposito R$0.10)");
						} else {
							escrever(
									"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
											+ "-" + agoraFormatado + "-"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getCliente()
													.getNome()
											+ ".txt",
									agoraFormatado1 + ": Deposito: R$" + valor + ". Saldo R$" + Math
											.round(listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo()
													* 100.0)
											/ 100.0);
						}
						escrever(
								"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\operacoes"
										+ "conta"
										+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
										+ ".txt",
								agoraFormatado + "_" + agoraFormatado1 + ": Deposito R$" + valor);
						break;
					case "3":
						if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
								.equals("CORRENTE")) {
							System.out.println("A transferencia feita por contas corrente tem uma taxa de R$0,20.");
						}
						// O cliente coloca a agencia da conta para a qual a tranferência será realizada
						System.out.print("Agencia: ");
						agencia = read.nextInt();
						// Checagem de existência da agência
						for (int i = 0; i < listaAgencias.size(); i++) {
							if (listaAgencias.get(i).idAgencia == agencia) {
								posicao3 = i;
								break;
							}
						}
						if (posicao3 == -5) {
							System.out.print("Agencia nao encontrada!\n");
							break;
						} else {
							System.out.print("Conta: ");
							conta = read.nextInt();
							for (int i = 0; i < listaAgencias.get(posicao3).getListaContas().size(); i++) {
								if (listaAgencias.get(posicao3).getListaContas().get(i).getNumero() == conta) {
									posicao4 = i;
									if (listaAgencias.get(posicao3).getIdAgencia() == listaAgencias.get(posicao1)
											.getIdAgencia()
											&& listaAgencias.get(posicao1).getListaContas().get(posicao2)
													.getNumero() == listaAgencias.get(posicao3).getListaContas()
															.get(posicao4).getNumero()) {
										System.out.println("Tranferencias disponiveis apenas para outras contas");
									} else {
										System.out.println("Valor da transferencia: ");
										valor = read.nextDouble();
										listaAgencias.get(posicao1).getListaContas().get(posicao2).Transferir(
												listaAgencias.get(posicao3).getListaContas().get(posicao4), valor);
									}
									break;
								}
							}
							if (posicao4 == -5) {
								System.out.println("Conta ou senha invalida!");
							}
						}
						// Checagem pra ver se a conta é diferente da conta do próprio cliente
						if (listaAgencias.get(posicao3).getIdAgencia() == listaAgencias.get(posicao1).getIdAgencia()
								&& listaAgencias.get(posicao1).getListaContas().get(posicao2)
										.getNumero() == listaAgencias.get(posicao3).getListaContas().get(posicao4)
												.getNumero()) {

						} else {
							// Geração de arquivos para extrato e relatório
							if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
									.equals("CORRENTE")) {
								escrever(
										"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
												+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
												+ "-" + agoraFormatado + "-"
												+ listaAgencias.get(posicao1).getListaContas().get(posicao2)
														.getCliente().getNome()
												+ ".txt",
										agoraFormatado1 + ": Transferencia: R$" + valor + " para conta "
												+ listaAgencias.get(posicao3).getListaContas().get(posicao4).getNumero()
												+ " agencia " + listaAgencias.get(posicao3).getIdAgencia()
												+ ". Saldo R$"
												+ Math.round(listaAgencias.get(posicao1).getListaContas().get(posicao2)
														.getSaldo() * 100.0) / 100.0
												+ " (taxa de transferÃªncia R$0.20)");
								escrever(
										"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
												+ listaAgencias.get(posicao3).getListaContas().get(posicao4).getNumero()
												+ "-" + agoraFormatado + "-"
												+ listaAgencias.get(posicao3).getListaContas().get(posicao4)
														.getCliente().getNome()
												+ ".txt",
										agoraFormatado1 + ": Transferencia recebida: R$" + valor + " da conta "
												+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
												+ " agencia " + listaAgencias.get(posicao1).getIdAgencia()
												+ ". Saldo R$" + Math.round(listaAgencias.get(posicao3).getListaContas()
														.get(posicao4).getSaldo() * 100.0) / 100.0);
							} else {
								escrever(
										"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
												+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
												+ "-" + agoraFormatado + "-"
												+ listaAgencias.get(posicao1).getListaContas().get(posicao2)
														.getCliente().getNome()
												+ ".txt",
										agoraFormatado1 + ": Transferencia: R$" + valor + " para conta "
												+ listaAgencias.get(posicao3).getListaContas().get(posicao4).getNumero()
												+ " agencia " + listaAgencias.get(posicao3).getIdAgencia()
												+ ". Saldo R$" + Math.round(listaAgencias.get(posicao1).getListaContas()
														.get(posicao2).getSaldo() * 100.0) / 100.0);
								escrever(
										"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
												+ listaAgencias.get(posicao3).getListaContas().get(posicao4).getNumero()
												+ "-" + agoraFormatado + "-"
												+ listaAgencias.get(posicao3).getListaContas().get(posicao4)
														.getCliente().getNome()
												+ ".txt",
										agoraFormatado1 + ": Transferencia recebida: R$" + valor + " de conta "
												+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
												+ " agencia " + listaAgencias.get(posicao1).getIdAgencia()
												+ ". Saldo R$" + Math.round(listaAgencias.get(posicao3).getListaContas()
														.get(posicao4).getSaldo() * 100.0) / 100.0);
							}
							escrever(
									"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\operacoes"
											+ "conta"
											+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
											+ ".txt",
									agoraFormatado + "_" + agoraFormatado1 + ": Transferencia R$" + valor);
							sobrescrever(
									"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\conta"
											+ listaAgencias.get(posicao3).getListaContas().get(posicao4).getNumero()
											+ ".txt",
									listaAgencias.get(posicao3).getListaContas().get(posicao4).getNumero() + ";"
											+ listaAgencias.get(posicao3).getListaContas().get(posicao4).getSaldo());
						}
						break;
					case "4":
						// Leitura de extrato para o cliente
						try {
							ler("C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\extrato-"
									+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero() + "-"
									+ agoraFormatado + "-"
									+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getCliente().getNome()
									+ ".txt");
						} catch (FileNotFoundException e) {
							System.out.println(
									"Saldo: " + listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo());
						}
						break;
					case "5":
						// A simulação de contas mostra a partir de um determinado depósito incial e um
						// tempo esperado
						if (listaAgencias.get(posicao1).getListaContas().get(posicao2).getTipoConta().name()
								.equals("CORRENTE")) {
							System.out.println("Simulacao disponivel apenas para contas poupanca.");
						} else {
							System.out.println("Valor simulado: ");
							deposito = read.nextDouble();
							System.out.println("Tempo de simulacao (meses): ");
							tempo = read.nextInt();
							System.out.println("O montante final e de: R$"
									+ Math.round(deposito * Math.pow(1.005, tempo) * 100.0) / 100.0);
						}

						break;
					case "6":
						// Na finalização o arquivo de input da conta é substituído por um novo, com o
						// novo saldo
						sobrescrever(
								"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\conta"
										+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero()
										+ ".txt",
								listaAgencias.get(posicao1).getListaContas().get(posicao2).getNumero() + ";"
										+ listaAgencias.get(posicao1).getListaContas().get(posicao2).getSaldo());
						break;
					default:
						System.out.println("Dados invalidos!");
						break;
					}
				}

			}
		} catch (Exception e) {
			System.out.println("Dados invalidos!");
		}

	}

//  O menu de funcionário tem papel de fazer a identificação do tipo de funcionário que realiza o login
	public static void menuFuncionario() throws IOException {
		String cpf;
		int senha;
		int posicao = -5;
		System.out.println("CPF: ");
		cpf = read.next();

		for (int i = 0; i < listaFuncionarios.size(); i++) {
			if (listaFuncionarios.get(i).getCpf().equals(cpf)) {
				posicao = i;
				break;
			}
		}
		if (posicao == -5) {
			System.out.println("CPF invalido!");
		} else {
			System.out.print("Senha: ");
			senha = read.nextInt();
			if (listaFuncionarios.get(posicao).getSenha() == senha) {
				if (listaFuncionarios.get(posicao).getTipo().name().equals("GERENTE")) {
					menuGerente(cpf, posicao);
				} else if (listaFuncionarios.get(posicao).getTipo().name().equals("DIRETOR")) {
					menuDiretor(cpf, posicao);
				} else {
					menuPresidente(cpf, posicao);
				}
			} else {
				System.out.println("Senha invalida!");
			}
		}

	}

//	Identificado como gerente, o funcionário pode realizar acesso a conta do cliente (mediante autorização via senha), e ter um relatório
//	da sua própria agência
	public static void menuGerente(String cpf, int posicao) throws IOException {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String agoraFormatado = agora.format(formatter);

		String escolha = "0";
		System.out.println("Bem-vindo " + listaFuncionarios.get(posicao).getNome());
		while (!escolha.equals("3")) {
			System.out.println("-------------------------   Menu gerente:  -----------------------------\n");
			System.out.println("1 - Acesso a conta de cliente");
			System.out.println("2 - Relatorio Gerente");
			System.out.println("3 - Sair");
			System.out.print("Opcao: ");
			escolha = read.next();
			switch (escolha) {
			case "1":
				menuCliente();
				break;
			case "2":
				escrever(
						"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\relatorio-"
								+ agoraFormatado + listaFuncionarios.get(posicao).getAgencia().getIdAgencia() + ".txt",
						"A agencia " + listaFuncionarios.get(posicao).getAgencia().getIdAgencia() + " possui "
								+ listaFuncionarios.get(posicao).getAgencia().getListaContas().size() + " contas.\n");
				System.out.println("Relatorio criado com sucesso!");
				break;
			case "3":
				break;
			default:
				break;
			}
		}

	}

//	O diretor pode realizar todas as operações dp gerente, e realizar um relatório com todos os contas e agências
	public static void menuDiretor(String cpf, int posicao) throws IOException {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String agoraFormatado = agora.format(formatter);
		String escolha = "0";
		int agencia;
		int posAgen = -5;
		System.out.println("Bem-vindo " + listaFuncionarios.get(posicao).getNome());
		while (!escolha.equals("4")) {
			System.out.println("-------------------------   Menu Diretor:  -----------------------------\n");
			System.out.println("1 - Acesso a conta de cliente");
			System.out.println("2 - Relatorio Gerente");
			System.out.println("3 - Relatorio Diretor");
			System.out.println("4 - Sair");
			System.out.print("Opcao:  ");
			escolha = read.next();
			switch (escolha) {
			case "1":
				menuCliente();
				break;
			case "2":
				System.out.print("Agencia: ");
				agencia = read.nextInt();

				for (int i = 0; i < listaAgencias.size(); i++) {
					if (listaAgencias.get(i).getIdAgencia() == agencia) {
						posAgen = i;
						break;
					}
				}
				if (posAgen == -5) {
					System.out.println("Agencia nao encontrada!");
				} else {
					escrever(
							"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\relatorio-"
									+ agoraFormatado + "-" + listaAgencias.get(posAgen).getIdAgencia()
									+ "(Diretor).txt",
							"A agencia " + listaAgencias.get(posAgen).getIdAgencia() + " possui "
									+ listaAgencias.get(posAgen).getListaContas().size() + " contas.\n");
					System.out.println("Relatorio criado com sucesso!");
				}

				break;
			case "3":
				for (int i = 0; i < listaClientes.size(); i++) {
					for (int j = 0; j < listaAgencias.size(); j++) {
						for (int k = 0; k < listaAgencias.get(j).getListaContas().size(); k++) {
							if (listaAgencias.get(j).getListaContas().get(k).getCliente().getNome()
									.equals(listaClientes.get(i).getNome())) {
								escrever(
										"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\relatorioContas-"
												+ agoraFormatado + ".txt",
										"Agencia: " + listaAgencias.get(j).getIdAgencia() + ". Conta: "
												+ listaAgencias.get(j).getListaContas().get(k).getNumero() + ". "
												+ listaClientes.get(i).toString());
							}
						}
					}
				}

				System.out.println("Relatorio criado com sucesso!");
				break;
			case "4":
				break;
			default:
				break;
			}
		}

	}

//	O presidente pode realizar todas as operações anteriores e ainda tem o relatório do capital de todo o sistema bancário
	public static void menuPresidente(String cpf, int posicao) throws IOException {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String agoraFormatado = agora.format(formatter);
		String escolha = "0";
		int agencia;
		int posAgen = -5;
		double soma = 0;
		System.out.println("Bem-vindo " + listaFuncionarios.get(posicao).getNome());
		while (!escolha.equals("5")) {
			System.out.println("-------------------------   Menu Presidente:  -----------------------------\n");
			System.out.println("1 - Acesso a conta de cliente");
			System.out.println("2 - Relatorio Gerente");
			System.out.println("3 - Relatorio Diretor");
			System.out.println("4 - Relatorio Presidente");
			System.out.println("5 - Sair");
			System.out.print("Opcao:  ");
			escolha = read.next();
			switch (escolha) {
			case "1":
				menuCliente();
				break;
			case "2":
				System.out.print("Agencia: ");
				agencia = read.nextInt();

				for (int i = 0; i < listaAgencias.size(); i++) {
					if (listaAgencias.get(i).getIdAgencia() == agencia) {
						posAgen = i;
						break;
					}
				}
				if (posAgen == -5) {
					System.out.println("Agencia nao encontrada!");
				} else {
					escrever(
							"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\relatorio-"
									+ agoraFormatado + "-" + listaAgencias.get(posAgen).getIdAgencia()
									+ "(Presidente).txt",
							"A agencia " + listaAgencias.get(posAgen).getIdAgencia() + " possui "
									+ listaAgencias.get(posAgen).getListaContas().size() + " contas.\n");
					System.out.println("Relatorio criado com sucesso!");
				}

				break;
			case "3":
				for (int i = 0; i < listaClientes.size(); i++) {
					for (int j = 0; j < listaAgencias.size(); j++) {
						for (int k = 0; k < listaAgencias.get(j).getListaContas().size(); k++) {
							if (listaAgencias.get(j).getListaContas().get(k).getCliente().getNome()
									.equals(listaClientes.get(i).getNome())) {
								escrever(
										"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\relatorioContas-"
												+ agoraFormatado + "(Presidente).txt",
										"Agencia: " + listaAgencias.get(j).getIdAgencia() + ". Conta: "
												+ listaAgencias.get(j).getListaContas().get(k).getNumero() + ". "
												+ listaClientes.get(i).toString());
							}
						}
					}
				}

				System.out.println("Relatorio criado com sucesso!");
				break;
			case "4":
				for (int i = 0; i < listaAgencias.size(); i++) {
					for (int j = 0; j < listaAgencias.get(i).getListaContas().size(); j++) {
						soma += listaAgencias.get(i).getListaContas().get(j).getSaldo();
					}
				}
				escrever(
						"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\relatorioCapital-"
								+ agoraFormatado + ".txt",
						"Capital total: R$" + soma);
				System.out.println("Relatorio criado com sucesso!");
				break;
			case "5":
				break;
			default:
				break;
			}
		}
	}

//	A leitura de arquivos e o input de dados faz a parte de criar os cliente, os funcionários e as contas referentes a cada um dos clientes{
	public static void LerArquivo() throws IOException {
		// Leitura dos arquivos
		BufferedReader arquivoRomulo = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoRomuloCC.txt"));
		BufferedReader arquivoJoao = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoJoaoCP.txt"));
		BufferedReader arquivoRonaldo = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoRonaldoCC.txt"));
		BufferedReader arquivoGabriel = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoGabrielCP.txt"));
		BufferedReader arquivoVictor = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoVictor.txt"));
		BufferedReader arquivoMarcelo = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoMarcelo.txt"));
		BufferedReader arquivoMatheus = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoMatheus.txt"));
		BufferedReader arquivoYan = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\arquivoYan.txt"));
		BufferedReader conta1234 = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\conta1234.txt"));
		BufferedReader conta1235 = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\conta1235.txt"));
		BufferedReader conta4321 = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\conta4321.txt"));
		BufferedReader conta5321 = new BufferedReader(new FileReader(
				"C:\\Users\\Victor Hugo\\Desktop\\Serratec\\Trabalhos\\POO\\Arquivos\\conta5321.txt"));
		String linha;
		String nome, cpf;
		int senha;
		int numero;
		double saldo;

		// Clientes:
		while (true) {
			linha = arquivoRomulo.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaClientes.add(new Cliente(nome, cpf, senha));
			} else {
				arquivoRomulo.close();
				break;
			}
		}
		while (true) {
			linha = arquivoJoao.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaClientes.add(new Cliente(nome, cpf, senha));
			} else {
				arquivoJoao.close();
				break;
			}
		}
		while (true) {
			linha = arquivoRonaldo.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaClientes.add(new Cliente(nome, cpf, senha));
			} else {
				arquivoRonaldo.close();
				break;
			}
		}
		while (true) {
			linha = arquivoGabriel.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaClientes.add(new Cliente(nome, cpf, senha));
			} else {
				arquivoGabriel.close();
				break;
			}
		}

		// Funcionários:
		while (true) {
			linha = arquivoVictor.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaGerentes.add(new Gerente(nome, cpf, senha, listaAgencias.get(0)));
			} else {
				arquivoVictor.close();
				break;
			}
		}
		while (true) {
			linha = arquivoMarcelo.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaGerentes.add(new Gerente(nome, cpf, senha, listaAgencias.get(1)));
			} else {
				arquivoMarcelo.close();
				break;
			}
		}
		while (true) {
			linha = arquivoMatheus.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaDiretores.add(new Diretor(nome, cpf, senha));
			} else {
				arquivoMatheus.close();
				break;
			}
		}
		while (true) {
			linha = arquivoYan.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				nome = arrayStrings[0];
				cpf = (arrayStrings[1]);
				senha = Integer.parseInt(arrayStrings[2]);
				listaPresidentes.add(new Presidente(nome, cpf, senha));
			} else {
				arquivoYan.close();
				break;
			}
		}

		// Criação das contas e vinculação com clientes:
		while (true) {
			linha = conta1234.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				numero = Integer.parseInt(arrayStrings[0]);
				saldo = Double.parseDouble(arrayStrings[1]);
				listaAgencias.get(0).adicionarConta(new ContaCorrente(numero, saldo, listaClientes.get(0)));
			} else {
				conta1234.close();
				break;
			}
		}
		while (true) {
			linha = conta1235.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				numero = Integer.parseInt(arrayStrings[0]);
				saldo = Double.parseDouble(arrayStrings[1]);
				listaAgencias.get(0).adicionarConta(new ContaPoupanca(numero, saldo, listaClientes.get(1)));
			} else {
				conta1235.close();
				break;
			}
		}
		while (true) {
			linha = conta4321.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				numero = Integer.parseInt(arrayStrings[0]);
				saldo = Double.parseDouble(arrayStrings[1]);
				listaAgencias.get(1).adicionarConta(new ContaCorrente(numero, saldo, listaClientes.get(2)));
			} else {
				conta4321.close();
				break;
			}
		}
		while (true) {
			linha = conta5321.readLine();
			String[] arrayStrings;
			if (linha != null) {
				arrayStrings = linha.split(";");
				numero = Integer.parseInt(arrayStrings[0]);
				saldo = Double.parseDouble(arrayStrings[1]);
				listaAgencias.get(1).adicionarConta(new ContaPoupanca(numero, saldo, listaClientes.get(3)));
			} else {
				conta5321.close();
				break;
			}
		}

	}

	public static void inputDados() throws IOException {
		// Criação das agências
		listaAgencias.add(new Agencia(1234));
		listaAgencias.add(new Agencia(4321));

		try {
			LerArquivo();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Listagem de funcionários (independente dos cargos)
		listaFuncionarios.add(listaGerentes.get(0));
		listaFuncionarios.add(listaGerentes.get(1));
		listaFuncionarios.add(listaDiretores.get(0));
		listaFuncionarios.add(listaPresidentes.get(0));

	}
//	}

//	A função escrever cria e escreve em arquivos sem sobrescrever dados que já estão presentes neste aquivo	
	public static void escrever(String path, String texto) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true));
		String linha = "";
		linha = texto;
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

//  A função ler tem o papel de ler linha-a-linha e escrever cada uma das linhas no console
	public static void ler(String path) throws IOException {

		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";

		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				System.out.println(linha);
			} else

				break;

		}
		buffRead.close();
	}

//	A função sobrescrever tem o mesmo papel da escrever, mas sobrescrevendo o arquivo
	public static void sobrescrever(String path, String texto) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		linha = texto;
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}
}