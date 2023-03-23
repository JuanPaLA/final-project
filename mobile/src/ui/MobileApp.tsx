import { PresenterFactory } from '@/ui/PresenterFactory'
import { Core } from '@/core/infrastructure/Core'
import { ReactNavigator } from '@/ui/services/navigator/ReactNavigator'
import { MainLayout } from '@/ui/layouts/MainLayout'
import { ReactNativeSessionStorage } from '@/ui/services/session/ReactNativeSessionStorage'
import Config from 'react-native-config'

export class MobileApp {
    private readonly presenters: PresenterFactory
    private sessionStorage = new ReactNativeSessionStorage()
    private readonly navigator = new ReactNavigator()

    constructor(private config: MobileAppConfig) {
        console.log(Config.API_BASE_URL)
        this.presenters = new PresenterFactory(config.core, this.sessionStorage, this.navigator)
    }

    render = () => (
        <MainLayout presenters={this.presenters} navigator={this.navigator} />
    )
}

interface MobileAppConfig {
    core: Core
}
