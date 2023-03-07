import { HttpMethod } from './HttpMethod'

export class HttpRequest {
    constructor(
        public method: HttpMethod,
        public url: string,
        public data: any|null = null,
        public headers: { [key: string]: string } = {},
    ) {
    }
}
