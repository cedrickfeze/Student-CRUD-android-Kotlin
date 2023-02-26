package cm.sigeris.roomdatabase.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.model.User
import cm.sigeris.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private  lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.edtUpdateFname.setText(args.currentUser.firstName)
        view.edtUpdateLname.setText(args.currentUser.lastName)
        view.edtUpdateAge.setText(args.currentUser.age.toString())

        view.btnUpdate.setOnClickListener {
            updateUserItem()
        }
        //show action bar
        setHasOptionsMenu(true)
        /*view.btnDelete.setOnClickListener {
            deleteUserItem()
        }*/
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.delete_all ->{
                deleteUserItem()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun deleteUserItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "successfully deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("no") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName.uppercase()} ?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName.uppercase()} ?")
        builder.show()
    }

    private  fun updateUserItem(){
        val firstName = edtUpdateFname.text.toString()
        val lastName = edtUpdateLname.text.toString()
        val age = edtUpdateAge.text.toString()
        if(formValidation(firstName, lastName, age)){
            val user = User(args.currentUser.id, firstName, lastName, age.toInt())
            mUserViewModel.updateUser(user)
            Toast.makeText(requireContext(), "successfully Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please try again later", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(firtName: String, lastname: String, age: String): Boolean {
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