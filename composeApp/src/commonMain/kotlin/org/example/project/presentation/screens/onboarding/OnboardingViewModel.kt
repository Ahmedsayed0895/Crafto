package org.example.project.presentation.screens.onboarding

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.domain.entity.OnboardingItem
import org.example.project.domain.repository.OnboardingRepository
import org.example.project.domain.usecase.session.MarkOnboardingCompleteUseCase
import org.example.project.presentation.screens.onboarding.model.toUiState
import org.example.project.presentation.shared.base.BaseViewModel


class OnboardingViewModel(
    private val repository: OnboardingRepository,
    private val markOnboardingCompleteUseCase: MarkOnboardingCompleteUseCase,
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
            onSuccess = ::onLoadDataSuccess,
            onError = { errorState ->
                updateState { it.copy(errorMessage = errorState, loading = false) }
            },
            dispatcher = ioDispatcher,
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
        markOnboardingComplete()
        sendNewEffect(OnboardingScreenEffect.NavigateToGetStartedScreen)
    }

    override fun onNextClick() {
        sendNewEffect(OnboardingScreenEffect.NavigateToNext)
    }

    override fun onGetStartedClick() {
        markOnboardingComplete()
        sendNewEffect(OnboardingScreenEffect.NavigateToRegisterScreen)
    }

    private fun markOnboardingComplete() {
        tryToCall(
            call = {
                markOnboardingCompleteUseCase()
            },
            onSuccess = {
                // Do nothing, just continue navigation
            },
            onError = { error ->
                updateState { it.copy(loading = false) }
                println("⚠️ Failed to mark onboarding complete: ${error.message}")
            },
        )
    }
}