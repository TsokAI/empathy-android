package com.empathy.empathy_android.ui.partnerinfo.partnerfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.empathy.empathy_android.BaseFragment
import com.empathy.empathy_android.Constants
import com.empathy.empathy_android.R
import com.empathy.empathy_android.extensions.loadImage
import com.empathy.empathy_android.extensions.observe
import com.empathy.empathy_android.http.appchannel.FragmentLifeCycle
import com.empathy.empathy_android.repository.model.Partner
import com.empathy.empathy_android.ui.partnerinfo_detail.PartnerInfoDetailActivity
import com.empathy.empathy_android.utils.OnSwipeTouchListener
import kotlinx.android.synthetic.main.fragment_partner_type_a.*
import java.util.*

internal class PartnerFragment: BaseFragment() {

    companion object {
        fun newInstance() = PartnerFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }

    private lateinit var backgroundDeco: ImageView

    private var isTypeB = false
    private var partner: Partner? = null

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        createViewModel(PartnerViewModel::class.java)
    }

    private var listener: OnSwipeTouchListener.OnViewTransitionListener? = null

    private var swipeTouchListener: OnSwipeTouchListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewId = when(Random().nextInt(2)) {
            0    -> R.layout.fragment_partner_type_a
            1    -> {
                isTypeB = true

                R.layout.fragment_partner_type_b
            }
            else -> R.layout.fragment_partner_type_a
        }

        val view = inflater.inflate(viewId, container, false).apply {
            setOnTouchListener(OnSwipeTouchListener(context, listener))

            if(isTypeB) {
                backgroundDeco = findViewById(R.id.partner_deco_image)

                val decoResource = when(Random().nextInt(4)) {
                    0    -> R.drawable.mask_r
                    1    -> R.drawable.mask_g
                    2    -> R.drawable.mask_b
                    3    -> R.drawable.mask_p
                    else -> R.drawable.mask_r
                }

                backgroundDeco.setImageResource(decoResource)
            }
        }

        swipeTouchListener = OnSwipeTouchListener(view.context, listener)

        view.setOnTouchListener(swipeTouchListener)
        view.setOnClickListener {
            if (swipeTouchListener!!.swipeDetected()){
                listener?.viewTransitioned()
            } else {
                startActivity(Intent(context, PartnerInfoDetailActivity::class.java).apply {
                    putExtra(Constants.EXTRA_KEY_PARTNER_INFO_DETAIL_TYPE, PartnerInfoDetailActivity.TYPE_PARTNER)
                    putExtra(Constants.EXTRA_KEY_PARTNER_ID, partner?.targetId)
                })
            }

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeLooknFeel()

        viewModel.channel.accept(FragmentLifeCycle.OnActivityCreated())
    }

    fun setOnSwipeListener(listener: OnSwipeTouchListener.OnViewTransitionListener) {
        this.listener = listener
    }


    private fun subscribeLooknFeel() {
        observe(viewModel.showPartnerInfo, ::handleShowPartnerInfo)
    }

    private fun handleShowPartnerInfo(looknFeel: PartnerLooknFeel.ShowPartnerInfo) {
        partner = looknFeel.partner

        partner_image.loadImage(partner?.imageURL ?: "")

        title.text = partner?.name
        category.text = partner?.kind
        address.text = partner?.locatiionStr
    }

    private fun initializeListener() {
//        container_view.setOnTouchListener { view, motionEvent ->
//
//            return@setOnTouchListener false
//        }
//
//        container_view.setOnClickListener {
//            startActivity(Intent(context, PartnerInfoDetailActivity::class.java).apply {
//                putExtra(Constants.EXTRA_KEY_PARTNER_INFO_DETAIL_TYPE, PartnerInfoDetailActivity.TYPE_PARTNER)
//                putExtra(Constants.EXTRA_KEY_PARTNER_ID, partner?.targetId)
//            })
//        }
    }

}
