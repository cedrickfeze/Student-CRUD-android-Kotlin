package cm.sigeris.roomdatabase.fragments.specialty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.Specialty
import cm.sigeris.roomdatabase.model.User
import cm.sigeris.roomdatabase.viewmodel.SpecialtyViewModel
import cm.sigeris.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_add_specialty.*
import kotlinx.android.synthetic.main.fragment_add_specialty.view.*


class addSpecialty : Fragment() {
    private lateinit var mSpecialtyViewModel: SpecialtyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_specialty, container, false)
        mSpecialtyViewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)
        view.btnInsertSpecialty.setOnClickListener {
            insertDataToDataBase()
        }
        return view
    }

    private fun insertDataToDataBase() {
        val NameSpecialty = edtNameSpecialty.text.toString()
        val DescSpecialty = edtDescSpecialty.text.toString()
        val DepartmentSpe = autoCompleteDepartment.text.toString()
        if(formValidation(NameSpecialty, DescSpecialty, DepartmentSpe)){
            val newSpecialty = Specialty(0, NameSpecialty.lowercase(), DescSpecialty.lowercase(), DepartmentSpe.toInt())
            mSpecialtyViewModel.addSpecialty(newSpecialty)
            Toast.makeText(requireContext(), "successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addSpecialty_to_listSpecialty)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(NameSpecialty: String, DescSpecialty: String, DepartmentSpe: String): Boolean {
        if(NameSpecialty.isEmpty()){
            edtNameSpecialty.error = "enter a valid Specialty name"
            edtNameSpecialty.requestFocus()
            return false
        }
        if(DescSpecialty.isEmpty()){
            edtDescSpecialty.error = "enter a valid Specialty description"
            edtDescSpecialty.requestFocus()
            return false
        }
        if(DepartmentSpe.toString().length == 0){
            autoCompleteDepartment.error = "Select a department"
            autoCompleteDepartment.requestFocus()
            return false
        }
        return true
    }


}