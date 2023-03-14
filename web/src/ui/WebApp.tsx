import { AppServicesProvider } from './components/context/AppServicesContext'
import { Router } from '@/ui/services/router/Router'
import { NextJsRouter } from '@/ui/services/router/NextJsRouter'
import { Signup } from "@/core/useCases/Signup";
import { AxiosHttpClient } from "@/core/infrastructure/http/axiosClient/AxiosHttpClient";
import { HttpUserService } from "@/core/infrastructure/HttpUserService";
import { HttpAuthService } from "@/core/infrastructure/HttpAuthService";
import { Login } from "@/core/useCases/Login";
import {BrowserSession} from "@/session/BrowserSession";
import {SessionState} from "@/session/SessionState";

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
    login: Login,
    session: SessionState
}

export interface WebAppServices extends WebAppConfig {}

export const defaultWebAppConfig = (): WebAppConfig => {
    let session = new BrowserSession()
    let auth = new HttpAuthService(new AxiosHttpClient('http://localhost:8080/'), session)
    return {
        router: new NextJsRouter(),
        signup: new Signup(new HttpUserService(new AxiosHttpClient('http://localhost:8080/'))),
        login: new Login(auth),
        session: session
    }
}
