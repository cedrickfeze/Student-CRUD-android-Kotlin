package cm.sigeris.roomdatabase.fragments.level

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.fragments.list.ListAdapter
import cm.sigeris.roomdatabase.fragments.list.ListFragmentDirections
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.model.User
import kotlinx.android.synthetic.main.level_row.view.*
import kotlinx.android.synthetic.main.user_row.view.*

class LevelAdapter: RecyclerView.Adapter<LevelAdapter.LevelViewHolder>()  {
    private var levellist = emptyList<Level>()

    class LevelViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        return LevelAdapter.LevelViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.level_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return levellist.size
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val currentItem = levellist[position]
        holder.itemView.txtRowIdLevel.text = (position+1).toString()
        holder.itemView.txtRowDescLevel.text = currentItem.descLevel.uppercase()
        holder.itemView.level_row_layout.setOnClickListener {
            val action = ListLevelDirections.actionListLevelToUpdateLevel(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(level: List<Level>){
        this.levellist = level
        notifyDataSetChanged()
    }
}