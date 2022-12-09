package com.example.engwordlockscreen.data.datasource.database.usersetting

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.data.datasource.database.dto.UserSettingEntity

@Database(entities = [UserSettingEntity::class], version = CustomConst.APP_VERSION_CODE)
abstract class UserSettingDB : RoomDatabase() {

    abstract fun UserSettingDAO() : UserSettingDAO

    companion object {
        const val DATABASE_NAME = "UserSettingDB.db"
    }

}