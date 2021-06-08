package `in`.bitcode.fragment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

open class UserFragment : Fragment()/*, LoginFragment.OnLoginListener*/ {

    lateinit var txtMessage : TextView
    lateinit var btnLogout : Button
    var username : String? = null

    interface OnLogoutListener {
        fun onLogout(username: String?)
    }

    var onLogoutListener : OnLogoutListener? = null
    set(value) {
        field = value
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.user_fragment_layout, null)

        txtMessage = view.findViewById(R.id.txtMessage)
        btnLogout = view.findViewById(R.id.btnLogout)

        //way 3
        btnLogout.setOnClickListener(View.OnClickListener {
           if(onLogoutListener != null) {
               onLogoutListener?.onLogout(username)
               btnLogout.visibility = View.INVISIBLE
           }
        })

        //way 3 .1
        var loginFragment : LoginFragment = fragmentManager?.findFragmentById(R.id.loginFragment) as LoginFragment
        if(loginFragment != null) {
            loginFragment.onLoginListener = UserFragLoginListener()
        }

        //way 3.2
        /*var loginFragment : LoginFragment = fragmentManager?.findFragmentById(R.id.loginFragment) as LoginFragment
        if(loginFragment != null) {
            loginFragment.onLoginListener = this
        }*/


        //way1
        /*btnLogout.setOnClickListener(View.OnClickListener {
            (activity as MainActivity).updateLogoutStatus(true)
            btnLogout.visibility = View.INVISIBLE
        })*/

        //way2
        /*
        btnLogout.setOnClickListener(View.OnClickListener {
            (fragmentManager?.findFragmentById(R.id.loginFragment) as LoginFragment)
                .updateLogoutStatus(true)
            btnLogout.visibility = View.INVISIBLE
            txtMessage.text = "Bye Bye!"
        })
         */

        return view
    }

    /*override fun onSuccess(username: String) {
        Toast.makeText(activity, "New: UserFrag: onSuccess!", Toast.LENGTH_LONG).show()
        txtMessage.text = "Welcome $username"
        btnLogout.visibility = View.VISIBLE
        this.username = username
    }

    override fun onFailure() {
        Toast.makeText(activity, "New: UserFrag: onFailure...", Toast.LENGTH_LONG).show()
        txtMessage.text = "Bye Bye..."
    }*/

    inner class UserFragLoginListener : LoginFragment.OnLoginListener {
        override fun onSuccess(name: String) {
            Toast.makeText(activity, "UserFrag: onSuccess!", Toast.LENGTH_LONG).show()
            txtMessage.text = "Welcome $username"
            btnLogout.visibility = View.VISIBLE
            username = name
        }

        override fun onFailure() {
            Toast.makeText(activity, "UserFrag: onFailure...", Toast.LENGTH_LONG).show()
            txtMessage.text = "Bye Bye..."
            btnLogout.visibility = View.GONE
        }

    }

    fun setLoginStatus(status : Boolean, username : String) {
        if(status == true) {
            txtMessage.text = "Welcome $username"
            btnLogout.visibility = View.VISIBLE
        }
        else {
            txtMessage.text = "Bye Bye $username"
        }
    }
}