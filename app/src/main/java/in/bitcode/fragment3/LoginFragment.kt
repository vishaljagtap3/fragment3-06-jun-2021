package `in`.bitcode.fragment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

open class LoginFragment : Fragment(),  UserFragment.OnLogoutListener{

    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button

    //way 3 ***
    interface OnLoginListener {
        fun onSuccess(userName: String)
        fun onFailure()
    }

    var onLoginListener: OnLoginListener? = null
        set(value) {
            field = value
        }


    override fun onLogout(username: String?) {
        btnLogin.isEnabled = true
        Toast.makeText(activity, "LoginFrag: onLogout!", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.login_fragment_layout, null)
        btnLogin = view.findViewById(R.id.btnLogin)
        edtUsername = view.findViewById(R.id.edtusername)
        edtPassword = view.findViewById(R.id.edtPassword)

        //way 3
        btnLogin.setOnClickListener(View.OnClickListener {
            if (onLoginListener != null) {
                if (edtUsername.text.toString().equals("bitcode") && edtPassword.text.toString()
                        .equals("bitcode")
                ) {
                    onLoginListener?.onSuccess(edtUsername.text.toString())
                    btnLogin.isEnabled = false
                } else {
                    onLoginListener?.onFailure()
                }
            }
        })

        //3.1
        //var userFragment : UserFragment = fragmentManager?.findFragmentById(R.id.userFragment) as UserFragment
        //userFragment.onLogoutListener = LoginFragLogoutListener()


        //way 1
        /*
        btnLogin.setOnClickListener(View.OnClickListener {
            if(edtUsername.text.toString().equals("bitcode") && edtPassword.text.toString().equals("bitcode")) {
                (activity as MainActivity).updateLoginStatus(true, edtUsername.text.toString())
                btnLogin.isEnabled = false
            }
            else {
                (activity as MainActivity).updateLoginStatus(false, edtUsername.text.toString())
            }
        });
        */

        //way 2
        /*
        btnLogin.setOnClickListener(View.OnClickListener {
            if(edtUsername.text.toString().equals("bitcode") && edtPassword.text.toString().equals("bitcode")) {
                (fragmentManager?.findFragmentById(R.id.userFragment) as UserFragment)
                    .setLoginStatus(true, edtUsername.text.toString())
                btnLogin.isEnabled = false
            }
            else {
                (fragmentManager?.findFragmentById(R.id.userFragment) as UserFragment)
                    .setLoginStatus(false, edtUsername.text.toString())
            }
        });
         */


        return view
    }

    inner class LoginFragLogoutListener : UserFragment.OnLogoutListener {
        override fun onLogout(username: String?) {
            btnLogin.isEnabled = true
        }
    }

    fun updateLogoutStatus(logoutStatus: Boolean) {
        Toast.makeText(activity, "User logged out", Toast.LENGTH_LONG).show()
        btnLogin.isEnabled = true
    }
}