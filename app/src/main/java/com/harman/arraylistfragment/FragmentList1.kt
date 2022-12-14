package com.harman.arraylistfragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.harman.arraylistfragment.databinding.AddItemBinding
import com.harman.arraylistfragment.databinding.FragmentList1Binding
import com.harman.arraylistfragment.databinding.FragmentList1Binding.*
import com.harman.arraylistfragment.databinding.UpdateItemBinding
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList1.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var listFrag :MainActivity
    lateinit var binding :FragmentList1Binding
    var arrayList : ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        listFrag = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentList1Binding.inflate(layoutInflater)
        var adapter =ArrayAdapter(listFrag,android.R.layout.simple_list_item_1,arrayList)
        arrayList.add("hey")
        arrayList.add("hello 1")
        arrayList.add("pay 2")
        arrayList.add("put 3")
        binding.list.adapter = adapter
        binding.btnFab.setOnClickListener {
            var dialogBinding=AddItemBinding.inflate(layoutInflater)
            var dialog = Dialog(listFrag)
            dialog.setCancelable(false)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnAdd.setOnClickListener {
                if (dialogBinding.etAdd.text.toString().isNullOrEmpty()) {
                    dialogBinding.etAdd.setError("Please Add Item")
                } else {
                    arrayList.add(dialogBinding.etAdd.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        binding.list.setOnItemClickListener { adapterView, view, i, l ->
            var dialogBinding = UpdateItemBinding.inflate(layoutInflater)
            var dialog = Dialog(listFrag)
            dialog.setCancelable(false)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.etUpdate.setText(arrayList[i])

            dialogBinding.btnUpdate.setOnClickListener {
                if (dialogBinding.etUpdate.text.toString().isNullOrEmpty()) {
                    dialogBinding.etUpdate.setError("enter item")
                } else {
                    arrayList.set(i, dialogBinding.etUpdate.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentList1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentList1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}