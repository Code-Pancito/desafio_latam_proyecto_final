package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_favourite.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_IMAGE = "image"
private const val ARG_NAME = "name"
private const val ARG_ATTACK = "attack"
private const val ARG_DEFENSE = "defense"


/**
 * A simple [Fragment] subclass.
 * Use the [AddFavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFavouriteFragment : Fragment() {

    private var image: String? = null
    private var name: String? = null
    private var attack: String? = null
    private var defense: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(ARG_IMAGE)
            name = it.getString(ARG_NAME)
            attack = it.getString(ARG_ATTACK)
            defense = it.getString(ARG_DEFENSE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(image).into(imageView_AddFavourite_Image)
        textView_AddFavourite_Name.text = name
        textView_AddFavourite_Attack.text = String.format(textView_AddFavourite_Attack.text.toString(), attack)
        textView_AddFavourite_Defense.text = String.format(textView_AddFavourite_Defense.text.toString(), defense)

        button_Cancel.setOnClickListener {
            Toast.makeText(context, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        button_Add.setOnClickListener {
            Toast.makeText(context, "Agregar", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param image Parameter 1.
         * @param name Parameter 2.
         * @return A new instance of fragment AddFavouriteFragment.
         */
        @JvmStatic
        fun newInstance(image: String, name: String, attack: String, defense: String) =
            AddFavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE, image)
                    putString(ARG_NAME, name)
                    putString(ARG_ATTACK, attack)
                    putString(ARG_DEFENSE, defense)
                }
            }
    }
}