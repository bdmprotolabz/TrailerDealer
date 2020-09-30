package Model

import com.google.gson.annotations.SerializedName


class ServerResponseGetprofile {
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
        @SerializedName("image")
        var image: String? = null
        @SerializedName("contact")
        var contact: String? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("totel_review")
        var totel_review: String? = null
        @SerializedName("id")
        var id: String? = null
        @SerializedName("email")
        var email: String? = null
        @SerializedName("lastname")
        var lastname: String? = null
        override fun toString(): String {
            return "ClassPojo [image = $image, contact = $contact, name = $name, totel_review = $totel_review, id = $id, email = $email]"
        }
    }

}