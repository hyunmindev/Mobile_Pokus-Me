package com.jhm.android.pokusme.ui.drawer.profileEdit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.jhm.android.pokusme.MainActivity
import com.jhm.android.pokusme.R
import com.jhm.android.pokusme.data.UserData
import com.jhm.android.pokusme.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile_edit.*


class ProfileEditFragment : Fragment() {
    private lateinit var profileEditViewModel: ProfileEditViewModel
    private lateinit var currentUser: UserData
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile_edit, container, false)
    
        profileEditViewModel = ViewModelProvider(this).get(ProfileEditViewModel::class.java)
        profileEditViewModel.displayName.observe(viewLifecycleOwner, Observer {
            text_profile_name.text = it
        })
        profileEditViewModel.email.observe(viewLifecycleOwner, Observer {
            text_profile_email.text = it
        })
    
        val mainActivity: MainActivity? = activity as MainActivity?
        currentUser = mainActivity!!.currentUser!!
        updateUserData()
    
        val buttonSignOut = view.findViewById(R.id.button_profile_signOut) as ImageButton
        val buttonEdit = view.findViewById(R.id.button_profile_edit) as ImageButton
        
        buttonSignOut.setOnClickListener { signOut() }
        buttonEdit.setOnClickListener { Log.d("jhmlog", "edit") }

        return view
    }
    
    private fun updateUserData() {
        profileEditViewModel.displayName.value = this.currentUser.displayName
        profileEditViewModel.email.value = this.currentUser.email
        profileEditViewModel.isEmailVerified.value = this.currentUser.isEmailVerified
    }
    
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(activity, LoginActivity::class.java))
        activity?.finish()
    }
}