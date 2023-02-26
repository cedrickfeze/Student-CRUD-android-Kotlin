package cm.sigeris.roomdatabase.fragments.level

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
import cm.sigeris.roomdatabase.viewmodel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_list_level.view.*


class ListLevel : Fragment() {
    private lateinit var mLevelViewModel: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_level, container, false)

        mLevelViewModel = ViewModelProvider(this).get(LevelViewModel::class.java)
        val adapter = LevelAdapter()
        view.levelrecyclerView.adapter = adapter
        view.levelrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mLevelViewModel.readAllData.observe(viewLifecycleOwner, Observer { level->
            adapter.setData(level)
        })

        view.levelActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listLevel_to_addLevel)
        }
        return view
    }

}