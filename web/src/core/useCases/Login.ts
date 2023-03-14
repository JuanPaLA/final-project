import { AuthService } from "@/core/model/AuthService";

export class Login {
    constructor(private userService: AuthService) {}

    async exec(name: string, password: string) {
        await this.userService.login(name, password)
    }
}
