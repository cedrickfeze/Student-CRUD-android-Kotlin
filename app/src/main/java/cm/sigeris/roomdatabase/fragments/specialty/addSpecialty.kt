package cm.sigeris.roomdatabase.fragments.specialty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.fragments.department.DepartmentAutoCompleteAdapter
import cm.sigeris.roomdatabase.model.Department
import cm.sigeris.roomdatabase.model.Specialty
import cm.sigeris.roomdatabase.viewmodel.DepartmentViewModel
import cm.sigeris.roomdatabase.viewmodel.SpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_add_specialty.*
import kotlinx.android.synthetic.main.fragment_add_specialty.view.*


class addSpecialty : Fragment() {
    private lateinit var mDepartmentViewModel: DepartmentViewModel
    private lateinit var mSpecialtyViewModel: SpecialtyViewModel
    var depID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_specialty, container, false)
//        mDepartmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
        mSpecialtyViewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)
        view.btnInsertSpecialty.setOnClickListener {
            insertDataToDataBase()
        }
        return view
    }


    private fun insertDataToDataBase() {
        val NameSpecialty = edtNameSpecialty.text.toString().trim().lowercase()
        val DescSpecialty = edtDescSpecialty.text.toString().trim().lowercase()
        val NameDepartment = autoCompleteDepartment.text.toString().trim().lowercase()
        if(formValidation(NameSpecialty, DescSpecialty, NameDepartment)){
            val newSpecialty = Specialty(0, NameSpecialty, DescSpecialty, depID)
//            System.out.println(" SPECIALTY = $newSpecialty")
            mSpecialtyViewModel.addSpecialty(newSpecialty)
            Toast.makeText(requireContext(), "successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addSpecialty_to_listSpecialty)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        mDepartmentViewModel=ViewModelProvider(this).get(DepartmentViewModel::class.java)
        mDepartmentViewModel.readAllData.observe(viewLifecycleOwner, Observer { dept ->
            //adapter.setData(dept)
            val adapter = DepartmentAutoCompleteAdapter (requireContext(),android.R.layout.simple_list_item_1, dept)
            autoCompleteDepartment.threshold = 1
            autoCompleteDepartment.setAdapter(adapter)
//            autoCompleteDepartment.setTextColor(color.RED)
            autoCompleteDepartment.setOnItemClickListener { parent, _, position, id ->
                val selectedDept = parent.adapter.getItem(position) as Department
                autoCompleteDepartment.setText(selectedDept.namedepartment.uppercase())
                depID = selectedDept.iddepartment
//                Toast.makeText(requireContext(), "ID = $depID and department ${selectedDept.namedepartment.uppercase()}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun formValidation(NameSpecialty: String, DescSpecialty: String, NameDepartment:String): Boolean {
        if(NameSpecialty.isEmpty()){
            edtNameSpecialty.error = "enter a valid Specialty "
            edtNameSpecialty.requestFocus()
            return false
        }
        if(DescSpecialty.isEmpty()){
            edtDescSpecialty.error = "enter a valid Specialty description"
            edtDescSpecialty.requestFocus()
            return false
        }

        if(NameDepartment.isEmpty()){
            autoCompleteDepartment.error = "Please search a  Department"
            autoCompleteDepartment.requestFocus()
            return false
        }
        return true
    }


}