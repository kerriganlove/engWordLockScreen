package com.example.engwordlockscreen.presentation.utils.string

import android.text.InputFilter
import java.util.regex.Pattern

object StringFilter
{
    val filterENG : InputFilter? = InputFilter { source, start, end, dest, dstart, dend ->
        var pattern = Pattern.compile("^[a-zA-Z]*$")
        if(!pattern.matcher(source).matches())
        {
            return@InputFilter ""
        }
        return@InputFilter null
    }

    val filterKOR : InputFilter? = InputFilter { source, start, end, dest, dstart, dend ->
        var pattern = Pattern.compile("^[ㄱ-ㅣ가-힣\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]*$")
        if(!pattern.matcher(source).matches())
        {
            return@InputFilter ""
        }
        return@InputFilter null
    }
}