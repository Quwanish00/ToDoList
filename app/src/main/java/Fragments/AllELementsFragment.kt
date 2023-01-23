package Fragments

import Dialogs.DialogAddElements
import Dialogs.DialogAddListt
import Dialogs.DialogEditElements
import EntityDao.DatabaseLists
import EntityDao.ElementsDao
import ToDoAdapters.ElementsAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemsElementBinding
import com.google.android.material.snackbar.Snackbar

class AllELementsFragment : Fragment(R.layout.items_element) {
    private lateinit var binding: ItemsElementBinding
    private lateinit var dao: ElementsDao
    private lateinit var databaseLists: DatabaseLists
    private var aDapter = ElementsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ItemsElementBinding.bind(view)

        databaseLists = DatabaseLists.getInstance(requireContext())
        dao = databaseLists.getElements()

        binding.elementsRecyclerview.adapter = aDapter
        val id =arguments?.getInt("id")?:0


        binding.add.setOnClickListener {





            val dialog = DialogAddElements(id)
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)

            dialog.setOnAddSuccessListener {

                aDapter.elements = dao.getElementsByID(id).toMutableList()

                Snackbar.make(
                    binding.add,
                    "Element added successfully!",
                    Snackbar.LENGTH_SHORT
                ).show()

            }

        }
        aDapter.setOnEditElementClickListener { elements, position ->

            val dialog = DialogEditElements()
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)

            dialog.setOnEditElementsListener {

                aDapter.elements = dao.getElementsByID(position).toMutableList()

                Snackbar.make(
                    binding.add,
                    "Element added successfully!",
                    Snackbar.LENGTH_SHORT
                ).show()

            }

        }


        aDapter.setOnItemClickListener { elements, position ->
            val bundler = Bundle()
            bundler.putInt("id", elements.iD)

            bundler.putString("name", elements.name)
            bundler.putString("date", elements.date)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, DialogEditElements::class.java, bundler)
                .addToBackStack(DialogEditElements::class.java.simpleName)
                .commit()

        }
//        aDapter.setOnCheckBoxClickListener { elements, position ->
//
//            if()
//        }

    }


}