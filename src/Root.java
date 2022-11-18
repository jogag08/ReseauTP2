import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Root extends Serveur // Serveur contacté par le resolver qui renvoie l'addresse IP du TLDserveur approprié
{
    public Root(int port)
    {
        super(port);
    }

    @Override
    public void run() //méthode appelée lors de l'exécution d'objet sur un thread
    {
        super.run(); // Éxécute ce qui est dans le run du parent
        System.out.println("Root");
        String resolverReq = "";
        while (resolverReq.equals(""))
        {
            try
            {
                resolverReq = dataInputStream.readUTF();
                String line = toResponseFormat(resolverReq);
                dataOutputStream.writeUTF(line);
            }
            catch (Exception e)
            {
                System.out.println(e + "Root");
            }
        }
        System.out.println("Q : " + resolverReq);
        System.out.println("R : " + toResponseFormat(resolverReq));
        System.out.println("------------");
    }

    public String toResponseFormat(String in)
    {
        String q = in;

        q = switchToResponse(in);
        String name = getName(q, getLen(q));
        String type = "0000000000000001";
        String rClass = "0000000000000001";
        String ttl ="00000000000000000000000000000030";

        q += name + type + rClass + ttl;

        return q;
    }

    public int getLen(String in)
    {
       String req = in.substring(94,96);
       int reqLen = Integer.parseInt(req);
       return reqLen;
    }

    public String getName(String in, int len)
    {
        String dName = in.substring(96, 96+len);
        return dName;
    }

    public String switchToResponse(String in)
    {
        String id = in.substring(0,16);
        String rightOfQr = in.substring(17);
        String newResp = id + "1" + rightOfQr;
        return newResp;
    }

    public String readFile(String in)
    {
        try
        {
            File f = new File("MasterFilesRoot.txt");
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine())
            {
                String info = scan.nextLine();
                System.out.println(info);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return null;
    }
}
