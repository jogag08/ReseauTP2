public class Main
{
    public static void main(String[] args)
    {
        Client client = new Client("127.0.0.1", 4000);

        Resolver resolver = new Resolver(4000, "127.0.0.1", 4001, "127.0.0.1", 4002, "127.0.0.1", 4003, "127.0.0.1", 4004, "127.0.0.1", 4005);
        Root rootserver = new Root(4001);
        TLD tld1 = new TLD(4002);
        TLD tld2 = new TLD(4003);
        Authoritative authoritative1 = new Authoritative(4004);
        Authoritative authoritative2 = new Authoritative(4005);

        Thread ClientThread = new Thread(client);
        Thread RootThread = new Thread(rootserver);
        Thread ResolverThread = new Thread(resolver);
        Thread TLDThread1 = new Thread(tld1);
        Thread TLDThread2 = new Thread(tld2);
        Thread AuthoritativeThread1 = new Thread(authoritative1);
        Thread AuthoritativeThread2 = new Thread(authoritative2);

        ClientThread.start();
        RootThread.start();
        ResolverThread.start();
        TLDThread1.start();
        TLDThread2.start();
        AuthoritativeThread1.start();
        AuthoritativeThread2.start();
    }
}