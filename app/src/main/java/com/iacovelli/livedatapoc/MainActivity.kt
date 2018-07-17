package com.iacovelli.livedatapoc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.transition.Transition
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.iacovelli.livedatapoc.databinding.ActivityMainBinding
import com.iacovelli.livedatapoc.list.ListFragment
import com.iacovelli.livedatapoc.login.LoginFragment

class MainActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.setLifecycleOwner(this)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setupUI(viewModel)
        goToScreen(Screen.LOGIN)
    }

    private fun setupUI(viewModel: MainViewModel) {
        viewModel.nextScreen.observe(this, Observer {
            it?.getContent()?.let {
                goToScreen(it)
            }
        })
    }

    private fun goToScreen(screen: Screen, add: Boolean = false) {
        val fragment = when(screen) {
            Screen.LOGIN -> LoginFragment()
            Screen.LIST -> ListFragment()
        }
        Log.d("debug", "go to screen ${screen.name}")
        val supportFragmentManager = supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

        if (add) {
            supportFragmentManager.add(R.id.container, fragment)
                    .commit()
        } else {
            supportFragmentManager.replace(R.id.container, fragment)
                    .addToBackStack(fragment.javaClass.name)
                    .commit()
        }
    }
}
