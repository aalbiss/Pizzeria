package Pizzeria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetturaDaFile {
	public static void main(String[] args) {
		// Specifica il percorso del file da leggere
		String percorsoFile = "ListinoPizze.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(percorsoFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementi = linea.split(",");
                

                for (String elemento : elementi) {
                    try {
                        int numero = Integer.parseInt(elemento);
                        System.out.println("Numero intero: " + numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Stringa: " + elemento);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
