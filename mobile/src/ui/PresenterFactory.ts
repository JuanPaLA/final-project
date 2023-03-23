import { Core } from '@/core/infrastructure/Core'
import { Navigator } from '@/ui/services/navigator/Navigator'
import { SessionStorage } from '@/ui/services/session/SessionStorage'
import { NavigationPresenter } from '@/ui/layouts/Navigation/NavigationPresenter'
import { HomePresenter } from '@/ui/screens/Home/HomePresenter'
import { SignupPresenter } from './screens/Signup/SignupPresenter'

export class PresenterFactory {
    constructor(private core: Core, private sessionStorage: SessionStorage, private navigator: Navigator) {}

    navigation = (onChange) => new NavigationPresenter(onChange, this.sessionStorage)
    home = (onChange) => new HomePresenter(onChange, this.core, this.sessionStorage)
    signup = (onChange) => new SignupPresenter(onChange, this.core, this.sessionStorage)
}
