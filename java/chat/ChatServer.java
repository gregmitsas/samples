package stage6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer
{

	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<String> CurrentUsers = new ArrayList<String>();

	public static void main(String[] args) throws IOException
	{
		try
		{
			final int port = 2612;
			ServerSocket server = new ServerSocket(port);
			System.out.println("Waiting for clients..");
			while (true)
			{
				Socket socket = server.accept();
				ConnectionArray.add(socket);
				System.out.println("Client connected from: " + socket.getLocalAddress().getHostName());
				AddUserName(socket);
				ChatServerThread SThread = new ChatServerThread(socket);
				Thread thread = new Thread(SThread);
				thread.start();
			}
		}
		catch (Exception X)
		{
			System.out.print(X);
		}

	}

	public static void AddUserName(Socket socket) throws IOException
	{
		Scanner input = new Scanner(socket.getInputStream());
		String UserName = input.nextLine();
		CurrentUsers.add(UserName);

		for (int i = 1; i <= ChatServer.ConnectionArray.size(); i++)
		{
			Socket temp_socket = (Socket) ChatServer.ConnectionArray.get(i - 1);
			PrintWriter out = new PrintWriter(temp_socket.getOutputStream());
			out.println("#?!" + CurrentUsers);
			out.flush();
		}
	}
}
