package br.com.battlepassCalculatorValorant.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.battlepassCalculatorValorant.BD_APP_VERSION
import br.com.battlepassCalculatorValorant.database.room.dao.UserTierDao
import br.com.battlepassCalculatorValorant.database.room.model.UserTier

@Database(entities = [UserTier::class], version = BD_APP_VERSION)
abstract class AppDB : RoomDatabase() {
    abstract val userTier: UserTierDao
}