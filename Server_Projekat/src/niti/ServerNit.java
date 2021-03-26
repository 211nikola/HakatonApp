/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class ServerNit extends Thread {

    private final ServerSocket serverSocket;
    ArrayList<ObradaKlijentskogZahteva> clients;
    private boolean active = true;

    public ServerNit() throws IOException {
        FileInputStream fis = new FileInputStream("db.properties");
        Properties prop = new Properties();
        prop.load(fis);

        String port = prop.getProperty("port", "9000");

        serverSocket = new ServerSocket(Integer.valueOf(port));
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            System.out.println("Cekanje klijenta...");
            try {
                while (active) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Klijent se konektovao");
                    ObradaKlijentskogZahteva client = new ObradaKlijentskogZahteva(socket);
                    client.start();
                    clients.add(client);
                }
                clients.forEach((client1) -> {
                    client1.saljiKraj();
                });

            } catch (IOException e) {
                System.out.println("Server je ugasen!");
                clients.forEach((client1) -> {
                    client1.saljiKraj();
                });
            }
        }
    }

    public void stopServer() {
        try {
            active = false;
            serverSocket.close();
            clients.forEach(Thread::interrupt);
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendShutDownMessage() {
        clients.forEach((client1) -> {
            client1.saljiKraj();
        });
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private void closeClients() {
        clients.forEach((client) -> {
            try {
                client.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
