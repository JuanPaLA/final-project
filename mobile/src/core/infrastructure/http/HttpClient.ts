export interface HttpClient {
    post(url: string, data: any, header?: any): Promise<HttpResponse>

    get(url: string, header: any): Promise<HttpResponse>
}

export interface HttpResponse {
    statusCode: number
    data: any
}
