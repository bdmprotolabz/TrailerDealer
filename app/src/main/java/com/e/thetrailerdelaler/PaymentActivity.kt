package com.e.thetrailerdelaler

//import com.sun.deploy.ui.CacheUpdateProgressDialog.dismiss
//import com.sun.deploy.ui.CacheUpdateProgressDialog.dismiss
import Fragment.FragmentAddAds
import Model.ServerResponseAddAds
import Model.ServerResponseGetprofile
import Model.ServerResponsePaymentApi
import Service.Interface
import Utils.AppUtils
import Utils.Constants
import Utils.RetrofitClient
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment.*
import net.authorize.acceptsdk.AcceptSDKApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File


class PaymentActivity : AppCompatActivity(), View.OnClickListener {


    private val MIN_CARD_NUMBER_LENGTH = 13
    private val MIN_YEAR_LENGTH = 2
    private val MIN_CVV_LENGTH = 3
    private val YEAR_PREFIX = "20"
    var preference: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var preference2: SharedPreferences? = null
    var editor2: SharedPreferences.Editor? = null
    var image: MultipartBody.Part? = null

    private var checkoutButton: Button? = null
    private var cardNumberView: EditText? = null
    private var monthView: EditText? = null
    private var yearView: EditText? = null
    private var cvvView: EditText? = null


    private var cardNumber: String? = null
    private var month: String? = null
    private var year: String? = null
    private var cvv: String? = null

    private var apiClient: AcceptSDKApiClient? = null
    var constants: Constants? = null
    var appUtils: AppUtils? = null
    var login_id = "";
    var id = " ";
    var price = ""
    var title = ""
    var des = ""
    var img_url = ""
    var c: Int? = null
    var type: String? = null
    var finalFile: File? = null;
    var fragmentAddAds = FragmentAddAds()
    var contact = ""
    var msg="Thank you for making payment on Ads subscription."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        BindUI()
        constants = Constants()
        appUtils = AppUtils(applicationContext)

//        fragmentAddAds= this
        preference = getSharedPreferences(constants!!.Shared_Pref, Context.MODE_PRIVATE)
        editor = preference!!.edit()
        preference2 = getSharedPreferences("VarunPref", Context.MODE_PRIVATE)
        editor2 = preference2!!.edit()

        login_id = preference!!.getString("loginid", "")!!
        price = intent.getStringExtra("price")
//        title = intent.getStringExtra("title")
//        des = intent.getStringExtra("des")
//        img_url = intent.getStringExtra("img_url")
//        c = intent.getIntExtra("c",100)
//        type = intent.getStringExtra("type")
//        type = intent.getStringExtra("type")
        HitGet_pofileApi()
    }

    private fun BindUI() {
        cardNumberView = findViewById(R.id.card_number_view)
        // setUpCreditCardEditText()
        monthView = findViewById(R.id.date_month_view)
        yearView = findViewById(R.id.date_year_view)
        cvvView = findViewById(R.id.security_code_view)

        checkoutButton = findViewById(R.id.button_checkout_order)
        checkoutButton?.setOnClickListener(this)

        // responseLayout = findViewById(R.id.response_layout)
        // responseTitle = findViewById(R.id.encrypted_data_title)
        //  responseValue = findViewById(R.id.encrypted_data_view)

    }

    override fun onClick(p0: View?) {


        if (!areFormDetailsValid()) {

//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        } else {


//            ShowMessage("" + cardNumber + "  " + month)
            hitpayment(cardNumber, month, year, cvv)
//            Toast.makeText(this, "oks", Toast.LENGTH_SHORT).show()

        }
    }


    fun areFormDetailsValid(): Boolean {
        cardNumber = cardNumberView!!.getText().toString().replace(" ", "");
        month = monthView!!.getText().toString();
        cvv = cvvView!!.getText().toString();
        year = yearView!!.getText().toString();

        if (isEmptyField()) {
            checkoutButton?.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.shake_error)
            );
            ShowMessage("Empty fields")
            return false;
        }

        year = YEAR_PREFIX + yearView!!.getText().toString();

        return validateFields();
    }

    fun isEmptyField(): Boolean {
        return (cardNumber != null && cardNumber?.isEmpty()!!) || (month != null && month?.isEmpty()!!) || (
                year != null && year?.isEmpty()!!) || (cvv != null && cvv?.isEmpty()!!);
    }

    fun validateFields(): Boolean {
        if (cardNumber?.length!! < MIN_CARD_NUMBER_LENGTH) {
            cardNumberView?.requestFocus();
            cardNumberView?.setError(getString(R.string.invalid_card_number));
            checkoutButton?.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.shake_error)
            );
            return false;
        }
        val monthNum = Integer.parseInt(month.toString());
        if (monthNum < 1 || monthNum > 12) {
            monthView?.requestFocus();
            monthView?.setError(getString(R.string.invalid_month));
            checkoutButton?.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.shake_error)
            );
            return false;
        }
        if (month?.length!! < MIN_YEAR_LENGTH) {
            monthView?.requestFocus();
            monthView?.setError(getString(R.string.two_digit_month));
            checkoutButton?.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.shake_error)
            );
            return false;
        }
        if (year?.length!! < MIN_YEAR_LENGTH) {
            yearView?.requestFocus();
            yearView?.setError(getString(R.string.invalid_year));
            checkoutButton?.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.shake_error)
            );
            return false;
        }
        if (cvv?.length!! < MIN_CVV_LENGTH) {
            cvvView?.requestFocus();
            cvvView?.setError(getString(R.string.invalid_cvv));
            checkoutButton?.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.shake_error)
            );
            return false;
        }
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will

        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            // overridePendingTransition(R.anim.slide_from_top,R.anim.slide_in_top);
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ShowMessage(sms: String) {
        Toast.makeText(applicationContext, sms, Toast.LENGTH_LONG).show()
    }

    private fun hitpayment(
        cardNumber: String?,
        month: String?,
        year: String?,
        cvv: String?
    ) {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)


        val call = service.hit_payment(
            constants!!.accesstoken, cardNumber, year + "-" + month, cvv, price, contact,msg
        )
        call?.enqueue(object : Callback<ServerResponsePaymentApi?> {

            override fun onResponse(
                call: Call<ServerResponsePaymentApi?>,
                response: Response<ServerResponsePaymentApi?>
            ) {
                val data = response.body()
                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {

                    var trans: String? = data!!.getData()?.transactionId
                    editor!!.putString("transId", trans.toString()).commit()
                    editor2!!.putString("a", "100").commit()


//        ShowMessage(""+trans)
                    finish()
//                    editor!!.putString("payed", "a")
//                    editor!!.commit()

//                    finish()
//                    hitAddAds(title, des, price, login_id, c.toString(), data!!.getData()?.transactionId)
//                    edt_title?.setText("")
//                    edt_description?.setText("")
//                    edt_price?.setText("")
//                    img_ads?.setImageBitmap(null);
//                    startActivity(Intent(context, HomeActivity::class.java))

                    // activity!!.finish()


                } else {
                    appUtils!!.dialog!!.dismiss()
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponsePaymentApi?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
                appUtils!!.dialog!!.dismiss()
            }

        })


    }

    private fun hitAddAds(
        title: String,
        descreption: String,
        price: String,
        userId: String?,
        c: String,
        transactionId: String?
    ) {
        appUtils!!.showDialog(this)
        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)

        if (null == img_url) {

        } else { //  Log.e("file",img_url);
            val requestFile =
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), img_url!!)
            image = MultipartBody.Part.createFormData("ads_img", finalFile!!.name, requestFile)
        }
        val C_title = RequestBody.create("text/plain".toMediaTypeOrNull(), title)
        val C_description = RequestBody.create("text/plain".toMediaTypeOrNull(), descreption)
        val C_price = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val C_click = RequestBody.create("text/plain".toMediaTypeOrNull(), c)
        val C_loginId = RequestBody.create("text/plain".toMediaTypeOrNull(), userId!!)
        val C_transaction_id = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            transactionId.toString()
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
                    ShowMessage("Sended for review")
//                    edt_title?.setText("")
//                    edt_description?.setText("")
//                    edt_price?.setText("")
//                    img_ads?.setImageBitmap(null);
//                    startActivity(Intent(this, HomeActivity::class.java))

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
            contentResolver.query(uri!!, null, null, null, null)
        cursor!!.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)
        // video_path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
        return cursor.getString(idx)
    }


    private fun HitGet_pofileApi() {
//        appUtils!!.showDialog(activity)
//        appUtils!!.dialog!!.show()
        var retrofitClient: RetrofitClient? = RetrofitClient()
        val service: Interface = retrofitClient!!.client!!.create(Interface::class.java)
        val call = service.Get_profile(login_id, constants!!.accesstoken)
        call?.enqueue(object : Callback<ServerResponseGetprofile?> {

            override fun onResponse(
                call: Call<ServerResponseGetprofile?>,
                response: Response<ServerResponseGetprofile?>
            ) {
                val data: ServerResponseGetprofile? = response.body()
//                appUtils!!.dialog!!.dismiss()
                if (data?.getCode().equals("200", true)) {
                    contact = data!!.getData()?.contact.toString()

                } else {
                    ShowMessage(data!!.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ServerResponseGetprofile?>, t: Throwable) {
                ShowMessage("Try again")
                Log.e("error", t.toString())
//                appUtils!!.dialog!!.dismiss()
            }

        })

    }
}