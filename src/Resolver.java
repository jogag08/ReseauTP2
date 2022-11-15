import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Resolver extends Serveur //Premier serveur contacté par le client lors d'une recherche de nom de domaine
{
    String rootIp;
    int rootPort;

    Socket socketServeur;
    DataInputStream dataInputStreamServeur;
    DataOutputStream dataOutputStreamServeur;
   public Resolver(int port, String rootIP, int rootPort)
   {
       super(port);
       this.rootIp = rootIP;
       this.rootPort = rootPort;
   }

    @Override
    public void run() //méthode appelée lors de l'exécution d'objet sur un thread
    {
        super.run(); // Éxécute ce qui est dans le run du parent
        System.out.println("Resolver");
        String clientReq = "";
        while (clientReq.equals(""))
        {
            try
            {
                clientReq = dataInputStream.readUTF();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        System.out.println("Du Resolver : " + clientReq);

        try
        {
            socketServeur = new Socket(rootIp, rootPort);
            dataInputStreamServeur = new DataInputStream(socketServeur.getInputStream());
            dataOutputStreamServeur = new DataOutputStream(socketServeur.getOutputStream());
            dataOutputStreamServeur.writeUTF(clientReq);
        }
        catch (Exception e)
        {
            System.out.println(e + "resolver à root");
        }
    }
}
