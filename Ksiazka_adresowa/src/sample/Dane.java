package sample;

import java.io.Serializable;

public class Dane implements Serializable {
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    private String imie;
    private String nazwisko;
    private Integer telefon;
    private Adres adres;


    Dane(String imie, String nazwisko, Integer telefon, String ulica, String nrDomu, Integer nrMieszkania, String kodPocztowy, String urzadPocztowy){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.adres = new Adres(ulica, nrDomu, nrMieszkania, kodPocztowy, urzadPocztowy);
    }
}
