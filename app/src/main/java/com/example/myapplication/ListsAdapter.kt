package ToDoAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ListItemBinding
import EntityDao.Elements
import EntityDao.Lists

class ListsAdapter:RecyclerView.Adapter<ListsAdapter.LIstViewHolder>() {

    inner class LIstViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lists: Lists) {
            binding.apply {
                name.text = lists.name

                menu.setOnClickListener {
                    onMenuClick.invoke(it, lists, adapterPosition)
                }

                listItem.setOnClickListener {
                    onItemClick.invoke(lists,adapterPosition)
                }
            }
        }
    }

    var lists = mutableListOf<Lists>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LIstViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val binding = ListItemBinding.bind(view)
        return LIstViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LIstViewHolder, position: Int) {
        holder.bind(lists[position])
        setFadeAnimation(holder.itemView)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }


    override fun getItemCount(): Int {
        return lists.size
    }

    private var onMenuClick: (v: View, lists: Lists, a: Int) -> Unit = { _, _, _ -> }
    fun setOnMenuClickListener(onMenuClick: (v: View, lists: Lists, position: Int) -> Unit) {
        this.onMenuClick = onMenuClick
    }

    fun removeAtPosition(position: Int) {
        lists.removeAt(position)
        notifyItemRemoved(position)
    }

    private var onDeleteClick: (elements: Elements, position: Int) -> Unit = { _, _ -> }
    fun setOnDeleteClickListener(cononDeleteClick: (elements: Elements, position: Int) -> Unit) {
        this.onDeleteClick = cononDeleteClick

    }
    private var onItemClick:(lists: Lists, position:Int) ->Unit ={ _, _ ->}
    fun setOnItemListClickListener(onItemClick:(lists: Lists, position:Int) ->Unit){
        this.onItemClick =onItemClick
    }
}