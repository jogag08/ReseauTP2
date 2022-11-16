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
        //System.out.println("Root");
        String resolverReq = "";
        while (resolverReq.equals(""))
        {
            try
            {
                resolverReq = dataInputStream.readUTF();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        System.out.println("Du Root : " + resolverReq);
    }
}
