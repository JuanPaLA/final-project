import {Session} from "@/session/Session";

export class AnonymousSession implements Session {
    name = null
    token = null
}