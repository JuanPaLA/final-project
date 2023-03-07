import { UserService } from '@/core/model/UserService'

export class Signup {
    constructor(private userService: UserService) {}

    async exec(name: string, password: string) {
        await this.userService.signup(name, password)
    }
}
