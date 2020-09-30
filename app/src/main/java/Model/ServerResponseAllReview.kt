package Model

class ServerResponseAllReview {
    private var code: String? = null

    private var data: Array<Data?>?=null

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
        var review: String? = null
        var name: String? = null

        override fun toString(): String {
            return "ClassPojo [review = $review, name = $name]"
        }
    }


}