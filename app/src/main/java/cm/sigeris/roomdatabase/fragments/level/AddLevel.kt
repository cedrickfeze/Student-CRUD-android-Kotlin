package cm.sigeris.roomdatabase.fragments.level

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.Department
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.viewmodel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_add_level.*
import kotlinx.android.synthetic.main.fragment_add_level.view.*

class AddLevel : Fragment() {
    private lateinit var mLevelViewModel: LevelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_level, container, false)
        mLevelViewModel = ViewModelProvider(this).get(LevelViewModel::class.java)
        view.btnInsertlevel.setOnClickListener {
            insertLevelToDatabase()
            val listLevel = ArrayList<Level>()
            listLevel.add(Level(0, "HND 1"))
            listLevel.add(Level(0, "HND 2"))
            listLevel.add(Level(0, "Bechelor"))
            listLevel.add(Level(0, "Masters 1"))
            listLevel.add(Level(0, "Masters 2"))
            listLevel.add(Level(0, "PHD 1"))
            listLevel.add(Level(0, "PHD"))


            for (i in listLevel){
                mLevelViewModel.addLevel(i.copy())
            }
        }
        return view
    }

    private fun insertLevelToDatabase() {
       val descLevel = edtdesclevel.text.toString()
        if (formValidation(descLevel)){
            // Function pour l'insertion
            val newLevel = Level(0,descLevel)
            mLevelViewModel.addLevel(newLevel)
            Toast.makeText(requireContext(), "Successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addLevel_to_listLevel)
        }else{
            Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(descLevel: String): Boolean {
        if (descLevel.isEmpty()){
            edtdesclevel.error = "enter a valid level description"
            edtdesclevel.requestFocus()

return false
        }
        return true
    }
}