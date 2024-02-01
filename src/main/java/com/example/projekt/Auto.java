package com.example.projekt;

import java.util.Locale;

public class Auto {
    private String nrVin;
    private String marka;
    private String model;
    private int rokProdukcji;
    private String silnik;
    private String paliwo;

    public String getNrVin() {
        return nrVin;
    }

    public void setNrVin(String nrVin) {
        if(nrVin==null)
        {
            throw new NullPointerException("Nie wolno podawać Nulla!");
        }
        if(nrVin.length()==17) {
            this.nrVin = nrVin;
        }
        else {
            throw new IllegalArgumentException("Nr Vin składa się z 17 znaków");
        }
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        if(marka==null || marka.isEmpty())
        {
            throw new NullPointerException("Prosze uzupełnić informacje!");
        }
        else if (marka.length()>45)
        {
            throw new IllegalArgumentException("Zbyt długi napis marki!");
        }
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model==null || model.isEmpty())
        {
            throw new NullPointerException("Prosze uzupełnić informacje!");
        }
        else if (model.length()>45)
        {
            throw new IllegalArgumentException("Zbyt długi napis modelu!");
        }
        this.model = model;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        if(rokProdukcji>1900) {
            this.rokProdukcji = rokProdukcji;
        }
        else
        {
            throw new IllegalArgumentException("nie przyjmujemy tak starych pojazdów!");
        }
    }

    public String getSilnik() {
        return silnik;
    }

    public void setSilnik(String silnik) {
        if(silnik==null || silnik.isEmpty())
        {
            throw new NullPointerException("Nie wolno podawać Nulla!");
        }
        else if (silnik.length()>45)
        {
            throw new IllegalArgumentException("Zbyt długi napis silnika!");
        }
        this.silnik = silnik;
    }

    public String getPaliwo() {
        return paliwo;
    }

    public void setPaliwo(String paliwo) {
        if(paliwo==null)
        {
            throw new NullPointerException("Nie wolno podawać Nulla!");
        }
        if(paliwo.toLowerCase().equals("diesel") || paliwo.toLowerCase().equals("benzyna") || paliwo.toLowerCase().equals("gaz") || paliwo.toLowerCase().equals("prąd")){
            this.paliwo = paliwo.toLowerCase();
        }
        else
        {
            throw new IllegalArgumentException("podano nieprawidłowe zasilanie!");
        }

    }


}
