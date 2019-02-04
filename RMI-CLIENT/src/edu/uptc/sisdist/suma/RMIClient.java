/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.sisdist.suma;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Ejemplo de un cliente RMI
 *
 * @author Carlos Arenas
 */
public class RMIClient {

    public RMIClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Lugar en el que esta el objeto remoto.
                        // Debe reemplazarse "localhost" por el nombre o ip donde
                        // esta corriendo "rmiregistry".
                        // Naming.lookup() obtiene el objeto remoto

                        InterfaceRemota objetoRemoto
                                = (InterfaceRemota) Naming.lookup("//localhost/ObjetoRemoto");
                        Registry registry = LocateRegistry.getRegistry();
                        String[] boundNames = registry.list();
                        for (String name : boundNames) {
                            System.out.println(name);
                        }
                        // Se realiza la suma remota.
                        System.out.print("5 + 6 = ");
                        System.out.println(objetoRemoto.suma(5, 6));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new RMIClient();
    }

}
