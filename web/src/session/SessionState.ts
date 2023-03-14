import {Session} from "@/session/Session";
import {UserSession} from "@/session/UserSession";

export interface SessionState {
    identity: Session,
    isAuthenticated: () => boolean,
    logout: () => void,
    getSession: () => UserSession
    authenticate: (session: UserSession) => void
}