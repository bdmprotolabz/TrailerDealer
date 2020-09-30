package Model

import com.google.gson.annotations.SerializedName

class ServerResponseMebership {
    @SerializedName("code")
    private var code: String? = null
    @SerializedName("data")
    private var data: List<Data?>?=null
    @SerializedName("message")
    private var message: String? = null

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getData(): List<Data?>? {
        return data
    }

    fun setData(data: List<Data?>) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    class Data {
        @SerializedName("created_on")
        var created_on: String? = null
        @SerializedName("price")
        var price: String? = null
        @SerializedName("id")
        var id: String? = null
        @SerializedName("type")
        var type: String? = null
        @SerializedName("status")
        var status: String? = null

        override fun toString(): String {
            return "ClassPojo [created_on = $created_on, price = $price, id = $id, type = $type, status = $status]"
        }
    }

}