import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
	Scanner input = new Scanner(System.in);
	List<Carta> cartasJogadas = new ArrayList<Carta>();
	List<Jogador> jogadores = new ArrayList<Jogador>();
	List<Integer> pontosRodada = new ArrayList<Integer>();
	Carta manilha;
	int vezRodada = 0;
	int vezTurno = 0;
	int valorPontoRodada = 1;
	int valorPontoTurno = 1;
	int jChamou = -1;
	
	Partida(){
        jogadores.add(new Jogador());
        System.out.println("Nome do jogador: >>");
        jogadores.get(0).nome = input.next();
		
		jogadores.add(new Jogador());
        jogadores.get(1).nome = "Computador";
	}
	
	int inicioPartida() {
		pontosRodada.add(0);
		pontosRodada.add(0);
		Baralho baralho = new Baralho();
		System.out.println("/~/~/~/~/~/~/Inicio da partida/~/~/~/~/~/~/");
		while (jogadores.get(0).pontos<12 && jogadores.get(1).pontos<12) {
			this.turno(baralho);
			vezTurno = this.mudarVez(vezTurno);
			valorPontoRodada = 1;
			valorPontoTurno =1;
			jChamou=-1;
		}
		if (jogadores.get(0).pontos>=12) {
			System.out.println(jogadores.get(0).nome +" venceu a partida!");
			System.out.println("Placar final:\n"+ jogadores.get(0).nome +" 12 x "+ jogadores.get(1).pontos +" Computador");
		}
		else {
			System.out.println("Computador venceu a partida!");
			System.out.println("Placar final:\nComputador 12 x "+ jogadores.get(0).pontos +""+ jogadores.get(1).nome);
		}
		System.out.println("[0] Sair\n[1]Jogar novamente");
		int jogar = input.nextInt();
		while (jogar!=1 && jogar!=0) {
			System.out.println("Entrada invalida! Tente novamente..");
			System.out.println("[0] Sair\n[1]Jogar novamente");
		}
		return jogar;
	}
	
	void turno(Baralho baralho) {
		baralho.embaralhar();
		//Carta vira = 
		baralho.distribuirCartas(jogadores.get(0), jogadores.get(1));
		jogadores.get(0).imprimirMao();
		//jogadores.get(1).ordenarCartas();//Debugando..
		//jogadores.get(1).imprimirMao();//Debugando..
		//System.out.println("");
		vezRodada = vezTurno;
		while (((pontosRodada.get(0)<2 && pontosRodada.get(1)<2) && (jogadores.get(0).pontos<12 && jogadores.get(1).pontos<12)) && (jogadores.get(0).mao.size()!=0)) {
			this.rodada(baralho);
		}
		if (pontosRodada.get(0)==2) {
			System.out.println("/~/~/~/~/~/~/Jogador venceu o turno/~/~/~/~/~/~/");
			pontosRodada.set(0, 0);
			pontosRodada.set(1, 0);
			jogadores.get(0).receberPontos(valorPontoTurno);
			System.out.println("Computador "+ jogadores.get(1).pontos +" x "+ jogadores.get(0).pontos +" "+ jogadores.get(0).nome);
		}
		else {
			System.out.println("/~/~/~/~/~/~/Computador venceu o turno/~/~/~/~/~/~/");
			pontosRodada.set(0, 0);
			pontosRodada.set(1, 0);
			jogadores.get(1).receberPontos(valorPontoTurno);
			System.out.println("Computador "+ jogadores.get(1).pontos +" x "+ jogadores.get(0).pontos +" "+ jogadores.get(0).nome);
		}
	}
	
	void rodada(Baralho baralho) {
		if (vezRodada==0) {
			System.out.println("\n/~/~/~/~/~/~/~/~/~/Sua vez/~/~/~/~/~/~/~/~/~/");
			jogadores.get(0).imprimirMao();
			this.jogada(0);
			if (pontosRodada.get(0)<2 && pontosRodada.get(1)<2) {
				System.out.println("\n/~/~/~/~/~/~/~/Vez do Computador/~/~/~/~/~/~/~/");
				//jogadores.get(1).imprimirMao();//COMENTAR
		        this.jogada(1);
		        if (pontosRodada.get(0)<2 && pontosRodada.get(1)<2) {
			        System.out.println("\n/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/");
			        this.avaliarRodada();
			        System.out.println("\n/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/");
		        }
			}
		}
		else {
			System.out.println("\n/~/~/~/~/~/~/~/Vez do Computador/~/~/~/~/~/~/~/");
			//jogadores.get(1).imprimirMao();//COMENTAR
	        this.jogada(1);
	        if (pontosRodada.get(0)<2 && pontosRodada.get(1)<2) {
				System.out.println("\n/~/~/~/~/~/~/~/~/~/Sua vez/~/~/~/~/~/~/~/~/~/");
				jogadores.get(0).imprimirMao();
				this.jogada(0);
				if (pontosRodada.get(0)<2 && pontosRodada.get(1)<2) {
			        System.out.println("\n/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/");
			        this.avaliarRodada();
			        System.out.println("\n/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/");
				}
	        }
		}
	}
	
	void avaliarRodada() {
		//System.out.println("Avaliando rodada: !!!!!!!!!!!!!!!!!!!!!!!!!!!");//Debugando..
		Carta cartaJogador = null;
		Carta cartaComputador = null;
		if (vezRodada==0) {
			cartaJogador = cartasJogadas.get(cartasJogadas.size()-2);
			cartaComputador = cartasJogadas.get(cartasJogadas.size()-1);
		}
		else {
			cartaJogador = cartasJogadas.get(cartasJogadas.size()-1);
			cartaComputador = cartasJogadas.get(cartasJogadas.size()-2);
		}
		if ((!cartaJogador.manilha) && (!cartaComputador.manilha)) {
			if (cartaJogador.valor>cartaComputador.valor) {
				System.out.println("\n/~/~/~/~/~/~/Jogador vence a rodada/~/~/~/~/~/~/");
				pontosRodada.set(0, pontosRodada.get(0)+valorPontoRodada);
				vezRodada = 0;
			}
			else if (cartaJogador.valor<cartaComputador.valor) {
				System.out.println("\n/~/~/~/~/~/Computador vence a rodada/~/~/~/~/~/");
				pontosRodada.set(1, pontosRodada.get(1)+valorPontoRodada);
				vezRodada = 1;
			}
			else {
				if (jogadores.get(0).mao.size()!=0) {
					System.out.println("\n/~/~/~/~/~/~/~/~/Jogo empachado/~/~/~/~/~/~/~/~/");
					valorPontoRodada+=1;
				}
				
			}
		}
		else if (cartaJogador.manilha && !cartaComputador.manilha) {
			System.out.println("\n/~/~/~/~/~/~/Jogador vence a rodada/~/~/~/~/~/~/");
			pontosRodada.set(0, pontosRodada.get(0)+valorPontoRodada);
			vezRodada = 0;
		}
		else if (!cartaJogador.manilha && cartaComputador.manilha) {
			System.out.println("\n/~/~/~/~/~/Computador vence a rodada/~/~/~/~/~/");
			pontosRodada.set(1, pontosRodada.get(1)+valorPontoRodada);
			vezRodada = 1;
		}
		else {
			if (cartaJogador.naipe>cartaComputador.naipe) {
				System.out.println("\n/~/~/~/~/~/~/Jogador vence a rodada/~/~/~/~/~/~/");
				pontosRodada.set(0, pontosRodada.get(0)+valorPontoRodada);
				vezRodada = 0;
			}
			else {
				System.out.println("\n/~/~/~/~/~/Computador vence a rodada/~/~/~/~/~/");
				pontosRodada.set(1, pontosRodada.get(1)+valorPontoRodada);
				vezRodada = 1;
			}
		}
	}
	
	int mudarVez(int vez) {
		vez+=1;
		if (vez==2) {
			vez=0;
		}
		return vez;
	}
	void jogada(int j) {
		if (j==0) {
			boolean continuar = false;
			if (jChamou!=0) {
				System.out.println("["+ jogadores.get(0).mao.size() +"] Pedir truco");
				while (!continuar) {
					System.out.println("Selecione a carta: >>");
					int escolhaJ = input.nextInt();
					if (escolhaJ==jogadores.get(0).mao.size() && valorPontoTurno<12 && jChamou!=0) {
						if (valorPontoTurno==1) {
							System.out.println("Jogador PEDIU TRUCO");
							jChamou=0;
							int resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
							if (resposta==0) {
								System.out.println("Computador REJEITOU TRUCO");
								pontosRodada.set(0, 2);
								continuar=true;
							}
							else if (resposta==1) {
								System.out.println("Computador ACEITOU TRUCO");
								valorPontoTurno=3;
							}
							else if (resposta==2) {
								System.out.println("Computador PEDIU SEIS");
								valorPontoTurno=3;
								jChamou=1;
								resposta = jogadores.get(0).respostaTruco(valorPontoTurno, 0);
								if (resposta==0) {
									System.out.println("Jogador REJEITOU SEIS");
									pontosRodada.set(0, 2);
									continuar=true;
								}
								else if (resposta==1) {
									System.out.println("Jogador ACEITOU SEIS");
									valorPontoTurno=6;
								}
								else if (resposta==2) {
									System.out.println("Jogador PEDIU NOVE");
									valorPontoTurno=6;
									jChamou=0;
									resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
									if (resposta==0) {
										System.out.println("Computador REJEITOU NOVE");
										pontosRodada.set(0, 2);
										continuar=true;
									}
									else if (resposta==1) {
										System.out.println("Computador ACEITOU NOVE");
										valorPontoTurno=9;
									}
									else if (resposta==2) {
										System.out.println("Computador PEDIU DOZE");
										valorPontoTurno=9;
										jChamou=1;
										resposta = jogadores.get(0).respostaTruco(valorPontoTurno, 0);
										if (resposta==0) {
											System.out.println("Jogador REJEITOU DOZE");
											pontosRodada.set(0, 2);
											continuar=true;
										}
										else if (resposta==1) {
											System.out.println("Jogador ACEITOU DOZE");
											valorPontoTurno=12;
										}
									}
								}
							}
						}
						else if (valorPontoTurno==3) {
							System.out.println("Jogador PEDIU SEIS");
							int resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
							if (resposta==0) {
								System.out.println("Computador REJEITOU SEIS");
								pontosRodada.set(0, 2);
								continuar=true;
							}
							else if (resposta==1) {
								System.out.println("Computador ACEITOU SEIS");
								valorPontoTurno=6;
							}
							else if (resposta==2) {
								System.out.println("Computador PEDIU NOVE");
								valorPontoTurno=6;
								jChamou=1;
								resposta = jogadores.get(0).respostaTruco(valorPontoTurno, 0);
								if (resposta==0) {
									System.out.println("Jogador REJEITOU NOVE");
									pontosRodada.set(0, 2);
									continuar=true;
								}
								else if (resposta==1) {
									System.out.println("Jogador ACEITOU NOVE");
									valorPontoTurno=9;
								}
								else if (resposta==2) {
									System.out.println("Jogador PEDIU DOZE");
									valorPontoTurno=9;
									jChamou=1;
									resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
									if (resposta==0) {
										System.out.println("Computador REJEITOU DOZE");
										pontosRodada.set(0, 2);
										continuar=true;
									}
									else if (resposta==1) {
										System.out.println("Computador ACEITOU DOZE");
										valorPontoTurno=12;
									}
								}
							}
						}
						else if (valorPontoTurno==6) {
							System.out.println("Jogador PEDIU NOVE");
							int resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
							if (resposta==0) {
								System.out.println("Computador REJEITOU NOVE");
								pontosRodada.set(0, 2);
								continuar=true;
							}
							else if (resposta==1) {
								System.out.println("Computador ACEITOU NOVE");
								valorPontoTurno=9;
							}
							else if (resposta==2) {
								System.out.println("Computador PEDIU DOZE");
								valorPontoTurno=9;
								jChamou=1;
								resposta = jogadores.get(0).respostaTruco(valorPontoTurno, 0);
								if (resposta==0) {
									System.out.println("Jogador REJEITOU DOZE");
									pontosRodada.set(0, 2);
									continuar=true;
								}
								else if (resposta==1) {
									System.out.println("Jogador ACEITOU DOZE");
									valorPontoTurno=9;
								}
							}
						}
						else if (valorPontoTurno==9) {
							System.out.println("Jogador PEDIU DOZE");
							int resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
							if (resposta==0) {
								System.out.println("Computador REJEITOU DOZE");
								pontosRodada.set(0, 2);
								continuar=true;
							}
							else if (resposta==1) {
								System.out.println("Computador ACEITOU DOZE");
								valorPontoTurno=12;
							}
						}
					}
					else if (escolhaJ>=0 && escolhaJ<jogadores.get(0).mao.size()) {
						cartasJogadas.add(jogadores.get(0).jogarCarta(escolhaJ));
						continuar=true;
					}
					else {
						System.out.println("Entrada invalida! Tente novamente..");
					}
				}
			}
			else {
				while (!continuar) {
					System.out.println("Selecione a carta: >>");
					int escolhaJ = input.nextInt();
					if (escolhaJ>=0 && escolhaJ<jogadores.get(0).mao.size()+1) {
						cartasJogadas.add(jogadores.get(0).jogarCarta(escolhaJ));
						continuar=true;
					}
					else {
						System.out.println("Entrada invalida! Tente novamente..");
					}
				}
			}
		}
		else {
			boolean continuar=false;
			while (!continuar) {
				jChamou = jogadores.get(1).pedirTruco(jChamou);
				if (jChamou==1 && valorPontoTurno==1) {
					System.out.println("Computador PEDIU TRUCO");
					int resposta = jogadores.get(0).respostaTruco(valorPontoTurno, 0);
					if (resposta==0) {
						System.out.println("Jogador REJEITOU TRUCO");
						pontosRodada.set(1, 2);
						continuar=true;
					}
					else if (resposta==1) {
						System.out.println("Jogador aceitou TRUCO");
						valorPontoTurno=3;
						
					}
					else {
						System.out.println("Jogador PEDIU SEIS");
						valorPontoTurno=3;
						jChamou=0;
						resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
						if (resposta==0) {
							System.out.println("Computador REJEITOU SEIS");
							pontosRodada.set(0, 2);
							continuar=true;
						}
						else if (resposta==1) {
							System.out.println("Computador ACEITOU SEIS");
							valorPontoTurno=6;
						}
						else if (resposta==2) {
							System.out.println("Computador PEDIU NOVE");
							valorPontoTurno=6;
							jChamou=1;
							resposta = jogadores.get(0).respostaTruco(valorPontoTurno, 0);
							if (resposta==0) {
								System.out.println("Jogador REJEITOU NOVE");
								pontosRodada.set(0, 2);
								continuar=true;
							}
							else if (resposta==1) {
								System.out.println("Jogador ACEITOU NOVE");
								valorPontoTurno=9;
							}
							else if (resposta==2) {
								System.out.println("Jogador PEDIU DOZE");
								valorPontoTurno=9;
								jChamou=1;
								resposta = jogadores.get(1).respostaTruco(valorPontoTurno, 1);
								if (resposta==0) {
									System.out.println("Computador REJEITOU DOZE");
									pontosRodada.set(0, 2);
									continuar=true;
								}
								else if (resposta==1) {
									System.out.println("Computador ACEITOU DOZE");
									valorPontoTurno=12;
								}
							}
						}
					}
				}
				else if (vezRodada==0) {
					cartasJogadas.add(jogadores.get(1).jogadaComputadorV0(cartasJogadas.get(cartasJogadas.size()-1), pontosRodada));
					continuar=true;
				}
				else {
					cartasJogadas.add(jogadores.get(1).jogadaComputadorV1());
					continuar=true;
				}
			}
		}
	}
}
