package com.example.engwordlockscreen.data.datasource.database.usersetting

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.engwordlockscreen.data.datasource.database.dto.UserSettingEntity

@Dao
interface UserSettingDAO {

    @Insert(onConflict = ABORT)
    fun setFirstAppInstall(userSettingEntity: UserSettingEntity)

    @Query("SELECT * FROM UserSettingDB")
    fun getFirstAppInstall() : Boolean
}