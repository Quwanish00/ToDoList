package Fragments

import Dialogs.DialogAddElements
import Dialogs.DialogAddListt
import Dialogs.DialogEditElements
import EntityDao.DatabaseLists
import EntityDao.ElementsDao
import ToDoAdapters.ElementsAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemsElementBinding
import com.google.android.material.snackbar.Snackbar

class AllELementsFragment:Fragment(R.layout.items_element) {
    private lateinit var binding: ItemsElementBinding
    private lateinit var dao:ElementsDao
    private lateinit var databaseLists: DatabaseLists
    private var aDapter =ElementsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsElementBinding.bind(view)

        databaseLists=DatabaseLists.getInstance(requireContext())
        dao =databaseLists.getElements()

        binding.elementsRecyclerview.adapter =aDapter

        binding.add.setOnClickListener {



            val dialog = DialogAddListt()
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)

            dialog.setOnAddSuccessListener {id->
                val bundllee=Bundle()
                bundllee.putInt("id",id)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container,DialogAddElements::class.java,bundllee)
                    .addToBackStack(AllELementsFragment::class.java.simpleName)
                    .commit()

                aDapter.elements =dao.getAllElements().toMutableList()

                Snackbar.make(
                    binding.add,
                    "Contact added successfully!",
                    Snackbar.LENGTH_SHORT
                ).show()

            }
        }
        aDapter.setOnItemClickListener { elements, position ->
            val bundler = Bundle()
                bundler.putInt("id",elements.iD)
                bundler.putString("name",elements.name)
                bundler.putString("date",elements.date)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container ,DialogEditElements::class.java,bundler)
                .addToBackStack(DialogEditElements::class.java.simpleName)
                .commit()

        }
    }


}