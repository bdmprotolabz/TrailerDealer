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
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

class AddProductActivity : AppCompatActivity(), View.OnClickListener {
    var img_product: ImageView? = null
    var btn_upload_image: Button? = null;
    var btn_upload: Button? = null
    var edt_product_name: EditText? = null;
    var edt_business: EditText? = null
    var preference: SharedPreferences? = null
    var constants: Constants? = null
    var appUtils: AppUtils? = null
    var edtPrice: EditText? = null

    var finalFile: File? = null;
    var lincenceFile: java.io.File? = null
    var bitmap: Bitmap? = null
    var image: MultipartBody.Part? = null;
    var lincence: okhttp3.MultipartBody.Part? = null
    val PIC_CROP = 100
    val REQUEST_IMAGE = 100
    var login_id = "";
    var product_name = "";
    var file_name = "";
    var our_business = ""
    var price = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        BindUI()
        constants = Constants()
        appUtils = AppUtils(applicationContext)
        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!

        btn_upload_image?.setOnClickListener(this)
        btn_upload?.setOnClickListener(this)

    }

    private fun BindUI() {
        img_product = findViewById(R.id.img_product)
        btn_upload_image = findViewById(R.id.btn_upload_image)
        btn_upload = findViewById(R.id.btn_upload)
        edt_product_name = findViewById(R.id.edt_product_name)
        edt_business = findViewById(R.id.edt_business)
        edtPrice = findViewById(R.id.edt_price)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_upload_image -> {
                onProfileImageClick()
            }
            R.id.btn_upload -> {
                if (Validation()) {
                    if (appUtils!!.isInternetConnected()) {
                        Hitadd_productApi()
                    } else {
                        ShowMessage("Please check your Internet connection")
                    }
                }
            }
        }
    }

    private fun Validation(): Boolean {
        product_name = edt_product_name?.text?.trim().toString()
        our_business = edt_business?.text?.trim().toString()
        price = edtPrice?.text?.trim().toString()


        if (product_name.isEmpty()) {
            edt_product_name?.setError("Please enter product name")
            return false
        } else if (file_name.isEmpty()) {
            ShowMessage("Please select your file")
            return false

        } else if (price.isEmpty()) {
            ShowMessage("Please enter price")
            return false

        }
        return true
    }

    private fun Hitadd_productApi() {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        if (null == finalFile) {

        } else { //  Log.e("file",img_url);
            val requestFile =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), finalFile!!)
            image = MultipartBody.Part.createFormData("product_img", finalFile!!.name, requestFile)
        }
        val dealerid = RequestBody.create("text/plain".toMediaTypeOrNull(), login_id)
        val pname = RequestBody.create("text/plain".toMediaTypeOrNull(), product_name)
        val url = RequestBody.create("text/plain".toMediaTypeOrNull(), our_business)
        val pri = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val token = RequestBody.create("text/plain".toMediaTypeOrNull(), constants!!.accesstoken)
        val call = service.add_product(dealerid, pname, url, pri, token, image)
        call?.enqueue(object : Callback<ServerResponseLogout?> {

            override fun onResponse(
                call: Call<ServerResponseLogout?>,
                response: Response<ServerResponseLogout?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    ShowMessage("Your product has been added")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.getParcelableExtra<Uri>("path")
                try { // You can update this bitmap to your server
                    val bitmap = MediaStore.Images.Media.getBitmap(this!!.contentResolver, uri)
                    val tempUri = getImageUri(this, bitmap)
                    finalFile = File(getRealPathFromURI(tempUri))
                    //txt_file?.setText(finalFile!!.name)
                    file_name = finalFile!!.name
                    img_product?.setImageURI(uri)

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
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

        ImagePickerActivity.showImagePickerOptions(this,
            object : ImagePickerActivity.PickerOptionListener {

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
        startActivityForResult(intent, REQUEST_IMAGE)
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
