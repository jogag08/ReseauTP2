import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

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
            }
            catch (Exception e)
            {
                System.out.println(e + "Root");
            }
        }
        System.out.println("Q : " + resolverReq);
        System.out.println("R : " + toResponseFormat(resolverReq));
    }

    public String toResponseFormat(String in)
    {
        String q = in;

        q = switchToResponse(in);
        String name = getName(q, getLen(q));

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
}
