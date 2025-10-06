package org.example.project.presentation.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.domain.entity.OnboardingItem
import org.example.project.domain.repository.OnboardingRepository
import org.example.project.presentation.screens.onboarding.OnboardingScreenEffect
import org.example.project.presentation.screens.onboarding.OnboardingScreenInteractionListener
import org.example.project.presentation.screens.onboarding.OnboardingScreenState
import org.example.project.presentation.screens.onboarding.model.toUiState
import org.example.project.presentation.viewmodel.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided


@KoinViewModel
class OnboardingViewModel(
    @Provided private val repository: OnboardingRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BaseViewModel<OnboardingScreenState, OnboardingScreenEffect>(initialState = OnboardingScreenState()),
    OnboardingScreenInteractionListener {

    init {
        loadData()
    }


    private fun loadData() {
        tryToCall(
            call = {
                updateState { it.copy(loading = true) }
                repository.getOnboardingData()
            },
            onSuccess =  ::onLoadDataSuccess ,
            onError = { errorState -> updateState { it.copy(errorMessage = errorState) } },
            dispatcher = ioDispatcher
        )
    }

    private fun onLoadDataSuccess(data: List<OnboardingItem>) {
        updateState {
            it.copy(
                onboardingData = data.map { item -> item.toUiState() },
                loading = false
            )
        }
    }

    override fun onSkipClick() {
        sendNewEffect(OnboardingScreenEffect.NavigateToGetStartedScreen)
    }

    override fun onNextClick() {
        sendNewEffect(OnboardingScreenEffect.NavigateToNext)
    }

    override fun onGetStartedClick() {
        sendNewEffect(OnboardingScreenEffect.NavigateToRegisterScreen)
    }
}