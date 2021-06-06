package `in`.bitcode.fragment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {

    lateinit var txtMessage : TextView
    lateinit var btnLogout : Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.user_fragment_layout, null)

        txtMessage = view.findViewById(R.id.txtMessage)
        btnLogout = view.findViewById(R.id.btnLogout)


        //way1
        /*btnLogout.setOnClickListener(View.OnClickListener {
            (activity as MainActivity).updateLogoutStatus(true)
            btnLogout.visibility = View.INVISIBLE
        })*/

        //way2
        btnLogout.setOnClickListener(View.OnClickListener {
            (fragmentManager?.findFragmentById(R.id.loginFragment) as LoginFragment)
                .updateLogoutStatus(true)
            btnLogout.visibility = View.INVISIBLE
            txtMessage.text = "Bye Bye!"
        })

        return view
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