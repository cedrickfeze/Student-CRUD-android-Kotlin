package cm.sigeris.roomdatabase.fragments.add


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.User
import cm.sigeris.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.btnInsert.setOnClickListener {
            insertDataToDataBase()
        }
        return view
    }

    private fun insertDataToDataBase() {
        val firtName = edtFname.text.toString()
        val lastName = edtLname.text.toString()
        val age = edtAge.text.toString()
        if(formValidation(firtName, lastName, age)){
            val user = User(0, firtName.lowercase(), lastName.lowercase(), age.toInt())
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(firtName: String, lastname: String, age: String): Boolean {
        //return !(TextUtils.isEmpty(firtName) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(age))
        if(firtName.isEmpty()){
            edtFname.error = "enter a valid first name"
            edtFname.requestFocus()
            return false
        }
        if(lastname.isEmpty()){
            edtLname.error = "enter a valid last name"
            edtLname.requestFocus()
            return false
        }
        if(age.toString().length == 0){
            edtAge.error = "enter a valid age"
            edtAge.requestFocus()
            return false
        }
        return true
    }
}