package com.example.aplicacionfragmentos.fragmentos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.controler.Controller
import com.example.aplicacionfragmentos.databinding.FragmentoMainBinding

class FragmentoMain : Fragment() {
    private var _binding: FragmentoMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var controller: Controller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentoMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        initRecyclerView()
        controller = Controller(requireContext(), binding.myRecyclerView) // Pasar el RecyclerView
        controller.setAdapter()

        // Asignar OnClickListener al ImageButton
        binding.btnAdd.setOnClickListener {
            Log.d("FragmentoMain", "ImageButton pulsado")
            controller.showEditDialog(null, null)
        }

        // Asignar OnClickListener al botón button_first para navegar al SecondFragment
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Puedes realizar otras configuraciones del RecyclerView aquí
    }
}
