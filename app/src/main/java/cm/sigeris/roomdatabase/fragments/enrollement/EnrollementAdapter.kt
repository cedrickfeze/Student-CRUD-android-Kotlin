package cm.sigeris.roomdatabase.fragments.enrollement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.Enrollement
import cm.sigeris.roomdatabase.model.Specialty
import kotlinx.android.synthetic.main.enrollement_row.view.*
import kotlinx.android.synthetic.main.specialty_row.view.*

class EnrollementAdapter: RecyclerView.Adapter<EnrollementAdapter.EnrollementViewHolder>() {
    private var enrollementlist = emptyList<Enrollement>()
    class EnrollementViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): EnrollementAdapter.EnrollementViewHolder {
        return EnrollementAdapter.EnrollementViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.enrollement_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return enrollementlist.size
    }

    override fun onBindViewHolder(holder: EnrollementAdapter.EnrollementViewHolder, position: Int) {
        val currentItem = enrollementlist[position]
        holder.itemView.txtIdEnrollement.text = (position+1).toString()
        holder.itemView.txtSpecialty.text = currentItem.id_specialty.toString()
        holder.itemView.txtLevel.text = currentItem.id_level.toString()
        holder.itemView.txtNameEnrollement.text = currentItem.id_student.toString()
        holder.itemView.txtYearEnrollement.text = currentItem.yearEnrollement.toString()
        holder.itemView.enrollement_row_layout.setOnClickListener {
            val action = ListEnrollementDirections.actionListEnrollementToUpdateEnrollement(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(enrollement: List<Enrollement>){
        this.enrollementlist = enrollement
        notifyDataSetChanged()
    }

}