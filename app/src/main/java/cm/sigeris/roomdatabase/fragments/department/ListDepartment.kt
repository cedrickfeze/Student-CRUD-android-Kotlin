package cm.sigeris.roomdatabase.fragments.department

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cm.sigeris.roomdatabase.R
import cm.sigeris.roomdatabase.viewmodel.DepartmentViewModel
import kotlinx.android.synthetic.main.fragment_list_department.view.*

class ListDepartment : Fragment() {
    private lateinit var mDepartmentViewModel: DepartmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_department, container, false)

        mDepartmentViewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
        val adapter = DepartmentAdapter()
        view.departmentrecyclerView.adapter = adapter
        view.departmentrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mDepartmentViewModel.readAllData.observe(viewLifecycleOwner, Observer { department->
            adapter.setData(department)
        })

        view.departmentActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listDepartment_to_addDepartment)
        }
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.delete_all ->{
                deleteAllDepartments()
                return true
            }

            R.id.level ->{
                Toast.makeText(requireContext(), "level pressed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listDepartment_to_listLevel)
                return true
            }

            R.id.enrollement ->{
                Toast.makeText(requireContext(), "enrollement pressed", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.specialty ->{
                Toast.makeText(requireContext(), "specialty pressed", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.student ->{
                Toast.makeText(requireContext(), "specialty pressed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_listDepartment_to_listFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllDepartments() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mDepartmentViewModel.deleteAllDepartments()
            Toast.makeText(requireContext(), "successfully deleted", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("no") { _, _ -> }
        builder.setTitle("Delete All Departments ?")
        builder.setMessage("Are you sure you want to delete all departments ?")
        builder.show()
    }




}