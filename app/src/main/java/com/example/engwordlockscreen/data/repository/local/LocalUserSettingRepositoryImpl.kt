package com.example.engwordlockscreen.data.repository.local

import com.example.engwordlockscreen.data.datasource.database.dto.UserSettingEntity
import com.example.engwordlockscreen.data.datasource.database.usersetting.UserSettingDAO
import javax.inject.Inject

class LocalUserSettingRepositoryImpl @Inject constructor(
    private val dao : UserSettingDAO
) : LocalUserSettingRepository{
    override suspend fun setFirstAppInstall(userSettingEntity: UserSettingEntity) {
        return dao.setFirstAppInstall(userSettingEntity)
    }

    override suspend fun getFirstAppInstall(): Boolean {
        return dao.getFirstAppInstall()
    }

}