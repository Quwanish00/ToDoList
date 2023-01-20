package EntityDao

import androidx.room.*


@Dao
    interface ElementsDao {

    @Query("SELECT * FROM elements")
    fun getAllElements(): List<Elements>

    @Query("SELECT * FROM elements WHERE topic_id = :topicID")
    fun getElementsByID(topicID:Int): List<Elements>

    @Insert
    fun addElements(elements: Elements)

    @Query ("SELECT * FROM elements WHERE name || date like :search_value")
    fun searchStudents(search_value:String):List<Elements>

    @Update(entity = Elements::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateElement(elements: Elements)


    @Delete(entity = Elements::class)
    fun deleteElement(elements: Elements)
}