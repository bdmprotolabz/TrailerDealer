package Model

class ServerResponseAddAds {

    private var code: String? = null

    private var message: String? = null

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

    override fun toString(): String {
        return "ClassPojo [code = $code, message = $message]"
    }
}