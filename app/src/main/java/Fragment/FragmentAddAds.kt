package Fragment

import Model.ServerResponseAddAds
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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.e.thetrailerdelaler.HomeActivity
import com.e.thetrailerdelaler.ImagePickerActivity
import com.e.thetrailerdelaler.PaymentActivity
import com.e.thetrailerdelaler.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


class FragmentAddAds : Fragment(), View.OnClickListener {

    var edt_title: EditText? = null
    var edt_description: EditText? = null
    var edt_price: EditText? = null
    var img_ads: ImageView? = null
    var btn_submitads: Button? = null
    var finalFile: File? = null;
    val REQUEST_IMAGE = 100
    var img_url: String? = null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var preference2: SharedPreferences? = null
    var editor2: SharedPreferences.Editor? = null
    var constants: Constants? = null
    var appUtils: AppUtils? = null
    var pricePerAdd: String? = null
    var login_id: String? = null
    var txt_view: TextView? = null
    var image: MultipartBody.Part? = null
    var ll: ImageView? = null
    var ll1: LinearLayout? = null
    var lincence: okhttp3.MultipartBody.Part? = null
    var a: Int? = null
    var title = ""
    var descreption = ""
    var price = ""
    var c: Int? = null
    var transId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_add_ads, container, false)

        constants = Constants()
        appUtils = this!!.context?.let { AppUtils(it) }


        preference = context?.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        editor = preference!!.edit()
        preference2 = context?.getSharedPreferences("VarunPref", Context.MODE_PRIVATE)
        editor2 = preference2!!.edit()
        editor2!!.clear().commit()

        pricePerAdd = preference!!.getString("price", "")
        login_id = preference!!.getString("loginid", "")


        initView(view)

//        editor!!.putString("payed", "0").commit()
        a = pricePerAdd!!.toInt()
//        ShowMessage("" + payed)

//        editor!!.putString("ssds","yo").commit()

        btn_submitads?.setOnClickListener(this)
        ll1?.setOnClickListener(this)

        return view
    }

    private fun initView(view: View) {
        edt_title = view.findViewById(R.id.edt_AddTitle)
        edt_description = view.findViewById<EditText>(R.id.edt_AddDescription)
        edt_price = view.findViewById<EditText>(R.id.edt_AddPrice)
        img_ads = view.findViewById<ImageView>(R.id.img_ads)
        btn_submitads = view.findViewById<Button>(R.id.btn_SubmitAds)
        txt_view = view.findViewById(R.id.txt_view)
        txt_view?.setText(pricePerAdd + " $ will be charged for per ads view")
        ll = view.findViewById(R.id.ll)
        ll1 = view.findViewById(R.id.ll1)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_SubmitAds -> {
                title = edt_title?.text.toString()
                descreption = edt_description?.text.toString()
                price = edt_price?.text.toString()
//                Toast.makeText(context,""+preference!!.getString("ssds",""),LENGTH_SHORT).show()

//ShowMessage(""+preference!!.getString("ssds",""))

                if (title.isEmpty() && descreption.isEmpty() && img_url == null && price.isEmpty()) {
                    ShowMessage("Enter the credential ")
                } else if (title.isEmpty()) {
                    ShowMessage("Enter title")
                } else if (descreption.isEmpty()) {
                    ShowMessage("Enter description")
                } else if (img_url == null) {
                    ShowMessage("Enter image")
                } else if (price!!.isEmpty()) {
                    ShowMessage("Enter price")
                } else {
                    var b: Int? = price.toInt()
                    c = a?.let { b?.div(it) }
//
////                    ShowMessage("" + c)
//
                    val intent = Intent(context, PaymentActivity::class.java)
//                    intent.putExtra("type", "ads")
                    intent.putExtra("price", price)
                    intent.putExtra("title", title)
                    intent.putExtra("des", descreption)
                    intent.putExtra("c", c)
                    startActivity(intent)

//                    var click = price.toInt() / pricePerAdd!!.toInt()
//                    ShowMessage(click.toString())
//                    hitAddAds(title,descreption,img_url,price,login_id)
//
//
//
//                    hitAddAds(title, descreption, price, login_id.toString(),c)
                }

            }
            R.id.ll1 -> {

                onProfileImageClick()
            }


        }


    }


    fun onProfileImageClick() {
        Dexter.withActivity(activity)
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

        ImagePickerActivity.showImagePickerOptions(activity,
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
        val intent = Intent(activity, ImagePickerActivity::class.java)
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
        val intent = Intent(activity, ImagePickerActivity::class.java)
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
        val builder =
            AlertDialog.Builder(activity)
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
            Uri.fromParts("package", activity!!.packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data!!.getParcelableExtra<Uri>("path")
                try { // You can update this bitmap to your server
                    val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)
                    val tempUri = getImageUri(activity, bitmap)
                    finalFile = File(getRealPathFromURI(tempUri))
                    img_url = tempUri.toString()
                    // loading profile image from local cache
                    img_ads!!.setImageURI(uri)
                    ll?.visibility = View.GONE
                    img_ads?.visibility = View.VISIBLE
                    // img_back.setImageURI(uri);
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


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

    fun getRealPathFromURI(uri: Uri?): String? {
        val cursor =
            activity!!.contentResolver.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)
        // video_path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
        return cursor.getString(idx)
    }


    internal fun ShowMessage(s: String) {
        Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show()
    }

    private fun hitAddAds(
        title: String,
        descreption: String,
        price: String,
        userId: String?,
        c: Int?,
        transId: String?
    ) {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)

        if (null == finalFile) {

        } else { //  Log.e("file",img_url);
            val requestFile =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), finalFile!!)
            image = MultipartBody.Part.createFormData("ads_img", finalFile!!.name, requestFile)
        }
        val C_title = RequestBody.create("text/plain".toMediaTypeOrNull(), title)
        val C_description = RequestBody.create("text/plain".toMediaTypeOrNull(), descreption)
        val C_price = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val C_click = RequestBody.create("text/plain".toMediaTypeOrNull(), c.toString())
        val C_loginId = RequestBody.create("text/plain".toMediaTypeOrNull(), userId!!)
        val C_transaction_id = RequestBody.create("text/plain".toMediaTypeOrNull(),
            transId.toString()
        )
        val token = RequestBody.create("text/plain".toMediaTypeOrNull(), constants!!.accesstoken)

        val call = service.add_ads(
            token, C_title, C_loginId,
            C_description, C_price, image,
            C_click, C_transaction_id
        )
        call?.enqueue(object : Callback<ServerResponseAddAds?> {

            override fun onResponse(
                call: Call<ServerResponseAddAds?>,
                response: Response<ServerResponseAddAds?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
//                    ShowMessage(data?.getMessage().toString())
//                    edt_title?.setText("")
//                    edt_description?.setText("")
//                    edt_price?.setText("")
//                    img_ads?.setImageBitmap(null);
                    ShowMessage("Ad is sent for review")
                    startActivity(Intent(context, HomeActivity::class.java))

                    // activity!!.finish()

                } else {
                    appUtils!!.dialog!!.dismiss()
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseAddAds?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })

    }

    override fun onResume() {
        super.onResume()

        transId = preference!!.getString("transId", "")
//        payed = preference!!.getString("payed", "")!!
//        ShowMessage(transId.toString())
        var a = preference2!!.getString("a", "")

        if (!a.equals("100")) {

//            ShowMessage("1"+a)
        } else {
//            ShowMessage("2"+a)

            hitAddAds(title, descreption, price, login_id, c,transId)
        }
    }
}


