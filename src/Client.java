import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client implements Runnable
{
    Socket socket;
    DataInputStream dataInputStream;
    DataInputStream consoleIn;
    DataOutputStream dataOutputStream;
    String resolverIp;
    int resolverPort;

    public Client(String resolverIP, int resolverPort)
    {
       this.resolverIp = resolverIP;
       this.resolverPort = resolverPort;
    }

    @Override
    public void run()
    {
        try
        {
            consoleIn = new DataInputStream(System.in);
            socket = new Socket(resolverIp, resolverPort);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String line = userInput();
            dataOutputStream.writeUTF(line);
        }
        catch (Exception e)
        {
            System.out.println(e + "client");
        }
    }

    public String userInput()
    {
        String userIn = "";
        while (userIn.equals(""))
        {
            try
            {
                userIn = consoleIn.readLine();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        return userIn;
    }
}
