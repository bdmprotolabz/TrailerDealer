package Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ServerResponseGetNotificationList {
    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: List<Datum?>? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): List<Datum?>? {
        return data
    }

    fun setData(data: List<Datum?>?) {
        this.data = data
    }

    class Datum {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("to_dealer")
        @Expose
        var toDealer: String? = null

        @SerializedName("to_driver")
        @Expose
        var toDriver: String? = null

        @SerializedName("noti_message")
        @Expose
        var notiMessage: String? = null

        @SerializedName("noti_status")
        @Expose
        var notiStatus: String? = null
        
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

    }
}