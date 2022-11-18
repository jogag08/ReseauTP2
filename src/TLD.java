import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class TLD extends Serveur // Serveur contacté par le Resolver
{
    public TLD(int port)
    {
        super(port);
    }

    @Override
    public void run() //méthode appelée lors de l'exécution d'objet sur un thread
    {
        super.run(); // Éxécute ce qui est dans le run du parent
        String resolverReq = "";
        while(resolverReq.equals(""))
        {
            try
            {
                resolverReq = dataInputStream.readUTF();
                String query = toQueryFormat(resolverReq);
                String resp = toResponseFormat(query);
                dataOutputStream.writeUTF(resp);
                System.out.println("TLD");
                System.out.println("Q : " + query);
                System.out.println("R : " + resp);
                System.out.println("------------");

            }
            catch(Exception e)
            {
                System.out.println(e + "TLD");
            }
        }
    }

    public String toQueryFormat(String in)
    {
        String q = in;

        String newQ = switchToQuery(q);
        q = newQ.substring(0,131);

        return q;
    }
    public String switchToQuery(String in)
    {
        String id = in.substring(0,16);
        String rightOfQr = in.substring(17);
        String newQuery = id + "0" + rightOfQr;
        return newQuery;
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
}
