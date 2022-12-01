package com.example.engwordlockscreen.presentation.lockscreen.components

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.engwordlockscreen.databinding.ActivityLockScreenBinding
import com.example.engwordlockscreen.presentation.lockscreen.quiz.QuizViewModel
import com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.MultiChoiceFragment
import com.example.engwordlockscreen.presentation.lockscreen.quiz.components.puzzle.PuzzleFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class LockScreenActivity : AppCompatActivity() {
    private var _binding : ActivityLockScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel = viewModels<QuizViewModel>()
    /*
     * LifeCycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLockScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    private fun init()
    {
        screenOn()
        binding.screenOffButton.setOnClickListener {
            finish()
        }
        val num = intent.getIntExtra("quizUI",0)
        val fragment = quizChoice(num)
        val sfm = supportFragmentManager.beginTransaction()
        sfm.replace(binding.quizFragments.id,fragment).commit()
    }
    private fun quizChoice(num : Int) : Fragment {
        var fragment = when(num)
        {
            0 -> MultiChoiceFragment()
            1 -> PuzzleFragment()
            else -> error("quizError")
        }
        return fragment
    }
    private fun screenOn()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            controller?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            }
            controller?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        else
        {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1)
        {
            setShowWhenLocked(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        }
        else
        {
            this.window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        }
    }
}