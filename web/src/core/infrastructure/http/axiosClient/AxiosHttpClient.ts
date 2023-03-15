import {HttpClient} from '../HttpClient'
import axios, {AxiosAdapter, AxiosError, AxiosInstance, Method} from 'axios'
import {HttpResponse} from '../HttpResponse'
import {HttpMethod} from '../HttpMethod'
import {HttpRequest} from '../HttpRequest'
import {ResponseFactory} from './ResponseFactory'

export class AxiosHttpClient implements HttpClient {
    private readonly http: AxiosInstance
    private readonly baseUrl: string
    private readonly responseFactory = new ResponseFactory()

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl
        this.http = axios.create({
            baseURL: baseUrl,
            withCredentials: true,
        })
    }

    async get<T = any>(url: string, headers: { [key: string]: string } = {}): Promise<HttpResponse<T>> {
        return this.send<T>(this.request(HttpMethod.GET, url, null, headers))
    }

    async post<T = any>(url: string, data: any = null, headers: { [key: string]: string } = {}): Promise<HttpResponse<T>> {
        return this.send<T>(this.request(HttpMethod.POST, url, data, headers))
    }

    async put<T = any>(url: string, data: any = null, headers: { [key: string]: string } = {}): Promise<HttpResponse<T>> {
        return this.send<T>(this.request(HttpMethod.PUT, url, data, headers))
    }

    async delete<T = any>(url: string, headers: { [key: string]: string } = {}): Promise<HttpResponse<T>> {
        return this.send<T>(this.request(HttpMethod.DELETE, url, null, headers))
    }

    async patch<T = any>(url: string, data: any = null, headers: { [key: string]: string } = {}): Promise<HttpResponse<T>> {
        return this.send<T>(this.request(HttpMethod.PATCH, url, data, headers))
    }

    async head<T = any>(url: string, headers: { [key: string]: string } = {}): Promise<HttpResponse<T>> {
        return this.send<T>(this.request(HttpMethod.HEAD, url, null, headers))
    }

    private request(method: HttpMethod, url: string, data: any = null, headers: { [key: string]: string } = {}): HttpRequest {
        return {method, url, data, headers}
    }

    async send<T = any>(request: HttpRequest, onProgress?: (progress: number) => void): Promise<HttpResponse<T>> {
        const axiosResponse = await this.http.request({
            url: request.url,
            data: request.data,
            method: request.method as Method,
            headers: request.headers,
            baseURL: this.baseUrl,
        })
        return this.responseFactory.create<T>(axiosResponse, request)
    }
}
