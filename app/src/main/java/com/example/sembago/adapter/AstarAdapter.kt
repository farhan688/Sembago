import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sembago.databinding.ItemAstarBinding
import com.example.sembago.response.MatchingProductsItem
import java.text.DecimalFormat

class AstarAdapter(private val productList: MutableList<MatchingProductsItem>) :
    RecyclerView.Adapter<AstarAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemAstarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<MatchingProductsItem>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ItemAstarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: MatchingProductsItem) {
            binding.apply {
                val decimalFormat = DecimalFormat("#")

                tvNamaItem.text = product.name
                tvDistance.text = "${decimalFormat.format(product.distKm ?: 0)} km"
                tvAlamat.text = product.alamat ?: "Address not available"
                tvDescription.text = product.description ?: "Description not available"
                tvHarga.text = product.price?.toString() ?: "-"
            }
        }
    }
}
