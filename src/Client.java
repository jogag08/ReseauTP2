import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;

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
            System.out.println("Q : " + line);
            System.out.println("------------");
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
        String newUserIn = toHeaderFormat(userIn);
        return newUserIn;
    }

    public String toHeaderFormat(String in)
    {
        String h ="";

        String id = generateId(in);
        String qr = "0";
        String op = "0000";
        String aa = "0";
        String tc = "0";
        String rd = "0";
        String ra = "0";
        String z = "0";
        String rcode = "0000";
        String qdCount = "0000000000000000";
        String anCount = "0000000000000000";
        String nsCount = "0000000000000000";
        String arCount = "0000000000000000";
        String len = getDomainNameLength(in);
        String qName = len + in;
        String qType = "0000000000000001";
        String qClass = "0000000000000001";

        h += id + qr + op + aa + tc + rd + ra + z + rcode + qdCount + anCount + nsCount + arCount + qName + qType + qClass;
        return h;
    }

    public String generateId(String in)
    {
        String id = "";
        for(int i = 0; i<16; i++)
        {
            Random rdmInt = new Random();
            int r = rdmInt.nextInt(10 - 1) + 1;
            id += Integer.toString(r);
        }
        return id;
    }

    public String getDomainNameLength(String in)
    {
        String inLen = "";
        if (in.length() < 10)
        {
            inLen += "0" + in.length();
        }
        else if (in.length() >= 10)
        {
            inLen += in.length();
        }
        return inLen;
    }
}
