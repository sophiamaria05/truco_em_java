import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
	List<Carta> cartasMonte = new ArrayList<Carta>();
	List<Carta> cartasDescarte = new ArrayList<Carta>();
	
    Baralho(){
    	List<Integer> cartasOrdem = new ArrayList<Integer>();
    	for (int i=0; i<40; i++){
            cartasOrdem.add(i);
        	cartasMonte.add(new Carta(i%10, i/10));
            //System.out.println(cartasMonte.get(i).imprimirCarta());
    	}
    }
    
    void embaralhar() {
    	while (cartasDescarte.size()!=0) {
    		cartasMonte.add(cartasDescarte.get(0));
    		cartasDescarte.remove(0);
    	}
    	Collections.shuffle(cartasMonte);
    }
    
    Carta distribuirCartas(Jogador j1, Jogador j2) {
    	System.out.println("/~/~/~/~/~/~/Distribuindo cartas/~/~/~/~/~/~/");
    	while (j1.mao.size()!=0) {
    		j1.mao.remove(0);
    	}
    	while (j2.mao.size()!=0) {
    		j2.mao.remove(0);
    	}
    	cartasMonte.get(0).manilha=false;
    	Carta vira = cartasMonte.get(0);
    	cartasDescarte.add(cartasMonte.get(0));
    	cartasMonte.remove(0);

		System.out.println("\n/~/~/~/~/~/~/~/~/~/Vira = "+ vira.imprimirCarta() +"/~/~/~/~/~/~/~/~/~/");
    	for (int i=0; i<3; i++) {
    		this.verificarManilha(vira, cartasMonte.get(0));
    		cartasDescarte.add(cartasMonte.get(0));
    		j1.receberCarta(cartasMonte.get(0));
    		cartasMonte.remove(0);
    		this.verificarManilha(vira, cartasMonte.get(0));
    		cartasDescarte.add(cartasMonte.get(0));
    		j2.receberCarta(cartasMonte.get(0));
    		cartasMonte.remove(0);
    	}
    	return vira;
    }
    
    void verificarManilha(Carta vira, Carta carta) {
    	if (carta.valor==vira.valor+1 || (vira.valor==9 && carta.valor==0)) {
    		carta.manilha=true;
    	}
    	else {
    		carta.manilha=false;
    	}
    }
}
//4567QJKA23
//OUROS ESPADAS COPAS PAUS
//♦ ♠ ♥ ♣