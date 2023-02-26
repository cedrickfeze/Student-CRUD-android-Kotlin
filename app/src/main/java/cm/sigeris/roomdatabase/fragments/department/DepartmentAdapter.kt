package cm.sigeris.roomdatabase.fragments.department

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.Department
import kotlinx.android.synthetic.main.department_row.view.*

class DepartmentAdapter: RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>()  {

    private var departmentlist = emptyList<Department>()

    class DepartmentViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }



    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): DepartmentAdapter.DepartmentViewHolder {
        return DepartmentAdapter.DepartmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.department_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return departmentlist.size
    }


    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {

        val currentItem = departmentlist[position]
        System.out.println("DEPARTMENT LIST"+ departmentlist)
        holder.itemView.txtRowIdDepartment.text = (position+1).toString()
        holder.itemView.txtRowNameDepartment.text = currentItem.namedepartment.uppercase()
        holder.itemView.department_row_layout.setOnClickListener {
            val action = ListDepartmentDirections.actionListDepartmentToUpdateDepartment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(department: List<Department>){
        this.departmentlist = department
        notifyDataSetChanged()
    }

}