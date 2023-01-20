package ToDoAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R


import EntityDao.Elements
import com.example.myapplication.databinding.ItemElementBinding


class ElementsAdapter:RecyclerView.Adapter<ElementsAdapter.ElementsViewHolder>() {

    inner class ElementsViewHolder(private val binding: ItemElementBinding) :
        ViewHolder(binding.root) {
        fun bind(elements: Elements) {
            binding.apply {
                name.text = elements.name
                date.text = elements.date
            }
        }
    }

    var elements = mutableListOf<Elements>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_element, parent, false)
        val binding = ItemElementBinding.bind(view)
        return ElementsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        holder.bind(elements[position])

//        setAnimation(holder.itemView,lastPosition)
    }
//    private var lastPosition = -1
//    private fun setAnimation(viewToAnimate: View, position: Int) {

//        if (position > lastPosition) {
//            val anim = ScaleAnimation(
//                0.0f,
//                1.0f,
//                0.0f,
//                1.0f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f
//            )
//            anim.duration =
//                Random().nextInt(501).toLong()
//            viewToAnimate.startAnimation(anim)
//            lastPosition = position
//        }

//    private fun setFadeAnimation(view: View) {
//        val anim = AlphaAnimation(0.0f, 1.0f)
//        anim.duration = 1000
//        view.startAnimation(anim)
//    }

    override fun getItemCount(): Int {
        return elements.size
    }

    private var onItemClick: (elements: Elements, position: Int) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (elements: Elements, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    fun removeAtPosition(position: Int) {
        elements.removeAt(position)
        notifyItemRemoved(position)
    }

    private var onDeleteClick: (elements: Elements, position: Int) -> Unit = { _, _ -> }
    fun setOnDeleteClickListener(cononDeleteClick: (elements: Elements, position: Int) -> Unit) {
        this.onDeleteClick = cononDeleteClick


    }
}
