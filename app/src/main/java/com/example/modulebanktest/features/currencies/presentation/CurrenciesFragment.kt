package com.example.modulebanktest.features.currencies.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.modulebanktest.R
import com.example.modulebanktest.databinding.FragmentCurrenciesBinding
import com.example.modulebanktest.features.currencies.domain.model.Valute
import com.example.modulebanktest.features.currencies.presentation.adapter.CurrenciesAdapter
import com.example.modulebanktest.utils.ViewState
import com.example.modulebanktest.utils.format
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {

    private val viewBinding: FragmentCurrenciesBinding by viewBinding(FragmentCurrenciesBinding::bind)
    private val viewModel: CurrenciesViewModel by viewModels()
    private val adapterC = CurrenciesAdapter(onValueTextChanged = { v, count ->
        viewBinding.etValue.text = (v.value * count).format(2)
        viewBinding.tvEquality.text = "${v.symbol}1 = ₽${v.value.format(2)}"
    }, onExchangedClick = { valute, int ->
        val lastFirstVisiblePosition =
            (viewBinding.rvCurrency.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        val sum = valute.value * int
        val have = valute.have - int
        viewModel.updateValute(Valute(valute.id, valute.charCode, valute.value, valute.symbol, have = have))
        viewModel.updateValute(Valute("123", "RUB", 1.00, "₽", have = sum))
        (viewBinding.rvCurrency.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(lastFirstVisiblePosition, 0)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dataBase(requireContext())
        observe()
        setupRecyclerView()
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewModel.currency.observe(viewLifecycleOwner) {
            when (it) {
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }

                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Error: ${it.throwable}", Toast.LENGTH_SHORT)
                        .show()
                }

                is ViewState.Show -> {
                    viewModel.addValute(it.data.rates)
                    readData()
                }
            }
        }
        viewModel.isVisible.observe(viewLifecycleOwner) {
            viewBinding.pbProgress.isVisible = it
            viewBinding.clCurrencies.isVisible = !it
        }

        viewModel.onExchangeClickEvent.observe(viewLifecycleOwner) {

        }
        viewModel.isVisible.observe(viewLifecycleOwner) {
            viewBinding.pbProgress.isVisible = it
            viewBinding.clCurrencies.isVisible = !it
        }
    }

    private fun readData() {
        viewModel.readAllData!!.observe(viewLifecycleOwner) {
            adapterC.submitList(it.filter {
                it.symbol != "₽"
            })
            viewBinding.rvCurrency.adapter = adapterC
        }
    }

    private fun setupRecyclerView() {
        val snapHelper: SnapHelper = PagerSnapHelper()
        viewBinding.rvCurrency.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(false)
        }
        snapHelper.attachToRecyclerView(viewBinding.rvCurrency)
    }
}