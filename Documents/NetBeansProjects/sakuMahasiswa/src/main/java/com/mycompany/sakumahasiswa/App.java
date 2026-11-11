package com.mycompany.sakumahasiswa;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<Transaksi> transaksi = new ArrayList<>();

        int pilihan;

        do {
            System.out.println();
            System.out.println("=== SAKU MAHASISWA ===");
            System.out.println("1. Tambah Pemasukan");
            System.out.println("2. Tambah Pengeluaran");
            System.out.println("3. Lihat Saldo");
            System.out.println("4. Lihat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();

            switch (pilihan) {

                case 1:
                    System.out.println();
                    System.out.println("--- TAMBAH PEMASUKAN ---");

                    System.out.print("Nominal: ");
                    double nominalPemasukan = input.nextDouble();

                    input.nextLine();

                    System.out.print("Tanggal: ");
                    String tanggalPemasukan = input.nextLine();

                    System.out.print("Keterangan: ");
                    String keteranganPemasukan = input.nextLine();

                    Pemasukan pemasukan = new Pemasukan(
                            nominalPemasukan,
                            tanggalPemasukan,
                            keteranganPemasukan
                    );

                    transaksi.add(pemasukan);

                    System.out.println();
                    System.out.println("Pemasukan berhasil ditambahkan.");

                    break;

                case 2:
                    System.out.println();
                    System.out.println("--- TAMBAH PENGELUARAN ---");

                    System.out.print("Nominal: ");
                    double nominalPengeluaran = input.nextDouble();

                    input.nextLine();

                    System.out.print("Tanggal: ");
                    String tanggalPengeluaran = input.nextLine();

                    System.out.print("Keterangan: ");
                    String keteranganPengeluaran = input.nextLine();

                    Pengeluaran pengeluaran = new Pengeluaran(
                            nominalPengeluaran,
                            tanggalPengeluaran,
                            keteranganPengeluaran
                    );

                    transaksi.add(pengeluaran);

                    System.out.println();
                    System.out.println("Pengeluaran berhasil ditambahkan.");

                    break;

                case 3:
                    double totalPemasukan = 0;
                    double totalPengeluaran = 0;

                    for (Transaksi t : transaksi) {

                        if (t instanceof Pemasukan) {
                            totalPemasukan += t.getNominal();
                        } else if (t instanceof Pengeluaran) {
                            totalPengeluaran += t.getNominal();
                        }
                    }

                    double saldo = totalPemasukan - totalPengeluaran;

                    System.out.println();
                    System.out.println("--- SALDO ---");
                    System.out.println("Total Pemasukan  : Rp" + totalPemasukan);
                    System.out.println("Total Pengeluaran: Rp" + totalPengeluaran);
                    System.out.println("Saldo            : Rp" + saldo);

                    break;

                case 4:
                    System.out.println();
                    System.out.println("--- DAFTAR TRANSAKSI ---");

                    if (transaksi.isEmpty()) {
                        System.out.println("Belum ada transaksi.");
                    } else {

                        for (Transaksi t : transaksi) {

                            System.out.println("-------------------------");

                            if (t instanceof Pemasukan) {
                                System.out.println("Jenis      : Pemasukan");
                            } else if (t instanceof Pengeluaran) {
                                System.out.println("Jenis      : Pengeluaran");
                            }

                            System.out.println("Nominal    : Rp" + t.getNominal());
                            System.out.println("Tanggal    : " + t.getTanggal());
                            System.out.println("Keterangan : " + t.getKeterangan());
                        }

                        System.out.println("-------------------------");
                    }

                    break;

                case 5:
                    System.out.println();
                    System.out.println("Terima kasih, sampai jumpa lagi.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 5);

        input.close();
    }
}