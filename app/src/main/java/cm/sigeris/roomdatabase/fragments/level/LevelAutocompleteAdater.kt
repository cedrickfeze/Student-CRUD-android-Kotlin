package cm.sigeris.roomdatabase.fragments.level

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import cm.sigeris.roomdatabase.model.Level

class LevelAutocompleteAdater (context: Context, @LayoutRes private val layoutResource: Int, private val allLev: List<Level>):
    ArrayAdapter<Level>(context, layoutResource, allLev),
    Filterable {

    private var mLev: List<Level> = allLev

    override fun getCount(): Int {
        return mLev.size
    }

    override fun getItem(p0: Int): Level? {
        return mLev.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return mLev.get(p0).idLevel.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        //view.text = "${mLev[position].idDep} ${mLev[position].nameDep} (${mLev[position].descDep})"
        view.text = "${mLev[position].descLevel}"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                mLev = filterResults.values as List<Level>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.lowercase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allLev
                else
                    allLev.filter {
                        it.descLevel.lowercase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}