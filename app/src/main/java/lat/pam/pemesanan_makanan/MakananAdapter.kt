package lat.pam.pemesanan_makanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.io.path.name



class MakananAdapter(
    private val makananList: MutableList<Makanan>,
    private val onSelectionChanged: (List<Makanan>) -> Unit
) : RecyclerView.Adapter<MakananAdapter.MakananViewHolder>() {


    class MakananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageFood: de.hdodenhof.circleimageview.CircleImageView = itemView.findViewById(R.id.img_item_photo)
        val foodName: TextView = itemView.findViewById(R.id.textViewFoodName)
        val foodDescription: TextView = itemView.findViewById(R.id.textViewFoodDescription)
        val checkbox: CheckBox = itemView.findViewById(R.id.checkboxFood) // Ganti ke CheckBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakananViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_makanan, parent, false)
        return MakananViewHolder(view)
    }


    override fun onBindViewHolder(holder: MakananViewHolder, position: Int) {
        val makanan = makananList[position]
        holder.imageFood.setImageResource(makanan.imageResId)
        holder.foodName.text = makanan.name
        holder.foodDescription.text = makanan.description


        holder.checkbox.setOnCheckedChangeListener(null)
        holder.checkbox.isChecked = makanan.isSelected


        holder.itemView.setOnClickListener {

            makanan.isSelected = !makanan.isSelected
            holder.checkbox.isChecked = makanan.isSelected


            notifySelectionChanged()
        }


        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            makanan.isSelected = isChecked

            notifySelectionChanged()
        }
    }


    fun getSelectedItems(): List<Makanan> {
        return makananList.filter { it.isSelected }
    }


    private fun notifySelectionChanged() {
        val selectedItems = makananList.filter { it.isSelected }
        onSelectionChanged(selectedItems)
    }

    override fun getItemCount() = makananList.size
}
