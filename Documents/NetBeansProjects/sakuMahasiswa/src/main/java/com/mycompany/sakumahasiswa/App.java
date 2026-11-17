package com.mycompany.sakumahasiswa;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    ArrayList<Transaksi> transaksi = new ArrayList<>();

    TextField inputNominal = new TextField();
    DatePicker inputTanggal = new DatePicker();
    TextField inputKeterangan = new TextField();

    Label labelSaldo = new Label("Saldo: Rp0");

    @Override
    public void start(Stage stage) {

        // ==========================
        // KOMPONEN FORM
        // ==========================

        Label judul = new Label("SAKU MAHASISWA");

        Label labelNominal = new Label("Nominal:");
        Label labelTanggal = new Label("Tanggal:");
        Label labelKeterangan = new Label("Keterangan:");

        inputNominal.setPromptText("Contoh: 500000");
        inputTanggal.setPromptText("Pilih tanggal");
        inputKeterangan.setPromptText("Contoh: Uang dari orang tua");

        Button tombolPemasukan =
                new Button("Tambah Pemasukan");

        Button tombolPengeluaran =
                new Button("Tambah Pengeluaran");

        Button tombolSaldo =
                new Button("Lihat Saldo");

        Button tombolTransaksi =
                new Button("Lihat Transaksi");

        // ==========================
        // TAMBAH PEMASUKAN
        // ==========================

        tombolPemasukan.setOnAction(e -> {

            String nominalText =
                    inputNominal.getText().trim();

            String keterangan =
                    inputKeterangan.getText().trim();

            if (nominalText.isEmpty()
                    || inputTanggal.getValue() == null
                    || keterangan.isEmpty()) {

                tampilkanAlert(
                        AlertType.WARNING,
                        "Peringatan",
                        "Semua data harus diisi."
                );

                return;
            }

            try {

                double nominal =
                        Double.parseDouble(nominalText);

                if (nominal <= 0) {

                    tampilkanAlert(
                            AlertType.WARNING,
                            "Input Tidak Valid",
                            "Nominal harus lebih dari 0."
                    );

                    return;
                }

                Pemasukan pemasukan =
                        new Pemasukan(
                                nominal,
                                inputTanggal.getValue().toString(),
                                keterangan
                        );

                transaksi.add(pemasukan);

                updateSaldo();
                kosongkanInput();

                tampilkanAlert(
                        AlertType.INFORMATION,
                        "Berhasil",
                        "Pemasukan berhasil ditambahkan."
                );

            } catch (NumberFormatException ex) {

                tampilkanAlert(
                        AlertType.ERROR,
                        "Kesalahan Format",
                        "Nominal harus berupa angka."
                );
            }
        });

        // ==========================
        // TAMBAH PENGELUARAN
        // ==========================

        tombolPengeluaran.setOnAction(e -> {

            String nominalText =
                    inputNominal.getText().trim();

            String keterangan =
                    inputKeterangan.getText().trim();

            if (nominalText.isEmpty()
                    || inputTanggal.getValue() == null
                    || keterangan.isEmpty()) {

                tampilkanAlert(
                        AlertType.WARNING,
                        "Peringatan",
                        "Semua data harus diisi."
                );

                return;
            }

            try {

                double nominal =
                        Double.parseDouble(nominalText);

                if (nominal <= 0) {

                    tampilkanAlert(
                            AlertType.WARNING,
                            "Input Tidak Valid",
                            "Nominal harus lebih dari 0."
                    );

                    return;
                }

                double saldo = hitungSaldo();

                if (nominal > saldo) {

                    tampilkanAlert(
                            AlertType.WARNING,
                            "Saldo Tidak Cukup",
                            "Saldo tidak mencukupi."
                    );

                    return;
                }

                Pengeluaran pengeluaran =
                        new Pengeluaran(
                                nominal,
                                inputTanggal.getValue().toString(),
                                keterangan
                        );

                transaksi.add(pengeluaran);

                updateSaldo();
                kosongkanInput();

                tampilkanAlert(
                        AlertType.INFORMATION,
                        "Berhasil",
                        "Pengeluaran berhasil ditambahkan."
                );

            } catch (NumberFormatException ex) {

                tampilkanAlert(
                        AlertType.ERROR,
                        "Kesalahan Format",
                        "Nominal harus berupa angka."
                );
            }
        });

        // ==========================
        // LIHAT SALDO
        // ==========================

        tombolSaldo.setOnAction(e -> {

            double pemasukan = 0;
            double pengeluaran = 0;

            for (Transaksi t : transaksi) {

                if (t instanceof Pemasukan) {
                    pemasukan += t.getNominal();
                } else {
                    pengeluaran += t.getNominal();
                }
            }

            tampilkanAlert(
                    AlertType.INFORMATION,
                    "Saldo",
                    "Total Pemasukan  : "
                    + formatRupiah(pemasukan)
                    + "\nTotal Pengeluaran: "
                    + formatRupiah(pengeluaran)
                    + "\nSaldo            : "
                    + formatRupiah(hitungSaldo())
            );
        });

        // ==========================
        // LIHAT TRANSAKSI
        // ==========================

        tombolTransaksi.setOnAction(e -> {

            if (transaksi.isEmpty()) {

                tampilkanAlert(
                        AlertType.INFORMATION,
                        "Daftar Transaksi",
                        "Belum ada transaksi."
                );

                return;
            }

            String daftar = "";

            for (Transaksi t : transaksi) {

                String jenis;

                if (t instanceof Pemasukan) {
                    jenis = "Pemasukan";
                } else {
                    jenis = "Pengeluaran";
                }

                daftar +=
                        "-------------------------\n"
                        + "Jenis      : " + jenis + "\n"
                        + "Nominal    : "
                        + formatRupiah(t.getNominal()) + "\n"
                        + "Tanggal    : "
                        + t.getTanggal() + "\n"
                        + "Keterangan : "
                        + t.getKeterangan() + "\n";
            }

            tampilkanAlert(
                    AlertType.INFORMATION,
                    "Daftar Transaksi",
                    daftar
            );
        });

        // ==========================
        // LAYOUT GRIDPANE
        // ==========================

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        grid.add(judul, 0, 0, 2, 1);

        grid.add(labelNominal, 0, 1);
        grid.add(inputNominal, 1, 1);

        grid.add(labelTanggal, 0, 2);
        grid.add(inputTanggal, 1, 2);

        grid.add(labelKeterangan, 0, 3);
        grid.add(inputKeterangan, 1, 3);

        HBox tombolInput =
                new HBox(
                        10,
                        tombolPemasukan,
                        tombolPengeluaran
                );

        grid.add(tombolInput, 1, 4);

        grid.add(labelSaldo, 0, 5, 2, 1);

        HBox tombolMenu =
                new HBox(
                        10,
                        tombolSaldo,
                        tombolTransaksi
                );

        grid.add(tombolMenu, 1, 6);

        // ==========================
        // SCENE
        // ==========================

        Scene scene =
                new Scene(grid, 400, 300);

        stage.setTitle("SakuMahasiswa");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // ==========================
    // HITUNG SALDO
    // ==========================

    private double hitungSaldo() {

        double totalPemasukan = 0;
        double totalPengeluaran = 0;

        for (Transaksi t : transaksi) {

            if (t instanceof Pemasukan) {
                totalPemasukan += t.getNominal();
            } else {
                totalPengeluaran += t.getNominal();
            }
        }

        return totalPemasukan - totalPengeluaran;
    }

    // ==========================
    // FORMAT RUPIAH
    // ==========================

    private String formatRupiah(double nominal) {

        NumberFormat format =
                NumberFormat.getNumberInstance(
                        new Locale("id", "ID")
                );

        format.setMaximumFractionDigits(0);

        return "Rp" + format.format(nominal);
    }

    // ==========================
    // UPDATE SALDO
    // ==========================

    private void updateSaldo() {

        labelSaldo.setText(
                "Saldo: " + formatRupiah(hitungSaldo())
        );
    }

    // ==========================
    // KOSONGKAN INPUT
    // ==========================

    private void kosongkanInput() {

        inputNominal.clear();
        inputTanggal.setValue(null);
        inputKeterangan.clear();
    }

    // ==========================
    // ALERT
    // ==========================

    private void tampilkanAlert(
            AlertType tipe,
            String judul,
            String pesan) {

        Alert alert = new Alert(tipe);

        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}