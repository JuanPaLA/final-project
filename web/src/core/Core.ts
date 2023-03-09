import { HttpUserService } from '@/core/infrastructure/HttpUserService'
import { Signup } from '@/core/useCases/Signup'
import { AxiosHttpClient } from '@/core/infrastructure/http/axiosClient/AxiosHttpClient'

export class Core {
    private httpClient = new AxiosHttpClient('http://localhost:8080/')
    protected userService = new HttpUserService(this.httpClient)

    signup() {
        return new Signup(this.userService)
    }
}
