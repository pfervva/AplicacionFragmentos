package com.example.aplicacionfragmentos.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionfragmentos.controler.Controller
import com.example.aplicacionfragmentos.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var controller: Controller

    // Esta propiedad solo es válida entre onCreateView y onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        controller = Controller(requireContext()) // Creamos el controlador
        controller.setAdapter(binding.myRecyclerView) // Pasar RecyclerView aquí

        // Asignar OnClickListener al ImageButton
        binding.btnAdd.setOnClickListener {
            // Aquí colocas la lógica que deseas ejecutar cuando se hace clic en el botón.
            // Por ejemplo, mostrar un diálogo para agregar una nueva música.
            controller.showEditDialog(null, null)
        }
    }

    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
