package cm.sigeris.roomdatabase.fragments.department

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import cm.sigeris.roomdatabase.model.Department

class DepartmentAutoCompleteAdapter (context: Context, @LayoutRes private val layoutResource: Int, private val allDep: List<Department>):
    ArrayAdapter<Department>(context, layoutResource, allDep),
    Filterable {

    private var mDep: List<Department> = allDep

    override fun getCount(): Int {
        return mDep.size
    }

    override fun getItem(p0: Int): Department? {
        return mDep.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return mDep.get(p0).iddepartment.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        //view.text = "${mDep[position].idDep} ${mDep[position].nameDep} (${mDep[position].descDep})"
        view.text = "${mDep[position].namedepartment}"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                mDep = filterResults.values as List<Department>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.lowercase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allDep
                else
                    allDep.filter {
                        it.namedepartment.lowercase().contains(queryString)
                    }
                return filterResults
            }
        }
    }

}