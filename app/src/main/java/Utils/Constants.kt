package Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Constants (){
    var LoginAs = 0
    //var Base_URL = "https://phpstack-102119-1083076.cloudwaysapps.com/API/"

    //live base url
// public static String Base_URL = "https://admin.theinfrazone.com/api/";
    var FCMID = "diveceid"
    var Shared_Pref = "share_pref"
    var accesstoken = "123hg213g123hg231hg321h123fgj"


    fun getDateFromTimestamp(
        timestamp: String,
        format: String?
    ): String? {
        val time = timestamp.toLong() * 1000
        return try {
            val sdf: DateFormat = SimpleDateFormat(format, Locale.getDefault())
            val netDate = Date(time)
            sdf.format(netDate)
        } catch (ex: Exception) {
            "xx xxxx xxxx"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String? {
        val cDate = Date()
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cDate)
    }


    fun timeComparison(starTime: String?, endTime: String?): Boolean {
        val pattern = "HH:mm"
        val flag: Int
        var diff: Long = 0
        var diffSeconds: Long = 0
        var diffMinutes: Long = 0
        var diffHours: Long = 0
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starTime)
            val date2 = sdf.parse(endTime)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == -1) {
                diff = date2.time - date1.time
                diffSeconds = diff / 1000
                diffMinutes = diff / (60 * 1000)
                diffHours = diff / (60 * 60 * 1000)
            }
            if (flag == -1 && diffMinutes >= 60) return true
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.parse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
            // Exception handling goes here
        }
        return false
    }

    fun timeAfterComparison(
        starTime: String?,
        endTime: String?
    ): String? {
        val pattern = "HH:mm"
        var timeComparison = ""
        val flag: Int
        var diff: Long = 0
        var diffSeconds: Long = 0
        var diffMinutes: Long = 0
        var diffHours: Long = 0
        var diffDays: Long = 0
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starTime)
            val date2 = sdf.parse(endTime)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == 1) {
                diff = date1.time - date2.time
                diffSeconds = diff / 1000
                diffMinutes = diff / (60 * 1000)
                diffHours = diff / (60 * 60 * 1000)
                diffDays = diffHours / 24
            }
            if (diffHours > 24) {
                timeComparison = "$diffDays days ago"
            } else if (diffHours >= 1 && diffHours <= 24) {
                timeComparison = "$diffHours hours ago"
            } else if (diffHours < 1 && diffMinutes >= 1) {
                timeComparison = "$diffMinutes mins ago"
            } else if (diffHours < 1 && diffMinutes < 1) {
                timeComparison = "$diffSeconds secs ago"
            }
            /*if (flag == 1 && diffMinutes > 0)
                return true;*/
/*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.parse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return timeComparison
            // Exception handling goes here
        }
        return timeComparison
    }

    fun dateBeforeEqualComparison(
        starDate: String?,
        endDate: String?
    ): Boolean {
        val pattern = "MM-yyyy"
        val flag: Int
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starDate)
            val date2 = sdf.parse(endDate)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == -1 || flag == 0) return true
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.pa0rse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
            // Exception handling goes here
        }
        return false
    }

    fun dateEqualsComparison(
        starDate: String?,
        endDate: String?
    ): Boolean {
        val pattern = "dd-MM-yyyy"
        val flag: Int
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starDate)
            val date2 = sdf.parse(endDate)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == 0) return true
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.pa0rse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
            // Exception handling goes here
        }
        return false
    }

    fun dateTimeComparison(starDate: String?, endDate: String?): String? {
        val pattern = "yyyy-MM-dd HH:mm:ss"
        var timeComparison = ""
        val flag: Int
        var diff: Long = 0
        var diffSeconds: Long = 0
        var diffMinutes: Long = 0
        var diffHours: Long = 0
        var diffDays: Long = 0
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starDate)
            val date2 = sdf.parse(endDate)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.pa0rse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/flag = date1.compareTo(date2)
            if (flag == 1) {
                diff = date1.time - date2.time
                diffSeconds = diff / 1000
                diffMinutes = diff / (60 * 1000)
                diffHours = diff / (60 * 60 * 1000)
                diffDays = diffHours / 24
            }
            if (diffHours > 24) {
                timeComparison = "$diffDays days ago"
            } else if (diffHours >= 1 && diffHours <= 24) {
                timeComparison = "$diffHours hours ago"
            } else if (diffHours < 1 && diffMinutes >= 1) {
                timeComparison = "$diffMinutes mins ago"
            } else if (diffHours < 1 && diffMinutes < 1) {
                timeComparison = "$diffSeconds secs ago"
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            return timeComparison
            // Exception handling goes here
        }
        return timeComparison
    }

    fun dateBeforeComparison(
        starDate: String?,
        currentDate: String?
    ): Boolean {
        val pattern = "MM-yyyy"
        val flag: Int
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starDate)
            val date2 = sdf.parse(currentDate)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == -1) return true
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.pa0rse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
            // Exception handling goes here
        }
        return false
    }

    fun dateAfterComparison(
        starDate: String?,
        endDate: String?
    ): Boolean {
        val pattern = "MM-yyyy"
        val flag: Int
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starDate)
            val date2 = sdf.parse(endDate)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == 1) return true
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.pa0rse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
            // Exception handling goes here
        }
        return false
    }

    fun yearsAfterComparison(
        starYear: String?,
        endYear: String?
    ): Boolean {
        val pattern = "yyyy"
        val flag: Int
        val sdf = SimpleDateFormat(pattern)
        try {
            val date1 = sdf.parse(starYear)
            val date2 = sdf.parse(endYear)
            // Outputs -1 as date1 is before date2
            println(date1.compareTo(date2))
            flag = date1.compareTo(date2)
            if (flag == 1) return true
            /*// Outputs 1 as date1 is after date1
            System.out.println(date2.compareTo(date1));

            date2 = sdf.pa0rse(starTime);
            // Outputs 0 as the dates are now equal
            System.out.println(date1.compareTo(date2));*/
        } catch (e: ParseException) {
            e.printStackTrace()
            return false
            // Exception handling goes here
        }
        return false
    }

    /**
     * Description : Check if user is online or not
     *
     * @return true if online else false
     */
    fun isInternetConnected(mActivity: Activity): Boolean {
        val cm =
            mActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm?.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}