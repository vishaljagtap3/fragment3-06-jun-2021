package `in`.bitcode.fragment3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    lateinit var userFragment : UserFragment
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        loginFragment = supportFragmentManager.findFragmentById(R.id.loginFragment) as LoginFragment
        userFragment = supportFragmentManager.findFragmentById(R.id.userFragment) as UserFragment
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