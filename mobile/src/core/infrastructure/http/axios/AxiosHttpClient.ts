import { HttpClient, HttpResponse } from '../HttpClient'
import axios, { AxiosInstance } from 'axios'

export class AxiosHttpClient implements HttpClient {
    private http: AxiosInstance

    constructor(baseUrl: string) {
        this.http = axios.create({
            baseURL: baseUrl,
            validateStatus: () => true
        })
    }

    async post(url: string, data: any, header: any = {}): Promise<HttpResponse> {
        const response = await this.http.post(url, data, { headers: { 'Authorization': header.Authorization }})
        return {
            statusCode: response.status,
            data: response.data,
        }
    }

    async get(url: string, header: any): Promise<HttpResponse> {
        const response = await this.http.get(url, {headers: header})
        return {
            statusCode: response.status,
            data: response.data
        }
    }
}
