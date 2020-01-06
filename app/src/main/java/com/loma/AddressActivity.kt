package com.loma

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.safframework.log.log
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import xui.MyLocationListener
import xui.toast
import java.util.*


class AddressActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager


   val locationListener=object :  MyLocationListener {
        override  fun onLocationChanged(location: Location?) {
            showLocal(location!!)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        RxPermissions(this).request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe({ granted ->

            if (granted) {
                getLocation()

            } else {
                toast("授权${granted}")
            }
        })


    }

    @SuppressLint("MissingPermission") private fun getLocation() {
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //2000代表每2000毫秒更新一次，5代表每5秒更新一次
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,   locationListener, null)
            var location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location != null) {
                showLocal(location)
            }
        }



    }


    override fun onDestroy() {
        super.onDestroy()
        locationListener?.let {
            locationManager?.removeUpdates(it)
        }



    }



    private fun showLocal(location: Location) {
        doAsync {
            val result: List<Address>? = try {
                Geocoder(applicationContext, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1)
            } catch (e: Exception) {
              null
            }

            runOnUiThread {
                result?.get(0)?.apply {
                    addressTextView.setText(locality + subLocality)

                }

            }

        }

    }
}