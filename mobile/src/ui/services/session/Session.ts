export class Session {
    private sessionToken: string|null = null

    authenticate(sessionToken: string) {
        this.sessionToken = sessionToken
    }

    get isAuthenticated() {
        return this.sessionToken !== null
    }

    snapshot() {
        return { sessionToken: this.sessionToken }
    }

    static fromSnapshot(snapshot): Session {
        const session = new Session()
        session.sessionToken = snapshot.sessionToken
        return session
    }
}