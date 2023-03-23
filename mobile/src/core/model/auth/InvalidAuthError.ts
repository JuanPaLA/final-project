import {CustomError} from "ts-custom-error";

export class InvalidAuthError extends CustomError {
    constructor(public error: string) {
        super()
    }
}