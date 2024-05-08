
public class Carta {
	int valor;
	int naipe;
	boolean manilha = false;
	
	Carta(int valor, int naipe){
		this.valor = valor;
		this.naipe = naipe;
	}
	
	String imprimirCarta() {
    	String naipe = null;
    	switch (this.naipe) {
	    	case 0:
	    		naipe = "♦";
	    		break;
	    	case 1:
	    		naipe = "♠";
	    		break;
	    	case 2:
	    		naipe = "♥";
	    		break;
	    	case 3:
	    		naipe = "♣";
	    		break;
    	}
    	String valor="";
    	switch (this.valor) {
    	  case 0:
    		  valor = "4";
    		  break;
    	  case 1:
    		  valor = "5";
      	    break;
    	  case 2:
    		  valor = "6";
      	    break;
    	  case 3:
    		  valor = "7";
      	    break;
    	  case 4:
    		  valor = "Q";
      	    break;
    	  case 5:
    		  valor = "J";
      	    break;
    	  case 6:
    		  valor = "K";
      	    break;
    	  case 7:
    		  valor = "A";
      	    break;
    	  case 8:
    		  valor = "2";
      	    break;
    	  case 9:
    		  valor = "3";
      	    break;
    	}
    	String str = ""+ valor + naipe;
    	return str;
    }
}
