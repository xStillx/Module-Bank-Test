package com.example.modulebanktest.features.currencies.presentation.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.modulebanktest.R
import com.example.modulebanktest.databinding.ItemCurrencyBinding
import com.example.modulebanktest.features.currencies.domain.model.Valute
import com.example.modulebanktest.utils.format

class CurrenciesAdapter(
    private val onValueTextChanged: (Valute, Int) -> Unit,
    private val onExchangedClick: (Valute, Int) -> Unit
) :
    ListAdapter<Valute, CurrenciesAdapter.CurrenciesViewHolder>(CurrenciesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
        CurrenciesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewBinding: ItemCurrencyBinding by viewBinding(ItemCurrencyBinding::bind)

        fun bind(valute: Valute) {
            viewBinding.tvCurrencyName.text = valute.charCode
            viewBinding.tvHave.text = "You have: ${valute.have}${valute.symbol}"
            viewBinding.tvEquality.text = "${valute.symbol}1 = ₽${valute.value.format(2)}"
            viewBinding.tvExchange.setOnClickListener {
                val value = viewBinding.etValue.text.toString().toDouble()
                if (value < valute.have){
                    onExchangedClick.invoke(valute, viewBinding.etValue.text.toString().toInt())
                }

            }
            viewBinding.etValue.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(viewBinding.etValue.text.toString() != ""){
                        onValueTextChanged.invoke(valute, viewBinding.etValue.text.toString().toInt())
                    }else{
                        onValueTextChanged.invoke(valute, 0)
                    }

                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
    }

    class CurrenciesDiffUtil : DiffUtil.ItemCallback<Valute>() {
        override fun areItemsTheSame(oldItem: Valute, newItem: Valute): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Valute, newItem: Valute): Boolean {
            return oldItem == newItem
        }
    }
}
