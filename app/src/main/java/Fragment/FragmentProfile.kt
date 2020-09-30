package Fragment

import Model.ServerResponseGetprofile
import Model.ServerResponseUpadeImage
import Model.ServerResponseUpdateProfile
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
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.e.thetrailerdelaler.ImagePickerActivity
import com.e.thetrailerdelaler.ImagePickerActivity.PickerOptionListener
import com.e.thetrailerdelaler.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
//import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class FragmentProfile: Fragment(), View.OnClickListener {
    var edt_name: EditText? = null; var edt_email:EditText? = null; var edt_phone:EditText? = null;var edt_lastname:EditText?=null
    var btn_submit: Button? = null
    var txt_name: TextView? = null;var txt_review: TextView? = null;var txt_phone: TextView? = null;
    var img_profile: CircleImageView? = null
    var name = ""; var email = "";var last_name=""
    var rl_edit_profile: RelativeLayout? = null
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var login_id = ""; var img_url:String? = ""; var type:String? = ""
    var appUtils: AppUtils? = null
    var constants: Constants?=null

    var finalFile: File? = null; var lincenceFile:java.io.File? = null
    var bitmap: Bitmap? = null
    var image: MultipartBody.Part? = null; var lincence:okhttp3.MultipartBody.Part? = null
    val PIC_CROP = 100
    val REQUEST_IMAGE = 100


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        BindUI(view);
        constants= Constants()
        appUtils = activity?.let { AppUtils(it) }
        preference = activity?.getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        login_id = preference!!.getString("loginid", "")!!

        btn_submit?.setOnClickListener(this)
        rl_edit_profile?.setOnClickListener(this)

        if (appUtils!!.isInternetConnected()) {
            HitGet_pofileApi()
        } else {
            ShowMessage("Please check your Internet connection")
        }

        return view
    }

    private fun BindUI(view: View) {
        txt_name=view.findViewById(R.id.txt_name)
        txt_review=view.findViewById(R.id.txt_review);
        edt_name=view.findViewById(R.id.edt_name);
        edt_email=view.findViewById(R.id.edt_email);
        edt_lastname=view.findViewById(R.id.edt_lastname)
        txt_phone=view.findViewById(R.id.txt_phone);
        btn_submit=view.findViewById(R.id.btn_submit);
        img_profile=view.findViewById(R.id.img_profile);
        rl_edit_profile=view.findViewById(R.id.rl_edit_profile);

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.rl_edit_profile->{
                onProfileImageClick()
            }
            R.id.btn_submit->{
             if (Validation()){
                 if (appUtils!!.isInternetConnected()) {
                     Hitedit_pofileApi()
                 } else {
                     ShowMessage("Please check your Internet connection")
                 }
             }
            }
        }
    }

    private fun Validation(): Boolean {
        name = edt_name!!.text.toString().trim { it <= ' ' }
        email = edt_email!!.text.toString().trim { it <= ' ' }
        last_name= edt_lastname!!.text.toString().trim { it <= ' ' }

        if(name.isEmpty()){
            edt_name?.error="Please enter name"
            return false
        }else if(last_name.isEmpty()){
            edt_lastname?.error="Please enter last name"
            return false
        } else if (email.isEmpty()){
            edt_email?.error="Please enter email address"
            return false
        }else if(!isValidEmail(email)){
            edt_email?.error="Please enter valid email address"
            return false
        }

        return true
    }

    //////////////////////hit api field
     fun Hitedit_pofileApi(): Unit {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)

        val call = service.update_profle(login_id,name,last_name,email,constants!!.accesstoken)
        call!!.enqueue(object : Callback<ServerResponseUpdateProfile?> {

            override fun onResponse(call: Call<ServerResponseUpdateProfile?>, response: Response<ServerResponseUpdateProfile?>) {
                val data: ServerResponseUpdateProfile? = response.body()
                appUtils!!.dialog!!.dismiss()

                if (data?.getCode().equals("200",true)) {
                    editor = preference!!.edit()
                    editor!!.putString("name",data!!.getData()!!.name);
                    editor!!.putString("last_name",data!!.getData()!!.lastname);
                    editor!!.putString("email",data!!.getData()!!.email);
                    editor!!.putString("contact", data!!.getData()!!.contact)
                    editor!!.putString("loginid", data!!.getData()!!.id)
                   // editor!!.putString("image", data!!.getData()!!.driverImg)
                    editor!!.putString("device_id", data!!.getData()!!.device_token)
                    editor!!.putString("device_type", data!!.getData()!!.devicetype)
                    editor!!.commit()
                    activity!!.finish()

                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseUpdateProfile?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }
        })
    }

    private fun Hitedit_imageApi() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)

        if (null == finalFile) {

        } else { //  Log.e("file",img_url);
            val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), finalFile!!)
            image =MultipartBody.Part.createFormData("user_img", finalFile!!.name, requestFile)
        }
        val C_id = RequestBody.create("text/plain".toMediaTypeOrNull(), login_id)
        val token= RequestBody.create("text/plain".toMediaTypeOrNull(), constants!!.accesstoken)

        val call = service.update_profile_image(C_id,token,image)
        call?.enqueue(object :Callback<ServerResponseUpadeImage?>{

            override fun onResponse(call: Call<ServerResponseUpadeImage?>, response: Response<ServerResponseUpadeImage?>) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200",true)) {
                    editor = preference!!.edit()
                    editor!!.putString("image", data!!.getData()!!.image)
                    editor!!.commit()
                   // activity!!.finish()

                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseUpadeImage?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })


    }

    private fun HitGet_pofileApi() {
        appUtils!!.showDialog(activity)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient?= RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.Get_profile(login_id,constants!!.accesstoken)
        call?.enqueue(object:Callback<ServerResponseGetprofile?>{

            override fun onResponse(call: Call<ServerResponseGetprofile?>, response: Response<ServerResponseGetprofile?>) {
                val data: ServerResponseGetprofile? = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200",true)) {
                     txt_name?.setText(data?.getData()?.name+" "+data?.getData()?.lastname)
                     txt_review?.setText(data?.getData()?.totel_review+" review")
                     txt_phone?.setText(data?.getData()?.contact)
                    edt_name?.setText(data?.getData()?.name)
                     edt_email?.setText(data?.getData()?.email)
                    edt_lastname?.setText(data?.getData()?.lastname)
                    var image:String= data?.getData()?.image.toString()
                    Log.e("image",image)

                    activity?.let { img_profile?.let { it1 -> Glide.with(it).load(image).placeholder(R.mipmap.default_user).into(it1) } }
                     //Picasso.with(activity).load("https://phpstack-102119-1169738.cloudwaysapps.com../img/location/ins34859.jpg").into(img_profile)



                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseGetprofile?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })

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

        ImagePickerActivity.showImagePickerOptions(activity,object :ImagePickerActivity.PickerOptionListener{

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
        startActivityForResult(intent,REQUEST_IMAGE)
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
                    img_profile!!.setImageURI(uri)
                    // img_back.setImageURI(uri);
                    Hitedit_imageApi()
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
    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
    private fun ShowMessage(sms: String) {
        Toast.makeText(activity, sms, Toast.LENGTH_LONG).show()
    }
}