package cm.sigeris.roomdatabase.fragments.list

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cm.sigeris.roomdatabase.MainActivity
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        //show action bar
        setHasOptionsMenu(true)
        // recycler view
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //userView Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.delete_all ->{
                deleteAllUsers()
                return true
            }
            R.id.department ->{
                Toast.makeText(requireContext(), "department pressed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listFragment_to_listDepartment)
                return true
            }

            R.id.level ->{
                Toast.makeText(requireContext(), "level pressed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listFragment_to_listLevel)
                return true
            }

            R.id.enrollement ->{
                Toast.makeText(requireContext(), "enrollement pressed", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.specialty ->{
                Toast.makeText(requireContext(), "specialty pressed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listFragment_to_listSpecialty)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(), "successfully deleted", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("no") { _, _ -> }
        builder.setTitle("Delete All users ?")
        builder.setMessage("Are you sure you want to delete all users ?")
        builder.show()
    }
}