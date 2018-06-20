package com.iacovelli.livedatapoc.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iacovelli.livedatapoc.R
import com.iacovelli.livedatapoc.databinding.LoginFragmentBinding
import android.view.inputmethod.InputMethodManager
import com.iacovelli.livedatapoc.MainViewModel
import com.iacovelli.livedatapoc.Screen


class LoginFragment: Fragment() {
    private lateinit var dataBinding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        dataBinding.setLifecycleOwner(this)

        val factory = LoginViewModel.Factory()
        val loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        dataBinding.viewModel = loginViewModel

        val mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        setupUI(loginViewModel, mainViewModel)
        return dataBinding.root
    }

    private fun setupUI(loginViewModel: LoginViewModel, mainViewModel: MainViewModel) {
        loginViewModel.message.observe(this, Observer {
            it?.getContent()?.let {
                hideKeyboard()
                Snackbar.make(dataBinding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })

        loginViewModel.userLogged.observe(this, Observer {
            it?.getContent()?.let {
                Log.d("debug", "User logged")
                mainViewModel.goToScreen(Screen.LIST)
            }
        })
    }

    private fun hideKeyboard() {
        activity?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.currentFocus.windowToken, 0)
        }

    }
}