package com.uteq.app_hidro_01.models;

public class acostumbrado {

    private String PH;
    private String Temperatura;

    private boolean IsAgua;
    private boolean IsLuz;


    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String temperatura) {
        Temperatura = temperatura;
    }

    public boolean isAgua() {
        return IsAgua;
    }

    public void setAgua(boolean agua) {
        IsAgua = agua;
    }

    public boolean isLuz() {
        return IsLuz;
    }

    public void setLuz(boolean luz) {
        IsLuz = luz;
    }

    @Override
    public String toString() {
        return PH ;
    }
}
