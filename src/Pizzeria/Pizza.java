package Pizzeria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Pizza {

	private String pizzaRichiesta, tipoPasta;
	private int costo;
	private Vector<String> pizzeOrdinate;

	public Pizza() {
		this.pizzaRichiesta = "";
		this.tipoPasta = "";
		this.costo = 0;
		this.pizzeOrdinate = new Vector<String>(200);
	}

	public Pizza(String pizzaRichiesta, String tipoPasta, int costo) {
		this.pizzaRichiesta = pizzaRichiesta;
		this.tipoPasta = tipoPasta;
		this.costo = costo;
		pizzeOrdinate = new Vector<String>(200);
	}

	public String getPizzaRichiesta() {
		return pizzaRichiesta;
	}

	public void setPizzaRichiesta(String pizzaRichiesta) {
		this.pizzaRichiesta = pizzaRichiesta;
	}

	public String getNomePizza() {
		return pizzaRichiesta;
	}

	public void setNomePizza(String pizzaRichiesta) {
		this.pizzaRichiesta = pizzaRichiesta;
	}

	public String getTipoPasta() {
		return tipoPasta;
	}

	public void setTipoPasta(String tipoPasta) {
		this.tipoPasta = tipoPasta;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getSize() {
		return pizzeOrdinate.size();
	}
	
	public void sceltaPizza(){
		Vector<String> listinoPizze = leggiListino("ListinoPizze.txt");
		Vector<String> listinoPrezzi = leggiListino("ListinoPrezzi.txt");
		Scanner KB = new Scanner(System.in);

		boolean pizzaAccettata = false;

		while (!pizzaAccettata) {
			System.out.print("Quale pizza desideri? ");
			String pizzaRichiesta = KB.nextLine().toLowerCase();		
			if (listinoPizze.contains(pizzaRichiesta)) {
				
				String p = prezzoPizza(trovaPizza(pizzaRichiesta));
				
				System.out.println("Hai scelto " + pizzaRichiesta + ", la pizza costa " + p + "€. L'ordine è accettato!");
				pizzeOrdinate.add(0, pizzaRichiesta);
				pizzaAccettata = true;
			} else {
				System.out.println("Mi dispiace, " + pizzaRichiesta + " non è nel listino. Scegli un'altra pizza.");
			}
		}
		setPizzaRichiesta(pizzaRichiesta);

	}

	private static Vector<String> leggiListino(String percorsoFile) {
		Vector<String> pizze = new Vector<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(percorsoFile))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				pizze.add(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pizze;
	}

	public int trovaPizza(String pizzaDaCercare) {

		String fileDaCercare = "ListinoPizze.txt";

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileDaCercare));
			String linea;
			int numeroRiga = 1;

			while ((linea = br.readLine()) != null) {
				if (linea.contains(pizzaDaCercare)) {
					return numeroRiga;
				}
				numeroRiga++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public String prezzoPizza(int lineaPrezzo) {
		String fileDaLeggere = "ListinoPrezzi.txt"; // Inserisci il percorso del tuo file di testo
      
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileDaLeggere));
            String linea;
            int numeroLineaCorrente = 0;

            while ((linea = br.readLine()) != null) {
                numeroLineaCorrente++;
                if (numeroLineaCorrente == lineaPrezzo) {
                    return linea;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return "";
	}

	public void visualizza() {
		System.out.println(pizzeOrdinate);
	}

	public void sfornata() {
		pizzeOrdinate.remove((pizzeOrdinate.size()-1));
	}

	
	
}




