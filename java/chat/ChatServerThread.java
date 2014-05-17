package stage6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("unused")
public class ChatServerThread implements Runnable
{
	Socket socket;
	private Scanner input;
	private PrintWriter out;
	String message = "";

	public ChatServerThread(Socket socket)
	{
		this.socket = socket;
	}

	public void CheckConnection() throws IOException
	{
		if (!socket.isConnected())
		{
			for (int i = 1; i <= ChatServer.ConnectionArray.size(); i++)
			{
				if (ChatServer.ConnectionArray.get(i) == socket)
				{
					ChatServer.ConnectionArray.remove(i);
				}
			}

			for (int i = 1; i <= ChatServer.ConnectionArray.size(); i++)
			{
				Socket temp_socket = (Socket) ChatServer.ConnectionArray.get(i - 1);
				PrintWriter temp_out = new PrintWriter(temp_socket.getOutputStream());
				temp_out.println(temp_socket.getLocalAddress().getHostName() + " disconnected!");
				temp_out.flush();
				System.out.println(temp_socket.getLocalAddress().getHostName() + " disconnected!");
			}
		}
	}

	public void run()
	{
		try
		{
			try
			{
				input = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				while (true)
				{
					CheckConnection();
					if (!input.hasNext())
					{
						return;
					}
					message = input.nextLine();
					System.out.println("Client said: " + message);
					for (int i = 1; i <= ChatServer.ConnectionArray.size(); i++)
					{
						Socket temp_socket = (Socket) ChatServer.ConnectionArray.get(i - 1);
						PrintWriter temp_out = new PrintWriter(temp_socket.getOutputStream());
						temp_out.println(message);
						temp_out.flush();
						System.out.println("Sent to: " + temp_socket.getLocalAddress().getHostName());
					}
				}
			}
			finally
			{
				socket.close();
			}
		}
		catch (Exception ex)
		{
			System.out.print(ex);
		}
	}
}
