package Dialogs

import EntityDao.DatabaseLists
import EntityDao.Elements
import EntityDao.ElementsDao
import EntityDao.Lists
import ToDoAdapters.ElementsAdapter
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogAddElementsBinding

class DialogAddElements(private var iD:Int):DialogFragment(R.layout.dialog_add_elements) {
    private lateinit var dao : ElementsDao
    private lateinit var databaseLists: DatabaseLists
    private lateinit var binding: DialogAddElementsBinding
    private var aDapter =ElementsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = DialogAddElementsBinding.bind(view)

        databaseLists = DatabaseLists.getInstance(requireContext())
        dao =databaseLists.getElements()
        binding.apply {

            save.setOnClickListener {
                val name = addItem.text.toString()
                val date = addDate.text.toString()



                if (name.isNotEmpty()) {


                    val elements = Elements(
                        name = name,
                        date = date,
                        topic_id = iD

                    )
                    dao.addElements(elements)
                     onAddSuccess.invoke()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    private var onAddSuccess: () -> Unit = {}

    fun setOnAddSuccessListener(onAddSuccess: () -> Unit) {
        this.onAddSuccess = onAddSuccess
    }


}