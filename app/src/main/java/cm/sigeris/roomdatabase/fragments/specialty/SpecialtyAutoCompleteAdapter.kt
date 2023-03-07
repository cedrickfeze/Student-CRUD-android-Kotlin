package cm.sigeris.roomdatabase.fragments.specialty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import cm.sigeris.roomdatabase.model.Specialty

class SpecialtyAutoCompleteAdapter (context: Context, @LayoutRes private val layoutResource: Int, private val allSpec: List<Specialty>):
    ArrayAdapter<Specialty>(context, layoutResource, allSpec),
    Filterable {
    private var mSpec: List<Specialty> = allSpec

    override fun getCount(): Int {
        return mSpec.size
    }

    override fun getItem(p0: Int): Specialty? {
        return mSpec.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return mSpec.get(p0).idSpecialty.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        //view.text = "${mSpec[position].idSpec} ${mSpec[position].nameSpec} (${mSpec[position].descSpec})"
        view.text = "${mSpec[position].nameSpecialty}"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                mSpec = filterResults.values as List<Specialty>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.lowercase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allSpec
                else
                    allSpec.filter {
                        it.nameSpecialty.lowercase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}