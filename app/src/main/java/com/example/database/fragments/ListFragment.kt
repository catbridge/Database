package com.example.database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.database.adapter.CarAdapter
import com.example.database.carDatabase
import com.example.database.databinding.FragmentListBinding
import com.example.database.room.Car

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter = CarAdapter(){
        requireContext().carDatabase.carDao().delete(it)
        updateCars()
    }

    override fun onResume() {
        super.onResume()
        updateCars()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.adapter = adapter
            updateCars()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadCars(): List<Car>{
        return requireContext().carDatabase.carDao().getCars()
    }

    private fun updateCars(){
        return adapter.submitList(loadCars())
    }
}