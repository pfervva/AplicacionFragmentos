package com.example.aplicacionfragmentos.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupSwitch(binding.switchNotifications)
        setupSeekBar(binding.seekBarVolume)
        setupSpinner(binding.spinnerAudioQuality)
        setupRadioGroup(binding.radioGroupTheme)

        // Más configuraciones aquí...

        return root
    }

    private fun setupSwitch(switch: Switch) {
        switch.setOnCheckedChangeListener { _, isChecked ->
            // Lógica para activar/desactivar notificaciones
        }
    }

    private fun setupSeekBar(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Lógica para ajustar el volumen
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(context, "Volumen ajustado", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.audio_quality_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Lógica para cambiar la calidad de audio
                // Nota: Si 'view' es null, simplemente no se utiliza en esta lógica.
                // Puedes añadir aquí la lógica para manejar la selección, sin depender de 'view'.
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Lógica en caso de que no se seleccione nada
            }
        }
    }

    private fun setupRadioGroup(radioGroup: RadioGroup) {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Lógica para cambiar el tema de la aplicación
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
