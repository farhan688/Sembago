package com.example.sembago.ui.activity

import AstarAdapter
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sembago.AstarViewModel
import com.example.sembago.R

class AstarActivity : AppCompatActivity() {

    private lateinit var viewModel: AstarViewModel
    private lateinit var adapter: AstarAdapter
    private lateinit var rvAstar: RecyclerView

    private lateinit var etLat: EditText
    private lateinit var etLong: EditText
    private lateinit var btnPostAstar: Button
    private lateinit var btnGetLocation: Button

    private lateinit var tvHasilAstar: TextView
    private lateinit var tvError: TextView

    private val REQUEST_LOCATION_PERMISSION = 1001
    private val MIN_TIME_BW_UPDATES: Long = 1000
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES = 1f

    private lateinit var spKategori: Spinner
    private lateinit var categorySpinnerAdapter: ArrayAdapter<String>

    private val categories = arrayOf("gula", "minyak", "beras", "kopi", "bayem", "kangkung")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astar)

        viewModel = ViewModelProvider(this).get(AstarViewModel::class.java)

        etLat = findViewById(R.id.etLat)
        etLong = findViewById(R.id.etLong)
        btnPostAstar = findViewById(R.id.btnPostAstar)
        btnGetLocation = findViewById(R.id.btnGetLocation)
        tvHasilAstar = findViewById(R.id.tvHasilAstar)
        tvError = findViewById(R.id.tvError)
        rvAstar = findViewById(R.id.rvAstar)

        adapter = AstarAdapter(ArrayList())
        rvAstar.adapter = adapter
        rvAstar.layoutManager = LinearLayoutManager(this)

        spKategori = findViewById(R.id.spKategori)
        categorySpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spKategori.adapter = categorySpinnerAdapter

        spKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        btnPostAstar.setOnClickListener {
            val userLat = etLat.text.toString().toDoubleOrNull() ?: 0.0
            val userLong = etLong.text.toString().toDoubleOrNull() ?: 0.0
            val category =  spKategori.selectedItem.toString()

            val sharedPreferences = getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", "")

            if (!token.isNullOrEmpty()) {
                viewModel.postAstarRequest(userLat, userLong, category, token)
            } else {

            }
        }

        btnGetLocation.setOnClickListener {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_PERMISSION
                )
            } else {
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    AlertDialog.Builder(this)
                        .setMessage("GPS is required for this feature. Do you want to enable it?")
                        .setPositiveButton("Yes") { _, _ ->
                            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                } else {
                    val locationListener = object : LocationListener {
                        override fun onLocationChanged(location: Location) {
                            val userLat = location.latitude
                            val userLong = location.longitude

                            etLat.setText(userLat.toString())
                            etLong.setText(userLong.toString())

                            locationManager.removeUpdates(this)
                        }

                        override fun onProviderDisabled(provider: String) {
                            // Jika provider dinonaktifkan
                        }

                        override fun onProviderEnabled(provider: String) {
                            // Jika provider diaktifkan kembali
                        }

                        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                            // Jika status provider berubah
                        }
                    }

                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(),
                        locationListener
                    )
                }
            }
        }


        viewModel.productList.observe(this, Observer { productList ->
            adapter.updateProductList(productList)
        })

        viewModel.errorLiveData.observe(this, Observer { error ->
            tvError.text = error
        })
    }
}
