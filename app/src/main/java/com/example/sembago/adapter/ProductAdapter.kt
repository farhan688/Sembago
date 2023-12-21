package com.example.sembago.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sembago.ui.activity.DetailProdukActivity
import com.example.sembago.ui.fragment.HomeFragment
import com.example.sembago.R
import com.example.sembago.response.ProductItem

class ProductAdapter(
    private val context: HomeFragment,
    private val dataList: ArrayList<ProductItem>
):RecyclerView.Adapter<ProductAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaProduk = view.findViewById<TextView>(R.id.tvNamaItem)
        val tvSellerName = view.findViewById<TextView>(R.id.tvSellerName)
        val tvAlamat = view.findViewById<TextView>(R.id.tvAlamat)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)

        val cvProduct = view.findViewById<CardView>(R.id.cvProduct)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_product, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvNamaProduk.text = dataList.get(position).title
        holder.tvSellerName.text = dataList.get(position).sellerName
        holder.tvAlamat.text = dataList.get(position).alamat
        holder.tvHarga.text = dataList.get(position).price

        holder.cvProduct.setOnClickListener {
            val productId = dataList[position].productId

            val intent = Intent(context.requireContext(), DetailProdukActivity::class.java)
            intent.putExtra("PRODUCT_ID", productId) // Kirim data productId ke DetailProdukActivity jika diperlukan

            context.requireContext().startActivity(intent)
        }


//        holder.cvProduct.setOnClickListener{
//            Toast.makeText(context.requireContext(), "" + dataList[position].productId, Toast.LENGTH_SHORT).show()
//
//        }
    }

    override fun getItemCount(): Int = dataList.size
    fun setData(newList: ArrayList<ProductItem>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

}

//class ProductAdapter(
//    private val dataList: ArrayList<ProductItem>
//) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
//
//    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val tvNamaProduk = view.findViewById<TextView>(R.id.tvNamaItem)
//        val tvSellerName = view.findViewById<TextView>(R.id.tvSellerName)
//        val tvAlamat = view.findViewById<TextView>(R.id.tvAlamat)
//        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
//
//        val cvProduct = view.findViewById<CardView>(R.id.cvProduct)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val itemView = layoutInflater.inflate(R.layout.item_product, parent, false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tvNamaProduk.text = dataList[position].title
//        holder.tvSellerName.text = dataList[position].sellerName
//        holder.tvAlamat.text = dataList[position].alamat
//        holder.tvHarga.text = dataList[position].price
//
//        holder.cvProduct.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "" + dataList[position].productId, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun getItemCount(): Int = dataList.size
//
//    fun setData(newList: ArrayList<ProductItem>) {
//        dataList.clear()
//        dataList.addAll(newList)
//        notifyDataSetChanged()
//    }
//}

