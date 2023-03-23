import { ChangeFunc } from '@/ui/services/presenters/ChangeFunc'
import { SessionStorage } from '@/ui/services/session/SessionStorage'

export class NavigationPresenter {
    private _model = new NavigationVM()

    constructor(private onChange: ChangeFunc, private sessionStorage: SessionStorage) {
        this.sessionStorage.sessionChanged.subscribe(this, (session) => this.updateModel(session))
    }

    get model() {
        return this._model
    }

    async start() {
        const session = await this.sessionStorage.get()
        this.updateModel(session)
    }

    private updateModel(session) {
        this._model.authStatus = session.isAuthenticated ? AuthStatuses.Authenticated : AuthStatuses.NotAuthenticated
        this.onChange(this._model)
    }

    stop() {
        this.sessionStorage.sessionChanged.unsubscribe(this)
    }
}

export class NavigationVM {
    authStatus = AuthStatuses.Loading
}

export enum AuthStatuses {
    Loading, Authenticated, NotAuthenticated
}
