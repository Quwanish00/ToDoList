package EntityDao

import androidx.room.*

@Dao
interface ListDao {
    @Query("SELECT * FROM lists")
    fun getAllLists(): List<Lists>

    @Insert
    fun addList(lists: Lists)

    @Query ("SELECT * FROM lists WHERE name  like :search_value")
    fun searchListt(search_value:String):List<Lists>

    @Delete(entity = Lists::class)
    fun deleteList(lists: Lists)

    @Update(entity = Lists::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateLists(lists: Lists)
}