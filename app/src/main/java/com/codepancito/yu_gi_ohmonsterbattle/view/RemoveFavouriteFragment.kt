package com.codepancito.yu_gi_ohmonsterbattle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.codepancito.yu_gi_ohmonsterbattle.R
import com.codepancito.yu_gi_ohmonsterbattle.model.db.FavouriteCardEntity
import com.codepancito.yu_gi_ohmonsterbattle.viewmodel.RemoveFavouriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_remove_favourite.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_IMAGE = "image"
private const val ARG_NAME = "name"
private const val ARG_ATTACK = "attack"
private const val ARG_DEFENSE = "defense"
private const val ARG_ID = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [RemoveFavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RemoveFavouriteFragment : Fragment() {

    private var image: String? = null
    private var name: String? = null
    private var attack: Int? = null
    private var defense: Int? = null
    private var id: Int? = null

    private lateinit var viewModel: RemoveFavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(ARG_IMAGE)
            name = it.getString(ARG_NAME)
            attack = it.getInt(ARG_ATTACK)
            defense = it.getInt(ARG_DEFENSE)
            id = it.getInt(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remove_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RemoveFavouriteViewModel::class.java)

        Picasso.get().load(image).into(imageView_RemoveFavourite_Image)
        textView_RemoveFavourite_Name.text = name
        textView_RemoveFavourite_Attack.text = String.format(textView_RemoveFavourite_Attack.text.toString(), attack)
        textView_RemoveFavourite_Defense.text = String.format(textView_RemoveFavourite_Defense.text.toString(), defense)

        button_Remove_Cancel.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }

        button_Remove.setOnClickListener {
            viewModel.removeCardFromFavourites(FavouriteCardEntity(id!!))
            button_Remove_Cancel.callOnClick()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RemoveFavouriteFragment.
         */
        @JvmStatic
        fun newInstance(image: String, name: String, attack: Int, defense: Int, id: Int) =
            RemoveFavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE, image)
                    putString(ARG_NAME, name)
                    putInt(ARG_ATTACK, attack)
                    putInt(ARG_DEFENSE, defense)
                    putInt(ARG_ID, id)
                }
            }
    }
}