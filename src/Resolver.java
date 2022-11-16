import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Resolver extends Serveur //Premier serveur contacté par le client lors d'une recherche de nom de domaine
{
    boolean connectionOpened = true;
    boolean hasCommunicatedWithRoot = false;
    boolean hasCommunicatedWithTLD = false;
    boolean hasCommunicatedWithAuthoritative = false;

    String rootIp;
    int rootPort;
    String tld1IP;
    int tld1Port;
    String tld2IP;
    int tld2Port;
    String authoritative1IP;
    int authoritative1Port;
    String authoritative2IP;
    int authoritative2Port;

    Socket socketServeur;
    DataInputStream dataInputStreamServeur;
    DataOutputStream dataOutputStreamServeur;
   public Resolver(int port, String rootIP, int rootPort, String tld1IP, int tld1Port, String tld2IP, int tld2Port, String authoritative1IP, int authoritative1Port, String authoritative2IP, int authoritative2Port)
   {
       super(port);
       this.rootIp = rootIP;
       this.rootPort = rootPort;
       this.tld1IP = tld1IP;
       this.tld1Port= tld1Port;
   }

    @Override
    public void run() //méthode appelée lors de l'exécution d'objet sur un thread
    {
        super.run(); // Éxécute ce qui est dans le run du parent
        System.out.println("Resolver");
        String clientReq = "";
        while (clientReq.equals("")) {
            try {
                clientReq = dataInputStream.readUTF();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Du Resolver : " + clientReq);

        //Partie qui communique avec le root (Resolver a Root)
        while (connectionOpened)
        {
            if (!hasCommunicatedWithRoot) {
                try {
                    socketServeur = new Socket(rootIp, rootPort);
                    dataInputStreamServeur = new DataInputStream(socketServeur.getInputStream());
                    dataOutputStreamServeur = new DataOutputStream(socketServeur.getOutputStream());
                    dataOutputStreamServeur.writeUTF(clientReq);
                    hasCommunicatedWithRoot = true;
                } catch (Exception e) {
                    System.out.println(e + "resolver à root");
                }
            }

            if (hasCommunicatedWithRoot && !hasCommunicatedWithTLD)
            {
                try {
                    socketServeur = new Socket(tld1IP, tld1Port);
                    dataInputStreamServeur = new DataInputStream(socketServeur.getInputStream());
                    dataOutputStreamServeur = new DataOutputStream(socketServeur.getOutputStream());
                    dataOutputStreamServeur.writeUTF(clientReq);
                    hasCommunicatedWithTLD = true;
                } catch (Exception e) {
                    System.out.println(e + "resolver à tld1");
                }
            }

            if (hasCommunicatedWithRoot && hasCommunicatedWithTLD && hasCommunicatedWithAuthoritative)
            {
                try {
                    socketServeur = new Socket(authoritative1IP, authoritative1Port);
                    dataInputStreamServeur = new DataInputStream(socketServeur.getInputStream());
                    dataOutputStreamServeur = new DataOutputStream(socketServeur.getOutputStream());
                    dataOutputStreamServeur.writeUTF(clientReq);
                    hasCommunicatedWithTLD = true;
                } catch (Exception e) {
                    System.out.println(e + "resolver à tld1");
                }
            }
        }
    }
}
