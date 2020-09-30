package Model


class ServerResponseGetPrice {

    private var code: String? = null

    private var data: Data? = null

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

    override fun toString(): String {
        return "ClassPojo [code = $code, data = $data, message = $message]"
    }

    class Data {
        var price: String? = null
        var id: String? = null
        var click: String? = null

        override fun toString(): String {
            return "ClassPojo [price = $price, id = $id, click = $click]"
        }
    }


}