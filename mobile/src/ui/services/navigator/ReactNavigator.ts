import { createNavigationContainerRef, StackActions } from '@react-navigation/native'
import { Navigator } from './Navigator'

export class ReactNavigator implements Navigator {
    private _ref = createNavigationContainerRef()

    get ref() {
        return this._ref
    }

    navigate(screenName: string, params: Record<string, any> = {}) {
        if (!this.ref.isReady()) return
        // @ts-ignore
        this.ref.navigate(screenName, params)
    }

    replaceWith(screenName: string, params?: Record<string, any>) {
        if (!this.ref.isReady()) return
        // @ts-ignore
        this.ref.dispatch(StackActions.replace(screenName, params))
    }

    goBack() {
        if (!this.ref.isReady()) return
        // @ts-ignore
        this.ref.goBack()
    }

    navigateToHome() {
        if (!this.ref.isReady()) return
        // @ts-ignore
        this.navigate('Home')
    }
}
