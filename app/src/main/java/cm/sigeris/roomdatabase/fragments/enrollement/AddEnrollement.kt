package cm.sigeris.roomdatabase.fragments.enrollement

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
import cm.sigeris.roomdatabase.fragments.level.LevelAutocompleteAdater
import cm.sigeris.roomdatabase.fragments.list.UserAutoCompleteAdapter
import cm.sigeris.roomdatabase.fragments.specialty.SpecialtyAutoCompleteAdapter
import cm.sigeris.roomdatabase.model.*
import cm.sigeris.roomdatabase.viewmodel.*
import kotlinx.android.synthetic.main.fragment_add_enrollement.*
import kotlinx.android.synthetic.main.fragment_add_enrollement.view.*
import kotlinx.android.synthetic.main.fragment_add_specialty.*
import kotlinx.android.synthetic.main.fragment_add_specialty.view.*


class AddEnrollement : Fragment() {
    private lateinit var mEnrollementViewModel: EnrollementViewModel
    private lateinit var mSpecialtyViewModel: SpecialtyViewModel
    private lateinit var mLevelViewModel: LevelViewModel
    private lateinit var mStudentViewModel: UserViewModel
    var SpecialtyID: Int = 0
    var LevelID: Int = 0
    var StudentID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_enrollement, container, false)
        mEnrollementViewModel = ViewModelProvider(this).get(EnrollementViewModel::class.java)
        view.btnAddEnrollement.setOnClickListener {
            insertDataToDataBase()
        }
        return view
    }

    private fun insertDataToDataBase() {
        val YearEnrollement = edtYearEnrollement.text.toString().trim().lowercase()
        val NameSpecialty = autoCompleteSpecialty.text.toString().trim().lowercase()
        val NameStudent = autoCompleteStudent.text.toString().trim().lowercase()
        val NameLevel = autoCompleteLevel.text.toString().trim().lowercase()
        if(formValidation(NameSpecialty, YearEnrollement, NameStudent, NameLevel)){
            val newEnrollement = Enrollement(0, YearEnrollement.toInt(), SpecialtyID, LevelID, StudentID)
//            System.out.println(" Enrollement = $newEnrollement")
            mEnrollementViewModel.addEnrollement(newEnrollement)
            Toast.makeText(requireContext(), "successfully created", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addEnrollement_to_listEnrollement)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        mStudentViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mLevelViewModel=ViewModelProvider(this).get(LevelViewModel::class.java)
        mSpecialtyViewModel=ViewModelProvider(this).get(SpecialtyViewModel::class.java)

        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            //adapter.setData(stu)
            val adapter = UserAutoCompleteAdapter (requireContext(),android.R.layout.simple_list_item_1, user)
            autoCompleteStudent.threshold = 1
            autoCompleteStudent.setAdapter(adapter)
//            autoCompleteStudent.setTextColor(color.RED)
            autoCompleteStudent.setOnItemClickListener { parent, _, position, id ->
                val selectedStu = parent.adapter.getItem(position) as User
                autoCompleteStudent.setText(selectedStu.firstName.uppercase())
                StudentID = selectedStu.id
            }
        })
        mLevelViewModel.readAllData.observe(viewLifecycleOwner, Observer { level ->
            //adapter.setData(Lev)
            val leveladapter = LevelAutocompleteAdater (requireContext(),android.R.layout.simple_list_item_1, level)
            autoCompleteLevel.threshold = 1
            autoCompleteLevel.setAdapter(leveladapter)
            autoCompleteLevel.setOnItemClickListener { parent, _, position, id ->
                val selectedLev = parent.adapter.getItem(position) as Level
                autoCompleteLevel.setText(selectedLev.descLevel.uppercase())
                LevelID = selectedLev.idLevel
            }
        })
        mSpecialtyViewModel.readAllData.observe(viewLifecycleOwner, Observer { specialty ->
            //adapter.setData(spe)
            val specialtyadapter = SpecialtyAutoCompleteAdapter (requireContext(),android.R.layout.simple_list_item_1, specialty)
            autoCompleteSpecialty.threshold = 1
            autoCompleteSpecialty.setAdapter(specialtyadapter)
            autoCompleteSpecialty.setOnItemClickListener { parent, _, position, id ->
                val selectedSpe = parent.adapter.getItem(position) as Specialty
                autoCompleteSpecialty.setText(selectedSpe.nameSpecialty.uppercase())
                SpecialtyID = selectedSpe.idSpecialty
            }
        })
    }


    private fun formValidation(NameSpecialty: String, YearEnrollement: String, NameStudent: String, NameLevel: String): Boolean {
        if (NameSpecialty.isEmpty()) {
            autoCompleteSpecialty.error = "enter a valid Specialty "
            autoCompleteSpecialty.requestFocus()
            return false
        }
        if (YearEnrollement.isEmpty()) {
            edtYearEnrollement.error = "enter a valid Year "
            edtYearEnrollement.requestFocus()
            return false
        }
        if (NameStudent.isEmpty()) {
            autoCompleteStudent.error = "enter a valid Student "
            autoCompleteStudent.requestFocus()
            return false
        }
        if (NameLevel.isEmpty()) {
            autoCompleteLevel.error = "enter a valid Level "
            autoCompleteLevel.requestFocus()
            return false
        }
        return true
    }

}