package com.example.projekt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ZapisDoPliku {

    PrintWriter zapis;

    ZapisDoPliku(String sciezka)
    {
        try {
            zapis = new PrintWriter(sciezka);


        }
        catch(IOException exc)
        {
           throw new RuntimeException("nie odnaleziono ścieżki do pliku!");
        }
    }

    public void zapiszAuto(ArrayList<Auto> listaAut)
    {

        listaAut.forEach(auto -> {
                zapis.print(auto.getNrVin());
                zapis.print(";");
                zapis.print(auto.getMarka());
                zapis.print(";");
                zapis.print(auto.getModel());
                zapis.print(";");
                zapis.print(auto.getRokProdukcji());
                zapis.print(";");
                zapis.print(auto.getSilnik());
                zapis.print(";");
                zapis.print(auto.getPaliwo());
                zapis.print(";");
                zapis.println();
                }
        );
        zapis.flush();

        zapis.close();
    }

    public void zapiszAutoLadnie(ArrayList<Auto> listaAut)
    {
        zapis.println(String.format("%-45s%-45s%-45s%-45s%-45s%-45s","Vin","Marka","Model","Rok Produkcji","Silnik","Paliwo"));

        listaAut.forEach(auto -> {
                    zapis.print(String.format("%-45s",auto.getNrVin()));
                    zapis.print(String.format("%-45s",auto.getMarka()));
                    zapis.print(String.format("%-45s",auto.getModel()));
                    zapis.print(String.format("%-45d",auto.getRokProdukcji()));
                    zapis.print(String.format("%-45s",auto.getSilnik()));
                    zapis.print(String.format("%-45s",auto.getPaliwo()));
                    zapis.println();
                }
        );
        zapis.close();


    }
}
