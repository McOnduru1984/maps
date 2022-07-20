package com.example.sieramap

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.sieramap.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient:  FusedLocationProviderClient
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val safaricomHQ = LatLng(-1.2583497236457262, 36.78952947979392)
        mMap.addMarker(MarkerOptions().position(safaricomHQ).title("Marker in SafaricomHQ"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(safaricomHQ,13f))

        val mater = LatLng(-1.304460113378617, 36.83463696250646)
        mMap.addMarker(MarkerOptions().position(mater).title("Marker in mater"))

        val AIU = LatLng(-1.2988289275812341, 36.691589356874644)
        mMap.addMarker(MarkerOptions().position(AIU).title("Marker in AIU"))

        val westgate = LatLng(-1.2546285482118071, 36.8031919154642)
        mMap.addMarker(MarkerOptions().position(westgate).title("Marker in westgate"))

        gps()
    }


    fun gps(){
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1)
            return

        }//end if
        mMap.isMyLocationEnabled = true    //user need to activate GPS to ON on their settings
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
                location ->

        if (location!=null){
            val currentLocation = LatLng(location.latitude, location.longitude)  //this is your current location
            mMap.addMarker(MarkerOptions().position(currentLocation).title("This is my location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12f))
        }//end if

        else {
            Toast.makeText(applicationContext, "We can't retrieve your location", Toast.LENGTH_LONG).show()
        }//end else

    }//end fused successlistener






}











}