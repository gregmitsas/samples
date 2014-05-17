package stage6;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ChatClientThread implements Runnable
{

	Socket socket;
	Scanner input;
	Scanner send = new Scanner(System.in);
	PrintWriter out;

	public ChatClientThread(Socket socket)
	{
		this.socket = socket;
	}

	public void run()
	{
		try
		{
			try
			{
				input = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				out.flush();
				CheckStream();
			}
			finally
			{
				socket.close();
			}
		}
		catch (Exception X)
		{
			System.out.print(X);
		}
	}

	public void disconnect() throws IOException
	{
		out.flush();
		socket.close();
		JOptionPane.showMessageDialog(null, "You have disconnected!");
		System.exit(0);
	}

	public void CheckStream()
	{
		while (true)
		{
			receive();
		}
	}

	public void receive()
	{
		if (input.hasNext())
		{
			String message = input.nextLine();
			if (message.contains("#?!"))
			{
				String temp1 = message.substring(3);
				temp1 = temp1.replace("[", "");
				temp1 = temp1.replace("]", "");
				String[] CurrentUsers = temp1.split(", ");
				ChatClient.jl_online.setListData(CurrentUsers);
			}
			else
			{
				ChatClient.ta_conversation.append(message + "\n");

			}
		}
	}

	public void send(String message)
	{
		out.println(ChatClient.UserName + ": " + message);
		out.flush();
		ChatClient.tf_message.setText("");
	}
}
