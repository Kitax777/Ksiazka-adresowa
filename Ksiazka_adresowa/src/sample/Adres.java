package sample;

import java.io.Serializable;

public class Adres implements Serializable {
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public Integer getNrMieszkania() {
        return nrMieszkania;
    }

    public void setNrMieszkania(Integer nrMieszkania) {
        this.nrMieszkania = nrMieszkania;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getUrzadPocztowy() {
        return urzadPocztowy;
    }

    public void setUrzadPocztowy(String urzadPocztowy) {
        this.urzadPocztowy = urzadPocztowy;
    }

    private String ulica;
    private String nrDomu;
    private Integer nrMieszkania;
    private String kodPocztowy;
    private String urzadPocztowy;

    Adres(String ulica, String nrDomu, Integer nrMieszkania, String kodPocztowy, String urzadPocztowy){
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.nrMieszkania = nrMieszkania;
        this.kodPocztowy = kodPocztowy;
        this.urzadPocztowy = urzadPocztowy;

    }
}