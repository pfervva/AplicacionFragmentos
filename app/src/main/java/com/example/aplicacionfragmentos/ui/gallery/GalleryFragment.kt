package com.example.aplicacionfragmentos.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.databinding.FragmentGalleryBinding
import com.example.aplicacionfragmentos.ui.Musica.models.Musica
import com.example.aplicacionfragmentos.ui.Musica.AdapterMusica
import com.example.aplicacionfragmentos.ui.Musica.MusicaViewModel

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val viewModel: MusicaViewModel by viewModels()

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
        setupRecyclerView()
        setupObservers()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        // Corrección aquí: Inicializa el adaptador con una lista vacía y los callbacks
        val adapterMusica = AdapterMusica(emptyList(), { musica, position -> showEditDialog(musica) }, { position ->
            // Esta lambda es llamada cuando se presiona el botón eliminar en un item
            viewModel.deleteMusica(position)
        })
        binding.myRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.myRecyclerView.adapter = adapterMusica
    }

    private fun setupObservers() {
        viewModel.listMusicas.observe(viewLifecycleOwner) { musicList ->
            (binding.myRecyclerView.adapter as AdapterMusica).updateList(musicList)
        }
    }

    private fun setupAddButton() {
        binding.btnAdd.setOnClickListener {
            // Pasar null para indicar que es una adición nueva
            showEditDialog(null)
        }
    }

    private fun showEditDialog(musica: Musica?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_edit, null)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextArtist = dialogView.findViewById<EditText>(R.id.editTextArtist)
        val editTextImage = dialogView.findViewById<EditText>(R.id.editTextImage)

        musica?.let {
            editTextName.setText(it.name)
            editTextArtist.setText(it.artista)
            editTextImage.setText(it.image)
        }

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setPositiveButton("Aceptar") { dialog, which ->
                val name = editTextName.text.toString()
                val artist = editTextArtist.text.toString()
                val imageUrl = editTextImage.text.toString()
                if (musica != null) {
                    viewModel.updateMusica(musica.id.toInt(), name, artist, imageUrl)
                } else {
                    viewModel.addMusica(name, artist, imageUrl)
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
