package com.example.database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.database.carDatabase
import com.example.database.databinding.FragmentInputDataBinding
import com.example.database.room.Car

class InputDataFragment : Fragment() {
    private var _binding: FragmentInputDataBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val carDao by lazy {
        requireContext().carDatabase.carDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentInputDataBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonAddCar.setOnClickListener {
                val carBrandStr = editTextBrand.text
                val carModelStr = editTextModel.text
                when {
                    editTextModel.text.toString().isBlank() && editTextBrand.text.toString().isBlank() -> {
                        textModelContainer.error = "Text is empty"
                        editTextBrand.error = "Text is empty"
                    }
                    editTextBrand.text.toString().isBlank()  -> {
                        editTextBrand.error = "Text is empty"
                        textModelContainer.error = null
                    }
                    editTextModel.text.toString().isBlank() -> {
                        textModelContainer.error = "Text is empty"
                        textBrandContainer.error = null
                    }
                    else -> {
                        createCar(carBrandStr.toString(), carModelStr.toString())
                        textModelContainer.error = null
                        textBrandContainer.error = null
                        editTextBrand.text = null
                        editTextModel.text = null
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private  fun createCar(carBrandStr: String, carModelStr: String) {
        carDao.insert(
            Car(carBrand = carBrandStr, carModel = carModelStr)
        )
    }
}