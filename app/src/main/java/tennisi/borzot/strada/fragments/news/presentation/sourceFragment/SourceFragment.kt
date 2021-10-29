package tennisi.borzot.strada.fragments.news.presentation.sourceFragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import tennisi.borzot.strada.databinding.FragmentSourceBinding
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment


class SourceFragment : Fragment() {

    private var _binding: FragmentSourceBinding? = null
    private val binding: FragmentSourceBinding
        get() = _binding ?: throw RuntimeException("FragmentSourceBinding == null")

    private val args by navArgs<SourceFragmentArgs>()

    private lateinit var onSaveButtonClickListener: CarItemFragment.OnSaveButtonClickListener
    private lateinit var onItemSelectedListener: CarItemFragment.OnSaveButtonClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CarItemFragment.OnSaveButtonClickListener) {
            onSaveButtonClickListener = context
            onItemSelectedListener = context

        } else {
            throw RuntimeException("Activity must implement OnItemSelectedListener")
        }
        onItemSelectedListener.onItemSelected()
    }

    override fun onDetach() {
        super.onDetach()
        onSaveButtonClickListener.onSaveButtonClick()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSourceBinding.inflate(inflater, container, false)

        webViewSetup()

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(){
        with (binding){
            webView.apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(url.toString())
                        progressBar.visibility = View.GONE
                        return true
                    }
                }
                loadUrl(args.url)
            }


        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}