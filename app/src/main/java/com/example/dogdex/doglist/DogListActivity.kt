package com.example.dogdex.doglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogdex.Dog
import com.example.dogdex.R
import com.example.dogdex.databinding.ActivityDogListBinding
import com.example.dogdex.dogdetail.DogDetailActivity
import com.example.dogdex.dogdetail.DogDetailActivity.Companion.DOG_KEY

class  DogListActivity : AppCompatActivity() {

    private val dogListViewModel: DogListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recycler = binding.dogRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = DogAdapter()

        adapter.setOnItemClickListener {
            // Pasar el dog a DogDetailActivity
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.putExtra(DOG_KEY, it)
            startActivity(intent)
        }
        recycler.adapter = adapter

        dogListViewModel.dogList.observe(this){
            dogList ->
            adapter.submitList(dogList)
        }

    }

}