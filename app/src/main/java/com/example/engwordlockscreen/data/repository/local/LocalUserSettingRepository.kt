package com.example.engwordlockscreen.data.repository.local

import com.example.engwordlockscreen.data.datasource.database.dto.UserSettingEntity

interface LocalUserSettingRepository {
    suspend fun setFirstAppInstall(userSettingEntity: UserSettingEntity)

    suspend fun getFirstAppInstall() : Boolean
}