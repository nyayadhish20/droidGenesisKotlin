package com.nyayadhish.droidgenesiskotlin.lib.utils


/**
 * Constants class is used to store all constant data used in application.
 *
 * @author Nikhil Nyayadhish
 */
object Constants {

    const val ERROR = "Error!"
    const val WARNING = "Warning!"
    const val SUCCESS = "Success!"
    /*Shared Preferences Keys*/
    const val PREF_USER_DATA = "UserData"

    /*Types of User*/
    const val USER_EMPLOYEE = "1"
    const val USER_PREF_ASSOCIATE = "2"
    const val USER_ASSOCIATE = "3"

    /*Hardcoded Values*/
    const val DEVICE_TYPE_ID = "A"
    const val USER_TYPE_ID = "2"
    const val SECURITY_TOKEN = "no token"

    /* General Flags*/
    const val FLAG_TRUE = "1"
    const val FLAG_FALSE = "0"
    const val FLAG_MALE = "1"
    const val FLAG_FEMALE = "2"

   /*Language*/
   const val LANGUAGE_ENGLISH = "en"
   const val LANGUAGE_AREBIC = "ar"

    /*Animation File Names*/
    const val JSON_NO_INTERNET = "no_internet.json"
    const val JSON_NO_POST = "no_post.json"
    const val JSON_SERVER_ERROR = "server_error.json"

    /*Cache File Names*/
    const val FILE_LOCAL_ERRORS = "LocalErrors.txt"
    const val FILE_SERVER_ERRORS = "ServerErrors.txt"

    // Error constants.
    const val _111 = "111"

    /*Component Constants*/
    const val COMPONENT_TIMELINE: String = "timeLineComponent"
    const val COMPONENT_BANNER: String = "banerComponent"
    const val COMPONENT_CATEGORY: String = "categoryComponent"
    const val COMPONENT_CATEGORY_ITEM: String = "categoryItemComponent"
    const val COMPONENT_EVENT: String = "eventComponent"
    const val COMPONENT_INVITE = "inviteComponent"



    /**
     * Notification constants.
     */
    const val DUMMY_TITLE = "Wiishme"
    const val NOTIFICATIONID = "notificationid"
    const val NOTIFICATIONCOUNT = "notificationcount"
    const val TITLE = "TITLE"

    //Dashboard dev base URL for the flow from Home
    private const val DASHBOARD_DEV_BASE_URL =
        "http://103.129.98.170/event_ticket_new/Ticket/public/index.php/api/v1/"

    //On-boarding dev base URL
    private const val ONBOARDING_DEV_BASE_URL =
      "http://103.129.98.170/event_ticket_new/User/public/index.php/api/v1/"
      //  "http://magnetoapp.mantraservers.com/event_ticket_new/User/public/index.php/api/v1/ "
//        "http://magnetoapp.mantraservers.com/event_ticket_new/User/public/index.php/api/v1/"

    // Staging
    private const val STAGING_BASE_URL =
        "http://54.156.147.140/scoped_staging/public/index.php/api/"
    // Staging Client
    private const val CLIENT_STAGING_BASE_URL = "http://staging.gositelink.com/api/"
    // Live play store
    private const val LIVE_BASE_URL = "http://api.gositelink.com/api/"


    const val DASHBOARD_BASE_URL = DASHBOARD_DEV_BASE_URL
    const val ONBOARDING_BASE_URL = ONBOARDING_DEV_BASE_URL
}
