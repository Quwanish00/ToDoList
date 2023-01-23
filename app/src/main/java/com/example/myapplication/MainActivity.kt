package com.example.myapplication

import EntityDao.DatabaseLists
import EntityDao.ListDao
import Fragments.AllELementsFragment
import Fragments.ListItemsFragment
import ToDoAdapters.ListsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dao : ListDao
    private lateinit var databaseLists: DatabaseLists
    private var adapter= ListsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
        databaseLists =DatabaseLists.getInstance(this)
        dao=databaseLists.getListDao()


        supportFragmentManager.beginTransaction()
            .replace(R.id.container,ListItemsFragment())
            .commit()

    }

    override fun onRestart() {
        super.onRestart()
        adapter.lists = dao.getAllLists().toMutableList()
    }
}