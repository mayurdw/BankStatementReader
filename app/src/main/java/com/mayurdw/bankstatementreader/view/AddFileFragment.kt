package com.mayurdw.bankstatementreader.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mayurdw.bankstatementreader.R

class AddFileFragment : Fragment() {

    companion object {
        fun newInstance() = AddFileFragment()
    }

    private lateinit var viewModel: AddFileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_file, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddFileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}