package Fragments

import Dialogs.DialogAddListt
import EntityDao.DatabaseLists
import EntityDao.ListDao
import ToDoAdapters.ListsAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemsListBinding

import com.google.android.material.snackbar.Snackbar

class ListItemsFragment:Fragment(R.layout.items_list) {
    private lateinit var binding: ItemsListBinding
    private lateinit var dao :ListDao
    private lateinit var databaseLists: DatabaseLists
    private var adapter= ListsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsListBinding.bind(view)

        databaseLists =DatabaseLists.getInstance(requireContext())
        dao=databaseLists.getListDao()


        binding.elementsRecyclerview.adapter=adapter

        binding.add.setOnClickListener {
                val dialog = DialogAddListt()
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)

            dialog.setOnAddSuccessListener {

                adapter.lists = dao.getAllLists().toMutableList()

                Snackbar.make(
                    binding.add,
                    "Contact added successfully!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }





            }

        adapter.setOnItemListClickListener { lists, position ->

            val bundle =Bundle()

            bundle.putInt("iD",lists.iD)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,AllELementsFragment::class.java,bundle)
                .commit()


}
            binding.add.setOnClickListener {


            }
        }




    }

