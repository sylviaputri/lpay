package com.example.sylviaputri.l_pay.Model;

public class HistoryWithdraw {
    public String tglTransaksi;
    public int total;
    public String idTransaksi;

    public HistoryWithdraw(String tglTransaksi, int total, String idTransaksi){
        this.tglTransaksi = tglTransaksi;
        this.total = total;
        this.idTransaksi = idTransaksi;
    }
}
