package com.example.engwordlockscreen.data.datasource.database.usersetting

import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.engwordlockscreen.data.datasource.database.dto.UserSettingEntity

interface UserSettingDAO {

    @Insert(onConflict = ABORT)
    fun setFirstAppInstall(userSettingEntity: UserSettingEntity)

    @Query("SELECT firstInstall FROM UserSettingDB")
    fun getFirstAppInstall() : Boolean
}