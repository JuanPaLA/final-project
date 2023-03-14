import {Session} from "@/session/Session";

export class UserSession implements Session {
    constructor(public name: string, public token: string) {
    }
}