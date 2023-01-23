package Fragments

import Dialogs.DialogAddElements
import Dialogs.DialogAddListt
import Dialogs.DialogEditList
import EntityDao.DatabaseLists
import EntityDao.ElementsDao
import EntityDao.ListDao
import EntityDao.Lists
import ToDoAdapters.ListsAdapter
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemsListBinding

import com.google.android.material.snackbar.Snackbar

class ListItemsFragment:Fragment(R.layout.items_list) {
    private lateinit var binding: ItemsListBinding
    private lateinit var dao: ListDao
    private lateinit var elementsDao:ElementsDao
    private lateinit var databaseLists: DatabaseLists
    private var adapter = ListsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsListBinding.bind(view)

        databaseLists = DatabaseLists.getInstance(requireContext())
        dao = databaseLists.getListDao()
        elementsDao =databaseLists.getElements()

            binding.  listRecyclerview.adapter = adapter


        binding.apply {


            adapter.setOnMenuClickListener { v, lists, position ->
                showMenu(v, R.menu.menu_purchase, lists, position)
            }

        }

        binding.add.setOnClickListener {




                Toast.makeText(requireContext(), "button clicked", Toast.LENGTH_SHORT).show()

                val dialog = DialogAddListt()
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                dialog.setOnAddSuccessListener {


                    adapter.lists = dao.getAllLists().toMutableList()

                    Snackbar.make(
                        binding.add,
                        "List added successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }


            }

            adapter.setOnItemListClickListener { lists, _ ->

                val bundle = Bundle()

                bundle.putInt("iD", lists.iD)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AllELementsFragment::class.java, bundle)
                    .commit()


            }
        }

    private fun showMenu(v: View, @MenuRes menuRes: Int, lists: Lists, position: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when(menuItem.itemId) {
                R.id.item1 -> {
                    val bundle = Bundle()
                    bundle.putString("name", lists.name)

                    val dialog = DialogEditList(lists.iD, lists.name)
                    dialog.show(requireActivity().supportFragmentManager, dialog.tag)

                    dialog.setOnEditClickListener {
                        adapter.lists = dao.getAllLists().toMutableList()

                        Snackbar.make(requireView(), "Ozgerdi", Snackbar.LENGTH_SHORT).show()
                    }

                    /* MaterialAlertDialogBuilder(requireContext())
                         .setTitle("Edit Contact")
                         .setMessage("${purchase.name} di ozgerteyikpa")
                         .setPositiveButton("Yes") { dialog, _ ->
                             dao.updatePurchase(purchase)
                             Snackbar.make(v, "Purchase edit successfully", Snackbar.LENGTH_SHORT)
                                 .show()
                             dialog.dismiss()
                         }
                         .setNegativeButton("Cancel") { dialog, _ ->
                             dialog.dismiss()
                         }
                         .show()*/
                }
                R.id.item2 -> {
                    dao.deleteList(lists)
                    adapter.removeAtPosition(position)

                    elementsDao.getElementsByID(lists.iD).forEach {
                        elementsDao.deleteElement(it)
                    }
                    Snackbar.make(requireView(), "oshdi", Snackbar.LENGTH_SHORT).show()
                }
            }
            true
        }

        popup.show()
    }
    }




