package com.e.thetrailerdelaler

import Model.ServerResponseLogout
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_navigation.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.util.*

class AddLocationActivity : AppCompatActivity(), View.OnClickListener {
    var preference: SharedPreferences? = null
    var constants: Constants?=null
    var appUtils: AppUtils? = null
    var edt_title:EditText?=null
    var txt_address:TextView?=null;var txt_file:TextView?=null
    var btn_submit:Button?=null
    var latitude:Double?= 0.0; var longitude:Double?= 0.0
    var AUTOCOMPLETE_REQUEST_CODE = 1
    var login_id = "";var address="";var file_name="";var title=""
    var mStringLatitude:String?=null;var mStringLongitude:String?=null

    var finalFile: File? = null; var lincenceFile:java.io.File? = null
    var bitmap: Bitmap? = null
    var image: MultipartBody.Part? = null; var lincence:okhttp3.MultipartBody.Part? = null
    val PIC_CROP = 100
    val REQUEST_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        BindUI()

        constants= Constants()
        appUtils = AppUtils(applicationContext)
        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!

        btn_submit?.setOnClickListener(this)
        txt_address?.setOnClickListener(this)
        txt_file?.setOnClickListener(this)


    }

    private fun BindUI() {
        edt_title=findViewById(R.id.edt_title)
        txt_address=findViewById(R.id.txt_address)
        txt_file=findViewById(R.id.txt_file)
        btn_submit=findViewById(R.id.btn_submit)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txt_address->{
                Places.initialize(applicationContext, resources.getString(R.string.google_maps_key))
                val field = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
                val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field) // .setTypeFilter(TypeFilter.ADDRESS)
                    .build(applicationContext)
                startActivityForResult(intent,AUTOCOMPLETE_REQUEST_CODE )
            }
            R.id.txt_file->{
                onProfileImageClick()
            }
            R.id.btn_submit->{
                if (Validaion()){
                    if (appUtils!!.isInternetConnected()) {
                        Hitadd_locationApi()
                    } else {
                        ShowMessage("Please check your Internet connection")
                    }
                }

            }
        }

    }

    private fun Hitadd_locationApi() {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        if (null == finalFile) {

        } else { //  Log.e("file",img_url);
            val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), finalFile!!)
            image =MultipartBody.Part.createFormData("loc_img", finalFile!!.name, requestFile)
        }
        val C_id = RequestBody.create("text/plain".toMediaTypeOrNull(), login_id)
        val C_title = RequestBody.create("text/plain".toMediaTypeOrNull(), title)
        val src_lat = RequestBody.create("text/plain".toMediaTypeOrNull(),mStringLatitude.toString())
        val src_lang = RequestBody.create("text/plain".toMediaTypeOrNull(), mStringLatitude.toString())
        val location = RequestBody.create("text/plain".toMediaTypeOrNull(), address)
        val token= RequestBody.create("text/plain".toMediaTypeOrNull(), constants!!.accesstoken)
        val call = service.mylocation(C_id,C_title,src_lat,src_lang,location,token,image)
        call?.enqueue(object :Callback<ServerResponseLogout?>{

            override fun onResponse(call: Call<ServerResponseLogout?>, response: Response<ServerResponseLogout?>) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200",true)) {
                    ShowMessage("Your location has been added.")
                    finish()
                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }
            override fun onFailure(call: Call<ServerResponseLogout?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })

    }

    private fun Validaion(): Boolean {
        title=edt_title?.text?.trim().toString()
        if (title.isEmpty()){
            edt_title?.setError("Please enter location title")
            return false
        }else if (address.isEmpty()){
            ShowMessage("Please enter your address")
            return false
        }else if(file_name.isEmpty()){
            ShowMessage("Please select your file")
            return false

        }
     return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                val latLng = place.latLng
                longitude = latLng!!.longitude
                latitude = latLng.latitude
                address = place.name!!
                mStringLatitude = latLng.latitude.toString()
                mStringLongitude = latLng.longitude.toString()

                Log.e("home=====", "" + place.name + ", " + mStringLatitude + "," + mStringLongitude
                )
                txt_address?.setText(place.name)
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) { // TODO: Handle the error.
                val status =
                    Autocomplete.getStatusFromIntent(data!!)
                Log.e("home=====", status.statusMessage)
            } else if (resultCode == Activity.RESULT_CANCELED) { // The user canceled the operation.
            }
        }
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.getParcelableExtra<Uri>("path")
                try { // You can update this bitmap to your server
                    val bitmap = MediaStore.Images.Media.getBitmap(this!!.contentResolver, uri)
                    val tempUri = getImageUri(this, bitmap)
                    finalFile = File(getRealPathFromURI(tempUri))
                    txt_file?.setText(finalFile!!.name)
                    file_name=finalFile!!.name

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }


    fun onProfileImageClick() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        showImagePickerOptions()
                    }
                    if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private fun showImagePickerOptions() {

        ImagePickerActivity.showImagePickerOptions(this,object :ImagePickerActivity.PickerOptionListener{

            override fun onTakeCameraSelected() {
                launchCameraIntent()
            }

            override fun onChooseGallerySelected() {
                launchGalleryIntent()
            }
        })
    }

    private fun launchCameraIntent() {
        val intent = Intent(this, ImagePickerActivity::class.java)
        intent.putExtra(
            ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
            ImagePickerActivity.REQUEST_IMAGE_CAPTURE
        )
        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000)
        startActivityForResult(intent,REQUEST_IMAGE)
    }

    private fun launchGalleryIntent() {
        val intent = Intent(this, ImagePickerActivity::class.java)
        intent.putExtra(
            ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
            ImagePickerActivity.REQUEST_GALLERY_IMAGE
        )
        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
        startActivityForResult(intent, REQUEST_IMAGE)
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_permission_title))
        builder.setMessage(getString(R.string.dialog_permission_message))
        builder.setPositiveButton(
            getString(R.string.go_to_settings)
        ) { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(
            getString(android.R.string.cancel)
        ) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri =
            Uri.fromParts("package", this!!.packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }


    fun getRealPathFromURI(uri: Uri?): String? {
        val cursor = this!!.contentResolver.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)
        // video_path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
        return cursor.getString(idx)
    }
    fun getImageUri(inContext: Context?, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val pathone = MediaStore.Images.Media.insertImage(
            inContext!!.contentResolver,
            inImage,
            "Title",
            null
        )
        // compressImage(pathone);
        return Uri.parse(pathone)
    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(applicationContext, sms, Toast.LENGTH_LONG).show()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            // overridePendingTransition(R.anim.slide_from_top,R.anim.slide_in_top);
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
