export class UserNotAuthenticatedError extends Error {
    constructor(message: string) {
        super(message);
    }
}