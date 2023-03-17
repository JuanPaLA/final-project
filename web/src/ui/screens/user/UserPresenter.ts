import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";
import {Router} from "@/ui/services/router/Router";
import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {SessionState} from "@/session/SessionState";
import {Read} from "@/core/useCases/Read";
import {PostVM} from "@/ui/viewModels/PostVM";

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

    setPosts(posts) {
        this.updateModel({ posts })
    }

    async start() {
        let { name } = this.router.query
        let token = this.session.getSession().token
        let requester = this.session.getSession().name
        let response = await this.read.exec(requester, name.toString(), token)
        console.log(response)
        this.setPosts(response.body.posts)
    }

}

export interface UserVM {
    posts: PostVM[]
}
