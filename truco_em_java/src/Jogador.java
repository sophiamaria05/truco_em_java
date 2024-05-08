import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.util.Random;

public class Jogador {
	Scanner input = new Scanner(System.in);
	List<Carta> mao = new ArrayList<Carta>();
	String nome;
	int pontos = 0;
	int trucoComputador = 0;
	
	void receberCarta(Carta carta) {
		mao.add(carta);
	}
	
	void imprimirMao() {
		for (int i=0; i<mao.size(); i++) {
			System.out.println("["+ i +"] "+ mao.get(i).imprimirCarta());
		}
	}
	
	Carta jogarCarta(int carta) {//JOGAR CARTA do jogador
		System.out.println(nome +" jogou: "+ mao.get(carta).imprimirCarta());
		Carta cartaJogada = mao.get(carta);
		mao.remove(carta);
		return cartaJogada;
	}
	void ordenarCartas() {
		for (int i=0; i<mao.size(); i++) {
			if (mao.get(0).valor>mao.get(i).valor) {//se carta atual valor menor que a primeira
				Collections.swap(mao, i, 0);
			}
			else if (mao.get(mao.size()-1).valor<mao.get(i).valor) {//se carta atual valor maior que a ultima
				Collections.swap(mao, i, mao.size()-1);
			}
			if (mao.get(i).manilha && mao.get(i).naipe==3) {
				trucoComputador=4;
			}
			else if (mao.get(i).manilha && trucoComputador<4){
				trucoComputador=3;
			}
			else if (mao.get(i).valor==9 && trucoComputador<3) {
				trucoComputador=2;
			}
			else if (mao.get(i).valor>6 && trucoComputador<2) {
				trucoComputador=1;
			}
		}
		//System.out.println("trucoComputador: "+ trucoComputador);
	}
	
	Carta jogadaComputadorV0(Carta cartaOponente, List<Integer> pontosRodada) {//JOGAR CARTA do computador (jogador começou a rodada)
		Carta cartaJogada = null;
		boolean computadorTemManilha = false;
		for (int i=0; i<mao.size(); i++) {
			if (!cartaOponente.manilha) {
				if (!mao.get(i).manilha) {
					if ((cartaOponente.valor<mao.get(i).valor) && (cartaJogada==null)) {
						System.out.println("Computador jogou: "+ mao.get(i).imprimirCarta());
						cartaJogada = mao.get(i);
						mao.remove(i);
					}
				}
			}
			else {
				if (mao.get(i).manilha) {
					if (cartaOponente.naipe<mao.get(i).naipe) {
						System.out.println("Computador jogou: "+ mao.get(i).imprimirCarta());
						cartaJogada = mao.get(i);
						mao.remove(i);
					}
				}
			}
			if (cartaJogada==null && mao.get(i).manilha) {
				computadorTemManilha = true;
			}
		}
		if (cartaJogada==null) {
			if (computadorTemManilha && pontosRodada.get(0)==1) {
				for (int i=0; i<mao.size(); i++) {
					if (mao.get(i).manilha) {
						if (cartaOponente.naipe<mao.get(i).naipe) {
							System.out.println("Computador jogou: "+ mao.get(i).imprimirCarta());
							cartaJogada = mao.get(i);
							mao.remove(i);
						}
					}
				}
			}
			else {
				System.out.println("Computador jogou: "+ mao.get(0).imprimirCarta());
				cartaJogada = mao.get(0);
				mao.remove(0);
			}
		}
		return cartaJogada;
	}
	
	Carta jogadaComputadorV1() {//JOGAR CARTA do computador (computador começou a rodada)
		Random rand = new Random();
		//System.out.println(mao.size());//Debugando..
		int i = rand.nextInt(mao.size());
		if (i==0) {//diminuindo a chance do computador jogar a menor carta de sua mao
			i = rand.nextInt(mao.size());
		}
		Carta cartaJogada = mao.get(i);
		System.out.println("Computador jogou: "+ mao.get(i).imprimirCarta());
		mao.remove(i);
		return cartaJogada;
	}
	
	int pedirTruco (int jChamou) {//PEDIR TRUCO do computador
		this.ordenarCartas();
		if (jChamou==-1 && trucoComputador>=2) {
			jChamou=1;
		}
		return jChamou;
	}
	
	int respostaTruco (int valorPontoTurno, int j) {//RESPOSTA do computador a chamada de truco
		int resposta=0;
		if(j==1) {//RESPOSTA do computador
			if (valorPontoTurno==1 && trucoComputador>=1) {
				if (trucoComputador>=2) {
					resposta=2;
				}
				else {
					resposta=1;
				}
			}
			else if(valorPontoTurno==3 && trucoComputador>=2) {
				if (trucoComputador>=3) {
					resposta=2;
				}
				else {
					resposta=1;
				}
			}
			else if(valorPontoTurno==6 && trucoComputador>=3) {
				if (trucoComputador>=4) {
					resposta=2;
				}
				else {
					resposta=1;
				}
			}
			else if(valorPontoTurno==9 && trucoComputador>=4) {
				resposta=1;
			}
		}
		else {//RESPOSTA do jogador
			System.out.println("[0] Negar\n[1] Aceitar\n [2]Aumentar");
			boolean continuar=false;
			while (!continuar) {
				resposta=input.nextInt();
				if (resposta>=0 && resposta<3) {
					continuar=true;
				}
				else {
					System.out.println("Entrada invalida! Tente novamente..");
				}
			}
		}
		return resposta;
	}
	
	void receberPontos(int pontosRodada) {
		pontos+=pontosRodada;
	}
}