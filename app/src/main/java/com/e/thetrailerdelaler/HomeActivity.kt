package com.e.thetrailerdelaler

import Adapter.RVHomeAdapter
import Adapter.RVNavigationAdapter
import Model.ServerResponseGetPrice
import Model.ServerResponseHitLocation
import Model.ServerResponseHome
import Model.ServerResponseLogout
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.GPSTracker
import Utils.RetrofitClient
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.TypedArray
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
//import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*


class HomeActivity : AppCompatActivity() {
    var txt_no_data: TextView? = null;
    var txt_name: TextView? = null;
    var txt_location: TextView? = null
    var rv_homelist: RecyclerView? = null;
    var rv_navigationlist: RecyclerView? = null
    var animation: Animation? = null
    var adapter: RVHomeAdapter? = null;
    var rv_adapter: RVNavigationAdapter? = null
    var toggle: ActionBarDrawerToggle? = null
    var navigationView: NavigationView? = null
    public var drawerLayout: DrawerLayout? = null
    var rl_header: RelativeLayout? = null
    var img_profile: CircleImageView? = null
    var titles: List<String>? = null
    var nav_image: TypedArray? = null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var constants: Constants? = null
    var login_id = "";
    var name = "";
    var location = "";
    var image = ""
    var gps: GPSTracker? = null
    var latitude: Double? = 0.0;
    var longitude: Double? = 0.0
    var appUtils: AppUtils? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        setNavigationView()
        BindUI()
        constants = Constants()
        appUtils = AppUtils(applicationContext)
        appUtils!!.showDialog(this)

        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)

        login_id = preference!!.getString("loginid", "")!!
        hit_getPrice()
        checkPermissions()
//ShowMessage(""+login_id)

        rv_navigationlist?.setLayoutManager(LinearLayoutManager(this))
        nav_image = this.resources.obtainTypedArray(R.array.customer_icon)
        titles = Arrays.asList(*this.resources.getStringArray(R.array.navigation_menu))
        rv_adapter =
            RVNavigationAdapter(this, nav_image!!, titles as MutableList<String>, drawerLayout)
        rv_navigationlist?.setAdapter(rv_adapter)
        //rl_bottom.setVisibility(View.VISIBLE);

        if (appUtils!!.isInternetConnected()) {
            hit_homeApi()
        } else {
            ShowMessage("Please check your Internet connection")
        }

        rl_header?.setOnClickListener {
            drawerLayout?.closeDrawers()
            intent = Intent(applicationContext, NavigationActivity::class.java)
            intent.putExtra("type", "profile")
            startActivity(intent)
        }


        Handler().postDelayed({
            gps = GPSTracker(this)
            if (gps!!.canGetLocation()) {
                latitude = gps!!.getLatitude()
                longitude = gps!!.getLongitude()
                Log.e("aaaa", "$latitude  $longitude")
                val geocoder: Geocoder
                val returnAddress: Address
                val addresses: List<Address>
                geocoder = Geocoder(this, Locale.getDefault())
                try {
                    if (latitude != 0.0 && longitude != 0.0) {
                        addresses = geocoder.getFromLocation(
                            latitude!!,
                            longitude!!,
                            2
                        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        returnAddress = addresses[0]
                        val locality = returnAddress.locality
                        val name = returnAddress.featureName
                        val subLocality = returnAddress.subLocality
                        val country = returnAddress.countryName
                        val region_code = returnAddress.countryCode
                        val zipcode = returnAddress.postalCode
                        val state = returnAddress.adminArea
                        if (locality == null && subLocality == null) {
                            txt_location?.setText("Location: " + name)
                        } else if (subLocality == null) {
                            txt_location?.setText("Location: " + name)
                            // source_name = name
                        } else {
                            txt_location?.setText("Location: " + "$name $subLocality $locality")
                        }
                        val mlongitude: String = longitude.toString()
                        val mlatitude: String = latitude.toString()
                        var location: String = state + "," + country

                        if (appUtils!!.isInternetConnected()) {
//                            hit_HitLocation(login_id,mlatitude,mlongitude)
                        } else {
//                            ShowMessage("Please check your Internet connection")
                        }
                    }
                    //  edt_country.setText(country);
                } catch (e: IOException) {
                    Log.e("error message", e.message)
                    e.printStackTrace()
                }
            } else {
                gps!!.showSettingsAlert()
            }


        }, 5000)


    }


    private fun BindUI() {
        txt_no_data = findViewById(R.id.txt_no_data)
        rv_homelist = findViewById(R.id.rv_homelist)
        rv_homelist?.setLayoutManager(LinearLayoutManager(this))
    }

    override fun onResume() {
        super.onResume()
        name = preference!!.getString("name", "")!!
        image = preference!!.getString("image", "")!!
        var last_name: String = preference!!.getString("last_name", "")!!

        txt_name?.setText(name + " " + last_name)

        if (!image.equals("", ignoreCase = true)) {
            img_profile?.let {
                Glide.with(this).load(image).placeholder(R.mipmap.default_user).into(it)
            }
        }

    }

    private fun setNavigationView() {
        navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        drawerLayout = findViewById<View>(R.id.drawer_layout_home) as DrawerLayout
        //  View header = navigationView.getHeaderView(0);
        rl_header = navigationView!!.findViewById(R.id.rl_header)
        img_profile = navigationView!!.findViewById(R.id.img_profile)
        txt_name = navigationView!!.findViewById(R.id.txt_name)
        txt_location = navigationView!!.findViewById(R.id.txt_location)
        rv_navigationlist = navigationView!!.findViewById(R.id.rv_navigationlist)
        toggle = object : ActionBarDrawerToggle(
            this@HomeActivity,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                hideKeyboard()
            }
        }
        drawerLayout!!.setDrawerListener(toggle)
        toggle?.syncState()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), 1
                )
                return
            } else {
                getCurrentlocation()
            }
        } else {
            getCurrentlocation()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            getCurrentlocation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getCurrentlocation() {
        gps = GPSTracker(this)
        if (gps!!.canGetLocation()) {
            latitude = gps!!.getLatitude()
            longitude = gps!!.getLongitude()
            Log.e("aaaa", "$latitude  $longitude")
            val geocoder: Geocoder
            val returnAddress: Address
            val addresses: List<Address>
            geocoder = Geocoder(this, Locale.getDefault())
            try {
                if (latitude != 0.0 && longitude != 0.0) {
                    addresses = geocoder.getFromLocation(
                        latitude!!,
                        longitude!!,
                        2
                    ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    returnAddress = addresses[0]
                    val locality = returnAddress.locality
                    val name = returnAddress.featureName
                    val subLocality = returnAddress.subLocality
                    val country = returnAddress.countryName
                    val region_code = returnAddress.countryCode
                    val zipcode = returnAddress.postalCode
                    val state = returnAddress.adminArea
                    if (locality == null && subLocality == null) {
                        txt_location?.setText("Location: " + name)
                    } else if (subLocality == null) {
                        txt_location?.setText("Location: " + name)
                        // source_name = name
                    } else {
                        txt_location?.setText("Location: " + "$name $subLocality $locality")
                    }
                    val mlongitude: String = longitude.toString()
                    val mlatitude: String = latitude.toString()
                    var location: String = state + "," + country

                    if (appUtils!!.isInternetConnected()) {
                        hit_upadtelocApi(mlatitude, mlongitude, location)
                    } else {
                        ShowMessage("Please check your Internet connection")
                    }
                }
                //  edt_country.setText(country);
            } catch (e: IOException) {
                Log.e("error message", e.message)
                e.printStackTrace()
            }
        } else {
            gps!!.showSettingsAlert()
        }
    }

    private fun hit_upadtelocApi(mlatitude: String, mlongitude: String, location: String) {
//        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.dealer_location(
            login_id,
            mlatitude,
            mlongitude,
            location,
            constants!!.accesstoken
        )
        call?.enqueue(object : Callback<ServerResponseLogout?> {
            override fun onResponse(
                call: Call<ServerResponseLogout?>,
                response: Response<ServerResponseLogout?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    //ShowMessage(data?.getMessage().toString())

                } else {
                    appUtils!!.dialog!!.dismiss()

                    ShowMessage(data?.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseLogout?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })

    }

    private fun hit_homeApi() {
//        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.location_list(login_id, constants!!.accesstoken)
        call?.enqueue(object : Callback<ServerResponseHome?> {

            override fun onResponse(
                call: Call<ServerResponseHome?>,
                response: Response<ServerResponseHome?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    if (data?.getData()?.size == 0) {
                        txt_no_data?.setVisibility(View.VISIBLE)
                        rv_homelist?.setVisibility(View.GONE)
                    } else {
                        txt_no_data?.setVisibility(View.GONE)
                        rv_homelist?.setVisibility(View.VISIBLE)
                        animation = AnimationUtils.loadAnimation(
                            applicationContext,
                            R.anim.item_animation_fall_down
                        )
                        rv_homelist?.setAnimation(animation)
                        adapter = RVHomeAdapter(this@HomeActivity, data?.getData())
                        rv_homelist?.setAdapter(adapter)
                    }

                } else {
                    appUtils!!.dialog!!.dismiss()

                    ShowMessage(data?.getMessage().toString())
                    txt_no_data?.setVisibility(View.VISIBLE)
                    rv_homelist?.setVisibility(View.GONE)
                }
            }

            override fun onFailure(call: Call<ServerResponseHome?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
                txt_no_data?.setVisibility(View.VISIBLE)
                rv_homelist?.setVisibility(View.GONE)
            }

        })
    }

    fun hideKeyboard() { // Check if no view has focus:
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(applicationContext, sms, Toast.LENGTH_LONG).show()
    }


    private fun hit_getPrice() {
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.get_price(constants!!.accesstoken)
        call?.enqueue(object : Callback<ServerResponseGetPrice?> {

            override fun onResponse(
                call: Call<ServerResponseGetPrice?>,
                response: Response<ServerResponseGetPrice?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {

                    var price: String = data?.getData()?.price.toString()
//                    ShowMessage(price)
                    editor = preference!!.edit()
                    editor?.putString("price", price)?.commit()

                } else {
                    appUtils?.dialog?.dismiss()
                    ShowMessage(data?.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseGetPrice?>, t: Throwable) {
                ShowMessage("Try again")
//                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()

            }

        })
    }
//
//    private fun hit_HitLocation(
//        loginId: String,
//        mlatitude: String,
//        mlongitude: String
//    ) {
////        appUtils!!.dialog!!.show()
//        var retrofitClient: RetrofitClient? = RetrofitClient()
//        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
//        val call = service.hit_Location(constants!!.accesstoken,loginId,mlatitude,mlongitude)
//        call?.enqueue(object : Callback<ServerResponseHitLocation?> {
//
//            override fun onResponse(
//                call: Call<ServerResponseHitLocation?>,
//                response: Response<ServerResponseHitLocation?>
//            ) {
//                val data = response.body()
////                appUtils!!.dialog!!.dismiss()
//                if (data?.getCode().equals("200", true)) {
//
//
//                } else {
////                    appUtils?.dialog?.dismiss()
//                    ShowMessage(data?.getMessage().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<ServerResponseHitLocation?>, t: Throwable) {
//                ShowMessage("Try again")
////                Log.e("error", t.toString())
////                appUtils!!.dialog!!.dismiss()
//
//            }
//
//        })
//    }


}
