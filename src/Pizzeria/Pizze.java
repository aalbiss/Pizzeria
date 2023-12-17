package Pizzeria;

import java.io.*;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Pizze {

	private String pizzaRichiesta, tipoPasta;
	private int costo;
	private int ore;
	private int minuti;
	private Vector<String> pizzeOrdinate;

	public Pizze() {
		this.pizzaRichiesta = "";
		this.tipoPasta = "";
		this.ore = 17;
		this.minuti = 55;
		this.pizzeOrdinate = new Vector<>(200);
	}

	public void setPizzaRichiesta(String pizzaRichiesta) {
		this.pizzaRichiesta = pizzaRichiesta;
	}

	public int getSize() {
		return pizzeOrdinate.size();
	}

	public int getOre() {
		return ore;
	}

	public int getMinuti() {
		return minuti;
	}

	public void sceltaPizza(){
		Vector<String> listinoPizze = leggiListino();
		Scanner KB = new Scanner(System.in);

		boolean pizzaAccettata = false;

		while (!pizzaAccettata) {
			String consegna;

			do{
				System.out.println("L'ordine è da asporto? (si o no)");
				consegna=KB.nextLine();
			}while(!consegna.equalsIgnoreCase("SI") && !consegna.equalsIgnoreCase("NO"));

			if (consegna.equalsIgnoreCase("SI") && ore<=18){
				pizzaAccettata = isPizzaAccettata();
			} else if (consegna.equalsIgnoreCase("NO")) {
				pizzaAccettata = isPizzaAccettata();
			} else{
				do {
					System.out.println("La pizza può essere solo ritirata in pizzeria\nVa bene comunque? ");
					consegna=KB.nextLine();
				}while(consegna.equalsIgnoreCase("SI") && consegna.equalsIgnoreCase("NO"));

				if(consegna.equalsIgnoreCase("SI")){
					pizzaAccettata = isPizzaAccettata();
				}else{
					pizzaAccettata = true;
				}
			}

		}
		setPizzaRichiesta(pizzaRichiesta);

	}

	private boolean isPizzaAccettata() {
		Vector<String> listinoPizze = leggiListino();
		Scanner KB = new Scanner(System.in);
		boolean pizzaAccettata = false;

		System.out.print("Quale pizza desideri? ");
		do {
			String pizzaRichiesta = KB.nextLine().toLowerCase();
			if (listinoPizze.contains(pizzaRichiesta)) {

				String p = prezzoPizza(trovaPizza(pizzaRichiesta));

				System.out.println("Hai scelto " + pizzaRichiesta + ", la pizza costa " + p + "€. L'ordine è accettato!");
				pizzeOrdinate.add(0, pizzaRichiesta);
				pizzaAccettata = true;
			} else {
				System.out.println("Mi dispiace, " + pizzaRichiesta + " non è nel listino. Scegli un'altra pizza.");
            }
		}while (!pizzaAccettata);
		return true;
	}

	private static Vector<String> leggiListino() {
		Vector<String> pizze = new Vector<>();
		File nomeFile = new File("ListinoPizze");

		try {
			FileInputStream fin = new FileInputStream(nomeFile);
			Scanner in = new Scanner(fin);
			while (in.hasNextLine()){
				String word = in.nextLine();
				pizze.add(word);
			}
		} catch (FileNotFoundException e) {
			System.out.println("L'eccezione è " + e);
		}

		return pizze;
	}

	public int trovaPizza(String pizzaDaCercare) {
		File nomeFile = new File("ListinoPizze");

		try {
			int numeroRiga = 1;
			FileInputStream fin = new FileInputStream(nomeFile);
			Scanner in = new Scanner(fin);
			while (in.hasNextLine()){
				String word = in.nextLine();
				if (word.contains(pizzaDaCercare)) {
					return numeroRiga;
				}
				numeroRiga++;
			}
		} catch (IOException e) {
			System.out.println("L'eccezione è " + e);
		}

		return 0;
	}

	public String prezzoPizza(int lineaPrezzo) {
		File nomeFile = new File("ListinoPrezzi");

		try {

			int numeroRiga = 1;
			FileInputStream fin = new FileInputStream(nomeFile);
			Scanner in = new Scanner(fin);
			while (in.hasNextLine()){
				String word = in.nextLine();
				if (numeroRiga == lineaPrezzo) {
					return word;
				}
				numeroRiga++;
			}
		} catch (IOException e) {
			System.out.println("Eccezione " + e);
		}

		return "";
	}

	public void visualizzaListino(){

		File listinoPizze = new File("ListinoPizze");
		File listinoPrezzi = new File("ListinoPrezzi");

		try {
			FileInputStream finPizze = new FileInputStream(listinoPizze);
			FileInputStream finPrezzi = new FileInputStream(listinoPrezzi);
			Scanner inPizze = new Scanner(finPizze);
			Scanner inPrezzi = new Scanner(finPrezzi);

			while (inPizze.hasNextLine() && inPrezzi.hasNextLine()){
				String pizza = inPizze.nextLine();
				String prezzo = inPrezzi.nextLine();
				System.out.println("La pizza " + pizza + " costa " + prezzo + "€");
			}
		} catch (FileNotFoundException e) {
			System.out.println("L'eccezione è " + e);
		}

	}

	public void visualizza() {
		System.out.println(pizzeOrdinate);
	}

	public void sfornata() {
		System.out.println("La pizza " + pizzeOrdinate.get(pizzeOrdinate.size()-1) + " è stata sfornata");
		pizzeOrdinate.remove((pizzeOrdinate.size()-1));

	}

	public void aumentaOrario(){
		if(minuti<59)
			minuti+=1;
		else {
			ore += 1;
			minuti = 0;
		}
	}





//	public void timerPizza(){
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				sfornata();
//			}
//		}, 10000);
//	}
//
//	public void timerGenerale(){
//		Timer generale = new Timer();
//		generale.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				if (getSize() == 1 || getSize() == 2) {
//					timerPizza();
//				}
//			}
//		}, 300);
//	}

//
//	public boolean inForno(){
//        return getSize() == 1 || getSize() == 2;
//    }

}