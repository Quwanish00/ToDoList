package Dialogs

import EntityDao.DatabaseLists
import EntityDao.Elements
import EntityDao.ElementsDao
import ToDoAdapters.ElementsAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogEditElementsBinding

class DialogEditElements :DialogFragment(R.layout.dialog_edit_elements) {
    private lateinit var binding: DialogEditElementsBinding
    private lateinit var dao :ElementsDao
    private lateinit var databaseLists: DatabaseLists
    private var aDapter = ElementsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogEditElementsBinding.bind(view)

        databaseLists = DatabaseLists.getInstance(requireContext())
        dao =databaseLists.getElements()
        val id = arguments?.getInt("id") ?: 0
        val oldName = arguments?.getString("name")
        val oldPhone = arguments?.getString("date")
        binding.apply {

            editItem.setText(oldName)
            editDate.setText(oldPhone)

            edit.setOnClickListener {
                val name = editItem.text.toString()
                val date = editDate.text.toString()
                val i_d =arguments?.getInt("id")?:0


                if (name.isNotEmpty()) {


                    val elements = Elements(
                        iD=id,
                        name = name,
                        date = date,
                        topic_id = i_d

                    )
                    dao.updateElement(elements)
//
                    aDapter.elements =dao.getElementsByID(i_d).toMutableList()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}