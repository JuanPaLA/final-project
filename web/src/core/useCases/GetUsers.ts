import {UserService} from "@/core/model/UserService";

export class GetUsers {
    constructor(private userService: UserService) { }

    async exec(name: string, token: string) {
        return await this.userService.getUsers(name, token)
    }
}