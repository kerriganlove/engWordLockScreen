package com.example.engwordlockscreen.data.datasource.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.engwordlockscreen.domain.database.UserSettingEntities

@Entity(tableName = "UserSettingDB")
data class UserSettingEntity(
    @PrimaryKey(autoGenerate = true) var userId : Int,
    var firstInstall : Boolean
)

fun UserSettingEntity.toUserSettingEntities() : UserSettingEntities{
    return UserSettingEntities(
        id = userId,
        firstInstall = firstInstall
    )
}