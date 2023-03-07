package cm.sigeris.roomdatabase.fragments.specialty

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
import cm.sigeris.roomdatabase.model.Specialty
import cm.sigeris.roomdatabase.viewmodel.SpecialtyViewModel
import kotlinx.android.synthetic.main.fragment_add_specialty.*
import kotlinx.android.synthetic.main.fragment_add_specialty.view.*
import kotlinx.android.synthetic.main.fragment_update_specialty.*
import kotlinx.android.synthetic.main.fragment_update_specialty.view.*


class updateSpecialty : Fragment() {
    private lateinit var mSpecialtyViewModel: SpecialtyViewModel
    private val args by navArgs<updateSpecialtyArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // afficher les elements avant de faire le MAj
        val view = inflater.inflate(R.layout.fragment_update_specialty, container, false)
        mSpecialtyViewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)
//        System.out.println(args.currentSpecialty)
//        view.edtUpdateNameSpecialty.setText("test")
        view.edtUpdateNameSpecialty.setText(args.currentSpecialty.nameSpecialty)
        view.edtUpdateDescSpecialty.setText(args.currentSpecialty.descSpecialty)
        view.UpdateautoCompleteDepartment.setText(args.currentSpecialty.id_department.toString())
        view.btnUpdateSpecialty.setOnClickListener {
            updateOneSpecialty()
        }
        return view

    }

    private fun updateOneSpecialty() {
        val NameSpe = edtUpdateNameSpecialty.text.toString().trim().lowercase()
        val DescSpe = edtUpdateDescSpecialty.text.toString().trim().lowercase()
        val Depart = UpdateautoCompleteDepartment.text.toString().trim().lowercase()

        if (formValidation(NameSpe, DescSpe, Depart)) {
            val newSpe = Specialty(args.currentSpecialty.idSpecialty, NameSpe, DescSpe, Depart.toInt())
            mSpecialtyViewModel.updateSpecialty(newSpe)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateSpecialty_to_listSpecialty)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(DescSpe: String, Depart: String, NameSpe: String): Boolean {
        if (NameSpe.isEmpty()) {
            edtNameSpecialty.error = "enter a valid Specialty "
            edtNameSpecialty.requestFocus()
            return false
        }
        if (DescSpe.isEmpty()) {
            edtDescSpecialty.error = "enter a valid Specialty description"
            edtDescSpecialty.requestFocus()
            return false
        }

        if (Depart.isEmpty()) {
            autoCompleteDepartment.error = "Please search a  Department"
            autoCompleteDepartment.requestFocus()
            return false
        }
        return true
    }
}

