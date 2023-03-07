import { Core } from '@/core/Core'
import { AppServicesProvider } from './components/context/AppServicesContext'
import { Router } from '@/ui/services/router/Router'
import { NextJsRouter } from '@/ui/services/router/NextJsRouter'

export class WebApp {
    private readonly services: WebAppServices

    constructor(private config: WebAppConfig) {
        this.services = {
            ...config,
        }
    }

    render(Page, pageProps) {
        return <>
            <AppServicesProvider services={this.services}>
                <Page {...pageProps} />
            </AppServicesProvider>
        </>
    }
}

export interface WebAppConfig {
    core: Core,
    router: Router,
}

export interface WebAppServices extends WebAppConfig {
}

export const defaultWebAppConfig = (): WebAppConfig => {
    return {
        core: new Core(),
        router: new NextJsRouter(),
    }
}
