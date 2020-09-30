package Model

import com.google.gson.annotations.SerializedName

class ServerResponseMyProducts {

    @SerializedName("code")
    private var code: String? = null
    @SerializedName("data")
    private var data: Array<Data?>?=null
    @SerializedName("message")
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): Array<Data?>? {
        return data
    }

    fun setData(data: Array<Data?>) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
    class Data {
        @SerializedName("img")
        var img: String? = null
        @SerializedName("created_at")
        var created_at: String? = null
        @SerializedName("id")
        var id: String? = null
        @SerializedName("product_name")
        var product_name: String? = null
        @SerializedName("url")
        var url: String? = null
        @SerializedName("dealer_id")
        var dealer_id: String? = null
        @SerializedName("price")
        var price: String? = null
    }

}