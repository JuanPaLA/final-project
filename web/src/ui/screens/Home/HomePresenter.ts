import { DefaultPresenter } from '@/ui/lib/presenters/DefaultPresenter'
import { ChangeFunc } from '@/ui/lib/presenters/ChangeFunc'
import { Core } from '@/core/Core'
import { Router } from '@/ui/services/router/Router'

export class HomePresenter extends DefaultPresenter<HomeVM> {
    constructor(onChange: ChangeFunc, private core: Core, private router: Router) {
        super(onChange)
        this.model = { counter: 0, username: '', password: '' }
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

    increment() {
        this.updateModel({ counter: this.model.counter + 1 })
    }

    async signup() {
        try {
            await this.core.signup().exec(this.model.username, this.model.password)
            this.router.navigate('/welcome')
        } catch (e) {
            console.error(e)
            this.router.navigate('/error')
        }
    }
}

interface HomeVM {
    counter: number
    username: string
    password: string
}
