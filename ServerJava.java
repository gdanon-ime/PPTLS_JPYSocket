package TrabFim;
import java.util.*;

import java.io.*;  
import java.net.*;

public class ServerJava {

	
	public static String getRandomElement(List<String> list)
	{
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	public static String playGame(){
		List<String> OptionsList = new ArrayList<>();
				
		OptionsList.add("Pedra");
		OptionsList.add("Papel");
		OptionsList.add("Tesoura");
		OptionsList.add("Lagarto");
		OptionsList.add("Spock");
		
		String ServerChoice = getRandomElement(OptionsList);
		return ServerChoice;
	}

	public static Integer resultGame(String serverChoice, String clientChoice){
		if(serverChoice == clientChoice){return 0;}
		if(serverChoice == "Tesoura"){
			if(clientChoice.equals("Papel") || clientChoice.equals("Lagarto")){return 1;}
		}
		if(serverChoice == "Papel"){
			if(clientChoice.equals("Spock") || clientChoice.equals("Pedra")){return 1;}
		}
		if(serverChoice == "Pedra"){
			if(clientChoice.equals("Tesoura") || clientChoice.equals("Lagarto")){return 1;}
		}
		if(serverChoice == "Lagarto"){
			if(clientChoice.equals("Spock") || clientChoice.equals("Papel")){return 1;}
		}
		if(serverChoice == "Spock"){
			if(clientChoice.equals("Tesoura") || clientChoice.equals("Pedra")){return 1;}
		}
		return -1;
	}
	
    public static void main(String[] args)
	{
		int PORT = 12345;

		try{ 

			// Aceitando a conexao do socket
			ServerSocket SocketJava = new ServerSocket(PORT);
			Socket Jsoc = SocketJava.accept();
			System.out.println("Receive new connection: " + Jsoc.getInetAddress());

			// Output e input da conexao
			DataOutputStream dout = new DataOutputStream(Jsoc.getOutputStream());  
			DataInputStream in = new DataInputStream(Jsoc.getInputStream());

			int serverPoints = 0;
			int clientPoints = 0;
			int loop = 0;
			while(loop < 15){
				String clientChoice = (String)in.readUTF();
				String myChoice = playGame();
				System.out.println("Game: " + myChoice + " (Server) x " + clientChoice + " (Client)");
				dout.writeUTF(myChoice + " (Server) x " + clientChoice + " (Client)");

				Integer aux = resultGame(myChoice, clientChoice);

				if(aux < 0){clientPoints += 1;}
				if(aux > 0){serverPoints += 1;}

				loop += 1;
			}

			System.out.println("\n");
			dout.writeUTF("\n");

			System.out.println("Pontuacao: Server = " + serverPoints + " x Client = " + clientPoints);
			dout.writeUTF("Pontuacao: Server = " + serverPoints + " x Client = " + clientPoints);

			dout.flush();
			dout.close();
			Jsoc.close();

			System.out.println("Connection Closed.");

		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	}
}
