package cm.sigeris.roomdatabase.fragments.level

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.fragments.update.UpdateFragmentArgs
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.viewmodel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_add_level.*
import kotlinx.android.synthetic.main.fragment_update_level.*
import kotlinx.android.synthetic.main.fragment_update_level.view.*


class UpdateLevel : Fragment() {
    private lateinit var mLevelViewModel: LevelViewModel
    private val args by navArgs<UpdateLevelArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // afficher les elements avant de faire le MAj
        val view =  inflater.inflate(R.layout.fragment_update_level, container, false)
        mLevelViewModel = ViewModelProvider(this).get(LevelViewModel::class.java)
        view.edtUpdatedesclevel.setText(args.currentLevel.descLevel)
        view.btnUpdatelevel.setOnClickListener {
            updateOneLevel()
        }

        return view
    }

    private fun updateOneLevel() {
        val descLevel = edtUpdatedesclevel.text.toString()
        if (formValidation(descLevel)){
            val newLevel = Level(args.currentLevel.idLevel, descLevel)
            mLevelViewModel.updateLevel(newLevel)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateLevel_to_listLevel)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
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