package cm.sigeris.roomdatabase.fragments.specialty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cm.sigeris.roomdatabase.R

import cm.sigeris.roomdatabase.viewmodel.SpecialtyViewModel

import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_list_specialty.view.*


class listSpecialty : Fragment() {
    private lateinit var mSpecialtyViewModel: SpecialtyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_specialty, container, false)

        mSpecialtyViewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)

        // recycler view
        val adapter = SpecialtyAdapter()

        view.specialtyrecyclerView.adapter = adapter
        view.specialtyrecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //specialtyView Model
        mSpecialtyViewModel.readAllData.observe(viewLifecycleOwner, Observer { specialty ->
            adapter.setData(specialty)
        })

        view.specialtyActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listSpecialty_to_addSpecialty)
        }
        return view
    }


}