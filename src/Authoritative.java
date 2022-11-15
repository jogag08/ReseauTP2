public class Authoritative extends Serveur
{
    public Authoritative(int port)
    {
        super(port);
    }

    @Override
    public void run() //méthode appelée lors de l'exécution d'objet sur un thread
    {
        super.run(); // Éxécute ce qui est dans le run du parent
        System.out.println("Autorithative");
    }
}
