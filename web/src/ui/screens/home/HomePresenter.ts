import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";
import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {Router} from "@/ui/services/router/Router";
import {SessionState} from "@/session/SessionState";
import {Post} from "@/core/useCases/Post";

export class HomePresenter extends DefaultPresenter<HomeVM> {
    constructor(
        onChange: ChangeFunc,
        private session: SessionState,
        private post: Post,
        private router: Router
    ) {
        super(onChange);
        this.model = { content: '' }
    }

    start() { }

    logout() {
        this.session.logout()
        this.router.navigate('/login')
    }

    setContent(content: string) {
        this.updateModel({ content })
    }

    getName(): string {
        try {
            return this.session.getSession().name
        } catch (e) {
            this.router.navigate('/login')
        }
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
}

interface HomeVM {
    content: string
}