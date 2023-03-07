package cm.sigeris.roomdatabase.fragments.enrollement

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
import cm.sigeris.roomdatabase.fragments.specialty.updateSpecialtyArgs
import cm.sigeris.roomdatabase.model.Enrollement
import cm.sigeris.roomdatabase.model.Specialty
import cm.sigeris.roomdatabase.viewmodel.EnrollementViewModel
import cm.sigeris.roomdatabase.viewmodel.SpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_add_specialty.*
import kotlinx.android.synthetic.main.fragment_update_enrollement.*
import kotlinx.android.synthetic.main.fragment_update_enrollement.view.*
import kotlinx.android.synthetic.main.fragment_update_specialty.*
import kotlinx.android.synthetic.main.fragment_update_specialty.view.*

class UpdateEnrollement : Fragment() {
    private lateinit var mEnrollementViewModel: EnrollementViewModel
    private val args by navArgs<UpdateEnrollementArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // afficher les elements avant de faire le MAj
        val view = inflater.inflate(R.layout.fragment_update_enrollement, container, false)
        mEnrollementViewModel = ViewModelProvider(this).get(EnrollementViewModel::class.java)
        System.out.println(args.currentEnrollement)
        view.UpdateYearEnrollement.setText(args.currentEnrollement.yearEnrollement)
        view.UpdateautoCompleteStudent.setText(args.currentEnrollement.id_student.toString())
        view.UpdateautoCompleteSpecialty.setText(args.currentEnrollement.id_specialty.toString())
        view.UpdateautoCompleteLevel.setText(args.currentEnrollement.id_level.toString())
        view.btnUpdateEnrollement.setOnClickListener {
            updateOneEnrollement()
        }
        return view

    }

    private fun updateOneEnrollement() {
        val NameSpe = UpdateautoCompleteSpecialty.text.toString().trim().lowercase()
        val NameLev = UpdateautoCompleteLevel.text.toString().trim().lowercase()
        val NameStu = UpdateautoCompleteStudent.text.toString().trim().lowercase()
        val EnrollYear = UpdateYearEnrollement.text.toString().trim().lowercase()

        if (formValidation(NameSpe, NameLev, NameStu, EnrollYear)) {
            val newEnroll = Enrollement(
                args.currentEnrollement.idEnrollement,
                EnrollYear,
                NameSpe.toInt(),
                NameLev.toInt(),
                NameStu.toInt()
            )
            mEnrollementViewModel.updateEnrollement(newEnroll)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEnrollement_to_listEnrollement)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(NameSpe: String, NameLev: String, NameStu: String, EnrollYear: String): Boolean {
        if (NameSpe.isEmpty()) {
            UpdateautoCompleteSpecialty.error = "enter a valid Specialty "
            UpdateautoCompleteSpecialty.requestFocus()
            return false
        }
        if (NameLev.isEmpty()) {
            UpdateautoCompleteLevel.error = "enter a valid Level "
            UpdateautoCompleteLevel.requestFocus()
            return false
        }
        if (NameStu.isEmpty()) {
            UpdateautoCompleteStudent.error = "enter a valid Student "
            UpdateautoCompleteStudent.requestFocus()
            return false
        }
        if (EnrollYear.isEmpty()) {
            UpdateYearEnrollement.error = "enter a valid Year "
            UpdateYearEnrollement.requestFocus()
            return false
        }
        return true
    }



}