import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";
import {Router} from "@/ui/services/router/Router";
import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {SessionState} from "@/session/SessionState";
import {Read} from "@/core/useCases/Read";

export class UserPresenter extends DefaultPresenter<UserVM> {
    constructor(
        onChange: ChangeFunc,
        private session: SessionState,
        private read: Read,
        private router: Router
    ) {
        super(onChange);
        this.model = { posts: [] }
    }

    async start() {
        let { name } = this.router.query
        let token = this.session.getSession().token
        let requester = this.session.getSession().name
        await this.read.exec(requester, name.toString(), token)
    }

    navigateToHome() {
        this.router.navigate('/home')
    }
}

interface UserVM {
    posts: PostVM[]
}

interface PostVM {
    id: number
    author: string
    content: string
    date: Date
}