package xui

import android.location.Location
import android.location.LocationListener
import android.location.LocationProvider
import android.os.Bundle

  interface MyLocationListener : LocationListener   {


    override   fun onStatusChanged(provider: String, status: Int, extras: Bundle){}


    override  fun onProviderEnabled(provider: String){}


    override  fun onProviderDisabled(provider: String){}
}