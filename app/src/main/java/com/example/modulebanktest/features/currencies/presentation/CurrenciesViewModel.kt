package com.example.modulebanktest.features.currencies.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modulebanktest.features.currencies.data.entity.ValuteDataBase
import com.example.modulebanktest.features.currencies.data.entity.ValuteRepository
import com.example.modulebanktest.features.currencies.domain.CurrencyInteractor
import com.example.modulebanktest.features.currencies.domain.model.Currency
import com.example.modulebanktest.features.currencies.domain.model.Valute
import com.example.modulebanktest.utils.SingleLiveEvent
import com.example.modulebanktest.utils.ViewState
import com.example.modulebanktest.utils.asLiveData
import com.example.modulebanktest.utils.asViewState
import com.example.modulebanktest.utils.call
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val _isVisible = MutableLiveData(false)
    val isVisible get() = _isVisible.asLiveData()

    private val _currency = MutableLiveData<ViewState<Currency>>()
    val currency get() = _currency.asLiveData()

    private var repository: ValuteRepository? = null
    var readAllData: LiveData<List<Valute>>? = null

    val onExchangeClickEvent = SingleLiveEvent<Unit>()

    init {
        getCurrency()
    }

    fun dataBase(context: Context) {
        val valuteDao = ValuteDataBase.getDataBase(context = context).valuteDao()
        repository = ValuteRepository(valuteDao)
    }

    fun addValute(valute: List<Valute>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.addValute(Valute("123", "RUB", 1.00, "₽", 0.0))
            for (valute in valute) {
                repository!!.addValute(
                    Valute(
                        valute.id,
                        valute.charCode,
                        valute.value,
                        valute.symbol,
                        100.00
                    )
                )
            }
        }
        readAllData = repository!!.readAllData
    }

    fun updateValute(valute: Valute){
        viewModelScope.launch(Dispatchers.IO) {
            repository!!.updateValute(valute)
        }
    }

    private fun getCurrency() {
        _isVisible.value = true
        viewModelScope.launch {
            _currency.value = currencyInteractor.getCurrency().asViewState()
            _isVisible.value = false
        }
    }

    fun onExchangeClick() {
        onExchangeClickEvent.call()
    }
}