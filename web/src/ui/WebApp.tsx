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
import {GetUsers} from "@/core/useCases/GetUsers";
import {HttpFollowingService} from "@/core/infrastructure/HttpFollowingService";
import {Follow} from "@/core/useCases/Follow";
import {Wall} from "@/core/useCases/Wall";
import {HttpWallService} from "@/core/infrastructure/HttpWallService";
import {Unfollow} from "@/core/useCases/Unfollow";
import {Followers} from "@/core/useCases/Followers";
import {Followings} from "../../test/core/useCases/Followings";

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
    read: Read,
    listUsers: GetUsers,
    follow: Follow,
    wall: Wall,
    unfollow: Unfollow,
    followers: Followers,
    followings: Followings
}

export interface WebAppServices extends WebAppConfig {
}

export const defaultWebAppConfig = (): WebAppConfig => {
    let session = new BrowserSession()
    let client = new AxiosHttpClient('http://localhost:8080/')
    let auth = new HttpAuthService(client, session)
    let postService = new HttpPostService(client)
    let userService = new HttpUserService(client)
    let followingService = new HttpFollowingService(client)
    let wallService = new HttpWallService(client)

    return {
        listUsers: new GetUsers(userService),
        login: new Login(auth),
        post: new Post(postService),
        read: new Read(postService),
        router: new NextJsRouter(),
        session: session,
        signup: new Signup(userService),
        follow: new Follow(followingService),
        wall: new Wall(wallService),
        unfollow: new Unfollow(followingService),
        followers: new Followers(followingService),
        followings: new Followings(followingService)
    }
}
