package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Products;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o caminho da pasta: ");
		String path = sc.nextLine();
		
		File sourceFile = new File(path);
		File parentDirectory = sourceFile.getParentFile();
		
		File destinationFile = new File(parentDirectory, "resumosVendas.txt");
		
		try(BufferedReader br = new BufferedReader(new FileReader(path)); BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))){
			Locale.setDefault(Locale.US);
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				Products prod = new Products(name, price, quantity);
				double total = prod.totalValue();
				
				bw.write(name + ", " + String.format("%.2f", total));
				bw.newLine();
				
				System.out.println("Lido: " + line);
				line = br.readLine();
				
			}
			System.out.println();
			System.out.println("Salvo em: " + destinationFile);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			sc.close();
		}
		
		System.out.println();
		System.out.println("Conteudo salvo no arquivo novo: ");
		try(BufferedReader br = new BufferedReader(new FileReader(destinationFile))){
			String line = br.readLine();
			while(line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			sc.close();
		}

	}

}
