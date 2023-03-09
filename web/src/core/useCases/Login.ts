import {UserService} from "@/core/model/UserService";

export class Login {
    constructor(private userService: UserService) {}
    async exec(username: string, password: string) {
        await this.userService.login(name, password)
    }
}