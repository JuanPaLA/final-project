import {AppServicesProvider} from './components/context/AppServicesContext'
import {Router} from '@/ui/services/router/Router'
import {NextJsRouter} from '@/ui/services/router/NextJsRouter'
import {Signup} from "@/core/useCases/Signup";
import {AxiosHttpClient} from "@/core/infrastructure/http/axiosClient/AxiosHttpClient";
import {HttpUserService} from "@/core/infrastructure/HttpUserService";
import {HttpAuthService} from "@/core/infrastructure/HttpAuthService";
import {Login} from "@/core/useCases/Login";
import {BrowserSession} from "@/session/BrowserSession";
import {SessionState} from "@/session/SessionState";
import {Post} from "@/core/useCases/Post";
import {HttpPostService} from "@/core/infrastructure/HttpPostService";
import {Read} from "@/core/useCases/Read";
import {Header} from "@/ui/layout/header/Header";

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
                <Header />
                <Page {...pageProps} />
            </AppServicesProvider>
        </>
    }
}

export interface WebAppConfig {
    router: Router,
    signup: Signup,
    login: Login,
    session: SessionState,
    post: Post,
    read: Read
}

export interface WebAppServices extends WebAppConfig {
}

export const defaultWebAppConfig = (): WebAppConfig => {
    let session = new BrowserSession()
    let auth = new HttpAuthService(new AxiosHttpClient('http://localhost:8080/'), session)
    let postService = new HttpPostService(new AxiosHttpClient('http://localhost:8080/'))

    return {
        router: new NextJsRouter(),
        signup: new Signup(new HttpUserService(new AxiosHttpClient('http://localhost:8080/'))),
        login: new Login(auth),
        session: session,
        post: new Post(postService),
        read: new Read(postService)
    }
}
