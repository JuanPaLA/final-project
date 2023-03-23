import {DefaultPresenter} from "@/ui/lib/presenters/DefaultPresenter";
import {Router} from "@/ui/services/router/Router";
import {ChangeFunc} from "@/ui/lib/presenters/ChangeFunc";
import {SessionState} from "@/session/SessionState";
import {Read} from "@/core/useCases/Read";
import {PostVM} from "@/ui/viewModels/PostVM";
import {Followers} from "@/core/useCases/Followers";
import {Followings} from "../../../../test/core/useCases/Followings";

export class UserPresenter extends DefaultPresenter<UserVM> {
    constructor(
        onChange: ChangeFunc,
        private session: SessionState,
        private read: Read,
        private router: Router,
        private followers: Followers,
        private followings: Followings
    ) {
        super(onChange);
        this.model = { posts: [], followers: [], followings: [] }
    }

    setPosts(posts) {
        this.updateModel({ posts })
    }

    async start() {
        await this.getPosts();
        await this.getFollowers();
        await this.getFollowings();
    }

    private async getPosts() {
        try {
            let {name} = this.router.query
            let token = this.session.getSession().token
            let requester = this.session.getSession().name
            let response = await this.read.exec(requester, name.toString(), token)
            this.setPosts(response.body.posts)
        } catch (e) {
            console.log(e)
        }
    }

    private async getFollowers() {
        try {
            let { name: user } = this.router.query
            user = user.toString()
            let requester = this.session.getSession().name
            let token = this.session.getSession().token
            let response = await this.followers.exec(requester, user , token)
            this.setFollowers(response.body.followers)
        } catch (e) {
            console.log(e)
        }
    }

    private async getFollowings() {
        try {
            let { name: user } = this.router.query
            user = user.toString()
            let requester = this.session.getSession().name
            let token = this.session.getSession().token
            let response = await this.followings.exec(requester, user , token)
            this.setFollowing(response.body.followings)
        } catch (e) {
            console.log(e)
        }
    }

    setFollowers(followers) {
        this.updateModel({ followers })
    }

    setFollowing(followings) {
        this.updateModel({ followings })
    }
}

export interface UserVM {
    posts: PostVM[]
    followers: string[]
    followings: string[]
}
