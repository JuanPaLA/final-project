export class RepeatedUserError extends Error {
    constructor(message: string) {
        super(message);
        this.name = "Repeated user error";
    }
}