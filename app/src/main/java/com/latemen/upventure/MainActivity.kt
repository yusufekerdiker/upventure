package com.latemen.upventure

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.latemen.upventure.databinding.ActivityMainBinding
import com.latemen.upventure.hilt.service.ProductsService
import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.model.mapper.ProductMapper
import com.latemen.upventure.model.network.NetworkProduct
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productsService: ProductsService

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var productMapper: ProductMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = ProductEpoxyController()
        binding.epoxyRecyclerView.setController(controller)
        controller.setData(emptyList())

        lifecycleScope.launchWhenStarted {

            val response: Response<List<NetworkProduct>> = productsService.getAllProducts()
            val domainProducts: List<Product> = response.body()?.map {
                productMapper.buildFrom(networkProduct = it)
            } ?: emptyList()

            controller.setData(domainProducts)

            if (domainProducts.isEmpty()) {
                Snackbar.make(binding.root, "Failed to fetch", Snackbar.LENGTH_LONG).show()
            }
        }
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