package com.latemen.upventure

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.airbnb.epoxy.Carousel
import com.latemen.upventure.databinding.ActivityMainBinding
import com.latemen.upventure.model.ui.UiProduct
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var store: Store<ApplicationState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.productsListFragment,
                R.id.profileFragment,
                R.id.cartFragment
            )
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfiguration)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        Carousel.setDefaultGlobalSnapHelperFactory(null)

        store.stateFlow.map {
            it.inCartProductIds.size
        }.distinctUntilChanged().asLiveData().observe(this) { numberOfProductsInCart ->
            binding.bottomNavigationView.getOrCreateBadge(R.id.cartFragment).apply {
                backgroundColor = Color.parseColor("#ff80cbc4")
                number = numberOfProductsInCart
                isVisible = numberOfProductsInCart > 0
            }
        }
    }

}