package `in`.bitcode.fragment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    lateinit var edtUsername : EditText
    lateinit var edtPassword : EditText
    lateinit var btnLogin : Button

    //way 2


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.login_fragment_layout, null)
        btnLogin = view.findViewById(R.id.btnLogin)
        edtUsername = view.findViewById(R.id.edtusername)
        edtPassword = view.findViewById(R.id.edtPassword)


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


        return view
    }

    fun updateLogoutStatus(logoutStatus : Boolean) {
        Toast.makeText(activity, "User logged out", Toast.LENGTH_LONG).show()
        btnLogin.isEnabled = true
    }
}