package Pizzeria;

//TODO if there are N pizze in the oven I have to wait for the others and when there is a empty space in the oven i can cook them
//FIXME the timers has to be in two different threads so i can run them in the same time
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainPizzeria {

	public static void main(String[] args) {
		Scanner KB = new Scanner(System.in);
		Pizze p = new Pizze();
//		p.sceltaPizza();
//		p.sceltaPizza();
//		p.visualizza()
		int selezione;

		Runnable updateDate = () -> {
            p.aumentaOrario();
//            System.out.println(p.getOre() + ":" + p.getMinuti());
//            System.out.println("Orario aumentato");
        };
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateDate, 0, 5, TimeUnit.SECONDS);

		Timer timer = new Timer();

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
