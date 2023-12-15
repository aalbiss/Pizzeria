package Pizzeria;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MainPizzeria {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner KB = new Scanner(System.in);
		Pizza p = new Pizza();
//		p.sceltaPizza();
//		p.sceltaPizza();
//		p.visualizza();
		
		int selezione;
		int selezioneVeicolo;
		
		Timer timer = new Timer();

		
//		timer.scheduleAtFixedRate(task, 0, 15000);	
		
		do {
			Utility.Inserimento();
			selezione = KB.nextInt();
			KB.nextLine();
			switch (selezione) {
			case 1:
				p.sceltaPizza();
				timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        p.sfornata();
                    }
                }, 10000);
				break;
			case 2:
				p.visualizza();
				break;
			case 3:
				
				break;
			default:
				break;
			}
			
		}while(selezione!=9);


	}

}
