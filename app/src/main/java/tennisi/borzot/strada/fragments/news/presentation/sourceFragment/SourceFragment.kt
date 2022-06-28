package tennisi.borzot.strada.fragments.news.presentation.sourceFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.StradaApplication
import tennisi.borzot.strada.databinding.FragmentSourceBinding
import tennisi.borzot.strada.fragments.add.presentation.ViewModelFactory
import tennisi.borzot.strada.fragments.add.presentation.carItemUI.CarItemFragment
import javax.inject.Inject


class SourceFragment : Fragment() {

    private var _binding: FragmentSourceBinding? = null
    private val binding: FragmentSourceBinding
        get() = _binding ?: throw RuntimeException("FragmentSourceBinding == null")

    private val args by navArgs<SourceFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

//    private val viewModel by lazy {
//        ViewModelProvider(this, viewModelFactory)[(SourceFragmentViewModel::class.java)]
//    }

    private val component by lazy {
        (requireActivity().application as StradaApplication).component
    }

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
        component.inject(this)
        _binding = FragmentSourceBinding.inflate(inflater, container, false)

        setToolBar()
        webViewSetup()
        setupBackClickListener()


        return binding.root
    }

    private fun setToolBar() {
        with(binding) {
            with(collapsingToolbar) {
                setContentScrimColor(ContextCompat.getColor(context, R.color.colorWhite))
                title = args.source
            }

            Glide.with(root)
                .load(args.urlPic)
                .centerCrop()
                .into(newsItemImage)
        }
    }


    private fun setupBackClickListener() {
        binding.toolbar.getChildAt(0).setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun webViewSetup() {
        with(binding.webView) {
            loadUrl(args.url)
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
            webViewClient = MyWebViewClient(requireActivity().applicationContext, binding.progressBar, binding.errorAnim)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}