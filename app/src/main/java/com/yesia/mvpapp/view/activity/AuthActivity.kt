package com.yesia.mvpapp.view.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.imastudio.customerapp.helper.SessionManager
import com.yesia.mvpapp.MainActivity
import com.yesia.mvpapp.R
import com.yesia.mvpapp.model.modelAuth.User
import com.yesia.mvpapp.presenter.AuthContract
import com.yesia.mvpapp.presenter.AuthPresenter
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.android.synthetic.main.layout_register.view.*
import kotlinx.android.synthetic.main.layout_register.view.regAdmin
import kotlinx.android.synthetic.main.layout_register.view.regUserbiasa
import kotlinx.android.synthetic.main.login.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AuthActivity : AppCompatActivity(), AuthContract.View, AdapterView.OnItemSelectedListener {


    var dataJenkel = arrayOf("laki-laki", "perempuan")
    var strJenkel: String? = null
    var strLevel: String? = null
    var progressDialog: ProgressDialog? = null

    lateinit var dialog: AlertDialog
    lateinit var session: SessionManager
    lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        progressDialog = ProgressDialog(this)
        session = SessionManager(this)
        initPrensenter()

        btnRegister.onClick {
            register()
        }

        btnSignIn.onClick {
            login()
        }
    }

    private fun initPrensenter() {
        presenter = AuthPresenter()
    }

    private fun login() {
        var alertRegister = AlertDialog.Builder(this)
        alertRegister.setTitle("Login")
        alertRegister.setMessage("Masukan Data Diri, Jika belum Pernah Register, Klik Tombol Register")
        var v: View = layoutInflater.inflate(R.layout.login, null)

        alertRegister.setView(v)
        alertRegister.setPositiveButton("Login", null)
        alertRegister.setNegativeButton("Register", null)

        dialog = alertRegister.create()
        dialog.show()

        v.logAdmin.onClick {
            strLevel = "Admin"
        }
        v.logUserbiasa.onClick {
            strLevel = "user biasa"

        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).onClick {
            register()
        }

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).onClick {
            var email = v.logUsername.text.toString()
            var password = v.logPass.text.toString()

            if (TextUtils.isEmpty(email)) v.logUsername.error = "Tidak Boleh Kosong"
            else if (TextUtils.isEmpty(password)) v.logPass.error = "Tidak Boleh Kosong"
            else presenter.prosesLogin(
                email, password, strLevel
            )


        }
    }

    private fun register() {
        var alertRegister = AlertDialog.Builder(this)
        alertRegister.setTitle("Register")
        alertRegister.setMessage("Silahkan Isi Data Diri Anda")
        var v: View = layoutInflater.inflate(R.layout.layout_register, null)

        alertRegister.setView(v)
        alertRegister.setPositiveButton("register", null)
        alertRegister.setNegativeButton("Login", null)

        dialog = alertRegister.create()
        dialog.show()
        //get nilai spinner jenkel
        getJenkel(v)
//        getlevel(v)
        v.regAdmin.onClick {
            strLevel = "Admin"
        }
        v.regUserbiasa.onClick {
            strLevel = "User biasa"
        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).onClick {
            login()
        }

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).onClick {
            var email = v.edtusername.text.toString()
            var nama = v.edtnama.text.toString()
            var nohp = v.edtnotelp.text.toString()
            var usia = v.edtusia.text.toString()
            var alamat = v.edtalamat.text.toString()
            var password = v.edtpassword.text.toString()
            var repassword = v.edtpasswordconfirm.text.toString()

            //cek if kosong

            if (TextUtils.isEmpty(email)) v.edtpassword.error = "Username Tidak Boleh Kosong"
            else if (TextUtils.isEmpty(nama)) v.edtnama.error = "Nama Tidak Boleh Kosong"
            else if (TextUtils.isEmpty(nohp)) v.edtnotelp.error = "No Hp Tidak Boleh Kosong"
            else if (TextUtils.isEmpty(usia)) v.edtnotelp.error = "Usia Tidak Boleh Kosong"
            else if (TextUtils.isEmpty(alamat)) v.edtalamat.error = "Alamat Tidak Boleh Kosong"
            else if (TextUtils.isEmpty(password)) v.edtnotelp.error = "Password Tidak Boleh Kosong"
            else if (!password.equals(repassword)) v.edtpasswordconfirm.error =
                "Password Tidak Sama"
            else presenter.prosesRegister(
                email,
                nama,
                nohp,
                alamat,
                password,
                strJenkel.toString(),
                usia,
                strLevel
            )
        }
    }

    private fun getlevel(v: View) {

        if (regAdmin.isChecked)
            strLevel = "Admin"
        else strLevel = "User biasa"

    }

    private fun getJenkel(v: View): String? {
        ArrayAdapter.createFromResource(
            this,
            R.array.jenkel,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            v.spinjenkel.adapter = adapter
            v.spinjenkel.onItemSelectedListener = this
        }
        return strJenkel
    }

    override fun showLoading() {
        progressDialog?.setTitle("Register")
        progressDialog?.setMessage("Loading...")
        progressDialog?.show()
    }

    override fun hideLoding() {
        progressDialog?.dismiss()
    }

    override fun hideDialog() {
        dialog.dismiss()
    }

    override fun showError(toString: String) {
        toast(toString)
    }

    override fun pindahHalaman(dataLogin: User?) {

        session.createLoginSession(dataLogin?.username)
        session.iduser = dataLogin?.idUser.toString()
        startActivity<MainActivity>()
        finish()
    }

    override fun onBackPressed() {
        alert("Keluar Aplikasi !","Apakah Anda Yakin?") {
            yesButton { System.exit(0) }
            noButton {  }
        }.show()
    }
    override fun showMsg(msg: String?) {
        toast(msg.toString())
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        strJenkel = p0?.getItemAtPosition(p2).toString()
    }
}
