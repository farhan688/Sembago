package com.example.sembago.adapter

import android.widget.Spinner
import com.example.sembago.R

//class ArrayAdapter {
//
//    val mySpinner: Spinner = findViewById(R.id.spKategori)
//
//    // Data yang akan ditampilkan di dropdown
//    val items = arrayOf("Gula", "Minyak", "Beras")
//
//    // Buat ArrayAdapter untuk dropdown
//    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
//
//// Set adapter ke Spinner
//    mySpinner.adapter = adapter
//
//// Menangani item yang dipilih dari dropdown
//    mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//            // Mendapatkan nilai dari item yang dipilih
//            val selectedItem = parent.getItemAtPosition(position).toString()
//
//            // Lakukan sesuatu dengan nilai yang dipilih
//            // Misalnya, tampilkan nilai yang dipilih
//            Toast.makeText(applicationContext, "Pilihan Anda: $selectedItem", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onNothingSelected(parent: AdapterView<*>) {
//            // Metode ini akan dipanggil ketika tidak ada item yang dipilih
//        }
//    }
//
//}