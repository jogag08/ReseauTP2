public class Main
{
    public static void main(String[] args)
    {
        Client client = new Client("127.0.0.1", 1000);

        Resolver resolver = new Resolver(1000, "127.0.0.1", 1001, "127.0.0.1", 1002, "127.0.0.1", 1003, "127.0.0.1", 1004, "127.0.0.1", 1005);
        Root rootserver = new Root(1001);
        TLD tld1 = new TLD(1002);
        TLD tld2 = new TLD(1003);
        Authoritative authoritative1 = new Authoritative(1004);
        Authoritative authoritative2 = new Authoritative(1005);

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