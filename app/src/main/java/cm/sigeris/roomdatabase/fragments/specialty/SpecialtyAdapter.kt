package cm.sigeris.roomdatabase.fragments.specialty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.fragments.level.LevelAdapter
import cm.sigeris.roomdatabase.fragments.level.ListLevelDirections
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.model.Specialty
import kotlinx.android.synthetic.main.level_row.view.*
import kotlinx.android.synthetic.main.specialty_row.view.*

class SpecialtyAdapter: RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder>() {
    private var specialtylist = emptyList<Specialty>()
    class SpecialtyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        return SpecialtyAdapter.SpecialtyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.specialty_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return specialtylist.size
    }

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        val currentItem = specialtylist[position]
        holder.itemView.txtIdSpecialty.text = (position+1).toString()
        holder.itemView.txtNameSpecialty.text = currentItem.nameSpecialty.uppercase()
        holder.itemView.txtDescSpecialty.text = currentItem.descSpecialty.uppercase()
        holder.itemView.txtdepartmentSpe.text = currentItem.id_department.toString()
        holder.itemView.specialty_row_layout.setOnClickListener {
            val action = listSpecialtyDirections.actionListSpecialtyToUpdateSpecialty(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(specialty: List<Specialty>){
        this.specialtylist = specialty
        notifyDataSetChanged()
    }


}