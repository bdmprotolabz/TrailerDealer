package Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ServerResponseAdList {

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
        @SerializedName("dealer_name")
        @Expose
        var dealerName: Any? = null

        @SerializedName("dealer_img")
        @Expose
        var dealerImg: Any? = null

        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("dealer_id")
        @Expose
        var dealerId: String? = null

        @SerializedName("ad_title")
        @Expose
        var adTitle: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("amount")
        @Expose
        var amount: String? = null

        @SerializedName("trajection_id")
        @Expose
        var trajectionId: String? = null

        @SerializedName("total_click")
        @Expose
        var totalClick: String? = null

        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("pending_clieck")
        @Expose
        var pendingClieck: String? = null

    }
}