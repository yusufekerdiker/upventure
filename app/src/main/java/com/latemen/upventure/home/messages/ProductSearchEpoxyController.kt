package com.latemen.upventure.home.messages
/*
import coil.Coil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelProductItemBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.model.domain.Product
import kotlinx.coroutines.ObsoleteCoroutinesApi
import java.math.BigDecimal

@ObsoleteCoroutinesApi
class ProductSearchEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
) : TypedEpoxyController<Product>() {

    var localException: ProductSearchPagingSource.LocalException? = null
        set(value) {
            field = value
            if (localException != null) {
                requestForcedModelBuild()
            }
        }

    override fun buildItemModel(currentPosition: Int, product: Product?): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(
            title = product!!.title,
            price = product!!.price,
            description = product!!.description,
            image = product!!.image,
            category = product!!.title,
            onCharacterSelected = { characterId ->
                onCharacterSelected(characterId)
            }
        ).id(product.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {

        localException?.let {
            LocalExceptionErrorStateEpoxyModel(this).id("error_state").addTo(this)
            return@let
        }

        if (models.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        super.addModels(models)
    }
    data class CharacterGridItemEpoxyModel(
        val title: String,
        val price: BigDecimal,
        val description: String,
        val image: String,
        val category: String,
        val onCharacterSelected: (Int) -> Unit
    ) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

        override fun EpoxyModelProductItemBinding.bind() {
            Coil.imageLoader(root.context).load(imageUrl).into(characterImageView)
            productTitleTextView.text = title
            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }

    data class LocalExceptionErrorStateEpoxyModel(
        val localException: CharacterSearchPagingSource.LocalException
    ) : ViewBindingKotlinModel<ModelLocalExceptionErrorStateBinding>(R.layout.model_local_exception_error_state) {

        override fun ModelLocalExceptionErrorStateBinding.bind() {
            titleTextView.text = localException.title
            descriptionTextView.text = localException.description
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }
}*/
