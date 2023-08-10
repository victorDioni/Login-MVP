package com.example.loginmvp.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.loginmvp.home.view.HomeActivity
import com.example.loginmvp.R
import com.example.loginmvp.login.presenter.LoginPresenter
import com.example.loginmvp.login.presenter.LoginContract
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var loginInput : TextInputEditText
    lateinit var passwordInput : TextInputEditText
    lateinit var btnLogin : Button

    override lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Bind do Presenter com a nossa View
        presenter = LoginPresenter(this)

        presenter.start() // Chama o método responsável por dizer a View o que deve ser inicializado.

        loginInput = findViewById(R.id.et_login)
        passwordInput = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            //Comunica e Transfere a responsabilidade do Login para o Presenter
            //Que então irá validar se o Login é válido ou não.
            presenter.isLoginValid(loginInput.text.toString(), passwordInput.text.toString())
        }
    }

    override fun displayErrorMessage() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        loginInput.error = "Login Failed!"
        passwordInput.error = "Login Failed!"
    }

    override fun displaySucessToast() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
    }

    override fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun bindViews() {
        loginInput = findViewById(R.id.et_login)
        passwordInput = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.buttonLogin)
    }
}