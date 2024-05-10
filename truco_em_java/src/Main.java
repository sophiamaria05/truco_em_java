
public class Main {
    public static void main(String[] args) {
        Partida partida = new Partida();//cria uma nova partida
        int jogar = partida.inicioPartida();//inicia ela e assim que encerrada salva a resposta do jogador
        while (jogar==1) {//jogar==1 caso o jogador queira jogar novavente e jogador==0 caso jogador queira sair do jogo
        	partida = new Partida();//cria uma nova partida para resetar todos os valores
        	jogar=partida.inicioPartida();//inicia novamente a partida
        }
    }
}
