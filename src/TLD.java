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
        System.out.println("TLD");
    }
}
