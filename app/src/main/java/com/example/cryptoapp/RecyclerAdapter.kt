package info.gopaisa.cryptoapp

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R


class RecyclerAdapter(val prodList : ArrayList<StockPrice>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_single_row,parent,false)
        return ViewHolder(view,prodList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.prodTitle.text = prodList.get(position).symbol
        holder.prodPrice.text = prodList.get(position).quoteAsset
        holder.prodDesc.text = prodList.get(position).baseAsset
    }

    override fun getItemCount(): Int {
        return prodList.size
    }

    class ViewHolder(itemView: View, prodList : ArrayList<StockPrice>) : RecyclerView.ViewHolder(itemView) {
        val prodTitle: TextView = itemView.findViewById(R.id.crypto_symbol)
        val prodPrice: TextView = itemView.findViewById(R.id.quote_asset)
        val prodDesc: TextView = itemView.findViewById(R.id.base_asset)

        init {
            itemView.setOnClickListener(View.OnClickListener {
                val dialog  = Dialog(itemView.context)
                val view : View = (dialog.layoutInflater)?.inflate(R.layout.custom_dialog, null)

                val askPrice = view.findViewById<TextView>(R.id.ask_price)
                askPrice.text = "${askPrice.text} ${prodList.get(layoutPosition).askPrice}"
                val at = view.findViewById<TextView>(R.id.at)
                at.text = "${at.text} ${prodList.get(layoutPosition).at}"
                val baseAsset = view.findViewById<TextView>(R.id.base_asset)
                baseAsset.text = "${baseAsset.text} ${prodList.get(layoutPosition).baseAsset}"
                val bidAsset = view.findViewById<TextView>(R.id.bid_price)
                bidAsset.text = "${bidAsset.text} ${prodList.get(layoutPosition).bidPrice}"
                val highPrice = view.findViewById<TextView>(R.id.high_price)
                highPrice.text = "${highPrice.text} ${prodList.get(layoutPosition).highPrice}"
                val lastPrice = view.findViewById<TextView>(R.id.last_price)
                lastPrice.text = "${lastPrice.text} ${prodList.get(layoutPosition).lastPrice}"
                val lowPrice = view.findViewById<TextView>(R.id.low_price)
                lowPrice.text = "${lowPrice.text} ${prodList.get(layoutPosition).lowPrice}"
                val openPrice = view.findViewById<TextView>(R.id.open_price)
                openPrice.text = "${openPrice.text} ${prodList.get(layoutPosition).openPrice}"
                val quoteAsset = view.findViewById<TextView>(R.id.quote_asset)
                quoteAsset.text = "${quoteAsset.text} ${prodList.get(layoutPosition).quoteAsset}"
                val symbol = view.findViewById<TextView>(R.id.symbol)
                symbol.text = "${symbol.text} ${prodList.get(layoutPosition).symbol}"
                val volume = view.findViewById<TextView>(R.id.volume)
                volume.text = "${volume.text} ${prodList.get(layoutPosition).volume}"


                dialog.setContentView(view)
                dialog.window?.setBackgroundDrawable(itemView.context.getDrawable(R.drawable.dialog_background))
                dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog.setCancelable(false)
                dialog.setCanceledOnTouchOutside(true)
                dialog.show()
            })
        }
    }
}