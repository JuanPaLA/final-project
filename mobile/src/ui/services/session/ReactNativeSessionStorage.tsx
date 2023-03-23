import { SessionStorage } from '@/ui/services/session/SessionStorage'
import { Session } from '@/ui/services/session/Session'
import AsyncStorage from '@react-native-async-storage/async-storage'

const sessionKey = 'session'

export class ReactNativeSessionStorage extends SessionStorage {
    async get(): Promise<Session> {
        const json = (await AsyncStorage.getItem(sessionKey)) ?? JSON.stringify(new Session())
        const snapshot = JSON.parse(json)
        return Session.fromSnapshot(snapshot)
    }

    async save(session: Session): Promise<void> {
        const snapshot = session.snapshot()
        await AsyncStorage.setItem(sessionKey, JSON.stringify(snapshot))
        this.sessionChanged.notify(session)
    }
}
