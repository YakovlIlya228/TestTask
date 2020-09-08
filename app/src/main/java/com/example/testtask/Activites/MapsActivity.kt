package com.example.testtask.Activites

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.testtask.R
import com.example.testtask.ViewModel.GeneralViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import org.koin.android.viewmodel.ext.android.viewModel


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val viewModel: GeneralViewModel by viewModel()
//    lateinit var locationService: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 200
            )

        }
        else
            getLastLocation()
    }
    @SuppressLint("MissingPermission")
    fun getLastLocation(){
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                p0?.let {
                    lat.text = String.format("%.3f", it.lastLocation.latitude)
                    lng.text = String.format("%.3f", it.lastLocation.longitude)
                    mMap.addMarker(
                        MarkerOptions().position(
                            LatLng(
                                it.lastLocation.latitude,
                                it.lastLocation.longitude
                            )
                        )
                            .title("My location")
                    )
                    mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .target(LatLng(it.lastLocation.latitude, it.lastLocation.longitude))
                                .zoom(10F)
                                .build()

                        )
                    )
                }
            }
        }
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest, locationCallback, null)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            200-> {getLastLocation()}
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        viewModel.getUsers().observe(this, Observer {
            for (user in it) {
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            user.address.geo.lat,
                            user.address.geo.lng
                        )
                    )
                        .title(user.name + "\n City: " + user.address.city + "\n Street" + user.address.street)
                )
            }
        })
        getLocationButton.setOnClickListener {
            LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
                it?.let {
                    lat.text = String.format("%.3f", it.latitude)
                    lng.text = String.format("%.3f", it.longitude)
                    mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .target(LatLng(it.latitude, it.longitude))
                                .zoom(10F)
                                .build()

                        )
                    )
                }
            }
        }
        zoomInBtn.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomBy(1.5f))
        }
        zoomOutBtn.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomBy(-1.5f))
        }
    }
}