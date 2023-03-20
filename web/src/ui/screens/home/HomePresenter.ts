import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";
import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {Router} from "@/ui/services/router/Router";
import {SessionState} from "@/session/SessionState";
import {Post} from "@/core/useCases/Post";
import {PostVM} from "@/ui/viewModels/PostVM";
import {UserVM} from "@/ui/viewModels/UserVM";
import {UserService} from "@/core/model/UserService";
import {GetUsers} from "@/core/useCases/GetUsers";
import {Follow} from "@/core/useCases/Follow";

export class HomePresenter extends DefaultPresenter<HomeVM> {
    constructor(
        onChange: ChangeFunc,
        private session: SessionState,
        private post: Post,
        private users: GetUsers,
        private follow: Follow,
        private router: Router
    ) {
        super(onChange);
        this.model = {content: '', posts: [], users: []}
    }

    async start() {
        this.navigateToLogin();
        if (this.model.users.length == 0) {
            await this.getUsers()
        }
    }

    private navigateToLogin() {
        if (!this.session.isAuthenticated()) {
            this.router.navigate('/login')
        }
    }

    async getUsers() {
        if (!this.session.isAuthenticated()) return
        try {
            let {name, token} = this.session.getSession()
            let response = await this.users.exec(name, token)
            this.setUsers(response.body.users)
        } catch (e) {
            console.log(e)
        }
    }

    setContent(content: string) {
        this.updateModel({ content })
    }

    setUsers(users: UserVM[]) {
        this.updateModel({ users })
    }

    getName(): string {
        try {
            return this.session.getSession().name
        } catch (e) {
            this.router.navigate('/login')
        }
    }

    logout() {
        this.session.logout()
        this.router.navigate('/login')
    }

    isPostDisabled(): boolean {
        let length = this.model.content.length
        return (length == 0 || length > 140);
    }

    navigateToUser() {
        this.router.navigate(`/users/${this.getName()}`)
    }

    async doPost() {
        try {
            if (this.isPostDisabled()) return
            if (!this.session.isAuthenticated()) return
            let content = this.model.content
            let {name, token} = this.session.getSession()
            await this.post.exec(name, content, token)
        } catch (e) {
            if (e instanceof Error) {
                alert(e.message)
            }
        }
        this.setContent('')
    }

    async doFollow(followee: string) {
        try {
            if (!this.session.isAuthenticated()) return
            let {name: follower, token} = this.session.getSession()
            await this.follow.exec(follower, followee, token)
            alert(`You are now following ${followee}`)
        } catch (e) {
            if (e instanceof Error) {
                alert(e.message)
            }
        }
        this.setContent('')
    }
}

interface HomeVM {
    content: string
    posts: PostVM[]
    users: UserVM[]
}