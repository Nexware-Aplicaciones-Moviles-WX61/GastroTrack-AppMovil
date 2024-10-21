package com.example.gastrotrack_appmovil.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.gastrotrack_appmovil.models.Members
import com.example.gastrotrack_appmovil.models.Notification
import com.example.gastrotrack_appmovil.models.Product
import com.example.gastrotrack_appmovil.models.Report
import com.example.gastrotrack_appmovil.models.Role
import com.example.gastrotrack_appmovil.models.Supplier
import com.example.gastrotrack_appmovil.models.Task
import com.example.gastrotrack_appmovil.models.User

@Database(entities = [User::class, Product::class, Notification::class, Task::class,Report::class,Supplier::class,Members::class,Role::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun productDAO(): ProductDAO
    abstract fun notificationDAO(): NotificationDAO
    abstract fun taskDAO():TaskDAO
    abstract fun reportDAO():ReportDAO
    abstract fun supplierDAO():SupplierDAO
    abstract fun membersDAO():MembersDAO
    abstract fun roleDAO():RoleDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "GastroTrack_App.db"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
