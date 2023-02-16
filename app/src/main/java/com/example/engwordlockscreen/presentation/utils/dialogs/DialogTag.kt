package com.example.engwordlockscreen.presentation.utils.dialogs

sealed class DialogTag{
    object LoadingDialog : DialogTag()
    object CorrectAnswerDialog : DialogTag()
    object PermissionDialog : DialogTag()
    object WordListDialog : DialogTag()
    object WordDeleteDialog : DialogTag()
}

