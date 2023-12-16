package Pizzeria;

//TODO if there are N pizze in the oven I have to wait for the others and when there is a empty space in the oven i can cook them
//FIXME the timers has to be in two different threads so i can run them in the same time

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MainPizzeria {

	public static void main(String[] args) {
		Scanner KB = new Scanner(System.in);
		Pizze p = new Pizze();
//		p.sceltaPizza();
//		p.sceltaPizza();
//		p.visualizza()
		int selezione;

		Timer timer = new Timer();
		Timer generale = new Timer();
		generale.schedule(new TimerTask() {
			@Override
			public void run() {
				p.aumentaOrario();
				System.out.println(p.getOre() + ":" + p.getMinuti());
				System.out.println("Orario aumentato");
			}
		}, 3000);

		p.visualizzaListino();
		do {
			Utility.Inserimento();
			selezione = KB.nextInt();
			KB.nextLine();
			switch (selezione) {
				case 1:
					p.sceltaPizza();
//					p.timerGenerale();
					if(p.getSize() == 1 || p.getSize() == 2){
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								p.sfornata();
							}
						}, 10000);
					}
					break;
				case 2:
					p.visualizza();
					break;
				case 3:
					p.visualizzaListino();
					break;
				default:
					break;
			}

		}while(selezione!=9);


	}

}
