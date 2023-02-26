package cm.sigeris.roomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.User
import kotlinx.android.synthetic.main.user_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.txtId.text = (position+1).toString()
        holder.itemView.txtFirstName.text = currentItem.firstName.uppercase()
        holder.itemView.txtLastName.text = currentItem.lastName.uppercase()
        holder.itemView.txtUserAge.text = currentItem.age.toString()
        holder.itemView.user_row_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}