package com.uteq.app_hidro_01.models;

public class HydroGrow {

    private String Calidad_del_Aire;
    private boolean FLUJO_DE_AGUA;
    private boolean LUCES_LED;
    private double LUMINOSIDAD;
    private boolean Llenado_de_Agua;
    private int NIVEL_DE_PH;
    private boolean NUTRIENTE_DE_NITRÒGENO_Y_FOSFORO;
    private boolean NUTRIENTE_DE_POTASIO;
    private double Temperatura;

    private int AguaLlena;

    public int getAguaLlena() {
        return AguaLlena;
    }

    public void setAguaLlena(int aguaLlena) {
        AguaLlena = aguaLlena;
    }

    public int CambioValor(){
        if(AguaLlena==1)return 0;
        else return 1;
    }

    public String getCalidad_del_Aire() {
        return Calidad_del_Aire;
    }

    public void setCalidad_del_Aire(String calidad_del_Aire) {
        Calidad_del_Aire = calidad_del_Aire;
    }

    public boolean isFLUJO_DE_AGUA() {
        return FLUJO_DE_AGUA;
    }

    public void setFLUJO_DE_AGUA(boolean FLUJO_DE_AGUA) {
        this.FLUJO_DE_AGUA = FLUJO_DE_AGUA;
    }

    public boolean isLUCES_LED() {
        return LUCES_LED;
    }

    public void setLUCES_LED(boolean LUCES_LED) {
        this.LUCES_LED = LUCES_LED;
    }

    public double getLUMINOSIDAD() {
        return LUMINOSIDAD;
    }

    public void setLUMINOSIDAD(double LUMINOSIDAD) {
        this.LUMINOSIDAD = LUMINOSIDAD;
    }

    public boolean isLlenado_de_Agua() {
        return Llenado_de_Agua;
    }

    public void setLlenado_de_Agua(boolean llenado_de_Agua) {
        Llenado_de_Agua = llenado_de_Agua;
    }

    public int getNIVEL_DE_PH() {
        return NIVEL_DE_PH;
    }

    public void setNIVEL_DE_PH(int NIVEL_DE_PH) {
        this.NIVEL_DE_PH = NIVEL_DE_PH;
    }

    public boolean isNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO() {
        return NUTRIENTE_DE_NITRÒGENO_Y_FOSFORO;
    }

    public void setNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO(boolean NUTRIENTE_DE_NITRÒGENO_Y_FOSFORO) {
        this.NUTRIENTE_DE_NITRÒGENO_Y_FOSFORO = NUTRIENTE_DE_NITRÒGENO_Y_FOSFORO;
    }

    public boolean isNUTRIENTE_DE_POTASIO() {
        return NUTRIENTE_DE_POTASIO;
    }

    public void setNUTRIENTE_DE_POTASIO(boolean NUTRIENTE_DE_POTASIO) {
        this.NUTRIENTE_DE_POTASIO = NUTRIENTE_DE_POTASIO;
    }

    public double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(double temperatura) {
        Temperatura = temperatura;
    }

    public String getEstadoFLUJO_DE_AGUA(){
        if(isFLUJO_DE_AGUA())
            return "ON";
        else
            return "OFF";
    }

    public String getLlenado_de_Agua(){
        if(isLlenado_de_Agua())
            return "ON";
        else
            return "OFF";
    }

    public String getEstadoLUCES_LED(){
        if(isLUCES_LED())
            return "ON";
        else
            return "OFF";
    }

    public String getEstadoNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO(){
        if(isNUTRIENTE_DE_NITRÒGENO_Y_FOSFORO())
            return "ON";
        else
            return "OFF";
    }

    public String getEstadoNUTRIENTE_DE_POTASIO(){
        if(isNUTRIENTE_DE_POTASIO())
            return "ON";
        else
            return "OFF";
    }




}
