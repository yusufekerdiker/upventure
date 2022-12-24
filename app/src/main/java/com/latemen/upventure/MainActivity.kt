package com.latemen.upventure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.latemen.upventure.databinding.ActivityMainBinding
import com.latemen.upventure.model.ui.UiProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = UiProductEpoxyController(viewModel)
        binding.epoxyRecyclerView.setController(controller)
        controller.setData(emptyList())

        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favoriteProductIds }
        ) { listOfProducts, setOfFavoriteIds ->
            listOfProducts.map { product ->
                UiProduct(product = product, isFavorite = setOfFavoriteIds.contains(product.id))
            }
        }.distinctUntilChanged().asLiveData().observe(this) { uiProducts ->
            controller.setData(uiProducts)
        }

        viewModel.refreshProducts()
    }

    /*    private fun refreshData() {
            lifecycleScope.launchWhenStarted {
                binding.productImageViewLoadingProgressBar.isVisible = true
                val response = productService.getAllProducts()
                binding.productImageView.load(
                    data = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ) {
                    listener { request, result ->
                        binding.productImageViewLoadingProgressBar.isGone = true
                    }
                }
                Log.i("DATA", response.body()!!.toString())
            }
        }*/

    private fun setupListeners() {
        /*            binding.cardView.setOnClickListener {
                        binding.productDescriptionTextView.apply {
                            isVisible = !isVisible
                        }
                    }

                    binding.addToCartButton.setOnClickListener {
                        binding.inCartView.apply {
                            isVisible = !isVisible
                        }
                    }

                    var isFavourite = false
                    binding.favoriteImageView.setOnClickListener {
                        val imageRes = if (isFavourite) {
                            R.drawable.baseline_favorite_border_24
                        } else {
                            R.drawable.baseline_favorite_24
                        }
                        binding.favoriteImageView.setIconResource(imageRes)
                        isFavourite = !isFavourite
                    }*/
    }

}