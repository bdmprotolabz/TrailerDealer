package Service

import Model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Interface {
    @FormUrlEncoded
    @POST("dealer.php/signup")
    fun signup(
        @Field("contact") contact: String?,
        @Field("accesstoken") accesstoken: String?,
        @Field("device_type") device_type: String?,
        @Field("device_token") device_token: String?
    ): Call<ServerResponeLogin?>?

    @FormUrlEncoded
    @POST("dealer.php/login")
    fun login(
        @Field("contact") contact: String?,
        @Field("accesstoken") accesstoken: String?,
        @Field("device_type") device_type: String?,
        @Field("device_token") device_token: String?
    ): Call<ServerResponeLogin?>?

    @FormUrlEncoded
    @POST("dealer.php/verify_otp")
    fun verify_otp(
        @Field("contact") contact: String?,
        @Field("otp") otp: String?,
        @Field("device_type") device_type: String?,
        @Field("device_token") device_token: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseOtp?>?

    @FormUrlEncoded
    @POST("dealer.php/logout")
    fun logout(
        @Field("dealerID") dealerID: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseLogout?>?

    @FormUrlEncoded
    @POST("dealer.php/dealer_location")
    fun dealer_location(
        @Field("dealerID") dealerID: String?,
        @Field("dealer_lat") dealer_lat: String?,
        @Field("dealer_long") dealer_long: String?,
        @Field("location") location: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseLogout?>?

    @FormUrlEncoded
    @POST("dealer.php/update_profile")
    fun update_profle(
        @Field("dealerID") dealerID: String?,
        @Field("name") name: String?,
        @Field("lastname") lastname: String?,
        @Field("email") email: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseUpdateProfile?>?

    @Multipart
    @POST("dealer.php/update_image")
    fun update_profile_image(
        @Part("dealerID") dealerID: RequestBody?,
        @Part("accesstoken") accesstoken: RequestBody?,
        @Part user_img: MultipartBody.Part?
    ): Call<ServerResponseUpadeImage?>?


    @FormUrlEncoded
    @POST("dealer.php/Get_profile")
    fun Get_profile(
        @Field("dealerID") dealerID: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseGetprofile?>?

    @GET("driver.php/get_membership")
    fun get_membership(): Call<ServerResponseMebership>?

    @Multipart
    @POST("dealer.php/mylocation")
    fun mylocation(
        @Part("dealerID") dealerID: RequestBody?,
        @Part("location_title") location_title: RequestBody?,
        @Part("src_lat") src_lat: RequestBody?,
        @Part("src_long") src_long: RequestBody?,
        @Part("location") location: RequestBody?,
        @Part("accesstoken") accesstoken: RequestBody?,
        @Part loc_img: MultipartBody.Part?
    ): Call<ServerResponseLogout?>?

    @FormUrlEncoded
    @POST("dealer.php/location_list")
    fun location_list(
        @Field("dealerID") dealerID: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseHome?>?

    @FormUrlEncoded
    @POST("dealer.php/my_location_list")
    fun my_location_list(
        @Field("dealerID") dealerID: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseMylocation?>?

    @FormUrlEncoded
    @POST("dealer.php/reviews")
    fun reviews(
        @Field("dealerid") dealerid: String?,
        @Field("location_id") location_id: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseAllReview?>?

    @Multipart
    @POST("dealer.php/add_product")
    fun add_product(
        @Part("dealerid") dealerID: RequestBody?,
        @Part("pname") pname: RequestBody,
        @Part("url") url: RequestBody,
        @Part("price") price: RequestBody,
        @Part("accesstoken") accesstoken: RequestBody?,
        @Part product_img: MultipartBody.Part?
    ): Call<ServerResponseLogout?>?

    @FormUrlEncoded
    @POST("dealer.php/get_product")
    fun get_product(
        @Field("dealerid") dealerid: String?,
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseMyProducts?>?

    @Multipart
    @POST("dealer.php/add_ads")
    fun add_ads(
        @Part("accesstoken") accesstoken: RequestBody?,
        @Part("ad_title") ad_title: RequestBody?,
        @Part("dealer_id") dealer_id: RequestBody?,
        @Part("description") description: RequestBody?,
        @Part("paid_amount") paid_amount: RequestBody?,
        @Part ads_img: MultipartBody.Part?,
        @Part("click") click: RequestBody?,
        @Part("trajection_id") trajection_id: RequestBody?
    ): Call<ServerResponseAddAds?>?


    @FormUrlEncoded
    @POST("dealer.php/Get_price")
    fun get_price(
        @Field("accesstoken") accesstoken: String?
    ): Call<ServerResponseGetPrice?>?

    @FormUrlEncoded
    @POST("driver.php/handle_online_pay")
    fun hit_payment(
        @Field("accesstoken") accesstoken: String?,
        @Field("card_number") card_number: String?,
        @Field("date") date: String?,
        @Field("CardCode") CardCode: String?,
        @Field("amount") amount: String?
        , @Field("contact") contact: String?
        ,@Field("msg") msg: String?
    ): Call<ServerResponsePaymentApi?>?

//    @FormUrlEncoded
//    @POST("dealer.php/update_location")
//    fun hit_Location(
//        @Field("accesstoken") accesstoken: String?, @Field("dealerID") dealerID : String?
//    , @Field("dealer_lat") dealer_lat: String?, @Field("dealer_long") dealer_long: String?
//    ): Call<ServerResponseHitLocation?>?


    @FormUrlEncoded
    @POST("dealer.php/add_list")
    fun get_AdList(
        @Field("accesstoken") accesstoken: String?,
        @Field("dealer_id") dealer_id: String?
    ): Call<ServerResponseAdList?>?


    @FormUrlEncoded
    @POST("dealer.php/getNotifications")
    fun hit_getNotifications(
        @Field("accesstoken") accesstoken: String?,
        @Field("dealer_id") dealer_id: String?

    )
            : Call<ServerResponseGetNotificationList?>?


    @FormUrlEncoded
    @POST("dealer.php/cleare_Notifications")
    fun hit_ClearNotifications(
        @Field("accesstoken") accesstoken: String?,
        @Field("dealer_id") dealer_id: String?

    )
            : Call<ServerResponseClearNotification?>?
}

