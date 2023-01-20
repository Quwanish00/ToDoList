package EntityDao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lists::class, Elements::class], version = 2, exportSchema = false)
abstract class DatabaseLists: RoomDatabase() {
    companion object {
        private var instance: DatabaseLists? = null

        fun getInstance(context: Context): DatabaseLists {
            instance?.let {
                return it
            }

            val db = Room.databaseBuilder(context, DatabaseLists::class.java, "lists.db")
                .allowMainThreadQueries()
                .build()

            instance = db
            return db
        }
    }

    abstract fun getListDao(): ListDao

    abstract fun getElements(): ElementsDao
}