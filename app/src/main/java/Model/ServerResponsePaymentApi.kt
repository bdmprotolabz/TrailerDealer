package Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ServerResponsePaymentApi {
    @SerializedName("code")
    @Expose
    private var code: String? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: Data? = null

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

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }

    class Data {
        @SerializedName("Transaction_id")
        @Expose
        var transactionId: String? = null

    }

}