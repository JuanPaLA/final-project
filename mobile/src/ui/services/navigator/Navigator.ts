export interface Navigator {
    navigate(screenName: string, params?: Record<string, any>)
    replaceWith(screenName: string, params?: Record<string, any>)
    goBack()
    navigateToHome()
}
