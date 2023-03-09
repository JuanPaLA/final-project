import { Core } from '@/core/Core'
import { AppServicesProvider } from './components/context/AppServicesContext'
import { Router } from '@/ui/services/router/Router'
import { NextJsRouter } from '@/ui/services/router/NextJsRouter'
import {Signup} from "@/core/useCases/Signup";
import {AxiosHttpClient} from "@/core/infrastructure/http/axiosClient/AxiosHttpClient";
import {HttpUserService} from "@/core/infrastructure/HttpUserService";

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
    router: Router,
    signup: Signup,
    core: Core
}

export interface WebAppServices extends WebAppConfig {

}

export const defaultWebAppConfig = (): WebAppConfig => {
    return {
        router: new NextJsRouter(),
        signup: new Signup(new HttpUserService(new AxiosHttpClient('http://localhost:8080/'))),
        core: new Core()
    }
}
