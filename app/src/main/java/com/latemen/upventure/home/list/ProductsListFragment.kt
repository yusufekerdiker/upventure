package com.latemen.upventure.home.list

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyTouchHelper
import com.latemen.upventure.R
import com.latemen.upventure.databinding.FragmentProductsListBinding
import com.latemen.upventure.home.cart.epoxy.CartItemEpoxyModel
import com.latemen.upventure.model.domain.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.math.max

@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding by lazy { _binding!! }

    private val viewModel: ProductsListViewModel by viewModels()

    @Inject
    lateinit var uiStateGenerator: ProductsListFragmentUiStateGenerator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


/*        val myProduct = Product(
            "electronic",
            "lorem ipsum set",
            12321,
            "https://i.pravatar.cc",
            BigDecimal(13.50),
            "test product",
            Product.Rating(4.1, 210)
        )
        viewModel.pushProduct(myProduct)
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message().toString())
            }
        })*/

        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = UiProductEpoxyController(viewModel)
        binding.epoxyRecyclerView.setController(controller)

        combine(
            viewModel.uiProductListReducer.reduce(viewModel.store),
            viewModel.store.stateFlow.map { it.productFilterInfo },
        ) { uiProducts, productFilterInfo ->
            uiStateGenerator.generate(uiProducts, productFilterInfo)
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { uiState ->
            controller.setData(uiState)
        }
        // todo
//        setupSwipeToFavorite()
        viewModel.refreshProducts()
    }

    // todo
    /*    private fun setupSwipeToFavorite() {

            //Problem
            EpoxyTouchHelper
                .initSwiping(binding.epoxyRecyclerView)
                .right()
                .withTarget(UiProductEpoxyModel::class.java)
                .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<UiProductEpoxyModel>() {
                    override fun onSwipeCompleted(
                        model: UiProductEpoxyModel?,
                        itemView: View?,
                        position: Int,
                        direction: Int
                    ) {
                        model?.let { epoxyModel ->
                            viewModel.viewModelScope.launch {
                                viewModel.store.update {
                                    return@update viewModel.uiProductFavoriteUpdater.update(
                                        productId = epoxyModel.uiProduct!!.product.id,
                                        currentState = it
                                    )
                                }
                            }
                        }
                    }
                    override fun onSwipeProgressChanged(
                        model: UiProductEpoxyModel?,
                        itemView: View?,
                        swipeProgress: Float,
                        canvas: Canvas?
                    ) {
                        itemView?.findViewById<View>(R.id.swipeToFavoriteTextView)?.apply {
                            translationX = max(-itemView.translationX, -measuredWidth.toFloat())
                            alpha = 3f * swipeProgress
                        }
                    }
                })

        }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}