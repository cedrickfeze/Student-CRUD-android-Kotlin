package cm.sigeris.roomdatabase.fragments.department

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
import cm.sigeris.roomdatabase.viewmodel.DepartmentViewModel
import cm.sigeris.roomdatabase.viewmodel.LevelViewModel
import kotlinx.android.synthetic.main.fragment_add_department.*
import kotlinx.android.synthetic.main.fragment_add_department.view.*
import kotlinx.android.synthetic.main.fragment_add_level.*
import kotlinx.android.synthetic.main.fragment_add_level.view.*


class AddDepartment : Fragment() {

    private lateinit var mDepartmentViewModel: DepartmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_department, container, false)
        mDepartmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
        view.btnInsertDepartment.setOnClickListener {
            insertDepartmentToDatabase()
        }
        return view
    }

    private fun insertDepartmentToDatabase() {
        val nameDepartment = edtnamedepartment.text.toString()
        if (formValidation(nameDepartment)){
            // Function pour l'insertion
            val newDepartment = Department(0,nameDepartment)
            mDepartmentViewModel.addDepartment(newDepartment)
            Toast.makeText(requireContext(), "Successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addDepartment_to_listDepartment)
        }else{
            Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(nameDepartment: String): Boolean {
        if (nameDepartment.isEmpty()) {
            edtnamedepartment.error = "enter a valid department name"
            edtnamedepartment.requestFocus()

            return false
        }
        return true
    }
}
