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
        //System.out.println("TLD");
        String resolverReq = "";
        while(resolverReq.equals(""))
        {
            try
            {
                resolverReq = dataInputStream.readUTF();
            }
            catch(Exception e)
            {
                System.out.println(e + "TLD");
            }
        }
        //System.out.println("Du TLD : " + resolverReq);
    }
}
