package cm.sigeris.roomdatabase.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import cm.sigeris.roomdatabase.model.User

class UserAutoCompleteAdapter (context: Context, @LayoutRes private val layoutResource: Int, private val allStu: List<User>):
    ArrayAdapter<User>(context, layoutResource, allStu),
    Filterable {

    private var mStu: List<User> = allStu

    override fun getCount(): Int {
        return mStu.size
    }

    override fun getItem(p0: Int): User? {
        return mStu.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return mStu.get(p0).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        //view.text = "${mStu[position].idDep} ${mStu[position].nameDep} (${mStu[position].descDep})"
        view.text = "${mStu[position].firstName}"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                mStu = filterResults.values as List<User>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.lowercase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allStu
                else
                    allStu.filter {
                        it.firstName.lowercase().contains(queryString)
                    }
                return filterResults
            }
        }
    }

}