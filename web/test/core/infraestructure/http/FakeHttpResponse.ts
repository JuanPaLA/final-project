import {HttpMethod} from "@/core/infrastructure/http/HttpMethod";
import {HttpResponse} from "@/core/infrastructure/http/HttpResponse";

export class FakeHttpResponse<T> implements HttpResponse<T> {
    body: T
    baseUrl = ''
    headers = {}
    method = HttpMethod.GET
    relativeUrl = ''
    requestData = null
    status = 200
    url = ''

    constructor(body: T = null, status: number = 200) {
        this.body = body
        this.status = status
    }
}
