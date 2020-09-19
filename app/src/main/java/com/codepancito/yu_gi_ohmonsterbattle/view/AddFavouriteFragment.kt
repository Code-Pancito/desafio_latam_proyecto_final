package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.FavouriteCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.AddFavouriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_favourite.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_IMAGE = "image"
private const val ARG_NAME = "name"
private const val ARG_ATTACK = "attack"
private const val ARG_DEFENSE = "defense"
private const val ARG_ID = "id"


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
    private var id: Int? = null

    private lateinit var viewModel: AddFavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(ARG_IMAGE)
            name = it.getString(ARG_NAME)
            attack = it.getString(ARG_ATTACK)
            defense = it.getString(ARG_DEFENSE)
            id = it.getInt(ARG_ID)
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

        viewModel = ViewModelProvider(this).get(AddFavouriteViewModel::class.java)

        viewModel.isAddFavouriteSuccessful.observe(viewLifecycleOwner, {
            if(it) {
                viewModel.isAddFavouriteSuccessful.value = false
                Toast.makeText(context, "Carta agregada a Favoritos", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.doesFavouriteExist.observe(viewLifecycleOwner, {
            if(it) {
                viewModel.doesFavouriteExist.value = false
                Toast.makeText(context, "La carta seleccionada ya existe en Favoritos", Toast.LENGTH_SHORT).show()
            }
        })

        Picasso.get().load(image).into(imageView_AddFavourite_Image)
        textView_AddFavourite_Name.text = name
        textView_AddFavourite_Attack.text = String.format(textView_AddFavourite_Attack.text.toString(), attack)
        textView_AddFavourite_Defense.text = String.format(textView_AddFavourite_Defense.text.toString(), defense)

        button_Cancel.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }

        button_Add.setOnClickListener {
            viewModel.insertCardIntoFavourites(FavouriteCardEntity(id!!), viewModel)
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
        fun newInstance(image: String, name: String, attack: String, defense: String, id: Int) =
            AddFavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE, image)
                    putString(ARG_NAME, name)
                    putString(ARG_ATTACK, attack)
                    putString(ARG_DEFENSE, defense)
                    putInt(ARG_ID, id)
                }
            }
    }
}