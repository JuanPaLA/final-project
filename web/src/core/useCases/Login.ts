import { AuthService } from "@/core/model/AuthService";

export class Login {
    constructor(private authService: AuthService) {}

    async exec(name: string, password: string) {
        await this.authService.login(name, password)
    }
}
