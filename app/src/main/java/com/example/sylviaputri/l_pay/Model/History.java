package com.example.sylviaputri.l_pay.Model;

public class History {
    public String tglTransaksi;
    public int harga;
    public String store;
    public String idTransaksi;

    public History(String tglTransaksi, int harga, String store, String idTransaksi){
        this.tglTransaksi = tglTransaksi;
        this.harga = harga;
        this.store = store;
        this.idTransaksi = idTransaksi;
    }
}
