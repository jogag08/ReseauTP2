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
        //System.out.println("Authoritative");
        String resolverReq = "";
        while(resolverReq.equals(""))
        {
            try
            {
                resolverReq = dataInputStream.readUTF();
            }
            catch(Exception e)
            {
                System.out.println(e + "Authoritative");
            }
        }
        //System.out.println("Du Authoritative : " + resolverReq);
    }
}
