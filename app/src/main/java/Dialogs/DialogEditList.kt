package Dialogs

import EntityDao.DatabaseLists
import EntityDao.Lists
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogAddListBinding
import com.example.myapplication.databinding.DialogEditListBinding

class DialogEditList(id:Int,val name:String) :DialogFragment(R.layout.dialog_edit_list) {
    private lateinit var binding: DialogEditListBinding
    private val select =id
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding = DialogEditListBinding.bind(view)



        val dao = DatabaseLists.getInstance(requireContext()).getListDao()

        binding.apply {
            editBtn.setOnClickListener {
                etName.setText(name)
                val name = etName.text.toString()


                if (name.isNotEmpty() ) {


                    val list = Lists(
                        iD =select,
                        name = name,
                    )
                    dao.updateLists(list)
                    onAddSuccess.invoke()
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private var onAddSuccess: () -> Unit = {}
    fun setOnEditClickListener(onAddSuccess: () -> Unit) {
        this.onAddSuccess = onAddSuccess
    }
}