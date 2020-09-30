package Model

import com.google.gson.annotations.SerializedName


class ServerResponseOtp {

    @SerializedName("code")
    private var code: String? = null
    @SerializedName("data")
    private var data: Data? = null
    @SerializedName("message")
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
    class Data {
        @SerializedName("driverImg")
        var driverImg: String? = null
        @SerializedName("contact")
        var contact: String? = null
        @SerializedName("device_token")
        var device_token: String? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("otp")
        var otp: String? = null
        @SerializedName("id")
        var id: String? = null
        @SerializedName("email")
        var email: String? = null
        @SerializedName("devicetype")
        var devicetype: String? = null
        @SerializedName("lastname")
        var lastname: String? = null

        override fun toString(): String {
            return "ClassPojo [driverImg = $driverImg, contact = $contact, device_token = $device_token, name = $name, otp = $otp, id = $id, email = $email, devicetype = $devicetype]"
        }
    }

}