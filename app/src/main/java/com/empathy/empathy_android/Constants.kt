package com.empathy.empathy_android

internal class Constants {


    companion object {
        const val BASE_URL = "http://ec2-13-209-245-253.ap-northeast-2.compute.amazonaws.com:8080/"

        const val EXTRA_KEY_FEED_IMAGE_URI = "extra_key_feed_image_uri"
        const val EXTRA_KEY_LOCATION_FILTER = "extra_key_location_filter"
        const val EXTRA_KEY_FEED_DETAIL_TYPE = "extra_key_feed_detail_type"
        const val EXTRA_KEY_FEED_ID = "extra_key_feed_id"
        const val EXTRA_KEY_USER = "extra_key_user"
        const val EXTRA_KEY_ADDRESS = "extra_key_address"
        const val EXTRA_KEY_PARTNER_ID = "extra_key_partner_id"
        const val EXTRA_KEY_TARGET_ID = "extra_key_target_id"
        const val EXTRA_KEY_CONTENT_TYPE = "extra_key_content_type"
        const val EXTRA_KEY_PARTNER_INFO_DETAIL_TYPE = "extra_key_partner_info_detail_type"

        const val DEFAULT_LATITUDE   = 126.981106
        const val DEFAULT_LONGTITUDE = 37.568477
        const val DEFAULT_RANGE = "500"
        const val T_MAP_KEY: String = "b5be9a2e-d454-4177-8912-1d2c1cbee391"

    }

}