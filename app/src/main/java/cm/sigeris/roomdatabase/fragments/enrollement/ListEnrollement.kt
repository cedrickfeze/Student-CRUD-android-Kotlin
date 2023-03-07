package cm.sigeris.roomdatabase.fragments.enrollement

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
import cm.sigeris.roomdatabase.fragments.specialty.SpecialtyAdapter
import cm.sigeris.roomdatabase.viewmodel.EnrollementViewModel
import cm.sigeris.roomdatabase.viewmodel.SpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_list_enrollement.view.*
import kotlinx.android.synthetic.main.fragment_list_specialty.view.*


class ListEnrollement : Fragment() {
    private lateinit var mEnrollementViewModel: EnrollementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_enrollement, container, false)

        mEnrollementViewModel = ViewModelProvider(this).get(EnrollementViewModel::class.java)

        // recycler view
        val adapter = EnrollementAdapter()

        view.enrollementyrecyclerView.adapter = adapter
        view.enrollementyrecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //specialtyView Model
        mEnrollementViewModel.readAllData.observe(viewLifecycleOwner, Observer { specialty ->
            adapter.setData(specialty)
        })

        view.enrollementActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listEnrollement_to_addEnrollement)
        }
        return view
    }

}