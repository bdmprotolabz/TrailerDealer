package in.techware.lataxi.util;

import com.google.gson.Gson;

import in.techware.lataxi.R;
import in.techware.lataxi.app.App;
import in.techware.lataxi.model.CountryListBean;
import in.techware.lataxi.net.ServiceNames;

public class AppConstants {


    public static final String SEPARATOR = "/";
    public static final String SPACE = " ";
    public static final String BASE_URL = ServiceNames.API_UPLOADS + SEPARATOR;

    public static final String UTF_8 = "UTF-8";
    public static final String MIME_TYPE_HTML = "text/html";
    public static final String PREFERENCE_KEY_SESSION_IS_FIRST_TIME = "";

    public static final int CAR_TYPE_LA_GO = 1;
    public static final int CAR_TYPE_LA_X = 2;
    public static final int CAR_TYPE_LA_XL = 3;
    public static final int CAR_TYPE_LA_XXL = 4;

    public static final int LOCATION_SELECTED_ONITEMCLICK = 0;
    public static final int LOCATION_SELECTED_ONHOMECLICK = 1;
    public static final int LOCATION_SELECTED_ONWORKCLICK = 2;

    public static final int SEARCH_SOURCE = 0;
    public static final int SEARCH_DESTINATION = 1;
    public static final int SEARCH_ESTIMATED_DESTINATION = 2;

    public static final int SEARCH_HOME = 0;
    public static final int SEARCH_WORK = 1;

    public static final int ADD_HOME = 0;
    public static final int ADD_WORK = 1;

    public static final String FEEDBACK1 = App.getInstance().getString(R.string.label_i_lost_an_item);
    public static final String FEEDBACK2 = App.getInstance().getString(R.string.label_i_was_incorrectly_charged_cancellation_fee);
    public static final String FEEDBACK3 = App.getInstance().getString(R.string.label_i_would_like_a_refund);
    public static final String FEEDBACK4 = App.getInstance().getString(R.string.feedback_my_driver_was_unprofessional);
    public static final String FEEDBACK5 = App.getInstance().getString(R.string.feedback_my_vehicle_wasn_t_what_i_expected);
    public static final String FEEDBACK6 = App.getInstance().getString(R.string.label_i_had_an_issue_with_reciept_or_payment);

    public static final String BAD_FEEDBACK_TYPE_SERVICE = App.getInstance().getString(R.string.feedback_bad_service);
    public static final String BAD_FEEDBACK_TYPE_CLEANLINESS = App.getInstance().getString(R.string.feedback_bad_cleanliness);
    public static final String BAD_FEEDBACK_TYPE_DRIVING = App.getInstance().getString(R.string.feedback_bad_driving);
    public static final String BAD_FEEDBACK_TYPE_COMFORT = App.getInstance().getString(R.string.feedback_bad_comfort);
    public static final String BAD_FEEDBACK_TYPE_CAR_QUALITY = App.getInstance().getString(R.string.feedback_bad_car_quality);
    public static final String BAD_FEEDBACK_TYPE_OTHER = App.getInstance().getString(R.string.feedback_bad_other);
    public static final String GOOD_FEEDBACK_TYPE_SERVICE = App.getInstance().getString(R.string.feedback_good_service);
    public static final String GOOD_FEEDBACK_TYPE_CLEANLINESS = App.getInstance().getString(R.string.feedback_good_cleanliness);
    public static final String GOOD_FEEDBACK_TYPE_DRIVING = App.getInstance().getString(R.string.feedback_good_driving);
    public static final String GOOD_FEEDBACK_TYPE_COMFORT = App.getInstance().getString(R.string.feedback_good_comfort);
    public static final String GOOD_FEEDBACK_TYPE_CAR_QUALITY = App.getInstance().getString(R.string.feedback_good_car_quality);
    public static final String GOOD_FEEDBACK_TYPE_OTHER = App.getInstance().getString(R.string.feedback_good_other);

    //    public static String WEB_ERROR_MSG = "Unable to connect to the server. Please try again later";
    public static String WEB_ERROR_MSG = App.getInstance().getString(R.string.message_web_error_msg);
    public static String NO_NETWORK_AVAILABLE = App.getInstance().getString(R.string.message_no_network_available);

    public static final String PREFERENCE_KEY_SESSION_TOKEN = "auth_token";
    public static final String PREFERENCE_KEY_SESSION_GCM_ID = "gcm_id";
    public static final String PREFERENCE_KEY_SESSION_DEVICE_ID = "device_id";
    public static final String PREFERENCE_KEY_SESSION_DEVICE_SECRET = "device_secret";
    public static final String PREFERENCE_KEY_SESSION_ACCESSTOKEN = "access_token";
    public static final String PREFERENCE_KEY_SESSION_REFRESHTOKEN = "refresh_token";
    public static final String PREFERENCE_KEY_SESSION_USERNAME = "username";
    public static final String PREFERENCE_KEY_SESSION_NAME = "name";
    public static final String PREFERENCE_KEY_SESSION_FIRSTNAME = "firstname";
    public static final String PREFERENCE_KEY_SESSION_LASTNAME = "lastname";
    public static final String PREFERENCE_KEY_SESSION_EMAIL = "email";
    public static final String PREFERENCE_KEY_SESSION_PHONE = "phone";
    public static final String PREFERENCE_KEY_SESSION_USERID = "userid";
    public static final String PREFERENCE_KEY_SESSION_PASSWORD = "password";
    public static final String PREFERENCE_KEY_SESSION_GENDER = "gender";
    public static final String PREFERENCE_KEY_SESSION_DOB = "DOB";
    public static final String PREFERENCE_KEY_SESSION_IS_PHONE_VERIFIED = "is_phone_verified";
    public static final String PREFERENCE_NAME_SESSION = "session";

    public static String ACTION_CHOOSE_MONTH = "ae.s4m.test.action.CHOOSE_MONTH";
    public static String ACTION_CHOOSE_DAY = "ae.s4m.test.action.CHOOSE_DAY";
    public static String ACTION_CHOOSE_YEAR = "ae.s4m.test.action.CHOOSE_YEAR";


    public static int YEAR_START = 1950;

    public static String MALE = "m";
    public static String FEMALE = "f";


    private static final String COUNTRY_LIST = "{\"countries\":[{\"name\":\"Canada\",\"dial_code\":\"+1\",\"code\":\"CA\"},{\"name\":\"United States\",\"dial_code\":\"+1\",\"code\":\"US\"},{\"name\":\"Malawi\",\"dial_code\":\"+265\",\"code\":\"MW\"},{\"name\":\"India\",\"dial_code\":\"+91\",\"code\":\"IN\"}]}";

    public static CountryListBean getCountryBean() {

        CountryListBean countryListBean = new Gson().fromJson(COUNTRY_LIST, CountryListBean.class);
        for (int i = 0; i < countryListBean.getCountries().size(); i++) {
            countryListBean.getCountries().get(i).setId(i);
        }
        return countryListBean;

    }

}
