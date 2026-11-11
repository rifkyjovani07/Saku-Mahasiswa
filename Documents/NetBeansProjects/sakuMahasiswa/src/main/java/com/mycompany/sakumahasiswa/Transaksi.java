package com.mycompany.sakumahasiswa;

public class Transaksi {
    private double nominal;
    private String tanggal;
    private String keterangan;
    
    public Transaksi(double nominal, String tanggal, String keterangan){
        this.nominal = nominal;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }
    
    public double getNominal(){
        return this.nominal;
    }
    
    public String getTanggal(){
        return this.tanggal;
    }
    
    public String getKeterangan(){
        return this.keterangan;
    }
    
    public void setNominal(double nominal){
        this.nominal = nominal;
    }
    
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    
    public void setKeterangan(String keterangan){
        this.keterangan = keterangan;
    }
}
