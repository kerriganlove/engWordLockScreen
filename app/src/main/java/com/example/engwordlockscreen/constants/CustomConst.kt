package com.example.engwordlockscreen.constants

object CustomConst {
    /*
     * App Setting Constants
     */
    const val FIRST_APP_INSTALL = "FIRST_APP_INSTALL"
    const val CLEAR_DB = "CLEAR_DB"
    const val OVERLAY_PERMISSION_TOGGLE = "OVERLAY_PERMISSION_TOGGLE"
    const val REMAIN_CHANCES_FREE_UNLOCK = "REMAIN_CHANCES_FREE_UNLOCK"
    const val DEFAULT_REMAIN_COUNT = 5
    const val APP_VERSION_CODE = 1

    /*
     * Query Constants
     */
    const val GET_LIST_BY_NAVER = "rss/channel/item"

    /*
     * Error String Constants
     */
    const val ERROR_BY_UNEXPECTED = "예기치 않은 상황으로 인한 오류입니다."
    const val ERROR_BY_NETWORK = "서버에 접속할 수 없습니다. 네트워크 상태를 확인해주세요."

    /*
     * Insert TAG
     */
    const val DELETE_INSERT_WORD_LIST = "DELETE_INSERT_WORD_LIST"
    const val ADD_INSERT_WORD_LIST = "ADD_INSERT_WORD_LIST"
    const val CHANGE_INSERT_WORD_LIST = "CHANGE_INSERT_WORD_LIST"

    /*
     * Time
     */
    const val ANIMATE_CORRECT_TIME : Long = 1000L // 1sec

}