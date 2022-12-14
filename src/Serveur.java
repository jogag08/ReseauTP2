import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur implements Runnable
{
    protected ServerSocket serverSocket; //Établit la connexion entre deux sockets
    protected Socket socket; //Socket connecté à un autre socket (client ou autre serveur)
    protected DataInputStream dataInputStream; //Stream par lequel le socket va recevoir de l'information de l'autre socket
    protected DataOutputStream dataOutputStream; //Stream par lequel le socket va envoyer de l'information à l'autre socket
    protected int port; //Port sur lequel va établir la connexion

    public Serveur(int port)
    {
        this.port = port; //this est la classe Serveur
    }
    @Override
    public void run() //Méthode appelée lors de l'exécution d'objet sur un thread
    {
        try
        {
            serverSocket = new ServerSocket(port); // Ouvre la connexion sur un port
            socket = serverSocket.accept(); // Bloque ici tant que personne est connecté
            dataInputStream = new DataInputStream(socket.getInputStream()); // Récupère la référence vers l'input stream du socket
            dataOutputStream = new DataOutputStream(socket.getOutputStream()); // Récupère la référence vers l'output stream du socket
        }
        catch (Exception e)
        {
            System.out.println(e + Integer.toString(port));
        }
    }
}
