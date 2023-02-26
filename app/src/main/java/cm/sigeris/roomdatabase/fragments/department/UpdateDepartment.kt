package cm.sigeris.roomdatabase.fragments.department

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
import cm.sigeris.roomdatabase.model.Department
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.viewmodel.DepartmentViewModel
import cm.sigeris.roomdatabase.viewmodel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_add_department.*
import kotlinx.android.synthetic.main.fragment_add_level.*
import kotlinx.android.synthetic.main.fragment_update_department.*
import kotlinx.android.synthetic.main.fragment_update_department.view.*
import kotlinx.android.synthetic.main.fragment_update_level.*
import kotlinx.android.synthetic.main.fragment_update_level.view.*


class UpdateDepartment : Fragment() {
    private lateinit var mDepartmentViewModel: DepartmentViewModel
    private val args by navArgs<UpdateDepartmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // afficher les elements avant de faire le MAj
        val view =  inflater.inflate(R.layout.fragment_update_department, container, false)
        mDepartmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
        view.edtUpdatenamedepartment.setText(args.currentDepartment.namedepartment)
        view.btnUpdatedepartment.setOnClickListener {
            updateOneDepartment()
        }

        return view
    }

    private fun updateOneDepartment() {
        val nameDepartment = edtUpdatenamedepartment.text.toString()
        if (formValidation(nameDepartment)){
            val newDepartment = Department(args.currentDepartment.iddepartment, nameDepartment)
            mDepartmentViewModel.updateDepartment(newDepartment)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateDepartment_to_listDepartment)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(nameDepartment: String): Boolean {
        if (nameDepartment.isEmpty()){
            edtnamedepartment.error = "enter a valid Department name"
            edtnamedepartment.requestFocus()

            return false
        }
        return true
    }


}