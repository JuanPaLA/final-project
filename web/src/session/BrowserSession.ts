import {SessionState} from "@/session/SessionState";
import {AnonymousSession} from "@/session/AnonymousSession";
import {UserSession} from "@/session/UserSession";

export class BrowserSession implements SessionState {
    identity: AnonymousSession = new AnonymousSession();

    authenticate(session: UserSession): void {
        sessionStorage.setItem('token', session.token)
        sessionStorage.setItem('name', session.name)
        this.identity = session;
    }

    logout(): void {
        try {
            sessionStorage.removeItem('name')
            sessionStorage.removeItem('token')
            this.identity = new AnonymousSession();
        } catch (e) {
            console.log(e.message)
        }
    }

    isAuthenticated(): boolean {
        try {
            let token = sessionStorage.getItem('token');
            let name = sessionStorage.getItem('name');
            if (token  && name) {
                return true
            } else return false
        } catch(e) {
            this.logout()
        }
    }

    getSession(): UserSession {
        if (this.isAuthenticated()) {
            let token = sessionStorage.getItem('token');
            let name = sessionStorage.getItem('name');
            if (token != '' && name != '') {
                this.identity = new UserSession(name, token)
                return this.identity as UserSession;
            } else{
                throw new Error('User is not authenticated')
            }
        } else {
            throw new Error('User is not authenticated')
        }
    }
}
