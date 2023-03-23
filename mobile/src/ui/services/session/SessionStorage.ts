import { Session } from './Session'
import { Observable } from '@/ui/services/Observable'

export abstract class SessionStorage {
    readonly sessionChanged = new Observable<Session>()

    abstract save(session: Session): Promise<void>
    abstract get(): Promise<Session>
}
