package `in`.bitcode.fragment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var userFragment : UserFragment
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    inner class MainActLoginListener : LoginFragment.OnLoginListener {
        override fun onSuccess(userName: String) {
           mt("MainActivity : Login Success!");
        }

        override fun onFailure() {
            mt("MainActivity : Login Failed...");
        }

    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


    private fun init() {
        loginFragment = supportFragmentManager.findFragmentById(R.id.loginFragment) as LoginFragment
        userFragment = supportFragmentManager.findFragmentById(R.id.userFragment) as UserFragment

        //loginFragment.onLoginListener = MainActLoginListener()
        //userFragment.onLogoutListener = MainActLogoutListener()

        //Way 3.2
        //loginFragment.onLoginListener = userFragment

        userFragment.onLogoutListener = loginFragment
    }

    inner class MainActLogoutListener : UserFragment.OnLogoutListener {
        override fun onLogout(username: String?) {
            loginFragment.updateLogoutStatus(true)
            mt("MainAct : onLogout")
        }
    }


    //way1
    fun updateLoginStatus(status: Boolean, username : String) {
        userFragment.setLoginStatus(status, username)
    }

    //way1
    fun updateLogoutStatus(logoutStatus: Boolean) {
        loginFragment.updateLogoutStatus(logoutStatus)
    }
}