package com.phoenix.myapplication

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.phoenix.myapplication.R
import kotlinx.android.synthetic.main.start_fragment.*

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private lateinit var viewModel: StartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.start_fragment, container, false)
        view.findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
            viewModel.doAction()
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
        //Use the ViewModel
        viewModel.getMessageStr().observe(this, Observer {
            message.text = it
        })
    }
}
