package com.example.sylviaputri.l_pay.Model;

public class TransaksiJualBeli {
    private String telp_pembeli;
    private Long total;
    private String waktu;
    private String nama_toko;
    private String image_toko;

    public TransaksiJualBeli() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    //constructor
    public TransaksiJualBeli(String image_toko, String nama_toko, Long total){
        this.image_toko = image_toko;
        this.nama_toko = nama_toko;
        this.total = total;
    }

    public String getTelp_pembeli() {
        return telp_pembeli;
    }

    public void setTelp_pembeli(String telp_pembeli) {
        this.telp_pembeli = telp_pembeli;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getImage_toko() {
        return image_toko;
    }

    public void setImage_toko(String image_toko) {
        this.image_toko = image_toko;
    }
}
