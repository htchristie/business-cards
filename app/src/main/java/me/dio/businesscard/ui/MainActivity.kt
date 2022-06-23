package me.dio.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import me.dio.businesscard.App
import me.dio.businesscard.databinding.ActivityMainBinding
import me.dio.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCards()
        share()
        fabListener()
    }

    private fun fabListener() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun share() {
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun getAllBusinessCards() {
        mainViewModel.getAll().observe(this) {
            adapter.submitList(it)
        }
    }
}