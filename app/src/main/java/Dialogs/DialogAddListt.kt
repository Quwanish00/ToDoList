package Dialogs

import EntityDao.DatabaseLists
import EntityDao.Lists
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogAddListBinding

class DialogAddListt:DialogFragment(R.layout.dialog_add_list) {
    private lateinit var binding: DialogAddListBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DialogAddListBinding.bind(view)

        val dao = DatabaseLists.getInstance(requireContext()).getListDao()

        binding.apply {
            addBtn.setOnClickListener {
                val name = etName.text.toString()


                if (name.isNotEmpty() ) {


                    val list = Lists(
                        name = name,
                    )
                    dao.addList(list)
                    onAddSuccess.invoke(list.iD)
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "Fill the fields!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private var onAddSuccess: (id:Int) -> Unit = {}
    fun setOnAddSuccessListener(onAddSuccess: (id:Int) -> Unit) {
        this.onAddSuccess = onAddSuccess
    }
    }
