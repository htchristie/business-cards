package me.dio.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import me.dio.businesscard.App
import me.dio.businesscard.R
import me.dio.businesscard.data.BusinessCard
import me.dio.businesscard.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = getString(R.string.add_card)
        saveCard()
    }

    private fun saveCard() {
        binding.btnSave.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                surname = binding.tilSurname.editText?.text.toString(),
                organization = binding.tilOrganization.editText?.text.toString(),
                jobTitle = binding.tilJobTitle.editText?.text.toString(),
                phone = binding.tilPhone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_success, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}