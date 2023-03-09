import { DefaultPresenter } from '@/ui/lib/presenters/DefaultPresenter'
import { ChangeFunc } from '@/ui/lib/presenters/ChangeFunc'
import { Router } from '@/ui/services/router/Router'
import { Signup } from "@/core/useCases/Signup";

export class SignUpPresenter extends DefaultPresenter<SignUpVM> {
    constructor(onChange: ChangeFunc, private signup: Signup, private router: Router) {
        super(onChange)
        this.model = { username: '', password: '' }
    }

    start() {
        console.log('presenter started')
    }

    setUsername(username: string) {
        this.updateModel({ username })
    }

    setPassword(password: string) {
        this.updateModel({ password })
    }

    isSignupEnabled(): boolean {
        return (
            this.model.username.length > 3 &&
            this.model.password.length > 3 &&
            this.model.username[0] == '@' &&
            this.model.username.indexOf(' ') == -1
        )
    }

    async doSignup() {
        // if (!this.isSignupEnabled()) return
        try {
            await this.signup.exec(this.model.username, this.model.password)
            // this.router.navigate('/welcome')
        } catch (e) {
            console.log(e.message, "ureeeeeeeeeeeeureeeeeeeeeeeeureeeeeeeeeeeeureeeeeeeeeeeeureeeeeeeeeeeeureeeeeeeeeeeeureeeeeeeeeeee")
            // this.router.navigate('/error')
        }
    }
}

interface SignUpVM {
    username: string
    password: string
}
